using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Net;
using System.IO;
using System.Windows.Forms;
using System.Xml.XPath;

namespace XPathTester
{
    public partial class Form1 : Form
    {
        WebClient client = new WebClient();

        /// <summary>
        /// Default constructor
        /// </summary>
        public Form1()
        {
            InitializeComponent();
            this.Text = this.Text + " - v" + Application.ProductVersion;
        }
        

        /// <summary>
        /// Method to adapt form components to new form size
        /// </summary>
        /// <param name="sender"> Sender object</param>
        /// <param name="e">Event arguments object</param>
        private void Form1_Resize(object sender, EventArgs e)
        {
            int widthStroke  = 50;
            int heightStroke = 100; 
            int middle       = (int)(this.Width / 2);
            int height       = (this.Height - groupBox1.Height);
            
            //Adapt components size (width & height)
            groupBox2.Width  = middle - widthStroke;
            groupBox2.Height = height - heightStroke;
            groupBox3.Left   = middle;
            groupBox3.Width  = middle - widthStroke;
            groupBox3.Height = height - heightStroke;            
        }


        /// <summary>
        /// Event Method for button1 (open stream) action
        /// </summary>
        /// <param name="sender"> Sender object</param>
        /// <param name="e">Event arguments object</param>
        private void button1_Click(object sender, EventArgs e)
        {          
            try
            {
                //Prompt user to select a file
                this.openFileDialog1.Title = "Select XML file to read....";
                this.openFileDialog1.Multiselect = false;
                this.openFileDialog1.CheckFileExists = true;
                this.openFileDialog1.DefaultExt = "xml";
                this.openFileDialog1.Filter = "XML files (*.xml)|*.*";
                if (this.openFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    textBox1.Text = this.openFileDialog1.FileName;
                }
            }
            catch (Exception exp)
            {
                logger(exp);  
            }
        }



        /// <summary>
        /// Event Method for button2 (run XPath expression) action
        /// </summary>
        /// <param name="sender"> Sender object</param>
        /// <param name="e">Event arguments object</param>
        private void button2_Click(object sender, EventArgs e)
        {
            StreamReader      reader     = null;
            XPathDocument     doc        = null;
			XPathNavigator    navigator  = null;
			XPathNodeIterator iterator   = null;
            XPathExpression   expression = null;

            try
            { 

                //Display original stream
                toolStripStatusLabel1.Text  = "Loading original stream...";
                toolStripProgressBar1.Visible = true;
                statusStrip1.Update();
                richTextBox1.Clear();
                reader            = new StreamReader(client.OpenRead(textBox1.Text));
                richTextBox1.Text = reader.ReadToEnd();
                richTextBox1.Update();

                //Create XPath object
                toolStripStatusLabel1.Text = "Execute XPath expression on stream..."; 
                statusStrip1.Update();
                doc        = new XPathDocument(new StreamReader(client.OpenRead(textBox1.Text)));
                navigator  = doc.CreateNavigator();
				expression = navigator.Compile(textBox2.Text);
				iterator   = navigator.Select(expression);

                //Display expression result
                toolStripStatusLabel1.Text = "Send result to displayer..."; 
                statusStrip1.Update();
                richTextBox2.Clear();
                if (iterator.Count == 0)
                    richTextBox2.AppendText("No result found !");
                else
                {
                    groupBox3.Text = "Expression result [ " + iterator.Count + " node(s) found ]";
                    while (iterator.MoveNext())
                    {
                        richTextBox2.AppendText(iterator.Current.OuterXml);
                        richTextBox2.AppendText("\r\n");
                    }
                }
                richTextBox2.Update(); 
                toolStripStatusLabel1.Text = ""; 
            }
            catch (Exception exp)
            {
                logger(exp);  
            }
            finally
            { 
                if( reader != null )
                    reader.Close();                            
                toolStripProgressBar1.Visible = false;
                statusStrip1.Update();
            }

        }


        /// <summary>
        /// Method to log exception
        /// </summary>
        /// <param name="exp">Exception logged</param>
        private void logger(Exception exp)
        { 
            toolStripStatusLabel1.Text = exp.Message; 
            statusStrip1.Update();
        }

        /// <summary>
        /// Event Method for textBox2 keyDown managment
        /// </summary>
        /// <param name="sender"> Sender object</param>
        /// <param name="e">Event arguments object</param>
        private void textBox2_KeyDown(object sender, KeyEventArgs e)
        {
            if( e.KeyCode == Keys.Enter  ) 
                button2.PerformClick();
        }






    }
}
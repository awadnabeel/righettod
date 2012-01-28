using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.IO;
using System.Net;
using System.Windows.Forms;

namespace DotTail
{
    /// <summary>
    /// Class that implement events managment
    /// </summary>
    public partial class Form1 : Form
    {
        private Utilities    utilities = new Utilities(Application.StartupPath);
        private List<String> fileCache = new List<String>();
        private WebClient    client    = new WebClient();

        public Form1()
        {
            String value = "";

            InitializeComponent();

            //Initialize components values
            this.toolStripStatusLabel1.Text = "Load user settings....";
            this.statusStrip1.Update();
            fileCache = utilities.loadFileHistory();
            foreach (String elt in fileCache)
                this.comboBox1.Items.Add(elt);

            value = utilities.getUserSetting("delay");
            if (value.Length == 0)
                this.timer1.Interval = 1000;
            else
            {
                this.timer1.Interval      = Int32.Parse(value);
                this.numericUpDown1.Value = Int32.Parse(value); 
            }

            value = utilities.getUserSetting("wordwrap");
            if (value.Length == 0)
                this.checkBox1.Checked = false;
            else
                this.checkBox1.Checked = Boolean.Parse(value);

            this.toolStripStatusLabel1.Text = "";
            this.statusStrip1.Update();
            this.Text = this.Text + " - v" + Application.ProductVersion;
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            //Adapt Groupbox2 component size
            this.groupBox2.Width  = Size.Width - 15;
            this.groupBox2.Height = Size.Height - 170;
            this.Update();
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
            //Update timer interval with value select by user
            this.timer1.Interval = (int)this.numericUpDown1.Value;
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            Int32Converter converter = new Int32Converter();
            bool           state     = false;

            //Save user settings
            this.toolStripStatusLabel1.Text = "Save user settings....";
            this.statusStrip1.Update();
            state = utilities.saveUserSetting("delay", converter.ConvertToString((int)this.numericUpDown1.Value));
            state = utilities.saveUserSetting("wordwrap", converter.ConvertToString(this.checkBox1.Checked));
            //Save file history
            state = utilities.saveFileHistory(fileCache);
            
            //Saving status check
            if (!state)
                utilities.logError("FormClosed", new Exception("Error during history and user setting save !"));
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //Check if file is selected
            if (this.comboBox1.Text.Trim().Length > 0)
            {
                //Component update
                this.toolStripStatusLabel1.Text = "";
                this.statusStrip1.Update();

                //Add file to cache file list if don't alreay exists
                if( !fileCache.Contains(this.comboBox1.Text.Trim()) )
                   fileCache.Add(this.comboBox1.Text.Trim());

                //Add file to combobox file list if don't alreay exists
                if( !this.comboBox1.Items.Contains(this.comboBox1.Text.Trim()) )
                   this.comboBox1.Items.Add(this.comboBox1.Text.Trim());

               //Activate file reading in timer
                if (!this.timer1.Enabled)
                {
                    this.button2.Text    = "Stop";
                    this.timer1.Enabled  = true;
                }
                else
                {
                    this.button2.Text = "Start";
                    this.timer1.Enabled = false;
                }
                this.timer1.Interval = (int)this.numericUpDown1.Value;
                this.button2.Update();
           }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //Prompt user to select a file
            this.openFileDialog1.Title           = "Select file to read....";
            this.openFileDialog1.Multiselect     = false;
            this.openFileDialog1.CheckFileExists = true;
            if (this.openFileDialog1.ShowDialog() == DialogResult.OK)
                this.comboBox1.Text = this.openFileDialog1.FileName;
        }



        private void timer1_Tick(object sender, EventArgs e)
        {
            StreamReader   reader    = null;
            String         source    = this.comboBox1.Text.Trim();
            Int32Converter conv      = new Int32Converter();
            long           start     = 0;
            long           end       = 0;
            int            newDelay  = 0;


            try
            {                
                //Update components properties
                start                      = (DateTime.Now.Hour * 60 * 60 * 1000) + (DateTime.Now.Minute * 60 * 1000) + (DateTime.Now.Second * 1000) + DateTime.Now.Millisecond;
                this.richTextBox1.WordWrap = this.checkBox1.Checked;

                //Get stream from source
                this.toolStripStatusLabel1.Text = "Get stream from source...";
                this.statusStrip1.Update();
                if (source.ToLower().StartsWith("http"))
                    reader = new StreamReader(client.OpenRead(source));
                else
                    reader = new StreamReader(source);

                //Display data in display component
                this.toolStripStatusLabel1.Text   = "Send data to displayer...";
                this.statusStrip1.Update();
                this.richTextBox1.Clear();
                this.richTextBox1.Text            = reader.ReadToEnd();
                this.richTextBox1.Focus();
                this.richTextBox1.SelectionStart  = this.richTextBox1.Text.Length;
                this.richTextBox1.SelectionLength = 0;
                this.toolStripStatusLabel1.Text   = "";
                this.statusStrip1.Update();
                end                               = (DateTime.Now.Hour * 60 * 60 * 1000) + (DateTime.Now.Minute * 60 * 1000) + (DateTime.Now.Second * 1000) + DateTime.Now.Millisecond;

                //Display delay to get data from source
                this.toolStripStatusLabel1.Text = "Data obtained in " + conv.ConvertToString( end - start ) + " ms";

                //Check display delay 
                //Adapt it if reading delay is too short than data loading delay
                if ((end - start) >= this.timer1.Interval)
                {
                    newDelay = ((int)((end - start) / this.numericUpDown1.Value)) + 2;
                    this.timer1.Interval = newDelay * 1000;//Conversion to milliseconds
                }
                                  
            }
            catch (Exception expt)
            {
                timer1.Enabled = false;
                this.toolStripStatusLabel1.Text = "Unable to read source ! [ " + expt.Message + " ]";
                this.statusStrip1.Update();
                timer1.Enabled = true;
            }
            finally
            {                
                if (reader != null)
                    reader.Close();            
            }
        }


        /// <summary>
        /// Event Method for ComboBox1 keyDown managment
        /// </summary>
        /// <param name="sender"> Sender object</param>
        /// <param name="e">Event arguments object</param>
        private void comboBox1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
                button2.PerformClick();
        }




    }
}
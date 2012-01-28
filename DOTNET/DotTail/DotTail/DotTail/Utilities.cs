using System;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Collections;
using System.IO;
using System.Text;
using System.Xml;
using System.Net;

namespace DotTail
{ 

    /// <summary>
    /// Class that contain utilities methods
    /// </summary>
    internal class Utilities
    {
        /// <summary>Application path</summary>
        internal String applicationPath = "";
        /// <summary>Attribute to store file history</summary>
        internal String HISTORY_FILE = "";
        /// <summary>Attribute to store user setting</summary>
        internal String USER_SETTINGS_FILE = "";
        /// <summary>Attribute to store history limit</summary>
        internal int HISTORY_LIMIT = 10;




        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="applicationPath">Application path</param>
        public Utilities(String applicationPath)
        {
            this.applicationPath = applicationPath.Trim();
            HISTORY_FILE         = applicationPath + "\\history.dat";
            USER_SETTINGS_FILE   = applicationPath + "\\userSettings.xml";
        }


        /// <summary>
        /// Method to manage error
        /// </summary>
        /// <param name="method">Exception source</param>
        /// <param name="expt">Exception to log</param>
        internal void logError(String method, Exception expt)
        {
            String msg = "Errors occurs in processing !\n->Method : " + method + "\n->Error : " + expt.Message;
            MessageBox.Show( msg, "DotTail",MessageBoxButtons.OK, MessageBoxIcon.Error);            
        }




        /// <summary>
        /// Method to save file history to file
        /// </summary>
        /// <param name="list">File list</param>
        /// <returns>Saving flag status</returns>
        internal bool saveFileHistory(List<String> list)
        { 
            bool         state  = false;
            StreamWriter writer = null;
            int          i      = 0;

            try
            {
                //Initialize stream
                writer = new StreamWriter(HISTORY_FILE);

                //Save history to file
                for( i = 0 ; i < list.Count ; i++ )
                {
                    writer.WriteLine(list[i]);
                    writer.Flush();
                    if((i + 1) == list.Count)
                        i = list.Count;
                }

                //Release stream
                writer.Close();

                //Set flag status
                state = true;                        
            }
            catch(Exception e)
            {
                state = false;
                logError("saveFileHistory", e);
            }

            return state;        
        }




        /// <summary>
        /// Method to load file history from file
        /// </summary>
        /// <param name="list">File list</param>
        /// <returns>History file list</returns>
        internal List<String> loadFileHistory()
        {
            List<String> list   = new List<String>();
            StreamReader reader = null;
            String       str    = "";
            int          i      = 0;

            try
            {
                if( File.Exists(HISTORY_FILE))
                {
                    //Initialize stream
                    reader = new StreamReader(HISTORY_FILE);

                    //Load data from file
                    while (!reader.EndOfStream && (i < HISTORY_LIMIT))
                    { 
                        str = reader.ReadLine();
                        if(!list.Contains(str) )
                           list.Add(str);
                        i++;
                    }

                    //Release stream
                    reader.Close();
               }
            }
            catch (Exception e)
            {
                list.Clear();
                logError("loadFileHistory", e);
            }

            return list;
        }




        /// <summary>
        /// Method to save user setting to XML file
        /// </summary>
        /// <param name="elementName">Setting name</param>
        /// <param name="elementValue">Setting value</param>
        /// <returns>Saving flag status</returns>
        internal bool saveUserSetting(String elementName, String elementValue)
        {
            bool          state    = false;
            XmlTextWriter writer   = null;
            XmlDocument   doc      = new XmlDocument();
            XmlNodeList   nodeList = null;
            XmlNode       node     = null; 

            try
            { 
                //If destination file don't exist initialize it
                if (!File.Exists(USER_SETTINGS_FILE))
                {
                    //Initialize stream
                    writer            = new XmlTextWriter(USER_SETTINGS_FILE, Encoding.UTF8);
                    writer.Formatting = Formatting.Indented;
                    writer.WriteComment("File storing user settings");
                    writer.WriteStartElement("settings");
                    writer.WriteElementString(elementName.Trim(), elementValue.Trim());
                    writer.WriteEndElement();
                    writer.Flush();
                    writer.Close();
                }
                else
                { 
                    //Initialize Xml document object
                    doc.Load(USER_SETTINGS_FILE);
                    writer            = new XmlTextWriter(USER_SETTINGS_FILE, Encoding.UTF8);
                    writer.Formatting = Formatting.Indented;

                    
                    //Update node value
                    nodeList = doc.GetElementsByTagName(elementName.Trim());
                    if( nodeList.Count > 0)
                       nodeList.Item(0).InnerText = elementValue.Trim();
                    else
                    {
                        node           = doc.CreateElement(elementName.Trim());
                        node.InnerText = elementValue.Trim();
                        doc.DocumentElement.AppendChild(node);                    
                    }

                    //Save stream to file
                    doc.WriteContentTo(writer);
                    writer.Flush();
                    writer.Close();
                }

                //Set flag status
                state = true;            
            }
            catch (Exception e)
            {
                state = false;
                logError("saveUserSetting", e);
            }

            return state;          
        }




        /// <summary>
        /// Method to extract user setting
        /// </summary>
        /// <param name="elementName">Setting name</param>
        /// <returns>Setting value</returns>
        internal String getUserSetting(String elementName)
        { 
            String      value    = "";
            XmlDocument doc      = new XmlDocument();
            XmlNodeList nodeList = null;

            try
            { 
                //Check file system
                if (File.Exists(USER_SETTINGS_FILE))
                { 
                    //Initialize Xml document object
                    doc.Load(USER_SETTINGS_FILE);
                    
                    //Update node value
                    nodeList = doc.GetElementsByTagName(elementName.Trim());
                    if( nodeList.Count > 0)
                       value = nodeList.Item(0).InnerText;
                }          
            }
            catch (Exception e)
            {
                value = "";
                logError("getUserSetting", e);
            }

            return value.Trim();          
        }
  
    }//End of class

}//End of Namespace

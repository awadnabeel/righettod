using System;
using System.IO;
using System.Json;
using System.Net;
using System.Reflection;
using System.Resources;
using System.ServiceModel.Syndication;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Browser;
using System.Windows.Controls;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Serialization;
using System.IO.IsolatedStorage;

namespace GeonamesSample
{
    /// <summary>
    /// Code behind class to define GUI behavior 
    /// </summary>
    public partial class Page : UserControl
    {
        /// <summary>
        /// Resource manager to perform localization
        /// </summary>
        private ResourceManager RManager = new ResourceManager("GeonamesSample.StringLibrary", Assembly.GetExecutingAssembly());

        /// <summary>
        /// Object used to keep a reference on main thread context (thread managing the UI) in order to use it on callback method (used in asynchronous processing)
        /// </summary>
        private SynchronizationContext UIThread;

        /// <summary>
        /// Simple attribute to store request start time
        /// </summary>
        private DateTime requestStartTime = DateTime.Now;

        /// <summary>
        /// Constructor
        /// </summary>
        public Page()
        {
            //Initialize components
            InitializeComponent();
            this.rbFormatXMLWithXLINQ.IsChecked = true;
            //Apply localization
            this.txLatLabel.Text = RManager.GetString("LatitudeLabel");
            this.txLongLabel.Text = RManager.GetString("LongitudeLabel");
            this.txCityLabel.Text = RManager.GetString("CityLabel");
            this.txNameLabel.Text = RManager.GetString("NameLabel");
            this.txtResultsLabel.Text = RManager.GetString("ResultLabel");
            this.txFormatLabel.Text = RManager.GetString("FormatLabel");
            this.btnAction.Content = RManager.GetString("ActionButtonLabel");
            //Update IS infos
            UpdateIsolatedStorageInfos();         
        }

        /// <summary>
        /// Method to retrieve data from GEONAMES services
        /// </summary>
        /// <param name="sender">Event sender</param>
        /// <param name="e">Event sended</param>
        private void btnAction_Click(object sender, RoutedEventArgs e)
        {
            //Display a confirmation dialog using a Silverlight bridge to call JavaScript code
            if (!HtmlPage.Window.Confirm(RManager.GetString("ConfirmActionLabel"))) 
            {
                //Exit 
                return;
            }

            //Keep a reference on UI thread context
            UIThread = SynchronizationContext.Current;

            //Update title of HTML hosting window using a Silverlight bridge to call JavaScript code
            HtmlPage.Window.Eval("document.title='Loading data from GEONAMES...'");
            this.requestStartTime = DateTime.Now;

            //Call service according the selected format
            //XML format
            if (this.rbFormatXMLWithXLINQ.IsChecked == true || this.rbFormatXMLWithSerializer.IsChecked == true || this.rbFormatXMLWithReader.IsChecked == true)
            {
                //Define service URI
                string uriPath =
                 "http://ws.geonames.org/neighbourhood?lat={0}&lng={1}&style=ful";
                Uri uri = new Uri(string.Format(uriPath, txLat.Text, txLong.Text),
                 UriKind.Absolute);
                //Call service
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
                request.BeginGetResponse(GetResponse, request);
            }            
            //JSON format
            if (this.rbFormatJSON.IsChecked == true) 
            {
                //Define service URI
                string uriPath =
                 "http://ws.geonames.org/neighbourhoodJSON?lat={0}&lng={1}";
                Uri uri = new Uri(string.Format(uriPath, txLat.Text, txLong.Text),
                 UriKind.Absolute);
                //Call service
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
                request.BeginGetResponse(GetResponse, request);
            }
            //RSS feed format
            if (this.rbFormatRSS.IsChecked == true)
            {
                //Define service URI
                string uriPath =
                 "http://ws.geonames.org/findNearbyWikipediaRSS?lat={0}&lng={1}";            
                Uri uri = new Uri(string.Format(uriPath, txLat.Text, txLong.Text),
                 UriKind.Absolute);
                //Call service
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
                request.BeginGetResponse(GetResponse, request);
            }

        }

        /// <summary>
        /// Method to manage asynchronous response (callback method)
        /// </summary>
        /// <param name="e">Event</param>
        private void GetResponse(IAsyncResult e)
        {
            try
            {
                //Get response content
                HttpWebRequest request = (HttpWebRequest)e.AsyncState;
                HttpWebResponse response =
                  (HttpWebResponse)request.EndGetResponse(e);
                Stream stream = response.GetResponseStream();
                //Call processing method on UI Thread in order to update UI with the response content
                UIThread.Post(UpdateUI, stream);
            }
            catch (Exception exception)
            {
                UIThread.Post(UpdateUI, exception);
            }

        }

        /// <summary>
        /// Method to update UI
        /// </summary>
        /// <param name="stream">Data</param>
        private void UpdateUI(object stream)
        {
            //Check if the data object is a exception
            if (stream is Exception)
            {
                //UpdateUI
                txtResults.Text = ((Exception)stream).Message;
                //Exit
                return;
            }

            //Check if the data format is XML using XLINQ to retrieve data
            if (this.rbFormatXMLWithXLINQ.IsChecked == true)
            {
                XmlReader responseReader = XmlReader.Create((Stream)stream);
                XElement xmlResponse = XElement.Load(responseReader);
                XElement root = xmlResponse.Element("neighbourhood");
                txtResults.Text = root.ToString();
                txCity.Text = (string)root.Element("city");
                txName.Text = (string)root.Element("name");
                //Save response in the isolated storage
                StoreResponseInIsolatedStorage("XML-XLINQ", txtResults.Text);
            }

            //Check if the data format is XML using Serializer to retrieve data
            if (this.rbFormatXMLWithSerializer.IsChecked == true)
            {
                XmlReader responseReader = XmlReader.Create((Stream)stream);
                responseReader.ReadToFollowing("neighbourhood");
                XmlSerializer serializer = new XmlSerializer(typeof(Neighbourhood));
                Neighbourhood hood = (Neighbourhood)serializer.Deserialize(responseReader);
                txCity.Text = hood.city;
                txName.Text = hood.name;
                txtResults.Text = hood.ToString();
                //Save response in the isolated storage
                StoreResponseInIsolatedStorage("XML-Serializer", txtResults.Text);
            }

            //Check if the data format is XML using a simple XML Reader
            if (this.rbFormatXMLWithReader.IsChecked == true)
            {
                XmlReader responseReader = XmlReader.Create((Stream)stream);
                responseReader.Read();
                responseReader.ReadToFollowing("city");
                string city = responseReader.ReadElementContentAsString();
                responseReader.ReadToFollowing("name");
                string name = responseReader.ReadElementContentAsString();
                responseReader.ReadEndElement();
                responseReader.ReadEndElement();
                txCity.Text = city;
                txName.Text = name;
                txtResults.Text = responseReader.ToString();
                //Save response in the isolated storage
                StoreResponseInIsolatedStorage("XML-Reader", txtResults.Text);
            }

            //Check if the data is JSON
            if (this.rbFormatJSON.IsChecked == true) 
            {
                JsonObject hood = (JsonObject)JsonObject.Load((Stream)stream);
                txCity.Text = hood["neighbourhood"]["city"];
                txName.Text = hood["neighbourhood"]["name"];
                txtResults.Text = hood.ToString();
                //Save response in the isolated storage
                StoreResponseInIsolatedStorage("JSON", txtResults.Text);
            }

            //Check if the data is RSS
            if (this.rbFormatRSS.IsChecked == true) 
            {
                //Create objects to parse feed content
                XmlReader responseReader = XmlReader.Create((Stream)stream);
                SyndicationFeed feed = SyndicationFeed.Load(responseReader);
                //Create objects to get feed RAW content
                StringBuilder feedContent = new StringBuilder();
                XmlWriterSettings writerSettings = new XmlWriterSettings();
                writerSettings.Indent = true;
                XmlWriter writer = XmlWriter.Create(feedContent,writerSettings);
                feed.GetRss20Formatter().WriteTo(writer);
                writer.Close();
                //Access to feed content through "SyndicationFeed" object
                txCity.Text = "";
                txName.Text = feed.Title.Text;
                txtResults.Text = feedContent.ToString();
                foreach (SyndicationItem item in feed.Items) 
                {
                    txCity.Text += item.Title.Text + ",";
                }
                //Save response in the isolated storage
                StoreResponseInIsolatedStorage("RSS", txtResults.Text);
            }

            //Update title of HTML hosting window using a Silverlight bridge to call JavaScript code
            StringBuilder SBuilder = new StringBuilder();
            SBuilder.Append("document.title='Process finished (Start: ");
            SBuilder.Append(this.requestStartTime.ToLongTimeString());
            SBuilder.Append(" - End: ");
            SBuilder.Append(DateTime.Now.ToLongTimeString() + ")'"); ;  
            HtmlPage.Window.Eval(SBuilder.ToString());
            //Display a alert (JavaScript alert box) using the Silverlight brige
            HtmlPage.Window.Alert(SBuilder.ToString().Replace("document.title=", "").Replace("'","").Trim());
            //Update isolated storage infos
            UpdateIsolatedStorageInfos();
        }

        /// <summary>
        /// Method to store response content in the Silverlight application isolated store on a virtual filesystem
        /// </summary>
        /// <param name="format">Response format</param>
        /// <param name="responseContent">Response content</param>
        private void StoreResponseInIsolatedStorage(String format,String responseContent)
        {
            //Retrieving the isolated storage for the current user
            IsolatedStorageFile Storage = IsolatedStorageFile.GetUserStoreForApplication();
               
            //Save response content to a file
            IsolatedStorageFileStream NewFileStream = new IsolatedStorageFileStream("RequestHistory_" + DateTime.Now.ToString("yyyyMMddHHmmss") + ".txt", FileMode.Create, Storage);
            StreamWriter Writer = new StreamWriter(NewFileStream);
            Writer.AutoFlush = true;
            Writer.WriteLine("*********************************************************************************************");
            Writer.WriteLine("REQUEST USING FORMAT " + format + " SEND ON " + DateTime.Now.ToString("yyyyMMddHHmmss"));
            Writer.WriteLine("*********************************************************************************************");
            Writer.Write(responseContent);
            Writer.Close();
        }

        /// <summary>
        /// Method to update isolated storage informations
        /// </summary>
        private void UpdateIsolatedStorageInfos()
        {
            //Retrieving the isolated storage for the current user
            IsolatedStorageFile Storage = IsolatedStorageFile.GetUserStoreForApplication();

            //Get infos from storage
            StringBuilder SBuilder = new StringBuilder();
            SBuilder.Append("Available free space [");
            SBuilder.Append(Storage.AvailableFreeSpace);
            SBuilder.Append("] - ");
            SBuilder.Append("Quota [");
            SBuilder.Append(Storage.Quota);
            SBuilder.Append("] - ");
            SBuilder.Append("Files count [");
            String[] Files = Storage.GetFileNames("*.*");
            SBuilder.Append((Files == null) ? "0" : Files.Length.ToString());
            SBuilder.Append("] - ");
            SBuilder.Append("Directories count [");
            String[] Directories = Storage.GetDirectoryNames("*");
            SBuilder.Append((Directories == null) ? "0" : Directories.Length.ToString());
            SBuilder.Append("]");

            //Update UI
            this.txIsolatedStoragerInfos.Text = SBuilder.ToString();
        }

        /// <summary>
        /// Method to clear application user isolated storage
        /// </summary>
        /// <param name="sender">Event sender</param>
        /// <param name="e">Sended event</param>
        private void btnClearIS_Click(object sender, RoutedEventArgs e)
        {
            //Retrieving the isolated storage for the current user
            IsolatedStorageFile Storage = IsolatedStorageFile.GetUserStoreForApplication();

            //Remove it
            Storage.Remove();

            //Update IS infos
            UpdateIsolatedStorageInfos();
        }

    }
}

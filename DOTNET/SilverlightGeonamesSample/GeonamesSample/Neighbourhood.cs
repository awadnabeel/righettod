using System.Xml.Serialization;

namespace GeonamesSample
{
    /// <summary>
    /// Class to store GEONAMES informations about a location.
    /// Instance of this class will be created using XML deserialization 
    /// and it's for this reason that we precise some XML mapping properties 
    /// where default properties cannot be used !
    /// </summary>
    [XmlRootAttribute("neighbourhood", Namespace = "")]
    public class Neighbourhood
    {
        public string countryCode { get; set; }
        public string countryName { get; set; }
        public string adminCode1 { get; set; }
        public string adminName1 { get; set; }
        public string adminCode2 { get; set; }
        public string adminName2 { get; set; }
        public string city { get; set; }
        public string name { get; set; }
    }
}

package as3
{
	/**
	 * Class defining a binding to a remote Java class (Value Object)
	*/	
   [Managed]
   [RemoteClass(alias="com.drighetto.blazedsrefu.service.vo.FileInformations")] 	
	public class FileInformations
	{
		public var name : String;
		public var path : String;
		public var size : int;
		public var directory : Boolean;
		public var lastModificationDate : String;
		public var writeable : Boolean;
		public var hidden : Boolean;
		public var parentPath : String;		
	}
}
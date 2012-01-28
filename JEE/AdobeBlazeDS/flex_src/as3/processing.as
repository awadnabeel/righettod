/*
 * Script containing application processing functions and variables 
*/
  import flash.events.Event;
  import flash.events.HTTPStatusEvent;
  import flash.events.ProgressEvent;
  import flash.net.FileReference;
  import flash.net.URLRequest;
  
  import mx.collections.ArrayCollection;
  import mx.controls.Alert;
  import mx.core.Application;
  import mx.events.ListEvent;
  import mx.rpc.events.ResultEvent;  
  
  //Application version
  private var version:String = "v1.1";
  
  //Application URI
  private var appURI:String = "";
  
  //File reference for the file downloading
  private var fileReference:FileReference = null;
  
  //Bindable collection to store files collection 
  [Bindable]
  private var filesCollection : ArrayCollection = new ArrayCollection();
  
  //Init function
  private function initApplication(): void{
  	//Some initialization  	
  	mainPanel.title += " " + version;
  	pBar.visible = false;
  	searchButton.enabled = false;
  	loadingButton.enabled = false;
  	downloadButton.enabled = false;
  	tabNavigator.selectedIndex = 0;
  	fileSystemStructure.doubleClickEnabled = true;
  	//Extract the root application URI
  	var myPattern:RegExp = /\/main.swf/g; 
  	appURI = Application.application.url.replace(myPattern,"");
  	myPattern = /\/front/g; 
  	appURI = appURI.replace(myPattern,"") + "/";  	
  	mainPanel.title += " (root uri : " + appURI + ")";
  }
  
  //Function to get the list of the children elements of the specified path
  private function obtainChildrenElement(path:String) : void {
  	  //Call the remote service
      var call:Object = servicesProvider.obtainFirstLevelChildren(path); 
      //Put a marker on the call in order to identify the response in the global callback method
	  call.marker = "obtainFirstLevelChildren"; 
	  tabNavigator.selectedIndex = 0;                       
  }
  
  //Function to load the content of the specified file path
  private function obtainFileContent(path:String) : void {
  	  //Call the remote service
      var call:Object = servicesProvider.obtainFileContent(path); 
      //Put a marker on the call in order to identify the response in the global callback method
	  call.marker = "obtainFileContent"; 
	  tabNavigator.selectedIndex = 1;                        
  } 
  
  //Function to handle result of call on any service of RemoteObject        
  private function handleResult( event : ResultEvent ) : void{
  	//Get call token in order to get the associated marker
  	var call:Object = event.token;
  	
  	//Process depends on marker value
  	//---File system structure extraction
    if(call.marker == "obtainFirstLevelChildren"){ 
         filesCollection = event.result as ArrayCollection;
         logs.htmlText +=  new Date().toString() + " : <font color='#007F0E'>File system structure extraction : OK</font><br>\n";	
         tabNavigator.selectedIndex = 0;
         boxFileSystemStructure.label="Remote file system structure (" + filesCollection.length + ")";        
         return;
    }
    //---File content extraction
    if(call.marker == "obtainFileContent"){ 
         fileContent.text = event.result as String;
         logs.htmlText += new Date().toString() + " : <font color='#007F0E'>File content extraction : OK</font><br>\n";
         tabNavigator.selectedIndex = 1;	
         return;	
    }           
  }   
  
  //Function to handle error of call on any service of RemoteObject
  private function handleFault( event : Object ) : void  {
        logs.htmlText += new Date().toString() + " : <font color='#FF0000'>" + event.toString() + "</font><br>\n";
        tabNavigator.selectedIndex = 2;
  }
  
  //Function to manage enabling of action buttons
  private function manageActionButtonEnabling(event : Event) : void {
	   if(remotePath.length > 0){
	  		searchButton.enabled = true;
	  		loadingButton.enabled = true;  
	  		downloadButton.enabled = true; 	
	   }else{
	   		searchButton.enabled = false;
	  		loadingButton.enabled = false;
	  		downloadButton.enabled = false;
	   }
  } 
  
  //Function to set the path of the selected item in the remote path text input
  private function selectFilePathFromGrid(event:Event) : void{
  	if(filesCollection != null && filesCollection.length > 0){
  		remotePath.text = filesCollection[fileSystemStructure.selectedIndex].path;
  		if(filesCollection[fileSystemStructure.selectedIndex].directory || filesCollection[fileSystemStructure.selectedIndex].name == ".."){
	  		loadingButton.enabled = false;
	  		downloadButton.enabled = false;
  		}else{
	  		loadingButton.enabled = true;  
	  		downloadButton.enabled = true;   			
  		}
  	}
  }
  
  //Function to manage the double click on a row of the file system display grid, 
  //if the selection is a directory then go into the directory and update file system structure 
  private function changeDirectory(event:ListEvent) : void{
  	if(event.itemRenderer.data.name == ".." && event.itemRenderer.data.parentPath != null && event.itemRenderer.data.parentPath != ""){
  		remotePath.text = event.itemRenderer.data.parentPath;
  		obtainChildrenElement(event.itemRenderer.data.parentPath);
  		return;
  	}else if(event.itemRenderer.data.directory){
  	 	obtainChildrenElement(event.itemRenderer.data.path);
  	 }	
  }  
  
  //Function to download a file from the server
  private function downloadFile(path:String):void {
  	//Create the target URI of the file to download
  	var targetURI:URLRequest = new URLRequest(appURI.concat("FileEndpointProvider?FILE_PATH=").concat(path));
  	//Create and configure download helper object
  	fileReference = new FileReference();
    fileReference.addEventListener(Event.OPEN, downloadOpenHandler);
    fileReference.addEventListener(Event.COMPLETE, downloadCompleteHandler);
    fileReference.addEventListener(ProgressEvent.PROGRESS,downloadProgressHandler);
    fileReference.addEventListener(IOErrorEvent.IO_ERROR, handleFault);
    fileReference.addEventListener(HTTPStatusEvent.HTTP_STATUS, handleFault);
	//Start download
	fileReference.download(targetURI);  	 	
  }
  
	//When the OPEN event has dispatched, change the progress bar's label 
	private function downloadOpenHandler(event:Event):void {
		pBar.visible = true;
		pBar.label = "Downloading file : " + remotePath.text;	    
	}
	
	//Fill the download completion during the download
	private function downloadProgressHandler(event:ProgressEvent):void{
		pBar.setProgress(event.bytesLoaded, event.bytesTotal);
	}
		
	 //Once the download has completed, change the progress bar's label one 
	 //last time
	private function downloadCompleteHandler(event:Event):void {
	    pBar.label = "File downloaded !";
	    pBar.setProgress(100,100);
	    Alert.show("File downloaded !","Informations");
	    pBar.visible = false;
	}
**********
* README *
**********

This project have to goal to show how access to Windows Management Instrumentation (WMI) from Java language, 
to achieve this, we will use the COM4J api (https://com4j.dev.java.net).

** What is WMI ? ( extract taken from http://msdn.microsoft.com/en-us/library/aa286547.aspx ) :
Windows Management Instrumentation (WMI) is a component of the Microsoft® Windows® operating system and is the Microsoft implementation
of Web-Based Enterprise Management (WBEM), which is an industry initiative to develop a standard technology for accessing management 
information in an enterprise environment. WMI offers a variety of programming interfaces such as C++, open database connectivity (ODBC), 
Microsoft® Visual Basic®, or HTML that developers can use to customize management applications. 
System administrators can use WMI by using scripts to automate administration tasks. WMI can integrate with Windows components, 
such as Active Directory® directory service to allow for a unified management experience.

** What is COM4J ? ( extract taken from the COM4J website ) :
- A Java library that allows Java applications to seemlessly interoperate with Microsoft Component Object Model (COM).
- A Java tool that imports a COM type library and generates the Java definitions of that library. 


** First step
Before to start to use WMI you must generate the Java Type Definitions (JTD) from a COM type library, COM type libraries are 
often found in .ocx, .dll, .exe, and/or .tlb files

To generate the Java type definitions for WMI we will use the tool "tlbimp" provided by COM4J, to use this tool we will use a Ant script, 
in the "build.xml" there a task named "generate.java.type.definition" that generate the JTD from the WMI COM library (defined in file "wbemdisp.tlb").

After the generation you must import the java classes generated into your java source directory.

** Second step
You ready to use WMI JTD into your application code, see samples in class "com.drighetto.wmicom4j.WMISamples"

Do not forget to put in your classpath the COM4J jar (com4j.jar) and also the DLL (in "extra" directory)...

To find the WMI object storing your target informations you can use the WMI Administration Tools (see in directory "others/tools") 
and use the WMIStudio (run it with InternetExplorer) 


--------------
D. Righetto
September 2009


**********
* README *
**********

This project have to objective to show how print directly a PDF stream from a browser request.

** Direct printing :
The idea is to add in PDF stream sent to browser a snippet of Adobe JavaSript code to 
indicate to Acrobat Reader to print the current document silently to the default printer.

The JS code is "this.print({bUI: false, bSilent: true, bShrinkToFit: true});"

The process use FOP and iText Pdf API and follow this steps :
1. Creation of PDF stream with FOP using XLS-FO sheet
2. Reading of this PDF stream with iText and tranfering of stream to a iText document
3. Adding of JS code and sending of stream to browser using response output stream

See method "doGet()" of servlet "com.drighetto.fop.essai.servlet.PDFGeneratorWithJS" 



** Acrobat Reader presence check :
See page "sample02.jsp", 
for IE we try to create a ActiveXObject
for FIREFOX we check the presence of AC plugin in "navigator.plugins[]" collections   



** Note : 
In case of deployment on ORACLE AS (OAS or OC4J) during the deplyment in 
the class loading configuration uncheck the use of shared library "oracle.xml"
in order to use xml api of JEE instead of xml api of Oracle. 

----------------
D. Righetto
September 2007

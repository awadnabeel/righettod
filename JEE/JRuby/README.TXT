************
*  README  *
************

This project have to objective to show the integration between Java and JRuby (Java implementation of the Ruby language).

** Requirement ***
-> Java JSE 6
-> JRuby binaries


*** Directories description ***
"src"
Contains the Java class that execute some JRuby scripts and show the integration using the new 
ScriptEngine feature of JSE6 with the JRuby script engine implementation.

"AntExternalLib"
Contains the AntForm library that is used to provide a GUI in a Ant script

"JRubyScriptEngineLib"
Contains the JRuby script engine implementation and the JRuby libraries needed to use the 
ScriptEngine feature of JSE6 for JRuby

"JRubyScripts"
Contains some JRuby scripts that show  :
- The using of Java classes in JRuby scripts
- The integration between Java and JRuby through the JSE6 ScriptEngine feature

This directory contains also a Ant build script that provide a GUI to execute the JRuby scripts, 
the properties to adapt to your JRuby installation and operating system 
are "jruby.home", "jruby.shell" and "jruby.script".

For the Linux/Unix platforms the values are : 
	jruby.shell  = /bin/sh	
	jruby.script = jruby


*** Links ***
JRuby
http://jruby.codehaus.org/

Ruby Development Tools for Eclipse
http://rubyeclipse.sourceforge.net/

JRuby and the Java Platform
http://java.sun.com/developer/technicalArticles/scripting/jruby/

Java Scripting
https://scripting.dev.java.net/

AntForm
http://antforms.sourceforge.net/


--------------
D. Righetto
April 2008
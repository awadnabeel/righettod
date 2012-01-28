************
*  README  *
************

This project show the integration between Spring and a dynamic language (JRuby here), 
this project use Maven2.

*** Note *** 
The JRuby scripts are placed in the "src/main/resources" directory because as they aren't class files the Eclipse 
automatic java compiler don't copy the *.rb files to the "target/classes" directory (and thus they aren't available 
in the classpath at the runtime).
If we place the *.rb files in the "src/main/resources" directory it's OK because they are considered like resources files 
and they are copied to the "target/classes" directory and thus they are available in the classpath at the runtime.

*** Links ***
JRuby
http://jruby.codehaus.org/

Spring dynamic language support
http://static.springframework.org/spring/docs/2.5.x/reference/dynamic-language.html

Maven2 directory structure
http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html

--------------
D. Righetto
May 2008
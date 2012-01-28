**********
* README *
**********

** DESCRIPTION
This project show a sample of use of the "KAPTCHA" utility web framework.

** REQUIREMENT
To run the project you must install the "KAPTCHA" artefact into your local Maven repository because is not available in Maven online repository. 

Download a "KAPTCHA" artefact binary distribution and tape this command to install the artefact into your local Maven repository:
mvn install:install-file -Dfile=[Path to "kaptcha-<version>.jar" file] -DgroupId=com.google.code -DartifactId=kaptcha -Dversion=[Version] -Dpackaging=jar

This sample require a JEE6 servlet container (it's implemented using Tomcat 7.x).

** LINKS
=> Kaptcha Google Code homepage
http://code.google.com/p/kaptcha

=> Kaptcha image generation servlet configuration options
http://code.google.com/p/kaptcha/wiki/ConfigParameters


----------------------
Dominique Righetto
May 2011
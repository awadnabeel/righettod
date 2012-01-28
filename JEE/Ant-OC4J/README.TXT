**********
* README *
**********

This project have to objective to show the :
- Deployment
- Undeployment
- Start and stop operation on application or instance
on OC4J server through ANT build script


--------------
D. Righetto
August 2007


Oracle documentation notes :
               Incorporating the Ant Tasks Using Ant 1.6.5 Outside OC4J

This section outlines the procedure for setting up your build environment to use the
Ant 1.6.5 implementation outside OC4J.
1. Add ANT_HOME/ant/bin to the system PATH environment variable.

2. Set the ANT_HOME environment variable to point to your Ant installation and
the JAVA_HOME environment variable to point to the location of the Java2
Standard Edition SDK.
The common ANT installation directory is ORACLE_HOME/ant

3. Declare the oracle namespace in the <project> element in the Ant build file
(build.xml). The OC4J Ant tasks will be referenced in build.xml using this
namespace.
<project name="test" default="all" basedir="."
xmlns:oracle="antlib:oracle">

4. Copy the ant-oracle.properties file from the ORACLE_
HOME/j2ee/utilities directory to the directory containing your build file
(build.xml).
Although you can modify the file in ORACLE_HOME/j2ee/utilities and
reference it from your build scripts, it is better to maintain the original file as a
template.

5. Set the values for arguments to pass to the Ant tasks in the
ant-oracle.properties file.
The properties within the file are set to the OC4J default values. The file also reads
in environment variable settings, such as for ORACLE_HOME and JAVA_HOME. You
can edit any of these properties as necessary to reflect the configuration of the
target OC4J instance or instances.

6. Copy the ant-oracle.xml file from the ORACLE_HOME/j2ee/utilities
directory to the directory containing your build file (build.xml).

7. At the top level of your build file, add this <import> element:
<import file="ant-oracle.xml"/>
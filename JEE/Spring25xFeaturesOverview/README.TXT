**********
* README *
**********

This project show a overview of some features of Spring 2.5.x following the documentation 
http://static.springframework.org/spring/docs/2.5.x/reference/new-in-2.html

Launch classes of each part start with the "Main" prefix. 

** Source folder "autowired" : 
Show the use of autowiring with the annotations	@Autowired, @Qualifier, @Resource, @PostConstruct, @PreDestroy, @Service, @Configurable
To run this part see the file "Main-Autowire-Run-Config.jpg" in order the view the JVM startup configuration... 



** Source folder "jmx"       :
Show the use JMX through, 
	- The definition of MBeans using a simple pojo (without annotations) or using the Java5 annotations through Spring support
	- The definition of a notification listener
	- The notification publishing
	
Startup class "MainSampleWithAnnotations" use the Spring context file "applicationContext-jmx-annotations.xml"
Startup class "MainSampleWithAnnotationsSmallConfig" use the Spring context file "applicationContext-jmx-annotations-smallconfig.xml"
Startup class "MainSampleWithoutAnnotations" use the Spring context file "applicationContext-jmx-noannotations.xml"



** Source folder "jms"       :
Show the use of the new JMS namespace (and in the same time a example of JMS 1.1 use) through,
	- Definition of a message producer
	- Definition of a message consumer using the JMS interface 'MessageListener'
	- Definition of a message consumer using a simple Pojo
	- Configuration of a listener container with the new JMS namespace

This part use the MOM Apache ActiveMQ v5.0.0, to run this sample follow this steps :
1. Download the MOM on http://www.eu.apache.org/dist/activemq/apache-activemq/5.0.0/apache-activemq-5.0.0.zip
2. Unzip the directory anywhere on your PC, and run the file $ACTIVEMQ_HOME/bin/activemq
3. Launch the class "com.drighetto.spring25x.jms.Main"



** Source folder "testing"   :
Show the use of the "Spring TestContext Framework" with JUNIT 4.4 BUT the transaction management part is not showed.

To run test launch the class "com.drighetto.spring25x.testing.MyTestBean"
http://static.springframework.org/spring/docs/2.5.x/reference/testing.html

----------------
D. RIGHETTO
March 2008


	
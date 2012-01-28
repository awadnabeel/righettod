#Needed to use java
require 'java'
#Import a java class , if a same class exist in  Ruby this one is redefined by the Java class imported 
#below and it's the Java class that it will be used NOT the Ruby class
import java.lang.Thread

class TestThreadImplementation

    #Indicate that this JRuby class implement the Runnable interface
	include java.lang.Runnable
	
	#Implement the run() method of the Runnable interface
	def run()
	  puts "Hello world !"
	end
	
end


##Run code
my_runnable = TestThreadImplementation.new
my_thread = Thread.new(my_runnable)
my_thread.start()

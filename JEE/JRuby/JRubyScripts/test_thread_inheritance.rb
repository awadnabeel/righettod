#Needed to use java
require 'java'
#Import a java class , if a same class exist in  Ruby this one is redefined by the Java class imported 
#below and it's the Java class that it will be used NOT the Ruby class
import java.lang.Thread

#Indicate the the class inherit from the Thread class
class TestThreadInheritance < Thread
	
	#Implement the run() method of the Thread class
	def run()
	  puts "Hello world !"
	end
	
end


##Run code
my_thread = TestThreadInheritance.new
my_thread.start()

#Needed to use java
require 'java'
#In order to use resources within a jar file from JRuby the jar file must either be on the classpath or you can make it available with the require method
require 'ThirdPartyJavaLib/commons-io.jar'
#Import a java class, if a same class exist in Ruby this one is redefined by the Java class imported below and it's the Java class that it will be used NOT the Ruby class
import java.io.File
#Import third party package through the creation of a JRuby module facade
module CommonsIO
   include_package "org.apache.commons.io"
end


### SAMPLE 1 ###
#Sample : Listing files of the current directory
my_file = File.new('.')
puts "Files in directory '#{my_file.absolutePath()}' :"
my_file.listFiles.each do |current_file|
	puts "-> #{current_file.name}"
end

### SAMPLE 2 ###
#Sample : Using a external java library
drive = "C:"
puts "Free space on '#{drive}' : #{CommonsIO::FileSystemUtils.freeSpaceKb(drive)}"




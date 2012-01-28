#Needed to use java
require 'java'

#JRuby implementation of the "com.drighetto.springjruby.ActionDefinition" Java interface
class ActionDefinitionJRubyImpl
  
  #Indicate that this JRuby class implement the "com.drighetto.springjruby.ActionDefinition" Java interface
  #-> Optional in Spring : If is not specified Spring will use the interface defined in the XML config declaration
  #-> Mandatory in pure JRuby
  #include com.drighetto.springjruby.ActionDefinition
  
  #Define instance attribute accessor
  attr_reader :last_called_method
  
  #Constructor        
  def initialize()
    @last_called_method = ""
  end
  
  #Explicit Java Style Setter method required by Spring !!!
  def setLast_called_method(text)
    @last_called_method  = text
  end 
  
  #Implementation of the writeContent() method
  def writeContent(content, filename)
    my_file = File.new(filename, "w")
    my_file.puts content
    my_file.close
    @last_called_method  = "writeContent() on #{filename}"
  end
  
  #Implementation of the readContent() method
  def readContent(filename)
    content = ""    
    my_file = File.new(filename, "r")
    while (line = my_file.gets)
      content = content + line      
    end
    my_file.close
    @last_called_method  = "readContent() on #{filename}"
    return content
  end
  
  #Implementation of the obtainLastMethodCalled() method
  def obtainLastMethodCalled()
    return @last_called_method
  end
  
end

#Instantiate and return a new instance of the ActionDefinitionJRubyImpl class
#Note from the Spring documentation : 
#  If you forget to do this, it is not the end of the world; this will however 
#result in Spring having to trawl (reflectively) through the type representation of 
#your JRuby class looking for a class to instantiate. 
#  In the grand scheme of things this will be so fast that you'll never notice it, but 
#it is something that can be avoided by simply having a line such as the one above as 
#the last line of your JRuby script. If you don't supply such a line, or if Spring cannot 
#find a JRuby class in your script to instantiate then an opaque ScriptCompilationException
#will be thrown immediately after the source is executed by the JRuby interpreter.
ActionDefinitionJRubyImpl.new
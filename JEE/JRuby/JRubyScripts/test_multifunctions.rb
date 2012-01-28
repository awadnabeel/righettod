#Script defining several functions in order to be called independently by a Java class
def function_one
  puts "functionOne"
end

def function_two(param1)
  return "functionTwo:" + param1
end


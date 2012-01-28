package com.drighetto.jruby;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Simple class to show the execution of JRuby scripts from a Java class
 * 
 * @author Dominique RIGHETTO <dominique.righetto@gmail.com>
 * 
 */
public class ScriptRunner {

	/**
	 * Entry point
	 * 
	 * @param args
	 *            Command line
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		try {
			FileReader scriptReader = null;
			Object obj = null;

			/* Initialize the script engine */
			ScriptEngineManager m = new ScriptEngineManager();
			ScriptEngine rubyEngine = m.getEngineByName("jruby");
			ScriptContext scriptContext = rubyEngine.getContext();
			Invocable rubyInvocableEngine = (Invocable) rubyEngine;

			/*
			 * Sample 1 : Pass a parameter to the JRuby script that display the
			 * value of the parameter passed
			 */

			System.out.println("###SAMPLE 1");
			scriptContext.setAttribute("param_from_java", "MyParamValue",
					ScriptContext.ENGINE_SCOPE);
			scriptReader = new FileReader(
					"JRubyScripts/test_param_java_ruby.rb");
			rubyEngine.eval(scriptReader, scriptContext);
			scriptReader.close();

			/*
			 * Sample 2 : Pass a parameter to the JRuby script that modify the
			 * parameter and return it
			 */

			System.out.println("###SAMPLE 2");
			scriptContext.setAttribute("param_from_java", "MyParamValue",
					ScriptContext.ENGINE_SCOPE);
			scriptReader = new FileReader(
					"JRubyScripts/test_param_java_ruby_with_return.rb");
			obj = rubyEngine.eval(scriptReader, scriptContext);
			scriptReader.close();
			System.out
					.printf(
							"Parameter before script call '%s' and after script call '%s'\n",
							"MyParamValue", obj);

			/*
			 * Sample 3 : Call a JRuby script to obtain a object (thread
			 * subclass defined in JRuby script)
			 */

			System.out.println("###SAMPLE 3");
			scriptContext.removeAttribute("param_from_java",
					ScriptContext.ENGINE_SCOPE);
			scriptReader = new FileReader(
					"JRubyScripts/test_param_ruby_java_with_return.rb");
			obj = rubyEngine.eval(scriptReader, scriptContext);
			Thread thread = (Thread) obj;
			scriptReader.close();
			System.out.printf("Thread name : %s\n", thread.getName());
			thread.start();
			Thread.sleep(1000);// Let the thread finish...

			/*
			 * Sample 4 : Call a specific function of a JRuby script
			 */
			System.out.println("###SAMPLE 4");
			scriptReader = new FileReader("JRubyScripts/test_multifunctions.rb");
			// Evaluate (execute) the script. Once evaluated, any functions
			// in the script can be called with the invokeFunction method.
			rubyEngine.eval(scriptReader);
			// Call function
			obj = rubyInvocableEngine.invokeFunction("function_two",
					"HelloWord");
			// Call method (procedure)
			rubyInvocableEngine.invokeMethod("", "function_one", new Object[0]);
			scriptReader.close();
			System.out.printf("Return of the function '%s'\n", obj);

		} catch (ScriptException se) {
			System.out.println("########EXCEPTION");
			System.out.printf("#Filename     : %s\n", se.getFileName());
			System.out.printf("#ColumnNumber : %s\n", se.getColumnNumber());
			System.out.printf("#LineNumber   : %s\n", se.getLineNumber());
			System.out.printf("#Message      : %s\n", se.getMessage());
			System.out.println("#################");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

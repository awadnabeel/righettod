package com.drighetto.essai.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Simple aspect defining Pointcut & Advice
 * 
 * @author Dominique RIGHETTO<br>
 *         23 mars 07<br>
 */
@Aspect
@SuppressWarnings("unused")
public class MyAspect {

	/**
	 * 
	 * Default constructor
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         23 mars 07<br>
	 */
	public MyAspect() {
		super();
	}

	// the pointcut expression : Before execution of any method with name
	// starting with 'say' and with string parameter
	@Before("execution(* say*(..)) && args(java.lang.String)")
	// the pointcut signature : void required
	private void aspectActionOne() {
		System.out.println("-AspectActionOne-");
	}

	// the pointcut expression : All methods in classes from package
	// 'com.drighetto.essai.springaop.bean'
	@Before("within(com.drighetto.essai.springaop.bean..*)")
	// the pointcut signature : void required
	private void aspectActionTwo() {
		System.out.println("-AspectActionTwo-");
	}

	// the pointcut expression : After normally execution (without exception) of
	// any method with name starting with 'say' and with string parameter
	@AfterReturning("execution(* say*(..)) && args(java.lang.String)")
	// the pointcut signature : void required
	private void aspectActionThree() {
		System.out.println("-AspectActionThree-");
	}
	
	// the pointcut expression : After normally execution (without exception) of
	// any method with name starting with 'say' 
	// and without parameter 
	// and returning string object type
	@AfterReturning(
			    pointcut="execution(* say*())",
			    returning="java.lang.String")
	// the pointcut signature : void required
	private void aspectActionFour() {
		System.out.println("-AspectActionFour-");
	}
	
	// the pointcut expression : On execution of
	// any method with name starting with 'say' and without parameter	
	@Around("execution(* say*())")
	// the pointcut signature : void NOT required with @Around advice because managing method join point invocation
	private Object aspectActionFive(ProceedingJoinPoint pjp)  throws Throwable {
		//Before method processing
		System.out.println("aspectActionFive : Before method processing");
		//Call method and get result as Object
		System.out.println("aspectActionFive : Method processing");
		Object obj = pjp.proceed();
		//After method processing
		System.out.println("aspectActionFive : After method processing");
		//Return method invocation result as Object 
		System.out.printf("aspectActionFive : Normal Join Point returned value [%s]\n",((String)(obj)).replaceAll("\n", ""));
		//It's possible to modify returned value here....
		obj = "Cool I can modify returned value in AOP with @Around advice !!!";
		return obj;
	}

	// the pointcut expression : On execution of
	// any method with name starting with 'say' and with string parameter	
	@Around("execution(* say*(..))  && args(java.lang.String)")
	// the pointcut signature : void NOT required with @Around advice because managing method join point invocation
	private void aspectActionSix(ProceedingJoinPoint pjp)  throws Throwable {
		//Before method processing
		System.out.println("aspectActionSix : Before method processing");
		//Call method and don't get result because join point is a void
		System.out.println("aspectActionSix : Method processing");
        //It's possible to modify input args value here....
		System.out.printf("aspectActionSix : Normal input args value [%s]",pjp.getArgs()[0]);
		Object[] param = {"ModifiedValue"};
		pjp.proceed(param);
		//After method processing
		System.out.println("aspectActionSix : After method processing");
	}		
}

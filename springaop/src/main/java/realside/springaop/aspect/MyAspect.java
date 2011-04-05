package realside.springaop.aspect;

import javax.management.RuntimeErrorException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import realside.springaop.annotation.LogAction;
 
@Aspect
public class MyAspect{
	
	public String requiredRoll = "admin";
	
	public String userProfile;
 
	@Pointcut(value="execution(public * *(..))")
	public void anyPublicMethod() {
		System.out.println("bla..");
	}
 
	@Around("anyPublicMethod() && @annotation(logAction)")
	public Object logAction(ProceedingJoinPoint pjp, LogAction logAction) throws Throwable {
 
		// Do what you want with the actionperformed
		@SuppressWarnings("unused")
		String actionPerformed = logAction.actionPerformed();
 
		if ( ! requiredRoll.equals( userProfile ) ){
			
			System.out.println("El usuario no cuenta con suficientes privilegios.");
			throw new Exception("El usuario no cuenta con suficientes privilegios.");
		}
		
		// Do what you want with the join point arguments
		for ( Object object : pjp.getArgs()) {
			System.out.println(object);
		}
 
		return pjp.proceed();
	}
}

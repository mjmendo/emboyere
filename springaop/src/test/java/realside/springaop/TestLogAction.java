/**
 * 
 */
package realside.springaop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import realside.springaop.aspect.MyAspect;



/**
 * @author Marcelo Mendoza
 *
 */
public class TestLogAction {

	 ApplicationContext appCon = new ClassPathXmlApplicationContext("/app-context.xml");
    
	 
	 @Test
	 public void testAOPCall(){
		 
		 MyAspect asp = (MyAspect) appCon.getBean("myAspect");
		 asp.userProfile = "admin";
		 
		 
		 FakeApp fa= (FakeApp) appCon.getBean("fakeApp");
		 fa.printThisMessage( "llamando a print...");
		 
	 }

}

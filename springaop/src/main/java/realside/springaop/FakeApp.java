package realside.springaop;

import realside.springaop.annotation.LogAction;

/**
 * Hello world!
 *
 */
public class FakeApp 
{
	
	
	public FakeApp() {
		System.out.println("iniciado...");
	}
	
	@LogAction(actionPerformed="doSomething")
	public void printThisMessage( String message ){
		System.out.println(message);
	}
}

package realside.log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSimpleLog {

	@Before
	public void before(){
		LogManager.shutdown();
	}
	
	@Test
	public void createLog(){
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		Assert.assertNotNull( logger );
	}
	
	@Test
	public void logSomething(){
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		BasicConfigurator.configure();
		
		logger.setLevel( Level.INFO );
		
		logger.trace("trace logging");
		logger.debug("debug logging");
		logger.info("info logging");
		logger.warn("warn logging");
		logger.error("error logging");
		logger.fatal("fatal logging");
				
	}
	
	@Test
	public void logFromSingleConfigFile(){
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		Properties prop = getPropertiesFile( "logFromSingleConfigFile.properties" );
			
		// BasicConfigurator replaced with PropertyConfigurator.
	    PropertyConfigurator.configure(prop);
		
		logger.trace("trace logging");
		logger.debug("debug logging");
		logger.info("info logging");
		logger.warn("warn logging");
		logger.error("error logging");
		logger.fatal("fatal logging");
		
	}

	
	@Test
	public void fileLogConfigWithTwoAppenders(){
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		Properties prop = getPropertiesFile( "fileLogConfigWithTwoAppenders.properties" );
	    PropertyConfigurator.configure(prop);
	    
		logger.trace("trace logging");
		logger.debug("debug logging");
		logger.info("info logging");
		logger.warn("warn logging");
		logger.error("error logging");
		logger.fatal("fatal logging");
	    
	}
	
	@Test
	public void log4jWithSpring(){
		
		
		Properties prop = getPropertiesFile( "log4jWithSpring.properties" );
	    PropertyConfigurator.configure(prop);

	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
	    
	    Logger logger = Logger.getLogger(this.getClass().getName());
		
		logger.trace("trace logging with spring");
		logger.debug("debug logging with spring");
		logger.info("info logging with spring");
		logger.warn("warn logging with spring");
		logger.error("error logging with spring");
		logger.fatal("fatal logging with spring");
		
		Object bean = ctx.getBean("log4jInitializer");
		logger.fatal("dont like spring beans");
		
	}
	
	/**
	 * @param fileName
	 * @return properties file
	 */
	private Properties getPropertiesFile(String fileName) {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream( fileName );
		Properties prop = new Properties();
		
		try {
			prop.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}

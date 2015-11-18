package resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This is the class which starts the webservice
 * @author Shalima
 *
 */
@ApplicationPath("resources")
public class MitchellApplication extends Application{
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dbInit.xml");

}

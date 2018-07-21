package introsde.rest.activitypreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.ws.Endpoint;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import introsde.rest.activitypreference.dao.ActivityPreferenceDao;
import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.soap.ws.GeneralImpl;

public class App
{    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException, ParseException
    {
	    String PROTOCOL = "http://";
	    String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
	    if (HOSTNAME.equals("127.0.1.1"))
	    {
	        HOSTNAME = "localhost";
	    }
	    String PORT = "6902";
	    String BASE_URL = "/ws/general";
	
	    if (String.valueOf(System.getenv("PORT")) != "null"){
	        PORT=String.valueOf(System.getenv("PORT"));
	    }
	
	    String endpointUrl = PROTOCOL+HOSTNAME+":"+PORT+BASE_URL;
	    System.out.println("Starting People Service...");
	    System.out.println("--> Published at = "+endpointUrl);
	    Endpoint.publish(endpointUrl, new GeneralImpl());
    }
}

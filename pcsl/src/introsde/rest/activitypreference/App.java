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

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import introsde.rest.activitypreference.dao.ActivityPreferenceDao;
import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;

public class App
{    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException, ParseException
    {
    	String protocol = "http://";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostname);
        if (hostname.equals("127.0.0.1") || hostname.equals("127.0.1.1"))
        {
        	hostname = "localhost";
        }
        String port = "5902";

        // We need this so the App will run on Heroku properly where we got the assigned port
        // in an environment value named PORT
        if(String.valueOf(System.getenv("PORT")) != "null") {
        	port = String.valueOf(System.getenv("PORT"));
        }

        URI baseUrl = new URI(protocol + hostname + ":" + port + "/introsde-pcsl/");

        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
        /*System.out.println("Starting introsde standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");*/
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting introsde-pcsl REST services...");
        return new MyApplicationConfig();
    }
}

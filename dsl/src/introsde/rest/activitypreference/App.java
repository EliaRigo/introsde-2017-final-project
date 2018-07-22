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
import introsde.rest.activitypreference.model.Domain;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Suggestion;

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
        String port = "5900";

        // We need this so the App will run on Heroku properly where we got the assigned port
        // in an environment value named PORT
        if(String.valueOf(System.getenv("PORT")) != "null") {
        	port = String.valueOf(System.getenv("PORT"));
        }

        URI baseUrl = new URI(protocol + hostname + ":" + port + "/introsde-dsl/");

        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
        /*System.out.println("Starting introsde standalone HTTP server...");
        JdkHttpServerFactory.createHttpServer(BASE_URI, createApp());
        System.out.println("Server started on " + BASE_URI + "\n[kill the process to exit]");*/
    }
    
    public static void init() throws ParseException, IOException {
    	try {
	    	EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
	    	EntityTransaction tx = em.getTransaction();
			tx.begin();
	    	em.createQuery("DELETE FROM Domain d").executeUpdate();
	    	em.createQuery("DELETE FROM Activity a").executeUpdate();
	    	em.createQuery("DELETE FROM Person p").executeUpdate();
	    	em.createQuery("DELETE FROM Item i").executeUpdate();
	    	em.createQuery("DELETE FROM Suggestion s").executeUpdate();
	    	tx.commit();
			ActivityPreferenceDao.instance.closeConnections(em);
    	}
    	catch(Exception ex) {}
    	
    	// Domains
    	Domain d1 = new Domain();
    	d1.setName("Outdoor");
    	d1 = Domain.postDomain(d1);
    	
    	Domain d2 = new Domain();
    	d2.setName("Evening Activity");
    	d2 = Domain.postDomain(d2);
    	
    	Domain d3 = new Domain();
    	d3.setName("Weekend");
    	d3 = Domain.postDomain(d3);
    	
    	// Items
    	Item i1 = new Item();
    	i1.setName("Jogging");
    	i1.setIdDomain(d1.getIdDomain());
    	i1.setDescription("Jogging near MUSE");
    	i1.setDate("2018-06-01 - 2018-09-30");
    	i1.setHourStart("18:00");
    	i1.setHourEnd("19:00");
    	i1 = Item.postItem(i1);
    	
    	Item i2 = new Item();
    	i2.setName("Joga");
    	i2.setIdDomain(d1.getIdDomain());
    	i2.setDescription("Joga in S. Bartolomeo");
    	i2.setDate("2018-06-01 - 2018-09-30");
    	i2.setHourStart("14:00");
    	i2.setHourEnd("16:00");
    	i2 = Item.postItem(i2);
    	
    	Item i3 = new Item();
    	i3.setName("Softair");
    	i3.setIdDomain(d1.getIdDomain());
    	i3.setDescription("Softair in Bondone Mountain meadows");
    	i3.setDate("2018-06-01 - 2018-09-30");
    	i3.setHourStart("08:00");
    	i3.setHourEnd("19:00");
    	i3 = Item.postItem(i3);
    	
    	Item i4 = new Item();
    	i4.setName("Raggea DJ set");
    	i4.setIdDomain(d2.getIdDomain());
    	i4.setDescription("LeValli Disco Club");
    	i4.setDate("2018-09-01 - 2018-09-30");
    	i4.setHourStart("23:00");
    	i4.setHourEnd("04:00");
    	i4 = Item.postItem(i4);
    	
    	Item i5 = new Item();
    	i5.setName("Dancehall");
    	i5.setIdDomain(d2.getIdDomain());
    	i5.setDescription("Dancehall in Centro Culturale Le Albere");
    	i5.setDate("2018-09-01 - 2018-12-31");
    	i5.setHourStart("20:00");
    	i5.setHourEnd("02:00");
    	i5 = Item.postItem(i5);
    	
    	Item i6 = new Item();
    	i6.setName("Wine Tasting");
    	i6.setIdDomain(d3.getIdDomain());
    	i6.setDescription("Wine tasting in Cantina Sociale Ferrari");
    	i6.setDate("2018-09-01 - 2018-12-31");
    	i6.setHourStart("09:00");
    	i6.setHourEnd("12:00");
    	i6 = Item.postItem(i6);
    	
    	Item i7 = new Item();
    	i7.setName("Ferrata");
    	i7.setIdDomain(d3.getIdDomain());
    	i7.setDescription("Ferrata in Val di Fiemme");
    	i7.setDate("2018-06-01 - 2018-09-15");
    	i7.setHourStart("09:00");
    	i7.setHourEnd("18:00");
    	i7 = Item.postItem(i7);
    	
    	Item i8 = new Item();
    	i8.setName("Mountain Bike");
    	i8.setIdDomain(d3.getIdDomain());
    	i8.setDescription("Mountain Bike in Val di Sole");
    	i8.setDate("2018-09-01 - 2018-09-15");
    	i8.setHourStart("09:00");
    	i8.setHourEnd("20:00");
    	i8 = Item.postItem(i8);
    	
    	// Person
    	Person p1 = new Person();
    	p1.setFirstname("Paperino");
    	p1.setLastname("Rossi");
    	p1.setBirthdate("1990-01-01");
    	p1.setIsAdmin(1);
    	p1.setWorkHourStart("08:00");
    	p1.setWorkHourEnd("13:00");
    	p1.setUsername("paper");
    	p1.setPassword("password123");
    	p1 = Person.newPerson(p1);
    	
    	Person p2 = new Person();
    	p2.setFirstname("Paperina");
    	p2.setLastname("Bianchi");
    	p2.setBirthdate("1990-02-15");
    	p2.setIsAdmin(0);
    	p2.setWorkHourStart("09:00");
    	p2.setWorkHourEnd("14:00");
    	p2.setUsername("pina");
    	p2.setPassword("password123");
    	p2 = Person.newPerson(p2);
    	
    	Person p3 = new Person();
    	p3.setFirstname("Pluto");
    	p3.setLastname("Verdi");
    	p3.setBirthdate("1990-02-15");
    	p3.setIsAdmin(0);
    	p3.setWorkHourStart("09:00");
    	p3.setWorkHourEnd("14:00");
    	p3.setUsername("pluto");
    	p3.setPassword("password123");
    	p3 = Person.newPerson(p3);
    	
    	//Activity
    	Activity a1 = new Activity();
    	a1.setIdPerson(p1.getIdPerson());
    	a1.setIdItem(i1.getIdItem());
    	a1.setDate("2018-09-01");
    	a1.setEvaluation(7);
    	a1 = Activity.postActivity(a1);
    	
    	Activity a2 = new Activity();
    	a2.setIdPerson(p1.getIdPerson());
    	a2.setIdItem(i2.getIdItem());
    	a2.setDate("2018-09-02");
    	a2.setEvaluation(10);
    	a2 = Activity.postActivity(a2);
    	
    	Activity a3 = new Activity();
    	a3.setIdPerson(p1.getIdPerson());
    	a3.setIdItem(i3.getIdItem());
    	a3.setDate("2018-09-03");
    	a3.setEvaluation(8);
    	a3 = Activity.postActivity(a3);
    	
    	Activity a4 = new Activity();
    	a4.setIdPerson(p2.getIdPerson());
    	a4.setIdItem(i6.getIdItem());
    	a4.setDate("2018-09-01");
    	a4.setEvaluation(10);
    	a4 = Activity.postActivity(a4);
    	
    	Activity a5 = new Activity();
    	a5.setIdPerson(p2.getIdPerson());
    	a5.setIdItem(i6.getIdItem());
    	a5.setDate("2018-09-05");
    	a5.setEvaluation(9);
    	a5 = Activity.postActivity(a5);
    	
    	Activity a6 = new Activity();
    	a6.setIdPerson(p2.getIdPerson());
    	a6.setIdItem(i6.getIdItem());
    	a6.setDate("2018-09-10");
    	a6.setEvaluation(10);
    	a6 = Activity.postActivity(a6);
    	
    	Activity a7 = new Activity();
    	a7.setIdPerson(p3.getIdPerson());
    	a7.setIdItem(i4.getIdItem());
    	a7.setDate("2018-09-01");
    	a7.setEvaluation(8);
    	a7 = Activity.postActivity(a7);
    	
    	Activity a8 = new Activity();
    	a8.setIdPerson(p3.getIdPerson());
    	a8.setIdItem(i5.getIdItem());
    	a8.setDate("2018-09-05");
    	a8.setEvaluation(10);
    	a8 = Activity.postActivity(a8);
    	
    	Activity a9 = new Activity();
    	a9.setIdPerson(p3.getIdPerson());
    	a9.setIdItem(i1.getIdItem());
    	a9.setDate("2018-09-03");
    	a9.setEvaluation(5);
    	a9 = Activity.postActivity(a9);
    	
    	//Suggestion
    	Suggestion s1 = new Suggestion();
    	s1.setIdPerson(p1.getIdPerson());
    	s1.setIdItem(i1.getIdItem());
    	s1.setDateAdded("2018-09-01");
    	s1.setEvaluation(8);
    	
    	Suggestion s2 = new Suggestion();
    	s2.setIdPerson(p1.getIdPerson());
    	s2.setIdItem(i4.getIdItem());
    	s2.setDateAdded("2018-09-03");
    	s2.setEvaluation(5);
    	s2 = Suggestion.postSuggestion(s2);
    	
    	Suggestion s3 = new Suggestion();
    	s3.setIdPerson(p1.getIdPerson());
    	s3.setIdItem(i3.getIdItem());
    	s3.setDateAdded("2018-09-02");
    	s3.setEvaluation(6);
    	s3 = Suggestion.postSuggestion(s3);
    	
    	Suggestion s4 = new Suggestion();
    	s4.setIdPerson(p2.getIdPerson());
    	s4.setIdItem(i6.getIdItem());
    	s4.setDateAdded("2018-09-01");
    	s4.setEvaluation(9);
    	s4 = Suggestion.postSuggestion(s4);
    	
    	Suggestion s5 = new Suggestion();
    	s5.setIdPerson(p2.getIdPerson());
    	s5.setIdItem(i7.getIdItem());
    	s5.setDateAdded("2018-09-01");
    	s5.setEvaluation(6);
    	s5 = Suggestion.postSuggestion(s5);
    	
    	Suggestion s6 = new Suggestion();
    	s6.setIdPerson(p3.getIdPerson());
    	s6.setIdItem(i7.getIdItem());
    	s6.setDateAdded("2018-09-02");
    	s6.setEvaluation(5);
    	s6 = Suggestion.postSuggestion(s6);
    	
    	//File f = new File("database.sqlite");
    	//if(f.exists() && !f.isDirectory()) { 
    	//    f.delete();
    	//}
    	//copyFileUsingStream(new File("database_origin.sqlite"), new File("database.sqlite"));
    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting introsde-dsl REST services...");
        return new MyApplicationConfig();
    }
    
    /*
    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }*/
}

package introsde.rest.activitypreference.resources;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/general")

public class GeneralResource {
	// Allows to insert contextual objects into the class,
	// E.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Will work only inside a Java EE application
	@PersistenceUnit(unitName = "assignment")
	EntityManager entityManager;

	// Will work only inside a Java EE application
	@PersistenceContext(unitName = "assignment", type = PersistenceContextType.TRANSACTION)
	private EntityManagerFactory entityManagerFactory;
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://127.0.1.1:5900/introsde-dsl").build();
		//return
		//UriBuilder.fromUri("https://introsde2017-assign-2-server.herokuapp.com/assignment/").build();
	}
	
	/**
	 * GET / Return if the service is UP
	 * 
	 * @return String OK
	 */
	@GET
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getBlslUp() {
		System.out.println("GET /");
		return "BLSL OK";
	}
	
	@GET
	@Path("activity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity getActivity() throws IOException {
		System.out.println("GET /activity");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/activity";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		for (int i=0; i < nodes.size(); i++) {
			Activity a = mapper.readValue(nodes.get(i).toString(), Activity.class);
			return a;
		}
		return null;
	}
	
	@GET
	@Path("item")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> getListItem() throws IOException {
		System.out.println("GET /activity");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/item";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Item> arrayItem = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Item a = mapper.readValue(nodes.get(i).toString(), Item.class);
			arrayItem.add(a);
		}
		return arrayItem;
		//return mapper.readValue(json, arrayItem.getClass());
	}
	
	@POST
	@Path("login")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person login(Person p) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("POST /login");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/person";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		for (int i=0; i < nodes.size(); i++) {
			Person a = mapper.readValue(nodes.get(i).toString(), Person.class);
			//System.out.println(a.getUsername() + " - " + p.getUsername());
			//System.out.println(a.getPassword() + " - " + p.getPassword());
			if (a.getUsername().equals(p.getUsername()) && 
				a.getPassword().equals(p.getPassword())) {
				//System.out.println("Found!");
				return a;
			}
		}
		return null;
	}
}

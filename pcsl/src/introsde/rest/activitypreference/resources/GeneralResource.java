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
import javax.ws.rs.client.Entity;
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
import introsde.rest.activitypreference.model.Domain;
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
		return UriBuilder.fromUri("http://127.0.1.1:5901/introsde-blsl").build();
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
		return "PCSL OK";
	}
	
	/**
	 * 
	 * GET
	 * 
	 */
	
	@GET
	@Path("domain")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Domain> getListDomain() throws IOException {
		System.out.println("GET /domain");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/domain";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Domain> arrayItem = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Domain d = mapper.readValue(nodes.get(i).toString(), Domain.class);
			arrayItem.add(d);
		}
		return arrayItem;
		//return mapper.readValue(json, arrayItem.getClass());
	}
	
	@GET
	@Path("domain/{id}/item")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> getListItemByDomainId(@PathParam("id") int id) throws IOException {
		System.out.println(String.format("GET domain/%d/item", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/item";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Item> arrayItem = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Item a = mapper.readValue(nodes.get(i).toString(), Item.class);
			if (a.getIdDomain() == id) {
				arrayItem.add(a);
			}
		}
		return arrayItem;
		//return mapper.readValue(json, arrayItem.getClass());
	}
	
	@GET
	@Path("person/{id}/activity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getListActivityByPersonId(@PathParam("id") int id) throws IOException {
		System.out.println(String.format("GET person/%d/activity", id));
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "general/activity";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Activity> arrayItem = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Activity a = mapper.readValue(nodes.get(i).toString(), Activity.class);
			if (a.getIdPerson() == id) {
				String itemRequest =String.format("general/item/%d", a.getIdItem());
				itemResp = service.path(itemRequest).request().accept(type).get();
				Item item = itemResp.readEntity(Item.class);
				a.setInfoItem(item);
				arrayItem.add(a);
			}
		}
		return arrayItem;
		//return mapper.readValue(json, arrayItem.getClass());
	}
	
	@GET
	@Path("item")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> getListItem() throws IOException {
		System.out.println("GET /item");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/item";
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
	
	/**
	 * 
	 * POST
	 * 
	 */
	
	@POST
	@Path("activity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Activity postActivity(Activity a) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("POST /activity");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/activity";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(a, content));
		return resp.readEntity(Activity.class);
	}
	
	@POST
	@Path("person")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person postPerson(Person p) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("POST /person");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/person";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(p, content));
		return resp.readEntity(Person.class);
	}
	
	@POST
	@Path("item")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Item postItem(Item i) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("POST /item");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/item";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(i, content));
		return resp.readEntity(Item.class);
	}
	
	@POST
	@Path("domain")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Domain postDomain(Domain d) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("POST /domain");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/domain";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(d, content));
		return resp.readEntity(Domain.class);
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
		
		String request = "/general/login";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(p, content));

		return resp.readEntity(Person.class);
	}
	
	/**
	 * 
	 * PUT
	 * 
	 */
	@PUT
	@Path("activity/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Activity putActivity(@PathParam("id") int id, String a) {
		/*
		 * Remember Activity as infoItem
		 */
		System.out.println(String.format("PUT /activity/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/activity/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).put(Entity.entity(a.toString(), content));
		return resp.readEntity(Activity.class);
	}
	
	@PUT
	@Path("domain/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Domain putDomain(@PathParam("id") int id, Domain a) {
		System.out.println(String.format("PUT /domain/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).put(Entity.entity(a, content));
		return resp.readEntity(Domain.class);
	}
	
	@PUT
	@Path("item/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Item putItem(@PathParam("id") int id, Item a) {
		System.out.println(String.format("PUT /item/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).put(Entity.entity(a, content));
		return resp.readEntity(Item.class);
	}
	
	/**
	 * 
	 * DELETE
	 * 
	 */
	
	@DELETE
	@Path("activity/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteActivity(@PathParam("id") int id) {
		/*
		 * Remember Activity as infoItem
		 */
		System.out.println(String.format("PUT /activity/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/activity/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
		//return resp.readEntity(Activity.class);
	}
	
	@DELETE
	@Path("domain/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteDomain(@PathParam("id") int id) {
		System.out.println(String.format("PUT /domain/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
		//return resp.readEntity(Domain.class);
	}
	
	@DELETE
	@Path("item/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteItem(@PathParam("id") int id, Item a) {
		System.out.println(String.format("PUT /item/%d", id));
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item/%d", id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
		//return resp.readEntity(Item.class);
	}
}

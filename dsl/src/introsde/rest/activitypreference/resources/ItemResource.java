package introsde.rest.activitypreference.resources;
import introsde.rest.activitypreference.App;
import introsde.rest.activitypreference.dao.ActivityPreferenceDao;
import introsde.rest.activitypreference.model.Activity;
//import introsde.rest.activitypreference.model.Item;
//import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Item;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/item")
public class ItemResource {
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
	
	/**
	 * GET /item Return the list of Item in JSON and XML
	 * 
	 * @return List<Item> List of Item (in XML or JSON format)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Item> getItemBrowser() {
		System.out.println("GET /item");
		return Item.getAll();
	}
	
	/**
	 * GET /item/{id} Return Item (via XML or JSON)
	 * 
	 * @param id Id of the Item (from path /item/{id})
	 * @return Item item (in XML or JSON format)
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItemByIdItem(@PathParam("id") int id) {
		System.out.println("GET /item/" + String.valueOf(id));
		Item item = Item.getItemById(id);
		if (item == null) {
			// Return 404 if the item is not present in the database
			return Response.status(404).build();
		}
		return Response.ok().entity(item).build();
	}
	
	/**
	 * POST /item Insert new Item (via XML or JSON)
	 * 
	 * @return New Item (in XML or JSON format)
	 */
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Item postItem(Item item) {
		System.out.println("POST /item");
		Item.postItem(item);
		return item;
	}
	
	/**
	 * PUT /item Update Item (via XML or JSON)
	 * 
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Item updateItem(Item item) {
		System.out.println("POST /item");
		Item.updateItem(item);
		return item;
	}
	
	/**
	 * DELETE /item/{id} Delete Item with specific {id}
	 * 
	 * @param id Id of the Item (from path /item/{id})
	 */
	@DELETE
	@Path("{id}")
	public void deleteItem(@PathParam("id") int id) {
		System.out.println("DELETE /item/" + String.valueOf(id));
		Item d = Item.getItemById(id);
		if (d == null) {
			// Raise internal exception if the person is not present in the database
			throw new RuntimeException("Delete: Item with " + id + " not found");
		}
		Item.removeItem(d);
	}
}

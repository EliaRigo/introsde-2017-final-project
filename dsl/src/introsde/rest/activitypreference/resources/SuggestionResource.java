package introsde.rest.activitypreference.resources;
import introsde.rest.activitypreference.App;
import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Domain;
import introsde.rest.activitypreference.model.Suggestion;

import java.io.IOException;
import java.text.ParseException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/suggestion")

public class SuggestionResource {
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
	 * GET /suggestion Return the list of Suggestion in JSON and XML
	 * 
	 * @return List<Suggestion> List of Suggestion (in XML or JSON format)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Suggestion> getItemBrowser() {
		System.out.println("GET /suggestion");
		return Suggestion.getAll();
	}
	
	/**
	 * POST /suggestion Insert new Suggestion (via XML or JSON)
	 * 
	 * @return New Suggestion (in XML or JSON format)
	 */
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Suggestion postSuggestion(Suggestion suggestion) {
		System.out.println("POST /suggestion");
		return Suggestion.postSuggestion(suggestion);
	}
	
}

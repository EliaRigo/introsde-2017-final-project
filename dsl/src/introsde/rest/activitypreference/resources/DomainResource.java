package introsde.rest.activitypreference.resources;
import introsde.rest.activitypreference.App;
import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Domain;

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
@Path("/domain")

public class DomainResource {
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
	 * GET /domain Return the list of Domain in JSON and XML
	 * 
	 * @return List<Domain> List of Domain (in XML or JSON format)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Domain> getItemBrowser() {
		System.out.println("GET /domain");
		return Domain.getAll();
	}
	
	/**
	 * GET /domain/{id} Return Domain (via XML or JSON)
	 * 
	 * @param id Id of the Domain (from path /domain/{id})
	 * @return Domain domain (in XML or JSON format)
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDomainByIdDomain(@PathParam("id") int id) {
		System.out.println("GET /domain/" + String.valueOf(id));
		Domain domain = Domain.getDomainById(id);
		if (domain == null) {
			// Return 404 if the domain is not present in the database
			return Response.status(404).build();
		}
		return Response.ok().entity(domain).build();
	}
	
	/**
	 * POST /domain Insert new Domain (via XML or JSON)
	 * 
	 * @return New Domain (in XML or JSON format)
	 */
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Domain postDomain(Domain domain) {
		System.out.println("POST /domain");
		Domain.postDomain(domain);
		return domain;
	}
	
	/**
	 * PUT /domain Update Domain (via XML or JSON)
	 * 
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Domain updateDomain(Domain domain) {
		System.out.println("POST /domain");
		Domain.updateDomain(domain);
		return domain;
	}
	
	/**
	 * DELETE /domain/{id} Delete Domain with specific {id}
	 * 
	 * @param id Id of the Domain (from path /domain/{id})
	 */
	@DELETE
	@Path("{id}")
	public void deleteDomain(@PathParam("id") int id) {
		System.out.println("DELETE /domain/" + String.valueOf(id));
		Domain d = Domain.getDomainById(id);
		if (d == null) {
			// Raise internal exception if the person is not present in the database
			throw new RuntimeException("Delete: Domain with " + id + " not found");
		}
		Domain.removeDomain(d);
	}
	
}

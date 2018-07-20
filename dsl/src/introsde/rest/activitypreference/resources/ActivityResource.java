package introsde.rest.activitypreference.resources;
import introsde.rest.activitypreference.App;
import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Item;

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
@Path("/activity")
public class ActivityResource {
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
	 * GET /activity Return the list of Activity in JSON and XML
	 * 
	 * @return List<Activity> List of Activity (in XML or JSON format)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getActivityBrowser() {
		System.out.println("GET /activity");
		return Activity.getAll();
	}
	
	/**
	 * POST /activity Insert new Activity (via XML or JSON)
	 * 
	 * @return New Activity (in XML or JSON format)
	 */
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Activity postActivity(Activity activity) {
		System.out.println("POST /activity");
		Activity.postActivity(activity);
		return activity;
	}
	
	/**
	 * 
	 */
	@PUT
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Activity putActivity(@PathParam("id") int id, Activity activity) {
		System.out.println("PUT /activity/" + String.valueOf(id));
		Activity existing = Activity.getActivityById(id);
		if (existing != null && id == activity.getIdActivity()) {
			// We allow updating only if the activity exits
			// and request's id and activity.getId() match
			
			if (activity.getIdPerson() == -1) {
				// Update idPerson
				activity.setIdPerson(existing.getIdPerson());
			}
			
			if (activity.getIdItem() == -1) {
				// Update idItem
				activity.setIdItem(existing.getIdItem());
			}
			
			if (activity.getDate() == null) {
				// Update date
				activity.setDate(existing.getDate());
			}
			
			if (activity.getEvaluation() == -1) {
				// Update date
				activity.setEvaluation(existing.getEvaluation());
			}
			Activity.updateActivity(activity);
		}
		return activity;
	}
	
	/**
	 * DELETE /activity/{id} Delete Activity with specific {id}
	 * 
	 * @param id Id of the Activity (from path /activity/{id})
	 */
	@DELETE
	@Path("{id}")
	public void deleteActivity(@PathParam("id") int id) {
		System.out.println("DELETE /activity/" + String.valueOf(id));
		Activity a = Activity.getActivityById(id);
		if (a == null) {
			// Raise internal exception if the person is not present in the database
			throw new RuntimeException("Delete: Activity with " + id + " not found");
		}
		Activity.removeActivity(a);
	}
}

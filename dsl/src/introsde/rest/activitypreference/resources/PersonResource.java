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
@Path("/person")
public class PersonResource {

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
	
	@GET
	@Path("/init")
	public String init() throws ParseException, IOException {
		//App.init();
		return "OK";
	}

	/**
	 * GET /person Return the list of people in JSON and XML
	 * 
	 * @return List<Person> List of Person (in XML or JSON format)
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Person> getPersonsBrowser() {
		System.out.println("GET /person");
		return Person.getAll();
	}

	/**
	 * GET /person/{id} Return person's and activities information (via
	 * XML or JSON)
	 * 
	 * @param id
	 *            Id of the Person (from path /person/{id})
	 * @return Person person (in XML or JSON format)
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPersonAndActivityByidPerson(@PathParam("id") int id) {
		System.out.println("GET /person/" + String.valueOf(id));
		Person person = Person.getPersonById(id);
		if (person == null) {
			// Return 404 if the person is not present in the database
			return Response.status(404).build();
		}
		return Response.ok().entity(person).build();
	}

	/**
	 * Request#3: PUT /person/{id} Updates person's information (via XML or JSON)
	 * 
	 * @param id
	 *            Id of the Person (from path /person/{id})
	 * @param person
	 *            Person (from requests' body)
	 * @return Person Updated person (in XML or JSON format)
	 * @throws ParseException
	 */
	@PUT
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person putPerson(@PathParam("id") int id, Person person) throws ParseException {
		System.out.println("PUT /person/" + String.valueOf(id));
		Person existing = Person.getPersonById(id);
		if (existing != null && id == person.getIdPerson()) {
			// We allow updating only if the person exits
			// and request's id and person.getId() match
			if (person.getFirstname() == null) {
				// Update firstname
				person.setFirstname(existing.getFirstname());
			}
			if (person.getLastname() == null) {
				// Update lastname
				person.setLastname(existing.getLastname());
			}
			if (person.getBirthdate() == null) {
				// Update birthdate
				person.setBirthdate(existing.getBirthdate());
			}
			if (person.getWorkHourStart() == null) {
				// Update workHourStart
				person.setWorkHourStart(existing.getWorkHourStart());
			}
			if (person.getWorkHourEnd() == null) {
				// Update workHourEnd
				person.setWorkHourEnd(existing.getWorkHourEnd());
			}
			if (person.getUsername() == null) {
				// Update username
				person.setUsername(existing.getUsername());
			}
			if (person.getPassword() == null) {
				// Update password
				person.setPassword(existing.getPassword());
			}
			Person.updatePerson(person);
		}
		return person;
	}

	/**
	 * POST /person Insert new Person (via XML or JSON)
	 * 
	 * @return New Person (in XML or JSON format)
	 */
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person postPerson(Person person) {
		System.out.println("POST /person");
		Person.newPerson(person);
		return person;
	}

	/**
	 * Request#5: DELETE /person/{id} Delete person with specific {id}
	 * 
	 * @param id
	 *            Id of the Person (from path /person/{id})
	 */
	@DELETE
	@Path("{id}")
	public void deletePerson(@PathParam("id") int id) {
		System.out.println("DELETE /person/" + String.valueOf(id));
		Person p = Person.getPersonById(id);
		if (p == null) {
			// Raise internal exception if the person is not present in the database
			throw new RuntimeException("Delete: Person with " + id + " not found");
		}
		Person.removePerson(p);
	}
}
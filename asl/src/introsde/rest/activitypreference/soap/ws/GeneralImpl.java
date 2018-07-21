package introsde.rest.activitypreference.soap.ws;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Domain;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Suggestion;

//Service Implementation

@WebService(endpointInterface = "introsde.rest.activitypreference.soap.ws.General",
  serviceName="PeopleService")
public class GeneralImpl implements General {

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://127.0.1.1:5900/introsde-dsl").build();
		//return
		//UriBuilder.fromUri("https://introsde2017-assign-2-server.herokuapp.com/assignment/").build();
	}
	
	/**
	 * General Methods
	 */
	
	public ArrayList<Domain> getListDomain() throws IOException {
		System.out.println("GET /domain");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/domain";
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
	
	public ArrayList<Activity> getListActivity() throws IOException {
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
		
		ArrayList<Activity> arrayItem = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Activity a = mapper.readValue(nodes.get(i).toString(), Activity.class);
			arrayItem.add(a);
		}
		return arrayItem;
		//return mapper.readValue(json, arrayItem.getClass());
	}
	
	public ArrayList<Item> getListItem() throws IOException {
		System.out.println("GET /item");
		
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
	
	/**
	 * SOAP Methods
	 */
	
	@Override
	public List<Suggestion> getMySuggestion() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("GET /suggestion");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/suggestion";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Suggestion> arraySuggestion = new ArrayList<>();
		
		for (int i=0; i < nodes.size(); i++) {
			Suggestion s = mapper.readValue(nodes.get(i).toString(), Suggestion.class);
			arraySuggestion.add(s);
		}
		return arraySuggestion;
		//return mapper.readValue(json, arrayItem.getClass());
	}

	@Override
	public Suggestion calculateSuggestion(@WebParam(name="personId") int id) throws IOException {
		ArrayList<Activity> activity = getListActivity();
		ArrayList<Item> item = getListItem();
		ArrayList<Activity> myActivity = new ArrayList<Activity>();
		ArrayList<Activity> activityPrefDomain = new ArrayList<Activity>();
		Map<Integer, Integer> domainVote = new HashMap<Integer, Integer>();
		Map<Integer, Integer> activityVote = new HashMap<Integer, Integer>();
		
		// Get myActivity
		for (Activity a : activity) {
			if (a.getIdPerson() == id) {
				myActivity.add(a);
			}
		}
		// Get pref Domain
		for (Activity myA : myActivity) {
			for (Item i : item) {
				if (i.getIdItem() == myA.getIdItem()) {
					int number = domainVote.get(i.getIdDomain()) == null ? 0 : domainVote.get(i.getIdDomain());
					domainVote.putIfAbsent(i.getIdDomain(), number++);
				}
			}
		}
		
		Map.Entry<Integer, Integer> maxEntry = null;
		for (Map.Entry<Integer, Integer> entry : domainVote.entrySet()) {
			if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		// Get activity in pref domain
		for (Activity a : activity) {
			for (Item i : item) {
				if (i.getIdItem() == a.getIdItem() &&
					i.getIdDomain() == maxEntry.getKey() &&
					i.getDate().compareTo(dtf.format(now)) > 0)
				{
					activityPrefDomain.add(a);
				}
			}
		}
		
		// Get activity with max evaluation from all users
		for (Activity a : activityPrefDomain) {
			int number = activityVote.get(a.getIdItem()) == null ? 0 : activityVote.get(a.getIdActivity());
			activityVote.putIfAbsent(a.getIdItem(), number += a.getEvaluation());
		}
		
		// Return item with max evaluation
		Map.Entry<Integer, Integer> maxItem = null;
		for (Map.Entry<Integer, Integer> entry : activityVote.entrySet()) {
			if (maxItem == null || entry.getValue() > maxItem.getValue()) {
				maxItem = entry;
			}
		}
		
		Suggestion s = new Suggestion();
		s.setIdPerson(id);
		s.setEvaluation(-1);
		s.setIdItem(maxItem.getKey());
		s.setDateAdded(dtf.format(now));
		
		return s;
	}

	@Override
	public Suggestion postSuggestion(Suggestion suggestion) {
		System.out.println("POST /suggestion");
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/suggestion";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(suggestion, content));
		return resp.readEntity(Suggestion.class);
	}

	//@Override
	//public Suggestion getMostPerformedActivity() {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

	//@Override
	//public Suggestion getMostRankedActivity() {
	//	// TODO Auto-generated method stub
	//	return null;
	//}

}

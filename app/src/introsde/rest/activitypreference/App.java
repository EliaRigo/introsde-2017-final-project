package introsde.rest.activitypreference;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.rest.activitypreference.model.Activity;
import introsde.rest.activitypreference.model.Domain;
import introsde.rest.activitypreference.model.Item;
import introsde.rest.activitypreference.model.Person;
import introsde.rest.activitypreference.model.Suggestion;

public class App {

	static Person p;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		init();
	}

	/**
	 * General methods
	 */

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://127.0.1.1:5902/introsde-pcsl").build();
		// return
		// UriBuilder.fromUri("https://introsde2017-assign-2-server.herokuapp.com/assignment/").build();
	}

	private static ArrayList<Item> getListItem() throws IOException {
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
	 * Print utility
	 * @throws IOException 
	 */

	public static void init() throws IOException {
		welcome();
		int i = sc.nextInt();
		switch (i) {
		case 1:
			getInit();
			init();
			break;
		case 2:
			login();
			break;
		case 3:
			registration();
			break;
		case 0:
			exit();
			break;
		default:
			welcome();
			break;
		}
	}
	
	public static void operations() throws IOException {
		System.out.println();
		System.out.println("Select your choose: ");
		System.out.println();
		System.out.println("1. Get my activity");
		System.out.println("2. Get available domain");
		System.out.println("3. Get all items by id domain");
		System.out.println("4. Get all items");
		System.out.println("5. Add new activity");
		System.out.println("6. Delete my activity");
		System.out.println("7. Update my activity");
		System.out.println("8. Calculate suggestions");
		System.out.println("9. Get suggestions list");
		if (p.getIsAdmin() == 1) {
			System.out.println();
			System.out.println("Congratulations! You are an admin!");
			System.out.println("You can choose also this additional options");
			System.out.println("10. Add new domain");
			System.out.println("11. Add new item");
			System.out.println("12. Update domain");
			System.out.println("13. Update item");
			System.out.println("14. Delete domain");
			System.out.println("15. Delete item");
			System.out.println();
		}
		System.out.println("0. Exit");
		System.out.println();
		System.out.print("> ");
		
		int i = sc.nextInt();
		if (i > 9 && p.getIsAdmin() == 0) {
			i = -1;
		}
		
		switch (i) {
		case 1:
			getMyActivity();
			operations();
			break;
		case 2:
			getAvailableDomain();
			operations();
			break;
		case 3:
			getAllItemsByIdDomain();
			break;
		case 4:
			getAllItems();
			operations();
			break;
		case 5:
			addNewActivity();
			operations();
			break;
		case 6:
			deleteMyActivity();
			operations();
			break;
		case 7:
			updateMyActivity();
			operations();
			break;
		case 8:
			calculateSuggestion();
			operations();
			break;
		case 9:
			getSuggestion();
			operations();
			break;
		case 10:
			addNewDomain();
			operations();
			break;
		case 11:
			addNewItem();
			operations();
			break;
		case 12:
			updateDomain();
			operations();
			break;
		case 13:
			updateItem();
			operations();
			break;
		case 14:
			deleteDomain();
			operations();
			break;
		case 15:
			deleteItem();
			operations();
			break;
		case 0:
			exit();
			break;
		default:
			operations();
			break;
		}
	}
	
	public static void exit() {
		sc.close();
		System.exit(0);
	}

	public static void welcome() {
		System.out.println("WELCOME TO TRENTO ACTIVITY PREFERENCE");
		System.out.println();
		System.out.println("Select your choose: ");
		System.out.println();
		System.out.println("1. Init/Reset system");
		System.out.println("2. Login");
		System.out.println("3. Registration");
		System.out.println("0. Exit");
		System.out.println();
		System.out.print("> ");
	}

	public static void printInfoAndStart() throws IOException {
		System.out.println();
		System.out.println(String.format("Welcome %s thesere are your information: ", p.getFirstname()));
		System.out.println();
		System.out.println(String.format("idPerson: %d", p.getIdPerson()));
		System.out.println(String.format("Firstname: %s", p.getFirstname()));
		System.out.println(String.format("Lastname: %s", p.getLastname()));
		System.out.println(String.format("Birthdate: %s", p.getBirthdate()));
		//System.out.println(String.format("Admin: %d", p.getIsAdmin()));
		System.out.println(String.format("Work Hour Start: %s", p.getWorkHourStart()));
		System.out.println(String.format("Work Hour End: %s", p.getWorkHourEnd()));
		System.out.println(String.format("username: %s", p.getUsername()));
		System.out.println();
		operations();
	}
	
	public static void getInit() {
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/init";
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		System.out.println("Init status: " + resp.readEntity(String.class));
		System.out.println("Available users: ");
		System.out.println("username - password");
		System.out.println("paper - password123");
		System.out.println("pina - password123");
		System.out.println("pluto - password123");
		System.out.println();
	}
	
	public static void login() throws IOException {
		p = new Person();
		System.out.println();
		System.out.println("LOGIN ");
		System.out.println();
		System.out.print("username: ");
		String username = sc.next();
		p.setUsername(username);

		System.out.print("password: ");
		String password = sc.next();
		p.setPassword(password);

		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());

		String request = "/general/login";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(p, content));
		p = resp.readEntity(Person.class);
		
		if (p != null) {
			printInfoAndStart();
		}
		else {
			System.out.println("Username or password are not correct.");
			System.out.println();
			init();
		}
	}

	public static void registration() throws IOException {
		System.out.println();
		System.out.println("REGISTRATION ");
		System.out.println();
		System.out.println(String.format("Input in the following form your personal information"));
		System.out.println();
		p = new Person();
		System.out.print(String.format("Firstname: "));
		String firstname = sc.next();
		p.setFirstname(firstname);
		System.out.print(String.format("Lastname: "));
		String lastname = sc.next();
		p.setLastname(lastname);
		System.out.print(String.format("Birthdate: "));
		String birhdate = sc.next();
		p.setBirthdate(birhdate);
		System.out.print(String.format("Work Hour Start: "));
		String workHourStart = sc.next();
		p.setWorkHourStart(workHourStart);
		System.out.print(String.format("Work Hour End: "));
		String workHourEnd = sc.next();
		p.setWorkHourEnd(workHourEnd);
		System.out.print(String.format("username: "));
		String username = sc.next();
		p.setUsername(username);
		System.out.print(String.format("password: "));
		String password = sc.next();
		p.setPassword(password);
		p.setIsAdmin(0);
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = "/general/person";
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).post(Entity.entity(p, content));
		p = resp.readEntity(Person.class);
		
		System.out.println();
		
		printInfoAndStart();
	}
	
	public static ArrayList<Activity> getMyActivity() throws IOException {
		System.out.println();
		System.out.println("MY ACTIVITY");
		System.out.println();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/person/%d/activity", p.getIdPerson());
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Activity> arrayItem = new ArrayList<>();
		for (int i=0; i < nodes.size(); i++) {
			Activity a = mapper.readValue(nodes.get(i).toString(), Activity.class);
			arrayItem.add(a);
			
			System.out.println("--- Item Information ---");
			System.out.println(String.format("Item name: %s", a.getInfoItem().getName()));
			System.out.println(String.format("Item date: %s", a.getInfoItem().getDate()));
			System.out.println(String.format("Item description: %s", a.getInfoItem().getDescription()));
			System.out.println(String.format("Item hour start: %s", a.getInfoItem().getHourStart()));
			System.out.println(String.format("Item hour end: %s", a.getInfoItem().getHourEnd()));
			System.out.println("--- Personal Information ---");
			System.out.println(String.format("Activity id: %d", a.getIdActivity()));
			System.out.println(String.format("Activity done on date: %s", a.getDate()));
			System.out.println(String.format("Activity evaluation: %d", a.getEvaluation()));
			System.out.println();
		}
		return arrayItem;
	}
	
	public static void getAvailableDomain() throws IOException {
		System.out.println();
		System.out.println("DOMAINS");
		System.out.println();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain");
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		for (int i=0; i < nodes.size(); i++) {
			Domain d = mapper.readValue(nodes.get(i).toString(), Domain.class);
			
			System.out.println("--- Domain Information ---");
			System.out.println(String.format("Domain id: %d", d.getIdDomain()));
			System.out.println(String.format("Domain name: %s", d.getName()));
			System.out.println();
		}
	}
	
	public static ArrayList<Item> getAllItems() throws IOException {
		System.out.println();
		System.out.println("ITEMS");
		System.out.println();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item");
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		ArrayList<Item> arrayItem = new ArrayList<Item>();
		
		for (int i=0; i < nodes.size(); i++) {
			Item d = mapper.readValue(nodes.get(i).toString(), Item.class);
			arrayItem.add(d);
			
			System.out.println("--- Item Information ---");
			System.out.println(String.format("Item id: %d", d.getIdItem()));
			System.out.println(String.format("Item domain id: %d", d.getIdDomain()));
			System.out.println(String.format("Item name: %s", d.getName()));
			System.out.println(String.format("Item description: %s", d.getDescription()));
			System.out.println(String.format("Item date: %s", d.getDate()));
			System.out.println(String.format("Item hour start: %s", d.getHourStart()));
			System.out.println(String.format("Item hour end: %s", d.getHourEnd()));
			System.out.println();
		}
		return arrayItem;
	}
	
	public static void getAllItemsByIdDomain() throws IOException {
		System.out.println();
		System.out.println("ITEMS");
		getAvailableDomain();
		System.out.println("Select your domain: ");
		int s = sc.nextInt();
		System.out.println();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item");
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);
		
		for (int i=0; i < nodes.size(); i++) {
			Item d = mapper.readValue(nodes.get(i).toString(), Item.class);
			if (d.getIdDomain() == s) {
				System.out.println("--- Item Information ---");
				System.out.println(String.format("Item id: %d", d.getIdItem()));
				System.out.println(String.format("Item domain id: %d", d.getIdDomain()));
				System.out.println(String.format("Item name: %s", d.getName()));
				System.out.println(String.format("Item description: %s", d.getDescription()));
				System.out.println(String.format("Item date: %s", d.getDate()));
				System.out.println(String.format("Item hour start: %s", d.getHourStart()));
				System.out.println(String.format("Item hour end: %s", d.getHourEnd()));
				System.out.println();
			}
		}
		operations();
	}
	
	public static void addNewActivity() throws IOException {
		System.out.println();
		System.out.println("ADD MY ACTIVITY");
		getAllItems();
		System.out.print("Id Item: ");
		int idItem = sc.nextInt();
		System.out.print("Date: ");
		String date = sc.next();
		System.out.print("Evaluation: ");
		int evaluation = sc.nextInt();
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/activity/");
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		String body = "{" + 
				"\"idPerson\":" + p.getIdPerson() + ", " + 
				"\"idItem\":" + idItem + ", " + 
				"\"date\": \"" + date + "\", " +  
				"\"evaluation\":" + evaluation +
				"}";
		
		resp = service.path(request).request().accept(type).post(Entity.entity(body, content));
		System.out.print("Activity succesfully added!");
	}
	
	public static void deleteMyActivity() throws IOException {
		System.out.println();
		System.out.println("DELETE MY ACTIVITY");
		getMyActivity();
		System.out.println("Select activity to delete: ");
		int s = sc.nextInt();
		System.out.println();
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/activity/%d", s);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
	}
	
	public static void updateMyActivity() throws IOException {
		System.out.println();
		System.out.println("UPDATE MY ACTIVITY");
		ArrayList<Activity> activity = getMyActivity();
		System.out.println("Id activity to update: ");
		int id = sc.nextInt();
		System.out.println("Update date: ");
		String date = sc.next();
		System.out.println("Update evaluation: ");
		int evaluation = sc.nextInt();
		Activity activityUpdate = new Activity();
		for(Activity a : activity) {
			if(a.getIdActivity() == id) {
				activityUpdate.setIdActivity(id);
				activityUpdate.setIdPerson(a.getIdPerson());
				activityUpdate.setIdItem(a.getIdItem());
				activityUpdate.setDate(date);
				activityUpdate.setEvaluation(evaluation);
				break;
			}
		}
		
		if (activityUpdate != null) {		
			Response resp;
			ObjectMapper mapper = new ObjectMapper();
			ClientConfig clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);
			WebTarget service = client.target(getBaseURI());
			
			String request = String.format("/general/activity/%d", activityUpdate.getIdActivity());
			String type = MediaType.APPLICATION_JSON;
			String content = MediaType.APPLICATION_JSON;
			String body = "{" + 
					"\"idActivity\":" + activityUpdate.getIdActivity() + ", " + 
					"\"idPerson\":" + activityUpdate.getIdPerson() + ", " + 
					"\"idItem\":" + activityUpdate.getIdItem() + ", " + 
					"\"date\": \"" + activityUpdate.getDate() + "\", " +  
					"\"evaluation\":" + activityUpdate.getEvaluation() +
					"}";
			
			resp = service.path(request).request().accept(type).put(Entity.entity(body, content));
		}
		else {
			System.out.println("Activity id not valid");
		}
	}
	
	public static void calculateSuggestion() throws IOException {
		ArrayList<Item> arrayItem = getListItem();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/person/" + p.getIdPerson() + "/calculate-suggestion");
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();

		Suggestion s = resp.readEntity(Suggestion.class);
		for (Item d : arrayItem) {
			if (s.getIdItem() == d.getIdItem()) {
				System.out.println("SUGGESTION");
				System.out.println(String.format("Item id: %d", d.getIdItem()));
				System.out.println(String.format("Item domain id: %d", d.getIdDomain()));
				System.out.println(String.format("Item name: %s", d.getName()));
				System.out.println(String.format("Item description: %s", d.getDescription()));
				System.out.println(String.format("Item date: %s", d.getDate()));
				System.out.println(String.format("Item hour start: %s", d.getHourStart()));
				System.out.println(String.format("Item hour end: %s", d.getHourEnd()));
				System.out.println();
				System.out.print("How do you evaluate this suggestion ? ");
				int evaluation = sc.nextInt();
				s.setEvaluation(evaluation);
				suggestionEvaluation(s);
				return;
			}
		}
	}
	
	public static void suggestionEvaluation(Suggestion s) {
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/suggestion/");
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).post(Entity.entity(s, content));
		if (resp != null) {
			System.out.println("Suggestion succesfully evaluated!");
		}
		else {
			System.out.println("Whoops, somenthing went wrong");
		}
	}
	
	public static void getSuggestion() throws IOException {
		ArrayList<Item> arrayItem = getListItem();
		
		Response resp, itemResp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/person/" + p.getIdPerson() + "/suggestion");
		String type = MediaType.APPLICATION_JSON;

		resp = service.path(request).request().accept(type).get();
		
		String json = resp.readEntity(String.class);
		JsonNode nodes = mapper.readTree(json);

		for (int i=0; i < nodes.size(); i++) {
			Suggestion s = mapper.readValue(nodes.get(i).toString(), Suggestion.class);
			for (Item d : arrayItem) {
				if (s.getIdItem() == d.getIdItem()) {
					System.out.println("--- Suggestion Item ---");
					System.out.println(String.format("Suggestion id: %d", s.getIdSuggestion()));
					System.out.println(String.format("Suggestion date added: %s", s.getDateAdded()));
					System.out.println(String.format("Suggestion personal evaluation: %d", s.getEvaluation()));
					System.out.println(String.format("Item id: %d", d.getIdItem()));
					System.out.println(String.format("Item domain id: %d", d.getIdDomain()));
					System.out.println(String.format("Item name: %s", d.getName()));
					System.out.println(String.format("Item description: %s", d.getDescription()));
					System.out.println(String.format("Item date: %s", d.getDate()));
					System.out.println(String.format("Item hour start: %s", d.getHourStart()));
					System.out.println(String.format("Item hour end: %s", d.getHourEnd()));
					System.out.println();
				}
			}
		}
	}

	/* Admin */
	
	public static void addNewDomain() {
		System.out.println();
		System.out.println("ADD NEW DOMAIN");
		System.out.print("Domain name: ");
		Domain d = new Domain();
		String name = sc.next();
		d.setName(name);
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain/");
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).post(Entity.entity(d, content));
		if (resp != null) {
			System.out.print("Domain succesfully added!");
		}
		else {
			System.out.print("Whoops, somenthing went wrong");
		}
	}
	
	public static void addNewItem() throws IOException {
		System.out.println();
		System.out.println("ADD NEW ITEM");
		getAvailableDomain();
		Item i = new Item();
		System.out.println();
		System.out.println("Name: ");
		String name = sc.next();
		i.setName(name);
		System.out.println("Description: ");
		String description = sc.next(); 
		i.setDescription(description);
		System.out.println("Id Domain: ");
		int idDomain = sc.nextInt();
		i.setIdDomain(idDomain);
		System.out.println("Date: ");
		String date = sc.next();
		i.setDate(date);
		System.out.println("Hour Start: ");
		String hourStart = sc.next();
		i.setHourStart(hourStart);
		System.out.println("Hour End: ");
		String hourEnd = sc.next();
		i.setHourEnd(hourEnd);
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item/");
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).post(Entity.entity(i, content));
		if (resp != null) {
			System.out.println("Item succesfully added!");
		}
		else {
			System.out.println("Whoops, somenthing went wrong");
		}
	}
	
	public static void updateDomain() throws IOException {
		System.out.println();
		System.out.println("UPDATE DOMAIN");
		getAvailableDomain();
		Domain d = new Domain();
		System.out.print("Domain id:");
		int id = sc.nextInt();
		d.setIdDomain(id);
		System.out.print("Domain name: ");
		String name = sc.next();
		d.setName(name);
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain/" + id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).put(Entity.entity(d, content));
		if (resp != null) {
			System.out.println("Domain succesfully updated!");
		}
		else {
			System.out.println("Whoops, somenthing went wrong");
		}
	}
	
	public static void updateItem() throws IOException {
		System.out.println();
		System.out.println("UPDATE ITEM");
		getAllItems();
		Item i = new Item();
		System.out.println();
		System.out.print("Id Item: ");
		int id = sc.nextInt();
		i.setIdItem(id);
		System.out.print("Name: ");
		String name = sc.next();
		i.setName(name);
		System.out.print("Description: ");
		String description = sc.next(); 
		i.setDescription(description);
		System.out.print("Id Domain: ");
		int idDomain = sc.nextInt();
		i.setIdDomain(idDomain);
		System.out.print("Date: ");
		String date = sc.next();
		i.setDate(date);
		System.out.print("Hour Start: ");
		String hourStart = sc.next();
		i.setHourStart(hourStart);
		System.out.print("Hour End: ");
		String hourEnd = sc.next();
		i.setHourEnd(hourEnd);
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item/" + id);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).put(Entity.entity(i, content));
		if (resp != null) {
			System.out.println("Item succesfully updated!");
		}
		else {
			System.out.println("Whoops, somenthing went wrong");
		}
	}
	
	public static void deleteDomain() throws IOException {
		System.out.println();
		System.out.println("DELETE DOMAIN");
		getAvailableDomain();
		System.out.println("Select id domain to delete: ");
		int s = sc.nextInt();
		System.out.println();
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/domain/%d", s);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
		System.out.println("Domain succesfully deleted!");
	}
	
	public static void deleteItem() throws IOException {
		System.out.println();
		System.out.println("DELETE ITEM");
		getAllItems();
		System.out.println("Select id item to delete: ");
		int s = sc.nextInt();
		System.out.println();
		
		Response resp;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		
		String request = String.format("/general/item/%d", s);
		String type = MediaType.APPLICATION_JSON;
		String content = MediaType.APPLICATION_JSON;
		
		resp = service.path(request).request().accept(type).delete();
		System.out.println("Item succesfully deleted!");
	}
}

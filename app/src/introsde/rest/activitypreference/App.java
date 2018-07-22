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

public class App {

	static Person p = new Person();
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

	/**
	 * Print utility
	 * @throws IOException 
	 */

	public static void init() throws IOException {
		welcome();
		int i = sc.nextInt();
		switch (i) {
		case 1:
			login();
			break;
		case 2:
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
		System.out.println("8. Get suggestions");
		if (p.getIsAdmin() == 1) {
			System.out.println();
			System.out.println("Congratulations! You are an admin!");
			System.out.println("You can choose also this additional options");
			System.out.println("10. Add new item");
			System.out.println("11. Add new domain");
			System.out.println("12. Update item");
			System.out.println("13. Update domain");
			System.out.println("14. Delete item");
			System.out.println("15. Delete domain");
			System.out.println();
		}
		System.out.println("0. Exit");
		System.out.println();
		System.out.print("> ");
		
		int i = sc.nextInt();
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
			//getSuggestions();
			break;
		case 10:
			addNewItem();
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
		System.out.println("1. Login");
		System.out.println("2. Registration");
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
	
	public static void login() throws IOException {
		System.out.println();
		System.out.println("LOGIN ");
		System.out.println();
		System.out.print("username: ");
		String username = sc.next();
		p.setUsername(username);

		System.out.print("username: ");
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
		System.out.print(String.format("Firstname: "));
		p.setFirstname(sc.next());
		System.out.print(String.format("Lastname: "));
		p.setLastname(sc.next());
		System.out.print(String.format("Birthdate: "));
		p.setBirthdate(sc.next());
		System.out.print(String.format("Work Hour Start: "));
		p.setWorkHourStart(sc.next());
		System.out.print(String.format("Work Hour End: "));
		p.setWorkHourEnd(sc.next());
		System.out.print(String.format("username: "));
		p.setUsername(sc.next());
		System.out.print(String.format("password: "));
		p.setPassword(sc.next());
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
	
	public static void getAllItems() throws IOException {
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
		
		for (int i=0; i < nodes.size(); i++) {
			Item d = mapper.readValue(nodes.get(i).toString(), Item.class);
			
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

	/* Admin */
	
	public static void addNewItem() {
		
	}
	
}

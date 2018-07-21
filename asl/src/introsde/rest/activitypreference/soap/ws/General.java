package introsde.rest.activitypreference.soap.ws;

import java.io.IOException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import introsde.rest.activitypreference.model.Suggestion;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface General {
		
	//Method #1
	@WebMethod(operationName="getMySuggestion")
	@WebResult(name="suggestion") 
	public List<Suggestion> getMySuggestion() throws JsonParseException, JsonMappingException, IOException;
	
	//Method #2
	//Auto Add
	@WebMethod(operationName="calculateSuggestion")
	@WebResult(name="suggestion") 
	public Suggestion calculateSuggestion(@WebParam(name="personId") int id) throws IOException;

	//Method #3
	@WebMethod(operationName="postSuggestion")
	@WebResult(name="suggestion") 
	public Suggestion postSuggestion(@WebParam(name="suggestion") Suggestion suggestion);
	
	//Method #3
	//@WebMethod(operationName="getMostPerformedActivity")
	//@WebResult(name="suggestion") 
	//public Suggestion getMostPerformedActivity();
	
	//Method #4
	//@WebMethod(operationName="getMostRankedActivity")
	//@WebResult(name="suggestion") 
	//public Suggestion getMostRankedActivity();
}

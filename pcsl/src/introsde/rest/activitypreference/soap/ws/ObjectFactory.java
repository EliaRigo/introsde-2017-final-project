
package introsde.rest.activitypreference.soap.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the introsde.rest.activitypreference.soap.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMySuggestion_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "getMySuggestion");
    private final static QName _CalculateSuggestion_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "calculateSuggestion");
    private final static QName _JsonMappingException_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "JsonMappingException");
    private final static QName _Suggestion_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "suggestion");
    private final static QName _IOException_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "IOException");
    private final static QName _GetMySuggestionResponse_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "getMySuggestionResponse");
    private final static QName _PostSuggestionResponse_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "postSuggestionResponse");
    private final static QName _PostSuggestion_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "postSuggestion");
    private final static QName _CalculateSuggestionResponse_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "calculateSuggestionResponse");
    private final static QName _JsonParseException_QNAME = new QName("http://ws.soap.activitypreference.rest.introsde/", "JsonParseException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: introsde.rest.activitypreference.soap.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateSuggestion }
     * 
     */
    public CalculateSuggestion createCalculateSuggestion() {
        return new CalculateSuggestion();
    }

    /**
     * Create an instance of {@link GetMySuggestion }
     * 
     */
    public GetMySuggestion createGetMySuggestion() {
        return new GetMySuggestion();
    }

    /**
     * Create an instance of {@link JsonMappingException }
     * 
     */
    public JsonMappingException createJsonMappingException() {
        return new JsonMappingException();
    }

    /**
     * Create an instance of {@link Suggestion }
     * 
     */
    public Suggestion createSuggestion() {
        return new Suggestion();
    }

    /**
     * Create an instance of {@link PostSuggestionResponse }
     * 
     */
    public PostSuggestionResponse createPostSuggestionResponse() {
        return new PostSuggestionResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link GetMySuggestionResponse }
     * 
     */
    public GetMySuggestionResponse createGetMySuggestionResponse() {
        return new GetMySuggestionResponse();
    }

    /**
     * Create an instance of {@link JsonParseException }
     * 
     */
    public JsonParseException createJsonParseException() {
        return new JsonParseException();
    }

    /**
     * Create an instance of {@link CalculateSuggestionResponse }
     * 
     */
    public CalculateSuggestionResponse createCalculateSuggestionResponse() {
        return new CalculateSuggestionResponse();
    }

    /**
     * Create an instance of {@link PostSuggestion }
     * 
     */
    public PostSuggestion createPostSuggestion() {
        return new PostSuggestion();
    }

    /**
     * Create an instance of {@link RequestPayload }
     * 
     */
    public RequestPayload createRequestPayload() {
        return new RequestPayload();
    }

    /**
     * Create an instance of {@link JsonLocation }
     * 
     */
    public JsonLocation createJsonLocation() {
        return new JsonLocation();
    }

    /**
     * Create an instance of {@link Reference }
     * 
     */
    public Reference createReference() {
        return new Reference();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMySuggestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "getMySuggestion")
    public JAXBElement<GetMySuggestion> createGetMySuggestion(GetMySuggestion value) {
        return new JAXBElement<GetMySuggestion>(_GetMySuggestion_QNAME, GetMySuggestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSuggestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "calculateSuggestion")
    public JAXBElement<CalculateSuggestion> createCalculateSuggestion(CalculateSuggestion value) {
        return new JAXBElement<CalculateSuggestion>(_CalculateSuggestion_QNAME, CalculateSuggestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JsonMappingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "JsonMappingException")
    public JAXBElement<JsonMappingException> createJsonMappingException(JsonMappingException value) {
        return new JAXBElement<JsonMappingException>(_JsonMappingException_QNAME, JsonMappingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Suggestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "suggestion")
    public JAXBElement<Suggestion> createSuggestion(Suggestion value) {
        return new JAXBElement<Suggestion>(_Suggestion_QNAME, Suggestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMySuggestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "getMySuggestionResponse")
    public JAXBElement<GetMySuggestionResponse> createGetMySuggestionResponse(GetMySuggestionResponse value) {
        return new JAXBElement<GetMySuggestionResponse>(_GetMySuggestionResponse_QNAME, GetMySuggestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostSuggestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "postSuggestionResponse")
    public JAXBElement<PostSuggestionResponse> createPostSuggestionResponse(PostSuggestionResponse value) {
        return new JAXBElement<PostSuggestionResponse>(_PostSuggestionResponse_QNAME, PostSuggestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostSuggestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "postSuggestion")
    public JAXBElement<PostSuggestion> createPostSuggestion(PostSuggestion value) {
        return new JAXBElement<PostSuggestion>(_PostSuggestion_QNAME, PostSuggestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSuggestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "calculateSuggestionResponse")
    public JAXBElement<CalculateSuggestionResponse> createCalculateSuggestionResponse(CalculateSuggestionResponse value) {
        return new JAXBElement<CalculateSuggestionResponse>(_CalculateSuggestionResponse_QNAME, CalculateSuggestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JsonParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.soap.activitypreference.rest.introsde/", name = "JsonParseException")
    public JAXBElement<JsonParseException> createJsonParseException(JsonParseException value) {
        return new JAXBElement<JsonParseException>(_JsonParseException_QNAME, JsonParseException.class, null, value);
    }

}


package introsde.rest.activitypreference.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per JsonParseException complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="JsonParseException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://ws.soap.activitypreference.rest.introsde/}jsonLocation" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processor" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="requestPayload" type="{http://ws.soap.activitypreference.rest.introsde/}requestPayload" minOccurs="0"/>
 *         &lt;element name="requestPayloadAsString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JsonParseException", propOrder = {
    "location",
    "message",
    "originalMessage",
    "processor",
    "requestPayload",
    "requestPayloadAsString"
})
public class JsonParseException {

    protected JsonLocation location;
    protected String message;
    protected String originalMessage;
    protected Object processor;
    protected RequestPayload requestPayload;
    protected String requestPayloadAsString;

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link JsonLocation }
     *     
     */
    public JsonLocation getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link JsonLocation }
     *     
     */
    public void setLocation(JsonLocation value) {
        this.location = value;
    }

    /**
     * Recupera il valore della proprietà message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Imposta il valore della proprietà message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Recupera il valore della proprietà originalMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalMessage() {
        return originalMessage;
    }

    /**
     * Imposta il valore della proprietà originalMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalMessage(String value) {
        this.originalMessage = value;
    }

    /**
     * Recupera il valore della proprietà processor.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getProcessor() {
        return processor;
    }

    /**
     * Imposta il valore della proprietà processor.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setProcessor(Object value) {
        this.processor = value;
    }

    /**
     * Recupera il valore della proprietà requestPayload.
     * 
     * @return
     *     possible object is
     *     {@link RequestPayload }
     *     
     */
    public RequestPayload getRequestPayload() {
        return requestPayload;
    }

    /**
     * Imposta il valore della proprietà requestPayload.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestPayload }
     *     
     */
    public void setRequestPayload(RequestPayload value) {
        this.requestPayload = value;
    }

    /**
     * Recupera il valore della proprietà requestPayloadAsString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestPayloadAsString() {
        return requestPayloadAsString;
    }

    /**
     * Imposta il valore della proprietà requestPayloadAsString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestPayloadAsString(String value) {
        this.requestPayloadAsString = value;
    }

}

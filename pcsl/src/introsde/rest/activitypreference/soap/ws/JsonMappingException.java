
package introsde.rest.activitypreference.soap.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per JsonMappingException complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="JsonMappingException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://ws.soap.activitypreference.rest.introsde/}jsonLocation" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="path" type="{http://ws.soap.activitypreference.rest.introsde/}reference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pathReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processor" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JsonMappingException", propOrder = {
    "location",
    "message",
    "originalMessage",
    "path",
    "pathReference",
    "processor"
})
public class JsonMappingException {

    protected JsonLocation location;
    protected String message;
    protected String originalMessage;
    @XmlElement(nillable = true)
    protected List<Reference> path;
    protected String pathReference;
    protected Object processor;

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
     * Gets the value of the path property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the path property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPath().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     * 
     * 
     */
    public List<Reference> getPath() {
        if (path == null) {
            path = new ArrayList<Reference>();
        }
        return this.path;
    }

    /**
     * Recupera il valore della proprietà pathReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathReference() {
        return pathReference;
    }

    /**
     * Imposta il valore della proprietà pathReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathReference(String value) {
        this.pathReference = value;
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

}

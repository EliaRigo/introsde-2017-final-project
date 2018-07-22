
package introsde.rest.activitypreference.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per suggestion complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="suggestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="evaluation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idItem" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPerson" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idSuggestion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "suggestion", propOrder = {
    "dateAdded",
    "evaluation",
    "idItem",
    "idPerson",
    "idSuggestion"
})
public class Suggestion {

    protected String dateAdded;
    protected int evaluation;
    protected int idItem;
    protected int idPerson;
    protected int idSuggestion;

    /**
     * Recupera il valore della proprietà dateAdded.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * Imposta il valore della proprietà dateAdded.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateAdded(String value) {
        this.dateAdded = value;
    }

    /**
     * Recupera il valore della proprietà evaluation.
     * 
     */
    public int getEvaluation() {
        return evaluation;
    }

    /**
     * Imposta il valore della proprietà evaluation.
     * 
     */
    public void setEvaluation(int value) {
        this.evaluation = value;
    }

    /**
     * Recupera il valore della proprietà idItem.
     * 
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * Imposta il valore della proprietà idItem.
     * 
     */
    public void setIdItem(int value) {
        this.idItem = value;
    }

    /**
     * Recupera il valore della proprietà idPerson.
     * 
     */
    public int getIdPerson() {
        return idPerson;
    }

    /**
     * Imposta il valore della proprietà idPerson.
     * 
     */
    public void setIdPerson(int value) {
        this.idPerson = value;
    }

    /**
     * Recupera il valore della proprietà idSuggestion.
     * 
     */
    public int getIdSuggestion() {
        return idSuggestion;
    }

    /**
     * Imposta il valore della proprietà idSuggestion.
     * 
     */
    public void setIdSuggestion(int value) {
        this.idSuggestion = value;
    }

}

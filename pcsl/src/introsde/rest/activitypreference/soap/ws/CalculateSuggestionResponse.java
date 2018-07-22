
package introsde.rest.activitypreference.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per calculateSuggestionResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="calculateSuggestionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="suggestion" type="{http://ws.soap.activitypreference.rest.introsde/}suggestion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateSuggestionResponse", propOrder = {
    "suggestion"
})
public class CalculateSuggestionResponse {

    protected Suggestion suggestion;

    /**
     * Recupera il valore della proprietà suggestion.
     * 
     * @return
     *     possible object is
     *     {@link Suggestion }
     *     
     */
    public Suggestion getSuggestion() {
        return suggestion;
    }

    /**
     * Imposta il valore della proprietà suggestion.
     * 
     * @param value
     *     allowed object is
     *     {@link Suggestion }
     *     
     */
    public void setSuggestion(Suggestion value) {
        this.suggestion = value;
    }

}

package introsde.rest.activitypreference.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import introsde.rest.activitypreference.dao.ActivityPreferenceDao;

/**
 * Persistent class of table "Item"
 */
@Entity
@Table(name = "\"Suggestion\"")
@NamedQuery(name = "Suggestion.findAll", query = "SELECT s FROM Suggestion s")
@XmlRootElement
public class Suggestion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "sqlite_item")
	// @TableGenerator(name="sqlite_sugestion", table="sqlite_sequence",
	// pkColumnName="name", valueColumnName="seq",
	// pkColumnValue="Suggestion")
	@Column(name = "\"idSuggestion\"")
	private int idSuggestion;
	
	@Column(name = "\"idPerson\"")
	private int idPerson;
	
	@Column(name = "\"idItem\"")
	private int idItem;
	
	@Column(name = "\"dateAdded\"")
	private String dateAdded;
	
	@Column(name = "\"evaluation\"")
	private int evaluation;

	/* Follow getter and setter for every attribute of this class */
	
	public int getIdSuggestion() {
		return idSuggestion;
	}

	public void setIdSuggestion(int idSuggestion) {
		this.idSuggestion = idSuggestion;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	/**
	 * Get all Suggestion
	 * 
	 * @return List of Suggestion
	 */
	public static List<Suggestion> getAll() {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		List<Suggestion> list = em.createNamedQuery("Suggestion.findAll", Suggestion.class).getResultList();
		ActivityPreferenceDao.instance.closeConnections(em);
		return list;
	}
}

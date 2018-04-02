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
@Table(name = "\"Item\"")
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
@XmlType(propOrder = {  "idItem", "name", "description", "date", "hourStart", "hourEnd", "idDomain"})
@XmlRootElement
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_item")
	// @TableGenerator(name="sqlite_person", table="sqlite_sequence",
	// pkColumnName="name", valueColumnName="seq",
	// pkColumnValue="Item")
	@Column(name = "\"idItem\"")
	private int idItem;
	
	@Column(name = "\"name\"")
	private String name;
	
	@Column(name = "\"description\"")
	private String description;
	
	@Column(name = "\"date\"")
	private String date;
	
	@Column(name = "\"hourStart\"")
	private String hourStart;
	
	@Column(name = "\"hourEnd\"")
	private String hourEnd;
	
	@Column(name = "\"idDomain\"")
	private int idDomain;
	
	public Item() {
		
	}
	
	/* Follow getter and setter for every attribute of this class */
	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHourStart() {
		return hourStart;
	}

	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}

	public String getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(String hourEnd) {
		this.hourEnd = hourEnd;
	}
	
	public int getIdDomain() {
		return idDomain;
	}

	public void setIdDomain(int idDomain) {
		this.idDomain = idDomain;
	}
	
	/**
	 * Get all Item
	 * 
	 * @return List of Item
	 */
	public static List<Item> getAll() {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		List<Item> list = em.createNamedQuery("Item.findAll", Item.class).getResultList();
		ActivityPreferenceDao.instance.closeConnections(em);
		return list;
	}
}

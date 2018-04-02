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
@Table(name = "\"Domain\"")
@NamedQuery(name = "Domain.findAll", query = "SELECT d FROM Domain d")
@XmlRootElement
public class Domain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "sqlite_item")
	// @TableGenerator(name="sqlite_domain", table="sqlite_sequence",
	// pkColumnName="name", valueColumnName="seq",
	// pkColumnValue="Item")
	@Column(name = "\"idDomain\"")
	private int idDomain;

	@Column(name = "\"name\"")
	private String name;
	
	/* Follow getter and setter for every attribute of this class */
	public int getIdDomain() {
		return idDomain;
	}

	public void setIdDomain(int idDomain) {
		this.idDomain = idDomain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get all Domain
	 * 
	 * @return List of Domain
	 */
	public static List<Domain> getAll() {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		List<Domain> list = em.createNamedQuery("Domain.findAll", Domain.class).getResultList();
		ActivityPreferenceDao.instance.closeConnections(em);
		return list;
	}
	
	/**
	 * Get single Domain by IdDomain
	 * @param id Integer IdDomain
	 * @return Single Domain
	 */
	public static Domain getDomainById(int id) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		Domain d = em.find(Domain.class, id);
		ActivityPreferenceDao.instance.closeConnections(em);
		return d;
	}
	
	/**
	 * Post new Domain
	 * 
	 * @param domain
	 *            Activity new Activity
	 * @return Single Domain
	 */
	public static Domain postDomain(Domain domain) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(domain);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
		return domain;
	}

	/**
	 * Update Domain
	 * 
	 * @param activity
	 *            Activity updated Activity
	 */
	public static void updateDomain(Domain domain) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(domain);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
	}
	
	/**
	 * Remove Domain
	 * 
	 * @param d Domain to be deleted
	 */
	public static void removeDomain(Domain d) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		d = em.merge(d);
		em.remove(d);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
	}
}

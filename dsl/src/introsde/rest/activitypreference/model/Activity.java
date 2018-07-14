package introsde.rest.activitypreference.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.rest.activitypreference.dao.ActivityPreferenceDao;

@Entity
@Table(name = "\"Activity\"")
// NameQueries used in this class
@NamedQueries({ @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")})
@XmlRootElement
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_activity")
	// @TableGenerator(name="sqlite_activity", table="sqlite_sequence",
	// pkColumnName="name", valueColumnName="seq",
	// pkColumnValue="Activity")
	@Column(name = "\"idActivity\"")
	private int idActivity;

	@Column(name = "\"idPerson\"")
	private int idPerson;

	@Column(name = "\"idItem\"")
	private int idItem;

	@Column(name = "\"date\"")
	private String date;

	@Column(name = "\"evaluation\"")
	private int evaluation;

	public Activity() {

	}

	/* Follow getter and setter for every attribute of this class */

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	/* Follow class methods */

	/**
	 * Get all Activity
	 * 
	 * @return List of Activity
	 */
	public static List<Activity> getAll() {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class).getResultList();
		ActivityPreferenceDao.instance.closeConnections(em);
		return list;
	}

	/**
	 * Get single Activity by IdActivity
	 * @param id Integer IdActivity
	 * @return Single Activity
	 */
	public static Activity getActivityById(int id) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		Activity a = em.find(Activity.class, id);
		ActivityPreferenceDao.instance.closeConnections(em);
		return a;
	}
	
	/**
	 * Post new Activity
	 * 
	 * @param activity
	 *            Activity new Activity
	 * @param idPerson
	 *            Integer IdPerson
	 * @param activityType
	 *            ActivityType activityType
	 * @return Single Activity
	 */
	public static Activity postActivity(Activity activity) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(activity);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
		return activity;
	}

	/**
	 * Update Activity
	 * 
	 * @param activity
	 *            Activity updated Activity
	 */
	public static void updateActivity(Activity activity) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(activity);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
	}
	
	/**
	 * Remove Item
	 * 
	 * @param d Item to be deleted
	 */
	public static void removeActivity(Activity a) {
		EntityManager em = ActivityPreferenceDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		a = em.merge(a);
		em.remove(a);
		tx.commit();
		ActivityPreferenceDao.instance.closeConnections(em);
	}
}

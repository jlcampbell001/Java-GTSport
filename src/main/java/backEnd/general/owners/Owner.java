package backEnd.general.owners;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Owners")
public class Owner {

	@Id
	@Column(name = "OwnKey")
	private String primaryKey = "";
	
	@Column(name = "OwnName", unique = true)
	private String ownerName = "";
	
	@Column(name = "OwnDefault")
	private Boolean defaultOwner = false;

	/**
	 * @param primaryKey
	 * @param ownerName
	 * @param defaultOwner
	 * Create an owner based on the passed parameter values.
	 */
	public Owner(String primaryKey, String ownerName, Boolean defaultOwner) {
		this.primaryKey = primaryKey;
		this.ownerName = ownerName;
		this.defaultOwner = defaultOwner;
	}

	/**
	 * Create a owner with default values. 
	 */
	public Owner() {
		this("", "", false);
	}
	
	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the defaultOwner
	 */
	public Boolean getDefaultOwner() {
		return defaultOwner;
	}

	/**
	 * @param defaultOwner the currentOwner to set
	 */
	public void setDefaultOwner(Boolean defaultOwner) {
		this.defaultOwner = defaultOwner;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Owner [primaryKey=" + primaryKey + ", ownerName=" + ownerName + ", defaultOwner=" + defaultOwner + "]";
	}

}

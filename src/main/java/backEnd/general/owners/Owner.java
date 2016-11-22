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
	
	@Column(name = "OwnCurrent")
	private Boolean currentOwner = false;

	/**
	 * @param primaryKey
	 * @param ownerName
	 * @param currentOwner
	 * Create an owner based on the passed parameter values.
	 */
	public Owner(String primaryKey, String ownerName, Boolean currentOwner) {
		this.primaryKey = primaryKey;
		this.ownerName = ownerName;
		this.currentOwner = currentOwner;
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
	 * @return the currentOwner
	 */
	public Boolean getCurrentOwner() {
		return currentOwner;
	}

	/**
	 * @param currentOwner the currentOwner to set
	 */
	public void setCurrentOwner(Boolean currentOwner) {
		this.currentOwner = currentOwner;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Owner [primaryKey=" + primaryKey + ", ownerName=" + ownerName + ", currentOwner=" + currentOwner + "]";
	}

}

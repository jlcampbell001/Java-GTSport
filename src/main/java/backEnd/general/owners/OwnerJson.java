package backEnd.general.owners;

/**
 * Json for an owner record.
 * 
 * @author Jonathan
 *
 */
public class OwnerJson {

	private String primaryKey = "";
	private String ownerName = "";
	private Boolean Current = false;

	/**
	 * Get the primary key.
	 * 
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Set the primary key.
	 * 
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Get the owner name.
	 * 
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * Set the owner name.
	 * 
	 * @param ownerName
	 *            the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * Get the owner current setting.
	 * 
	 * @return the current
	 */
	public Boolean getCurrent() {
		return Current;
	}

	/**
	 * Set the owner current setting.
	 * 
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(Boolean current) {
		Current = current;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OwnerJson [primaryKey=" + primaryKey + ", ownerName=" + ownerName + ", Current=" + Current + "]";
	}

}

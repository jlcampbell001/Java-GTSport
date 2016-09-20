package backEnd.general;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KeySequence")
public class KeySequence {

	@Id
	@Column(name = "TableName")
	private String tableName = "";
	
	@Column(name = "LastKeyValue")
	private int lastKeyValue = 0;
	
	//Constructors
	/**
	 * Create the KeySequence with the values passed.
	 * @param tableName the name of the table the key sequence is for
	 * @param lastKeyValue the last key sequence value
	 */
	public KeySequence(String tableName, int lastKeyValue) {
		this.tableName = tableName;
		this.lastKeyValue = lastKeyValue;
	}

	/**
	 * Create the KeySequence with default values.
	 */
	public KeySequence() {
		this("", 0);
	}
	
	/**
	 * Create a copy of a KeySequence.
	 * @param originalKeySequence the KeySequence to copy
	 */
	public KeySequence(KeySequence originalKeySequence) {
		this(originalKeySequence.tableName, originalKeySequence.lastKeyValue);
	}

	//Getters/Setters
	/**
	 * Gets the table name.
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Set the table name.
	 * @param tableName the table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the last key sequence value.
	 * @return the last key sequence value
	 */
	public int getLastKeyValue() {
		return lastKeyValue;
	}

	/**
	 * Set the last key sequence value.
	 * @param lastKeyValue the last key sequence value
	 */
	public void setLastKeyValue(int lastKeyValue) {
		this.lastKeyValue = lastKeyValue;
	}

	//Functions
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeySequence [tableName=" + tableName + ", lastKeyValue=" + lastKeyValue + "]";
	}	
	
}
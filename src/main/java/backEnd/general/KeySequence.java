package backEnd.general;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The entity that represents a key sequence record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "KeySequence")
public class KeySequence implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "TableName")
    private String tableName = "";

    @Column(name = "LastKeyValue")
    private int lastKeyValue = 0;

    //Constructors
    /**
     * Create the KeySequence with the values passed.
     *
     * @param tableNameValue the name of the table the key sequence is for
     * @param lastKeyValueToSet the last key sequence value
     */
    public KeySequence(String tableNameValue, int lastKeyValueToSet) {
        this.tableName = tableNameValue;
        this.lastKeyValue = lastKeyValueToSet;
    }

    /**
     * Create the KeySequence with default values.
     */
    public KeySequence() {
        this("", 0);
    }

    /**
     * Create a copy of a KeySequence.
     *
     * @param originalKeySequence the KeySequence to copy
     */
    public KeySequence(KeySequence originalKeySequence) {
        this(originalKeySequence.tableName, originalKeySequence.lastKeyValue);
    }

    //Getters/Setters
    /**
     * Gets the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Set the table name.
     *
     * @param tableNameValue the table name
     */
    public void setTableName(String tableNameValue) {
        this.tableName = tableNameValue;
    }

    /**
     * Gets the last key sequence value.
     *
     * @return the last key sequence value
     */
    public int getLastKeyValue() {
        return lastKeyValue;
    }

    /**
     * Set the last key sequence value.
     *
     * @param lastKeyValueToSet the last key sequence value
     */
    public void setLastKeyValue(int lastKeyValueToSet) {
        this.lastKeyValue = lastKeyValueToSet;
    }

    //Methods
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "KeySequence [tableName=" + tableName + ", lastKeyValue=" + lastKeyValue + "]";
    }

}

package backEnd.general;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import backEnd.general.KeySequenceRepository;

import static utils.StringUtils.*;

@Service
@Configurable
public class KeySequenceService {

	@Autowired
	private KeySequenceRepository keySequenceRepository;
		
	/**
	 * Creates and returns the next primary key for the passed table.
	 * 
	 * @param tableName - the table to create a primary key for
	 * @param keyPrefix - the primary key prefix to add to the beginning of the key
	 * @return - the primary key value
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getNextKey(String tableName, String keyPrefix) {
		// Get the next key value for the table.
		Integer keyValue = getNextKeyValue(tableName);

		// Combine the key value to the key prefix.
		String primaryKey = keyPrefix + padLeft(keyValue.toString(), 9, '0');

		return primaryKey;
	}

	/**
	 * Sets a table's key value to the passed value.
	 * 
	 * @param tableName - the table to set
	 * @param keyValue - the key value to set to
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void resetKeyValue(String tableName, Integer keyValue) {
		boolean createNewKeySequence = false;

		KeySequence keySequence = null;

		// Try and get the current key sequence record if it exists.
		try {
			keySequence = keySequenceRepository.findOne(tableName);

			if (keySequence == null) {
				createNewKeySequence = true;
			}
		} catch (NoResultException e) {
			createNewKeySequence = true;
		}

		// If it is not found then create a new record.
		if (createNewKeySequence) {
			keySequence = new KeySequence(tableName, 0);
		}

		keySequence.setLastKeyValue(keyValue);

		keySequenceRepository.saveAndFlush(keySequence);
	}
	
	/**
	 * Gets the next key value for the passed table.
	 * 
	 * @param tableName - the table to get the next key value for
	 * @return - the key value
	 */
	private Integer getNextKeyValue(String tableName) {
		boolean createNewKeySequence = false;

		KeySequence keySequence = null;

		// Try and get the current key sequence record if it exists.
		try {
			keySequence = keySequenceRepository.findOne(tableName);

			if (keySequence == null) {
				createNewKeySequence = true;
			}
		} catch (NoResultException e) {
			createNewKeySequence = true;
		}

		// If it is not found then create a new record.
		if (createNewKeySequence) {
			keySequence = new KeySequence(tableName, 0);
		}

		Integer keyValue = keySequence.getLastKeyValue() + 1;
		keySequence.setLastKeyValue(keyValue);

		keySequenceRepository.saveAndFlush(keySequence);

		return keyValue;
	}
}

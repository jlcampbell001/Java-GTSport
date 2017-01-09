package backEnd.general.owners;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import backEnd.general.KeySequenceService;
import javassist.bytecode.stackmap.BasicBlock.Catch;

/**
 * @author Jonathan
 *
 */
@Service
public class OwnerService {
	static final String DEFAULT_OWNER_NAME = "DEFAULT";
	
	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private KeySequenceService keySequenceService;

	/**
	 * Looks for an owner record by the primary key and returns an OwnerJson.
	 * 
	 * @param primaryKey
	 *            - the primary key to look for
	 * @return ownerJson - the owner found as an ownerJson object
	 * @throws OwnerException
	 *             - errors thrown if the record can not be found
	 */
	@Transactional
	public OwnerJson getOwnerJsonByKey(String primaryKey) throws OwnerException {
		Owner owner = null;

		try {
			owner = ownerRepository.findOne(primaryKey);

			if (owner == null) {
				throw new OwnerException(OwnerException.OWNER_NOT_FOUND_KEY_ERROR + primaryKey);
			}
		} catch (NoResultException ne) {
			throw new OwnerException(OwnerException.OWNER_NOT_FOUND_KEY_ERROR + primaryKey);
		}

		OwnerJson ownerJson = toJson(owner);

		return ownerJson;
	}

	/**
	 * Looks for an owner record by the name and returns an OwnerJson.
	 * 
	 * @param ownerName
	 *            - the name to look for
	 * @return ownerJson - the owner found as an ownerJson object
	 * @throws OwnerException
	 *             - errors thrown if the record can not be found
	 */
	@Transactional
	public OwnerJson getOwnerJsonByName(String ownerName) throws OwnerException {
		Owner owner = null;

		try {
			owner = ownerRepository.findByName(ownerName);
			if (owner == null) {
				throw new OwnerException(OwnerException.OWNER_NOT_FOUND_NAME_ERROR + ownerName);
			}
		} catch (NoResultException ne) {
			throw new OwnerException(OwnerException.OWNER_NOT_FOUND_NAME_ERROR + ownerName);
		}

		OwnerJson ownerJson = toJson(owner);

		return ownerJson;
	}

	@Transactional
	public OwnerJson getDefaultOwnerJson() throws OwnerException {
		// Try and get the default owner if one is set.
		Owner defaultOwner = null;
		Boolean foundDefaultOwner = false;

		try {
			defaultOwner = ownerRepository.findDefaultOwner();
			if (defaultOwner != null) {
				foundDefaultOwner = true;
			}
		} catch (NoResultException ne) {
			// Do nothing as an owner will be set as the default or
			// a default owner will be create. Just swallow this error.
		}

		// If there is only one owner set that owner to the default owner.
		if (!foundDefaultOwner) {
			if (ownerRepository.count() == 1) {
				OwnerJson singleOwnerJson = getOwnerJsonByKey(ownerRepository.getMaxKey());

				singleOwnerJson.setDefaultOwner(true);

				saveOwner(singleOwnerJson);

				defaultOwner = toOwner(singleOwnerJson);

				foundDefaultOwner = true;
			}
		}

		// Find or create a default owner.
		if (!foundDefaultOwner) {
			OwnerJson defaultOwnerJson = null;
			
			try {
				defaultOwnerJson = getOwnerJsonByName(DEFAULT_OWNER_NAME);
			} catch (OwnerException oe) {
				// Create a new default owner as the default owner was not found.
				defaultOwnerJson = new OwnerJson();
				
				defaultOwnerJson.setOwnerName(DEFAULT_OWNER_NAME);
				defaultOwnerJson.setDefaultOwner(true);
				
				saveOwner(defaultOwnerJson);
			}
			
			defaultOwner = toOwner(defaultOwnerJson);
			
			foundDefaultOwner = true;
		}

		OwnerJson defaultOwnerJson = toJson(defaultOwner);

		return defaultOwnerJson;
	}

	/**
	 * Save an owner record.
	 * 
	 * @param ownerJson
	 *            - the owner to save
	 * @throws OwnerException
	 *             - errors thrown if the owner can not be validated
	 */
	@Transactional
	public void saveOwner(OwnerJson ownerJson) throws OwnerException {
		validateOwnerSave(ownerJson);

		// New records do not have the primary key filled, so if it is not
		// filled get one.
		if (ownerJson.getPrimaryKey().trim().isEmpty()) {
			String primaryKey = keySequenceService.getNextKey("OWNER", "OWN");
			ownerJson.setPrimaryKey(primaryKey);
		}

		Owner owner = toOwner(ownerJson);

		ownerRepository.saveAndFlush(owner);
	}

	/**
	 * Delete the owner based on the passed primary key.
	 * 
	 * @param primaryKey
	 *            - the primary key of the owner to delete
	 * @throws OwnerException
	 *             - errors thrown if the owner can not be validated
	 */
	@Transactional
	public void deleteOwner(String primaryKey) throws OwnerException {
		validateOwnerDelete(primaryKey);

		ownerRepository.delete(primaryKey);
	}

	/**
	 * Gets a list of owner records.
	 * 
	 * @return a list of ownerjsons.
	 */
	@Transactional
	public List<OwnerJson> getOwnersList() {
		List<OwnerJson> ownerJsons = new ArrayList<OwnerJson>();

		List<Owner> owners = ownerRepository.findAll();

		for (Owner owner : owners) {
			OwnerJson ownerJson = toJson(owner);

			ownerJsons.add(ownerJson);
		}

		return ownerJsons;
	}

	/**
	 * Finds the max value key in the owners table and set the key sequence
	 * record for the owners table to it.
	 */
	@Transactional
	public void resetKeys() {
		String maxKey = ownerRepository.getMaxKey();

		Integer maxKeyValue = 0;

		if (maxKey != null && !maxKey.trim().isEmpty()) {
			maxKeyValue = Integer.valueOf(maxKey.substring(3));
		}

		keySequenceService.resetKeyValue("OWNER", maxKeyValue);
	}

	/**
	 * Converts an owner object to an ownerJson object.
	 * 
	 * @param owner
	 *            - the owner object to convert
	 * @return ownerJson - the new ownerJson object
	 */
	private OwnerJson toJson(Owner owner) {
		OwnerJson ownerJson = new OwnerJson();

		ownerJson.setPrimaryKey(owner.getPrimaryKey());
		ownerJson.setOwnerName(owner.getOwnerName());
		ownerJson.setDefaultOwner(owner.getDefaultOwner());

		return ownerJson;
	}

	/**
	 * Converts an ownerJson to an owner object.
	 * 
	 * @param ownerJson
	 *            - the ownerJson object to convert
	 * @return owner - the new owner object
	 */
	private Owner toOwner(OwnerJson ownerJson) {
		Owner owner = new Owner();

		owner.setPrimaryKey(ownerJson.getPrimaryKey());
		owner.setOwnerName(ownerJson.getOwnerName());
		owner.setDefaultOwner(ownerJson.getDefaultOwner());

		return owner;
	}

	/**
	 * Checks to see if the owner data to save is valid.
	 * 
	 * @param ownerJson
	 *            - the owner data to check
	 * @throws OwnerException
	 *             - errors thrown if the owner will not be allowed to be saved.
	 */
	private void validateOwnerSave(OwnerJson ownerJson) throws OwnerException {
		// Make sure the owner name is set.
		if (ownerJson.getOwnerName().trim().isEmpty()) {
			throw new OwnerException(OwnerException.OWNER_NAME_NOT_SET);
		}

		// Make sure the owner name dose not already exist.
		Owner existingOwner = ownerRepository.findByName(ownerJson.getOwnerName());

		if (existingOwner != null) {
			if (ownerJson.getPrimaryKey().trim().isEmpty()
					|| !ownerJson.getPrimaryKey().trim().equals(existingOwner.getPrimaryKey().trim())) {
				throw new OwnerException(OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + ownerJson.getOwnerName());
			}
		}
	}

	/**
	 * TODO: need to check the owned cars table for records before allowing
	 * delete.
	 * 
	 * @param primaryKey
	 *            - the primary key of the owner to delete
	 * @throws OwnerException
	 *             - errors thrown if the owner will not be allowed to be
	 *             deleted
	 */
	private void validateOwnerDelete(String primaryKey) throws OwnerException {
		// Make sure the owner record exists to delete.
		if (!ownerRepository.exists(primaryKey)) {
			throw new OwnerException(OwnerException.OWNER_NOT_FOUND_KEY_DELETE_ERROR + primaryKey);
		}
	}
}

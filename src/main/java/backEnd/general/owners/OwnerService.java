package backEnd.general.owners;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backEnd.general.KeySequenceService;

/**
 * @author Jonathan
 *
 */
@Service
public class OwnerService {
	/**
	 * If there is no default owner setup a new one will be created using this as the owner name
	 * or if the owner already exists it will be set as the default owner.
	 */
	public static final String DEFAULT_OWNER_NAME = "DEFAULT";
	
	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private KeySequenceService keySequenceService;
	
	@Autowired
	private OwnerValidate ownerValidate;

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
		boolean foundDefaultOwner = false;

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
		ownerValidate.validateOwnerSave(ownerJson);

		// New records do not have the primary key filled, so if it is not
		// filled get one.
		if (ownerJson.getPrimaryKey().trim().isEmpty()) {
			String primaryKey = keySequenceService.getNextKey("OWNER", "OWN");
			ownerJson.setPrimaryKey(primaryKey);
		}

		Owner owner = toOwner(ownerJson);

		ownerRepository.saveAndFlush(owner);
		
		//If the owner is the default owner make sure it is the only one.
		if (ownerJson.isDefaultOwner()) {
			clearOtherDefaultOwners(ownerJson);
		}
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
		ownerValidate.validateOwnerDelete(primaryKey);

		ownerRepository.delete(primaryKey);
	}

	/**
	 * Gets a list of owner records.
	 * 
	 * @return a list of owner jsons.
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

		int maxKeyValue = 0;

		if (maxKey != null && !maxKey.trim().isEmpty()) {
			maxKeyValue = Integer.valueOf(maxKey.substring(3));
		}

		keySequenceService.resetKeyValue("OWNER", maxKeyValue);
	}


	/**
	 * Set all other owners default setting to false.
	 * 
	 * @param currentDefaultOwner - the owner that should be the default owner
	 */
	private void clearOtherDefaultOwners(OwnerJson currentDefaultOwner) {
		List<Owner> defaultOwners = ownerRepository.findAllDefaultOwners();
		
		for (Owner owner : defaultOwners) {
			
			if (!owner.getPrimaryKey().equals(currentDefaultOwner.getPrimaryKey())) {
				owner.setDefaultOwner(false);
				
				ownerRepository.saveAndFlush(owner);
			}
		}
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
		owner.setDefaultOwner(ownerJson.isDefaultOwner());

		return owner;
	}
}

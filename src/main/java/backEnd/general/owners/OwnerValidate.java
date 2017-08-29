package backEnd.general.owners;

import backEnd.general.ownerCars.OwnerCar;
import backEnd.general.ownerCars.OwnerCarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the owner service that will validate an OwnerJson for
 * saving/deleting.
 *
 * @author jonathan
 */
@Service
public class OwnerValidate {

    @Autowired
    private OwnerRepository ownerRepository;
    
    @Autowired
    private OwnerCarRepository ownerCarRepository;

    /**
     * Checks to see if the owner data to save is valid.
     *
     * @param ownerJson - the owner data to check
     * @throws OwnerException - errors thrown if the owner will not be allowed
     * to be saved.
     */
    @Transactional
    public void validateOwnerSave(OwnerJson ownerJson) throws OwnerException {
        // Make sure the owner name is set.
        if (ownerJson.getOwnerName().trim().isEmpty()) {
            throw new OwnerException(OwnerException.OWNER_NAME_NOT_SET);
        }

        // Make sure the owner name does not already exist.
        Owner existingOwner = ownerRepository.findByName(ownerJson.getOwnerName());

        if (existingOwner != null) {
            if (ownerJson.getPrimaryKey().trim().isEmpty()
                    || !ownerJson.getPrimaryKey().trim().equals(existingOwner.getPrimaryKey().trim())) {
                throw new OwnerException(OwnerException.OWNER_NAME_EXISTS_ALREADY_ERROR + ownerJson.getOwnerName());
            }
        }
    }

    /**
     * Checks to make sure the owner can be deleted.
     *
     * @param primaryKey - the primary key of the owner to delete
     * @throws OwnerException - errors thrown if the owner will not be allowed
     * to be deleted
     */
    @Transactional
    public void validateOwnerDelete(String primaryKey) throws OwnerException {
        // Make sure the owner record exists to delete.
        if (!ownerRepository.exists(primaryKey)) {
            throw new OwnerException(OwnerException.OWNER_NOT_FOUND_KEY_DELETE_ERROR + primaryKey);
        }
        
        // Make sure the owner record is not used by an ownercar record.
        List<OwnerCar> ownerCars = ownerCarRepository.findAllByOwnerKey(primaryKey);
        
        if(!ownerCars.isEmpty()) {
            throw new OwnerException(OwnerException.OWNER_IS_IN_USE);
        }
    }
}

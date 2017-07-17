package backEnd.general.regions;

import backEnd.general.countries.Country;
import backEnd.general.countries.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service that will validate a region for saving/deleting.
 *
 * @author jonathan
 */
@Service
public class RegionValidate {

    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    /**
     * Validates the region data on a save.
     *
     * @param regionJson - the region to validate
     * @throws RegionException - Errors for a region where the data is not
     * correct. - Description not set. - Description already exists.
     */
    @Transactional
    public void validateRegionSave(RegionJson regionJson) throws RegionException {
        // Make sure the region description is set.
        if (regionJson.getDescription().trim().isEmpty()) {
            throw new RegionException(RegionException.REGION_DESCRIPTION_NOT_SET);
        }

        // Make sure the region description does not already exist.
        Region existingRegion = regionRepository.findbyDescription(regionJson.getDescription());

        if (existingRegion != null) {
            if (regionJson.getPrimaryKey().trim().isEmpty()
                    || !regionJson.getPrimaryKey().trim().equals(existingRegion.getPrimaryKey().trim())) {
                throw new RegionException(RegionException.REGION_DESCRIPTION_ALREADY_EXISTS + regionJson.getDescription());
            }
        }
    }

    /**
     * Validates that a region with the passed primary key can be deleted.
     *
     * @param primaryKey - the primary key to delete
     * @throws RegionException - Errors if the region can not be deleted. -
     * Primary key does not exist.
     */
    @Transactional
    public void validateRegionDelete(String primaryKey) throws RegionException {
        //Make sure the region key exists to delete.
        if (!regionRepository.exists(primaryKey)) {
            throw new RegionException(RegionException.REGION_KEY_NOT_FOUND_TO_DELETE + primaryKey);
        }
        
        //Make sure the region is not in used with a country.
        List<Country> countries = countryRepository.findAllByRegionKey(primaryKey);
        
        if (!countries.isEmpty()) {
            throw new RegionException(RegionException.REGION_CANNOT_DELETE_IN_USE_COUNTRY);
        }
    }
}

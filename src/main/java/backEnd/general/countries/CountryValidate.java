package backEnd.general.countries;

import backEnd.general.regions.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The validates for saving/deleting a country.
 *
 * @author jonathan
 */
@Service
public class CountryValidate {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    /**
     * Validates the country Json to save.
     *
     * @param countryJson - the country Json to validate
     * @throws CountryException - Error: - The description must be filled. - The
     * region key must be filled. - The region key must exist. - The description
     * must be unique.
     */
    public void validateCountrySave(CountryJson countryJson) throws CountryException {

        // Make sure the description for the country is filled.
        if (countryJson.getDescription().trim().isEmpty()) {
            throw new CountryException(CountryException.DESCRIPTION_NOT_SET);
        }

        // Make sure the region key is filled.
        if (countryJson.getRegionKey().trim().isEmpty()) {
            throw new CountryException(CountryException.REGION_KEY_NOT_SET);
        }

        // Make sure the description is unique.
        Country existingCountry = countryRepository.findByDescription(countryJson.getDescription());

        if (existingCountry != null) {
            if ((countryJson.getPrimaryKey().trim().isEmpty())
                    || (!countryJson.getPrimaryKey().trim().equals(existingCountry.getPrimaryKey().trim()))) {
                throw new CountryException(CountryException.DESCRIPTION_ALREADY_EXISTS);
            }
        }

        // Make sure the region key exists.
        if (!regionRepository.exists(countryJson.getRegionKey())) {
            throw new CountryException(CountryException.REGION_DOSE_NOT_EXIST + countryJson.getRegionKey());
        }
    }

    /**
     * Validates the deleting of a country.
     *
     * @param primaryKey - the country primary key to delete
     * @throws CountryException - Errors: - The primary key must exists to
     * delete.
     */
    public void validateCountryDelete(String primaryKey) throws CountryException {
        if (!countryRepository.exists(primaryKey)) {
            throw new CountryException(CountryException.COUNTRY_KEY_NOT_FOUND_DO_DELETE + primaryKey);
        }

        // Need a validate to make sure the country is not used in a car.
    }
}

package backEnd.general.countries;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The country service.
 *
 * @author jonathan
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private KeySequenceService keySequenceService;

    @Autowired
    private CountryValidate countryValidate;

    /**
     * Get a country Json by the primary key.
     *
     * @param primaryKey - the primary key to look for
     * @return - the country Json found
     * @throws CountryException - error if the primary key can not be found
     */
    @Transactional
    public CountryJson getCountryJsonByKey(String primaryKey) throws CountryException {
        Country country = null;

        try {
            country = countryRepository.findOne(primaryKey);

            if (country == null) {
                throw new CountryException(CountryException.COUNTRY_KEY_NOT_FOUND + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new CountryException(CountryException.COUNTRY_KEY_NOT_FOUND + primaryKey);
        }

        CountryJson countryJson = toJson(country);

        return countryJson;
    }

    /**
     * Get the country Json by the description.
     *
     * @param description - the description to look for
     * @return - the country Json found
     * @throws CountryException - error if the country is not found
     */
    @Transactional
    public CountryJson getCountryJsonByDescription(String description) throws CountryException {
        Country country = null;

        try {
            country = countryRepository.findByDescription(description);

            if (country == null) {
                throw new CountryException(CountryException.COUNTRY_DESCRIPTION_NOT_FOUND + description);
            }
        } catch (NoResultException nre) {
            throw new CountryException(CountryException.COUNTRY_DESCRIPTION_NOT_FOUND + description);
        }

        CountryJson countryJson = toJson(country);

        return countryJson;
    }

    /**
     * Save a country record with the information from the country Json.
     *
     * @param countryJson - the country data to save.
     * @throws CountryException - errors reported by the country validate
     */
    @Transactional
    public void saveCountry(CountryJson countryJson) throws CountryException {
        countryValidate.validateCountrySave(countryJson);

        if (countryJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("COUNTRIES", "COU");
            countryJson.setPrimaryKey(primaryKey);
        }

        Country country = toCountry(countryJson);

        countryRepository.saveAndFlush(country);
    }

    /**
     * Delete the country for the passed primary key.
     *
     * @param primaryKey - the country primary key to delete
     * @throws CountryException - errors reported by the country delete validate
     */
    @Transactional
    public void deleteCountry(String primaryKey) throws CountryException {
        countryValidate.validateCountryDelete(primaryKey);

        countryRepository.delete(primaryKey);
    }

    /**
     * Gets a list of countries.
     *
     * @return - list of countries
     */
    @Transactional
    public List<CountryJson> getCountryList() {
        List<CountryJson> countryJsons = new ArrayList<CountryJson>();

        List<Country> countries = countryRepository.findAll();

        for (Country country : countries) {
            CountryJson countryJson = toJson(country);

            countryJsons.add(countryJson);
        }

        return countryJsons;
    }

    /**
     * Gets a list of countries for the passed region key.
     *
     * @param regionKey - the region key to filter by
     * @return - a list of countries for the region key
     */
    @Transactional
    public List<CountryJson> getCountryListByRegionKey(String regionKey) {
        List<CountryJson> countryJsons = new ArrayList<CountryJson>();

        List<Country> countries = countryRepository.findAllByRegionKey(regionKey);

        for (Country country : countries) {
            CountryJson countryJson = toJson(country);

            countryJsons.add(countryJson);
        }

        return countryJsons;
    }

    /**
     * Resets the last country key to the highest key value in the country
     * table.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = countryRepository.getMaxKey();
        int maxKeyValue = 0;

        if (!(maxKey == null) && !(maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("COUNTRIES", maxKeyValue);
    }

    private CountryJson toJson(Country country) {
        CountryJson countryJson = new CountryJson();
        countryJson.setPrimaryKey(country.getPrimaryKey());
        countryJson.setDescription(country.getDescription());
        countryJson.setRegionKey(country.getRegionKey());

        return countryJson;
    }

    private Country toCountry(CountryJson countryJson) {
        Country country = new Country();
        country.setPrimaryKey(countryJson.getPrimaryKey());
        country.setDescription(countryJson.getDescription());
        country.setRegionKey(countryJson.getRegionKey());

        return country;
    }
}

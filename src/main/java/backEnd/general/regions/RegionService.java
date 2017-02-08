package backEnd.general.regions;

import backEnd.general.KeySequenceService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for the region.
 *
 * @author jonathan
 */
@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    KeySequenceService keySequenceService;

    @Autowired
    RegionValidate regionValidate;

    /**
     * Gets the region for the passed primary key.
     *
     * @param primaryKey - the primary key to look for
     * @return - the region Json found
     * @throws RegionException - An error if the region is not found.
     */
    @Transactional
    public RegionJson getRegionJsonByKey(String primaryKey) throws RegionException {
        Region region = null;

        try {
            region = regionRepository.findOne(primaryKey);

            if (region == null) {
                throw new RegionException(RegionException.REGION_NOT_FOUND_BY_KEY + primaryKey);
            }
        } catch (NoResultException nre) {
            throw new RegionException(RegionException.REGION_NOT_FOUND_BY_KEY + primaryKey);
        }

        RegionJson regionJson = toJson(region);

        return regionJson;
    }

    /**
     * Gets the region for the passed description.
     *
     * @param description - the description to look for
     * @return - the region Json found
     * @throws RegionException - An error if the region is not found.
     */
    @Transactional
    public RegionJson getRegionJsonByDescription(String description) throws RegionException {
        Region region = null;

        try {
            region = regionRepository.findbyDescription(description);

            if (region == null) {
                throw new RegionException(RegionException.REGION_NOT_FOUND_BY_DESCRIPTION + description);
            }
        } catch (NoResultException nre) {
            throw new RegionException(RegionException.REGION_NOT_FOUND_BY_DESCRIPTION + description);
        }

        RegionJson regionJson = toJson(region);

        return regionJson;
    }

    /**
     * Save a region record.
     *
     * @param regionJson - the region data to save.
     * @throws RegionException - Errors if there is an issue with the data
     * provided.
     */
    @Transactional
    public void saveRegion(RegionJson regionJson) throws RegionException {
        regionValidate.validateRegionSave(regionJson);

        if (regionJson.getPrimaryKey().trim().isEmpty()) {
            String primaryKey = keySequenceService.getNextKey("REGIONS", "REG");
            regionJson.setPrimaryKey(primaryKey);
        }

        Region region = toRegion(regionJson);

        regionRepository.saveAndFlush(region);
    }

    /**
     * Delete the region for the passed primary key.
     *
     * @param primaryKey - the primary key to look for to delete
     * @throws RegionException - Errors found when validating that the record
     * can be deleted.
     */
    @Transactional
    public void deleteRegion(String primaryKey) throws RegionException {
        regionValidate.validateRegionDelete(primaryKey);

        regionRepository.delete(primaryKey);
    }

    /**
     * Gets a list of all the region records.
     *
     * @return - a list of regions
     */
    @Transactional
    public List<RegionJson> getRegionList() {
        List<RegionJson> regionJsons = new ArrayList<RegionJson>();

        List<Region> regions = regionRepository.findAll();

        for (Region region : regions) {
            RegionJson regionJson = toJson(region);

            regionJsons.add(regionJson);
        }

        return regionJsons;
    }

    /**
     * Resets the last region key used to the highest key in the region table.
     */
    @Transactional
    public void resetKeys() {
        String maxKey = regionRepository.getMaxKey();

        int maxKeyValue = 0;

        if ((maxKey != null) && (!maxKey.trim().isEmpty())) {
            maxKeyValue = Integer.parseInt(maxKey.substring(3));
        }

        keySequenceService.resetKeyValue("REGIONS", maxKeyValue);
    }

    /**
     * Converts a region object to a region Json object.
     *
     * @param region - the region object to convert
     * @return - the region Json created
     */
    private RegionJson toJson(Region region) {
        RegionJson regionJson = new RegionJson();

        regionJson.setPrimaryKey(region.getPrimaryKey());
        regionJson.setDescription(region.getDescription());

        return regionJson;
    }

    /**
     * Converts a region Json object to a region object.
     *
     * @param regionJson - the region Json object to convert
     * @return - the region created
     */
    private Region toRegion(RegionJson regionJson) {
        Region region = new Region();

        region.setPrimaryKey(regionJson.getPrimaryKey());
        region.setDescription(regionJson.getDescription());

        return region;
    }
}

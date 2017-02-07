/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import backEnd.general.KeySequenceService;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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

    private RegionJson toJson(Region region) {
        RegionJson regionJson = new RegionJson();

        regionJson.setPrimaryKey(region.getPrimaryKey());
        regionJson.setDescription(region.getDescription());

        return regionJson;
    }

    private Region toRegion(RegionJson regionJson) {
        Region region = new Region();

        region.setPrimaryKey(regionJson.getPrimaryKey());
        region.setDescription(regionJson.getDescription());

        return region;
    }
}

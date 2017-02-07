/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import backEnd.general.owners.Owner;
import backEnd.general.owners.OwnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonathan
 */
@Service
public class RegionValidate {

    @Autowired
    RegionRepository regionRepository;

    @Transactional
    public void validateRegionSave(RegionJson regionJson) throws RegionException {
        // Make sure the region description is set.
        if (regionJson.getDescription().trim().isEmpty()) {
            throw new RegionException(RegionException.REGION_DESCRIPTION_NOT_SET);
        }

        // Make sure the region description dose not already exist.
        Region existingRegion = regionRepository.findbyDescription(regionJson.getDescription());

        if (existingRegion != null) {
            if (regionJson.getPrimaryKey().trim().isEmpty()
                    || !regionJson.getPrimaryKey().trim().equals(existingRegion.getPrimaryKey().trim())) {
                throw new RegionException(RegionException.REGION_DESCRIPTION_ALREADY_EXISTS + regionJson.getDescription());
            }
        }
    }
    
    @Transactional 
    public void validateRegionDelete(String primaryKey) throws RegionException {
        //Make sure the region key exists to delete.
        if (!regionRepository.exists(primaryKey)) {
            throw new RegionException(RegionException.REGION_KEY_NOT_FOUND_TO_DELETE + primaryKey);
        }
    }
}
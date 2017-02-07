/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonathan
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, String>{
    
    @Query("select reg from Region reg where reg.description = :description")
    Region findbyDescription(@Param("description") String description);
    
    @Query("select max(reg.primaryKey) from Region reg")
    String getMaxKey();
}

package backEnd.general.ownerCars;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonathan
 */
@Repository
public interface OwnerCarRepository extends JpaRepository<OwnerCar, String>{
    
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.carId = :carId")
    OwnerCar findByCarId(@Param("carId") String carId);
    
    @Query("Select max(ownerCar.primaryKey) from OwnerCar ownerCar")
    String getMaxKey();
    
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.ownerKey = :ownerKey")
    List<OwnerCar> findAllByOwnerKey(@Param("ownerKey") String ownerKey);
    
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.carKey = :carKey")
    List<OwnerCar> findAllByCarKey(@Param("carKey") String carKey);
}

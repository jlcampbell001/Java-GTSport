package backEnd.general.ownerCars;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the owner car.
 *
 * @author jonathan
 */
@Repository
public interface OwnerCarRepository extends JpaRepository<OwnerCar, String> {

    /**
     * Get an owner car by the car Id.
     *
     * @param carId the car Id to look for
     * @return The owner car found.
     */
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.carId = :carId")
    OwnerCar findByCarId(@Param("carId") String carId);

    /**
     * Get the highest valued primary key.
     *
     * @return The highest valued primary key.
     */
    @Query("Select max(ownerCar.primaryKey) from OwnerCar ownerCar")
    String getMaxKey();

    /**
     * Get a list of owner cars for the passed owner key.
     *
     * @param ownerKey the owner key to filter by
     * @return A list of owner cars found.
     */
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.ownerKey = :ownerKey")
    List<OwnerCar> findAllByOwnerKey(@Param("ownerKey") String ownerKey);

    /**
     * Get a list of owner cars for the passed car key.
     *
     * @param carKey the car key to filter by
     * @return A list of owner cars found.
     */
    @Query("Select ownerCar from OwnerCar ownerCar where ownerCar.carKey = :carKey")
    List<OwnerCar> findAllByCarKey(@Param("carKey") String carKey);
}

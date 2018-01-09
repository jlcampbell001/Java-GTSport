package backEnd.general.cars;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the cars table.
 *
 * @author jonathan
 */
@Repository
public interface CarRepository extends JpaRepository<Car, String>, CarRepositoryCustom {

    /**
     * Get a car by the car name (exact match).
     *
     * @param name the name of the car to look for.
     * @return The car if found or null if not found.
     */
    @Query("Select car from Car car where car.name = :name")
    Car findByName(@Param("name") String name);

    /**
     * Get the highest car primary key in the table.
     *
     * @return The highest car primary key.
     */
    @Query("Select max(car.primaryKey) from Car car")
    String getMaxKey();

    /**
     * Get a list of cars for the manufacturer key.
     *
     * @param manufacturerKey the manufacturer key to look for cars against
     * @return The list of car found for the manufacturer.
     */
    @Query("Select car from Car car where car.manufacturerKey = :manufacturerKey")
    List<Car> findAllByManufacturerKey(@Param("manufacturerKey") String manufacturerKey);

    /**
     * Get a list of car level statistics as a list of Objects[]. <br>
     * Element 0 = the car level. <br>
     * Element 1 = the number of cars in that level <br>
     * Element 2 = the average power points of the cars in that level <br>
     * Element 3 = the average horse power of the cars in that level <br>
     * Element 4 = the average price of the cars in that level <br>
     *
     * @return The list of car level statistics.
     */
    @Query("Select car.level, count(car), avg(car.powerPoints), avg(car.horsePower), "
            + "avg(car.price) from Car car group by car.level order by car.level")
    List<Object[]> getCarsStatistics();
}

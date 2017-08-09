package backEnd.general.cars;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the cars table.
 * @author jonathan
 */
@Repository
public interface CarRepository extends JpaRepository<Car, String>, CarRepositoryCustom {
    @Query("Select car from Car car where car.name = :name")
    Car findByName(@Param("name") String name);
    
    @Query("Select max(car.primaryKey) from Car car")
    String getMaxKey();
    
    @Query("Select car from Car car where car.dealerKey = :dealerKey")
    List<Car> findAllByDealerKey(@Param("dealerKey") String dealerKey);
    
    @Query("Select car.level, count(car), avg(car.powerPoints), avg(car.horsePower), "
            + "avg(car.price) from Car car group by car.level order by car.level")
    List<Object[]> getCarsStatistics();
}

package backEnd.general.manufacturers;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the manufacturer table.
 *
 * @author jonathan
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {

    /**
     * Get a dealer record by the manufacturer name.
     *
     * @param name - the manufacturer name to look for
     * @return - a manufacturer record found or null it not found
     */
    @Query("Select man from Manufacturer man where man.name = :name")
    Manufacturer findByName(@Param("name") String name);

    /**
     * Get the highest value primary key.
     *
     * @return - the highest value primary key
     */
    @Query("Select max(man.primaryKey) from Manufacturer man")
    String getMaxKey();

    /**
     * Get a list of manufacturer records filtered by the country key.
     *
     * @param countryKey - the country key to filter by
     * @return - a list of manufacturers found
     */
    @Query("Select man from Manufacturer man where man.countryKey = :countryKey")
    List<Manufacturer> findAllByCountryKey(@Param("countryKey") String countryKey);

}

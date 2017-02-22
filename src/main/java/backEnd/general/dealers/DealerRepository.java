package backEnd.general.dealers;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the dealer table.
 *
 * @author jonathan
 */
@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {

    /**
     * Get a dealer record by the dealer name.
     *
     * @param name - the dealer name to look for
     * @return - a dealer record found or null it not found
     */
    @Query("Select dea from Dealer dea where dea.name = :name")
    Dealer findByName(@Param("name") String name);

    /**
     * Get the highest value primary key.
     *
     * @return - the highest value primary key
     */
    @Query("Select max(dea.primaryKey) from Dealer dea")
    String getMaxKey();

    /**
     * Get a list of dealer records filtered by the country key.
     *
     * @param countryKey - the country key to filter by
     * @return - a list of dealers found
     */
    @Query("Select dea from Dealer dea where dea.countryKey = :countryKey")
    List<Dealer> findAllByCountryKey(@Param("countryKey") String countryKey);

}

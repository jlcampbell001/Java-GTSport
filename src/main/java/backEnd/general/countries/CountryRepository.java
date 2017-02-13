package backEnd.general.countries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the repository for the country.
 *
 * @author jonathan
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Get a country record by the description.
     *
     * @param description - the description to look for
     * @return - the country found
     */
    @Query("select cou from Country cou where cou.description = :description")
    Country findByDescription(@Param("description") String description);

    /**
     * Get the highest primary key value.
     *
     * @return - the highest primary key
     */
    @Query("select max(cou.primaryKey) from Country cou")
    String getMaxKey();

    /**
     * Get a list of countries for the passed region key.
     *
     * @param regionKey - the region key to filter by
     * @return - a list of countries found
     */
    @Query("select cou from Country cou where cou.regionKey = :regionKey")
    List<Country> findAllByRegionKey(@Param("regionKey") String regionKey);
}

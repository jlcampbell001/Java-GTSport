package backEnd.general.cars;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * The repository to find cars based of a set of criteria.
 *
 * @author jonathan
 */
@Repository
public interface CarRepositoryCustom {

    /**
     * Get a list of cars based a set of criteria.
     *
     * @param searchJson the criteria to search for cars against
     * @return A List of car records found that match the criteria.
     * @throws CarException if no cars are found or there a issues with the
     * passed criteria
     */
    List<Car> findAllByCriteria(CarSearchJson searchJson)
            throws CarException;
}

package backEnd.general.statistics;

import backEnd.general.cars.CarRepository;
import backEnd.general.cars.Category;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The service for statistics.
 *
 * @author jonathan
 */
@Service
public class StatisticsService {

    @Autowired
    private CarRepository carRepository;

    /**
     * Gets the list of the car level statistics.
     *
     * @return The list of car level statistics.
     * @throws CarCategoryStatisticException if there are issues with a car
     * level statistic
     */
    public Map<Category, CarCategoryStatistic> getCarLevelStatistics() throws CarCategoryStatisticException {
        List<Object[]> carsStats = carRepository.getCarsStatistics();

        Map<Category, CarCategoryStatistic> carLevelStatistics = new EnumMap<Category, CarCategoryStatistic>(Category.class);

        if (carsStats != null) {
            for (int i = 0; i < carsStats.size(); i++) {
                Object[] carStat = carsStats.get(i);

                CarCategoryStatistic carLevelStatistic = new CarCategoryStatistic(carStat);
                carLevelStatistics.put(carLevelStatistic.getCategory(), carLevelStatistic);
            }
        }

        return carLevelStatistics;
    }
}

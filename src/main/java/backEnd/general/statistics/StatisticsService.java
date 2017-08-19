package backEnd.general.statistics;

import backEnd.general.cars.CarRepository;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
     * @throws CarLevelStatisticException if there are issues with a car level
     * statistic
     */
    public Map<Integer, CarLevelStatistic> getCarLevelStatistics() throws CarLevelStatisticException {
        List<Object[]> carsStats = carRepository.getCarsStatistics();

        Map<Integer, CarLevelStatistic> carLevelStatistics = new TreeMap<Integer, CarLevelStatistic>();

        if (carsStats != null) {
            for (int i = 0; i < carsStats.size(); i++) {
                Object[] carStat = carsStats.get(i);

                CarLevelStatistic carLevelStatistic = new CarLevelStatistic(carStat);
                carLevelStatistics.put(carLevelStatistic.getLevel(), carLevelStatistic);
            }
        }

        return carLevelStatistics;
    }
}

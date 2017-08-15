/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.statistics;

import backEnd.general.cars.CarException;
import backEnd.general.cars.CarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonathan
 */
@Service
public class StatisticsService {

    @Autowired
    private CarRepository carRepository;

    public Map<Integer, CarLevelStatistic> getCarLevelStatistics() throws CarException {
        List<Object[]> carsStats = carRepository.getCarsStatistics();

        Map<Integer,CarLevelStatistic> carLevelStatistics = new TreeMap<Integer, CarLevelStatistic>();
        
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

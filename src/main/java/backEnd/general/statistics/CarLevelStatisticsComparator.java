/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.statistics;

import java.util.Comparator;

/**
 *
 * @author jonathan
 */
public class CarLevelStatisticsComparator implements Comparator<CarLevelStatistic>{

    public int compare(CarLevelStatistic o1, CarLevelStatistic o2) {
        return o1.getLevel().compareTo(o2.getLevel());
    }
    
    
}

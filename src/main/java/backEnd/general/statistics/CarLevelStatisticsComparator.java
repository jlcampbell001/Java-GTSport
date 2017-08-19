package backEnd.general.statistics;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The class that will compare two car level statistic objects.<br>
 * It orders them by the level property.
 *
 * @author jonathan
 */
public class CarLevelStatisticsComparator implements Comparator<CarLevelStatistic>, Serializable {

    private static final long serialVersionUID = 100L;

    public int compare(CarLevelStatistic o1, CarLevelStatistic o2) {
        return o1.getLevel().compareTo(o2.getLevel());
    }
}

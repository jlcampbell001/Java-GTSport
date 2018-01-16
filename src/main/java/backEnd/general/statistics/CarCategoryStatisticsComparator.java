package backEnd.general.statistics;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The class that will compare two car level statistic objects.<br>
 * It orders them by the level property.
 *
 * @author jonathan
 */
public class CarCategoryStatisticsComparator implements Comparator<CarCategoryStatistic>, Serializable {

    private static final long serialVersionUID = 100L;

    public int compare(CarCategoryStatistic o1, CarCategoryStatistic o2) {
        return o1.getCategory().getDBValue().compareTo(o2.getCategory().getDBValue());
    }
}

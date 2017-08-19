package backEnd.general.statistics;

/**
 * The errors for the car level statistics.
 *
 * @author jonathan
 */
public class CarLevelStatisticException extends Exception {

    /**
     * The error message for the object[] not having enough elements.
     */
    public static final String CAR_STAT_OBJECT_SIZE_WRONG = "Object array must contain 5 elements: ";

    /**
     * The error message if the first element is not an integer.
     */
    public static final String CAR_STAT_OBJECT_0_NOT_INTEGER = "Object Element 0 (Level) must be an Integer to convert to a CarLevelStatistic object: ";

    /**
     * The error message if the second element is not a long.
     */
    public static final String CAR_STAT_OBJECT_1_NOT_LONG = "Object Element 1 (No of Cars) must be a Long to convert to a CarLevelStatistic object: ";

    /**
     * The error message if the third element is not a double.
     */
    public static final String CAR_STAT_OBJECT_2_NOT_DOUBLE = "Object Element 2 (Avg PP) must be a Double to convert to a CarLevelStatistic object: ";

    /**
     * The error message if the fourth element is not a double.
     */
    public static final String CAR_STAT_OBJECT_3_NOT_DOUBLE = "Object Element 3 (Avg HP) must be a Double to convert to a CarLevelStatistic object: ";

    /**
     * The error message if the fifth element if not a double or the perfect
     * being.
     */
    public static final String CAR_STAT_OBJECT_4_NOT_DOUBLE = "Object Element 4 (Avg Price) must be a Double to convert to a CarLevelStatistic object: ";

    /**
     * Creates a new instance of <code>CarLevelStatisticException</code> without
     * detail message.
     */
    public CarLevelStatisticException() {
    }

    /**
     * Constructs an instance of <code>CarLevelStatisticException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CarLevelStatisticException(String msg) {
        super(msg);
    }
}

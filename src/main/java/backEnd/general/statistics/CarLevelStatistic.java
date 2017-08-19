package backEnd.general.statistics;

/**
 * The object for the car level statistic record.
 *
 * @author jonathan
 */
public class CarLevelStatistic {

    private Integer level = 0;
    private Long noOfCars = 0L;
    private double avgPowerPoints = 0L;
    private double avgHorsePower = 0L;
    private double avgPrice = 0.00;

    /**
     * Create a blank car level statistic.
     */
    public CarLevelStatistic() {
    }

    /**
     * Create a car level statistic with the passed values.
     *
     * @param level the car level of the record
     * @param noOfCars the number of cars in that level
     * @param avgPowerPoints the average power points in that level
     * @param avgHorsePower the average horse power in that level
     * @param avgPrice the average price in that level
     */
    public CarLevelStatistic(Integer level, Long noOfCars, double avgPowerPoints,
            double avgHorsePower, double avgPrice) {
        this.level = level;
        this.noOfCars = noOfCars;
        this.avgPowerPoints = avgPowerPoints;
        this.avgHorsePower = avgHorsePower;
        this.avgPrice = avgPrice;
    }

    /**
     * Creates a car level statistics with a passed object array. It depends on
     * the exact format of the array.<br>
     * Element 0 - car level - Integer<br>
     * Element 1 - number of cars - Long<br>
     * Element 2 - average power points - Double<br>
     * Element 3 - average horse power - Double<br>
     * Element 4 - average price - Double<br>
     *
     * @param carStatObjects the object array to create from
     * @throws CarLevelStatisticException if the object array is not the proper
     * format
     */
    public CarLevelStatistic(Object[] carStatObjects) throws CarLevelStatisticException {
        if (carStatObjects == null) {
            this.level = 0;
            this.noOfCars = 0L;
            this.avgPowerPoints = 0.0;
            this.avgHorsePower = 0.0;
            this.avgPrice = 0.0;
        } else {
            if (carStatObjects.length != 5) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_SIZE_WRONG
                        + carStatObjects.length);
            }

            if (!(carStatObjects[0] instanceof Integer)) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_0_NOT_INTEGER
                        + carStatObjects[0].getClass().getName());
            }

            if (!(carStatObjects[1] instanceof Long)) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_1_NOT_LONG
                        + carStatObjects[1].getClass().getName());
            }

            if (!(carStatObjects[2] instanceof Double)) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_2_NOT_DOUBLE
                        + carStatObjects[2].getClass().getName());
            }

            if (!(carStatObjects[3] instanceof Double)) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_3_NOT_DOUBLE
                        + carStatObjects[3].getClass().getName());
            }

            if (!(carStatObjects[4] instanceof Double)) {
                throw new CarLevelStatisticException(CarLevelStatisticException.CAR_STAT_OBJECT_4_NOT_DOUBLE
                        + carStatObjects[4].getClass().getName());
            }

            this.level = (Integer) carStatObjects[0];
            this.noOfCars = (Long) carStatObjects[1];
            this.avgPowerPoints = (Double) carStatObjects[2];
            this.avgHorsePower = (Double) carStatObjects[3];
            this.avgPrice = (Double) carStatObjects[4];
        }
    }

    /**
     * Get the level.
     *
     * @return The level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set the level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Get the number of car.
     *
     * @return The number of cars.
     */
    public Long getNoOfCars() {
        return noOfCars;
    }

    /**
     * Set the number of cars.
     *
     * @param noOfCars the number of cars
     */
    public void setNoOfCars(Long noOfCars) {
        this.noOfCars = noOfCars;
    }

    /**
     * Get the average power points.
     *
     * @return The average power points.
     */
    public double getAvgPowerPoints() {
        return avgPowerPoints;
    }

    /**
     * Set the average power points.
     *
     * @param avgPowerPoints the average power points
     */
    public void setAvgPowerPoints(double avgPowerPoints) {
        this.avgPowerPoints = avgPowerPoints;
    }

    /**
     * Get the average horse power.
     *
     * @return the average horse power
     */
    public double getAvgHorsePower() {
        return avgHorsePower;
    }

    /**
     * Set the average horse power.
     *
     * @param avgHorsePower the average horse power
     */
    public void setAvgHorsePower(double avgHorsePower) {
        this.avgHorsePower = avgHorsePower;
    }

    /**
     * Get the average price.
     *
     * @return The average price.
     */
    public double getAvgPrice() {
        return avgPrice;
    }

    /**
     * Set the average price.
     *
     * @param avgPrice the average price
     */
    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public String toString() {
        return "CarLevelStatistic{" + "level=" + level + ", noOfCars=" + noOfCars + ", avgPowerPoints=" + avgPowerPoints + ", avgHorsePower=" + avgHorsePower + ", avgPrice=" + avgPrice + '}';
    }
}

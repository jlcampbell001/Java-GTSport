package backEnd.general.statistics;

import backEnd.general.cars.Category;

/**
 * The object for the car category statistic record.
 *
 * @author jonathan
 */
public class CarCategoryStatistic {

    private Category category = Category.EMPTY;
    private Long noOfCars = 0L;
    private double avgMaxPower = 0L;
    private double avgPrice = 0.00;

    /**
     * Create a blank car category statistic.
     */
    public CarCategoryStatistic() {
    }

    /**
     * Create a car category statistic with the passed values.
     *
     * @param category the car category of the record
     * @param noOfCars the number of cars in that category
     * @param avgPrice the average price in that category
     */
    public CarCategoryStatistic(Category category, Long noOfCars, double avgMaxPower,
            double avgPrice) {
        this.category = category;
        this.noOfCars = noOfCars;
        this.avgMaxPower = avgMaxPower;
        this.avgPrice = avgPrice;
    }

    /**
     * Creates a car category statistics with a passed object array. It depends on
     * the exact format of the array.<br>
     * Element 0 - car category - Category<br>
     * Element 1 - number of cars - Long<br>
     * Element 2 - average max points - Double<br>
     * Element 3 - average price - Double<br>
     *
     * @param carStatObjects the object array to create from
     * @throws CarCategoryStatisticException if the object array is not the proper
     * format
     */
    public CarCategoryStatistic(Object[] carStatObjects) throws CarCategoryStatisticException {
        if (carStatObjects == null) {
            this.category = Category.EMPTY;
            this.noOfCars = 0L;
            this.avgMaxPower = 0.0;
            this.avgPrice = 0.0;
        } else {
            if (carStatObjects.length != 4) {
                throw new CarCategoryStatisticException(CarCategoryStatisticException.CAR_STAT_OBJECT_SIZE_WRONG
                        + carStatObjects.length);
            }

            if (!(carStatObjects[0] instanceof Category)) {
                throw new CarCategoryStatisticException(CarCategoryStatisticException.CAR_STAT_OBJECT_0_NOT_CATEGORY
                        + carStatObjects[0].getClass().getName());
            }

            if (!(carStatObjects[1] instanceof Long)) {
                throw new CarCategoryStatisticException(CarCategoryStatisticException.CAR_STAT_OBJECT_1_NOT_LONG
                        + carStatObjects[1].getClass().getName());
            }

            if (!(carStatObjects[2] instanceof Double)) {
                throw new CarCategoryStatisticException(CarCategoryStatisticException.CAR_STAT_OBJECT_2_NOT_DOUBLE
                        + carStatObjects[2].getClass().getName());
            }

            if (!(carStatObjects[3] instanceof Double)) {
                throw new CarCategoryStatisticException(CarCategoryStatisticException.CAR_STAT_OBJECT_3_NOT_DOUBLE
                        + carStatObjects[3].getClass().getName());
            }

            this.category = (Category) carStatObjects[0];
            this.noOfCars = (Long) carStatObjects[1];
            this.avgMaxPower = (Double) carStatObjects[2];
            this.avgPrice = (Double) carStatObjects[3];
        }
    }

    /**
     * Get the category.
     *
     * @return The category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set the category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
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
     * Get the average max power
     *
     * @return The average max power.
     */
    public double getAvgMaxPower() {
        return avgMaxPower;
    }

    /**
     * Set the average max power.
     *
     * @param avgMaxPower the average max power
     */
    public void setAvgMaxPower(double avgMaxPower) {
        this.avgMaxPower = avgMaxPower;
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
        return "CarCategoryStatistic[" + "category=" + category + ", noOfCars=" 
                + noOfCars + ", avgMaxPower=" + avgMaxPower 
                + ", avgPrice=" + avgPrice + ']';
    }

}

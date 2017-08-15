/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.statistics;

import backEnd.general.cars.CarException;

/**
 *
 * @author jonathan
 */
public class CarLevelStatistic {
    private Integer level = 0;
    private Long noOfCars = 0L;
    private double avgPowerPoints = 0L;
    private double avgHorsePower = 0L;
    private double avgPrice = 0.00;

    public CarLevelStatistic() {
    }

    public CarLevelStatistic(Integer level, Long noOfCars, double avgPowerPoints,
            double avgHorsePower, double avgPrice) {
        this.level = level;
        this.noOfCars = noOfCars;
        this.avgPowerPoints = avgPowerPoints;
        this.avgHorsePower = avgHorsePower;
        this.avgPrice = avgPrice;
    }
    
    public CarLevelStatistic(Object[] carStatObjects) throws CarException {
        if (carStatObjects == null) {
            this.level = 0;
            this.noOfCars = 0L;
            this.avgPowerPoints = 0.0;
            this.avgHorsePower = 0.0;
            this.avgPrice = 0.0;
        } else {
            if (!(carStatObjects[0] instanceof Integer)) {
                throw new CarException(CarException.CAR_STAT_OBJECT_0_NOT_INTEGER
                + carStatObjects[0].getClass().getName());
            }
            
            if (!(carStatObjects[1] instanceof Long)) {
                throw new CarException(CarException.CAR_STAT_OBJECT_1_NOT_LONG
                + carStatObjects[1].getClass().getName());
            }

            if (!(carStatObjects[2] instanceof Double)) {
                throw new CarException(CarException.CAR_STAT_OBJECT_2_NOT_DOUBLE
                + carStatObjects[2].getClass().getName());
            }
            
            if (!(carStatObjects[3] instanceof Double)) {
                throw new CarException(CarException.CAR_STAT_OBJECT_3_NOT_DOUBLE
                + carStatObjects[3].getClass().getName());
            }
            
            if (!(carStatObjects[4] instanceof Double)) {
                throw new CarException(CarException.CAR_STAT_OBJECT_4_NOT_DOUBLE
                + carStatObjects[4].getClass().getName());
            }
            
        this.level = (Integer) carStatObjects[0];
            this.noOfCars = (Long) carStatObjects[1];
            this.avgPowerPoints = (Double) carStatObjects[2];
            this.avgHorsePower = (Double) carStatObjects[3];
            this.avgPrice = (Double) carStatObjects[4];
        }
    }

    
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(Long noOfCars) {
        this.noOfCars = noOfCars;
    }

    public double getAvgPowerPoints() {
        return avgPowerPoints;
    }

    public void setAvgPowerPoints(double avgPowerPoints) {
        this.avgPowerPoints = avgPowerPoints;
    }

    public double getAvgHorsePower() {
        return avgHorsePower;
    }

    public void setAvgHorsePower(double avgHorsePower) {
        this.avgHorsePower = avgHorsePower;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public String toString() {
        return "CarLevelStatistic{" + "level=" + level + ", noOfCars=" + noOfCars + ", avgPowerPoints=" + avgPowerPoints + ", avgHorsePower=" + avgHorsePower + ", avgPrice=" + avgPrice + '}';
    }    
}

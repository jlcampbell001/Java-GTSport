/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

/**
 *
 * @author jonathan
 */
public class CarSearchJson {
    private Integer levelFrom = null;
    private Integer levelTo = null;
    private Integer yearFrom = null;
    private Integer yearTo = null;
    private Integer powerPointsFrom = null;
    private Integer powerPointsTo = null;
    private Integer horsePowerFrom = null;
    private Integer horsePowerTo = null;
    
    private String driveTrain = null;
    private String dealerName = null;

    public Integer getLevelFrom() {
        return levelFrom;
    }

    public void setLevelFrom(Integer levelFrom) {
        this.levelFrom = levelFrom;
    }

    public Integer getLevelTo() {
        return levelTo;
    }

    public void setLevelTo(Integer levelTo) {
        this.levelTo = levelTo;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    public Integer getPowerPointsFrom() {
        return powerPointsFrom;
    }

    public void setPowerPointsFrom(Integer powerPointsFrom) {
        this.powerPointsFrom = powerPointsFrom;
    }

    public Integer getPowerPointsTo() {
        return powerPointsTo;
    }

    public void setPowerPointsTo(Integer powerPointsTo) {
        this.powerPointsTo = powerPointsTo;
    }

    public Integer getHorsePowerFrom() {
        return horsePowerFrom;
    }

    public void setHorsePowerFrom(Integer horsePowerFrom) {
        this.horsePowerFrom = horsePowerFrom;
    }

    public Integer getHorsePowerTo() {
        return horsePowerTo;
    }

    public void setHorsePowerTo(Integer horsePowerTo) {
        this.horsePowerTo = horsePowerTo;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    
}

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
public enum DriveTrain {
    FOURWD ("4WD"),
    FF ("FF"),
    FR ("FR"),
    MR ("MR"),
    RR ("RR");
    
    private final String description;

    private DriveTrain(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public static DriveTrain toDriveTrain(String description) {
        DriveTrain driveTrain = null;
        
        for (DriveTrain value : DriveTrain.values()) {
            if (description.equals(value.description)) {
                driveTrain = value;
            }
        }
        
        return driveTrain;
    }
}

package backEnd.general.cars;

/**
 * The drivetrain of cars.<br>
 * Where the engine sits and how it runs the wheels.<br>
 * The description is the value is stored in the table.
 *
 * @author jonathan
 */
public enum DriveTrain {

    /**
     * Four-Wheel drive.
     */
    FOURWD("4WD"),
    /**
     * Front engine / Front-Wheel drive.
     */
    FF("FF"),
    /**
     * Front engine / Rear-Wheel drive.
     */
    FR("FR"),
    /**
     * Mid-Engine / Rear-Wheel drive.
     */
    MR("MR"),
    /**
     * Rear engine / Rear-Wheel drive.
     */
    RR("RR"),
    /**
     * Not set or not known.
     */
    EMPTY("");

    private final String description;

    private DriveTrain(String description) {
        this.description = description;
    }

    /**
     * Get the drivetrain description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the drivetrain that matches the passed description.<br>
     * If the description is not found then a null is returned.
     *
     * @param description the description to look up
     * @return the drivetrain found
     */
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

package backEnd.general.cars;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * The converter to convert the drivetrain to a string and vise versa for the
 * database.
 *
 * @author jonathan
 */
@Converter(autoApply = true)
public class DriveTrainConverter implements AttributeConverter<DriveTrain, String> {

    /**
     * Converts a drivetrain to a string value.
     *
     * @param driveTrain the drivetrain to convert
     * @return The string value of the drivetrain.
     */
    public String convertToDatabaseColumn(DriveTrain driveTrain) {
        return driveTrain.getDescription();
    }

    /**
     * Converts a string value to a drivetrain.<br>
     * Will return null if the string will not convert to a drivetrain.
     *
     * @param description the string value to look up
     * @return The drivetrain found.
     */
    public DriveTrain convertToEntityAttribute(String description) {
        return DriveTrain.toDriveTrain(description);
    }

}

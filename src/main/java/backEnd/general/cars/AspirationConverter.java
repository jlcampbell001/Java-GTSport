package backEnd.general.cars;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts the Aspiration Enum to a string and vise versa for the database.
 *
 * @author jonathan
 */
@Converter(autoApply = true)
public class AspirationConverter implements AttributeConverter<Aspiration, String> {

    /**
     * Convert the Aspiration to a string value to store in the database.
     *
     * @param aspiration the aspiration to convert
     * @return The string value of the aspiration.
     */
    public String convertToDatabaseColumn(Aspiration aspiration) {
        return aspiration.getDescription();
    }

    /**
     * Convert a string value from the database to an aspiration. Will return
     * null if the aspiration can not be found.
     *
     * @param description the string value to look for
     * @return The aspiration found or null if not found.
     */
    public Aspiration convertToEntityAttribute(String description) {
        return Aspiration.toAspiration(description);
    }
}

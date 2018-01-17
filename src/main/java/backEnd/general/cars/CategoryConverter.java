package backEnd.general.cars;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts a category in the database to a car category.
 *
 * @author jonathan
 */
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    /**
     * Converts the category to a database value.
     *
     * @param category the category to convert
     * @return the database value
     */
    public String convertToDatabaseColumn(Category category) {
        return category.getDBValue();
    }

    /**
     * Converts a database value to a category.
     *
     * @param dbValue the database value to convert
     * @return the category
     */
    public Category convertToEntityAttribute(String dbValue) {
        return Category.toCategoryFromDBValue(dbValue);
    }
}

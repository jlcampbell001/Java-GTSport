/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jonathan
 */
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String>{

    public String convertToDatabaseColumn(Category category) {
        return category.getDBValue();
    }

    public Category convertToEntityAttribute(String dbValue) {
        return Category.toCategoryFromDBValue(dbValue);
    }
}

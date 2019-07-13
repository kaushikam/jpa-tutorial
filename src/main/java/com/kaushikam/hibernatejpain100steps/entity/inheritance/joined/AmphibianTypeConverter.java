package com.kaushikam.hibernatejpain100steps.entity.inheritance.joined;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmphibianTypeConverter implements AttributeConverter<Amphibian.AmphibianType, String> {

    @Override
    public Amphibian.AmphibianType convertToEntityAttribute(String dbData) {
        return Amphibian.AmphibianType.valueOf(dbData);
    }

    @Override
    public String convertToDatabaseColumn(Amphibian.AmphibianType attribute) {
        return attribute.name();
    }
}

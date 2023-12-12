package com.str.shootingresulttracker.domain.infrastructure;


import com.str.shootingresulttracker.domain.model.Distance;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DistanceConverter implements AttributeConverter<Distance, Float> {

    @Override
    public Float convertToDatabaseColumn(Distance attribute) {
        return attribute.getMeters();
    }

    @Override
    public Distance convertToEntityAttribute(Float dbData) {
        return Distance.ofMeters(dbData);
    }
}

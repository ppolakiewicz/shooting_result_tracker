package com.str.shootingresulttracker.domain.infrastructure;


import com.str.shootingresulttracker.domain.model.Distance;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DistanceConverter implements AttributeConverter<Distance, Double> {

    @Override
    public Double convertToDatabaseColumn(Distance attribute) {
        return attribute.getInMeters();
    }

    @Override
    public Distance convertToEntityAttribute(Double dbData) {
        return Distance.ofMeters(dbData);
    }
}

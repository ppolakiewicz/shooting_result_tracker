package com.str.shootingresulttracker.domain.infrastructure;

import com.str.shootingresulttracker.domain.training.focus.MoaFocus;
import jakarta.persistence.AttributeConverter;

public class MoaFocusConverter implements AttributeConverter<MoaFocus, Double> {

    @Override
    public Double convertToDatabaseColumn(MoaFocus attribute) {
        return attribute.getValue();
    }

    @Override
    public MoaFocus convertToEntityAttribute(Double dbData) {
        return MoaFocus.withValue(dbData);
    }
}

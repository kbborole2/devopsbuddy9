package com.devopsbuddy9.backend.persistence.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by kb on 7/24/2017.
 */
@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {



    public Timestamp convertToDatabaseColumn(LocalDateTime localeDateTime){
        return (localeDateTime == null ? null: Timestamp.valueOf(localeDateTime));
    }


    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp){
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}

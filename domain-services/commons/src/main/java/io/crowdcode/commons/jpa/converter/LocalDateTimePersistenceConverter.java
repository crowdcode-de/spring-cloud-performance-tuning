package io.crowdcode.commons.jpa.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    private static final Logger log = LoggerFactory.getLogger(LocalDateTimePersistenceConverter.class);

    public LocalDateTimePersistenceConverter() {
        log.debug("CROWDCODE-COMMONS | Initializing LocalDateTimePersistenceConverter");
    }


    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        if (entityValue != null) {
            return Timestamp.valueOf(entityValue);
        } else {
            return null;
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalDateTime();
        } else {
            return null;
        }
    }

}
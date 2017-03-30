package io.crowdcode.commons.jpa.converter;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocalDateTimePersistenceConverterTest {

    private final LocalDateTimePersistenceConverter converter = new LocalDateTimePersistenceConverter();

    @Test
    public void testFromEntityToDatabaseAndBack() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        assertThat(converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now)), is(now));
    }

    @Test
    public void testFromNullToDatabaseAndBack() throws Exception {
        assertThat(converter.convertToEntityAttribute(converter.convertToDatabaseColumn(null)),
                is(nullValue(LocalDateTime.class)));

    }

    @Test
    public void testFromNullToEntityAndBack() throws Exception {
        assertThat(converter.convertToDatabaseColumn(converter.convertToEntityAttribute(null)), is(nullValue(Timestamp.class)));
    }

    @Test
    public void testFromDatabaseToEntityAndBack() throws Exception {
        Timestamp now = Timestamp.valueOf("2017-03-29 00:00:00");
        assertThat(converter.convertToDatabaseColumn(converter.convertToEntityAttribute(now)), equalTo(now));
    }
}
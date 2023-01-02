package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

// @JsonDeserialize(using = CustomCalendarDeserializer.class)
public class CustomCalendarDeserializer extends JsonDeserializer<Calendar> {

    @Override
    public Calendar deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String dateAsString = jsonparser.getText();
        try {
            Date date = CustomCalendarSerializer.FORMATTER.parse(dateAsString);
            Calendar calendar = Calendar.getInstance(
                    CustomCalendarSerializer.LOCAL_TIME_ZONE,
                    CustomCalendarSerializer.LOCALE_HUNGARIAN
            );
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
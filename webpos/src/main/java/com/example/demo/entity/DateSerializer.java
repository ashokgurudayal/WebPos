package com.example.demo.entity;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateSerializer extends JsonSerializer<Date> {

    final static DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    static {
        df.setTimeZone(TimeZone.getTimeZone("EST"));
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(df.format(value));
    }
}

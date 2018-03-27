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

import org.assertj.core.util.DateUtil;
import org.thymeleaf.util.DateUtils;

public class DateSerializerr extends JsonSerializer<Date> {

    final static DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    static {
        df.setTimeZone(TimeZone.getTimeZone("EST"));
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        long l = value.getTime()+3600*1000;
    	gen.writeString(df.format(l));
    }
}

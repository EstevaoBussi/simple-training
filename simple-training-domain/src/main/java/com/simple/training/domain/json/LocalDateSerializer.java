package com.simple.training.domain.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate>{

	private static final long serialVersionUID = 1L;

	public LocalDateSerializer() {
        this(null);
    }
   
    public LocalDateSerializer(Class<LocalDate> t) {
        super(t);
    }

	@Override
	public void serialize(LocalDate date, JsonGenerator json, SerializerProvider arg2) throws IOException {
		if (date != null) {
    		json.writeString(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
	}
	
}


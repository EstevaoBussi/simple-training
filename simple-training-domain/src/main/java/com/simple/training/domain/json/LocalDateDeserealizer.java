package com.simple.training.domain.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserealizer extends StdDeserializer<LocalDate>{

	private static final long serialVersionUID = 1L;

	public LocalDateDeserealizer() {
        this(null);
    }
   
    public LocalDateDeserealizer(Class<LocalDate> t) {
        super(t);
    }

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.hasNonNull(0)) {
			return LocalDate.parse(node.get(0).asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
        return null;
	}
	
}

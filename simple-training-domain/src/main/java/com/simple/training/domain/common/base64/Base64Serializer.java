package com.simple.training.domain.common.base64;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class Base64Serializer extends StdSerializer<Base64>{

	private static final long serialVersionUID = 1L;

	public Base64Serializer() {
        this(null);
    }
   
    public Base64Serializer(Class<Base64> t) {
        super(t);
    }

	@Override
	public void serialize(Base64 image, JsonGenerator json, SerializerProvider arg2) throws IOException {
		if (image != null) {
			json.writeStartObject();
			if (image.getType() != null && image.getSrc() != null) {
				json.writeStringField("type", image.getType());
				json.writeStringField("src", java.util.Base64.getEncoder().encodeToString(image.getSrc()));
			}
			json.writeEndObject();
		}
	}
	
}


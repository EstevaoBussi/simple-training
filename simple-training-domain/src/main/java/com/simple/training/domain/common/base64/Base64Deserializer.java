package com.simple.training.domain.common.base64;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class Base64Deserializer extends StdDeserializer<Base64>{

	private static final long serialVersionUID = 1L;

	public Base64Deserializer() {
        this(null);
    }
   
    public Base64Deserializer(Class<Base64> t) {
        super(t);
    }

	@Override
	public Base64 deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node != null) {
			Base64 image = new Base64();
			if (node.has("type") && node.has("src")) {
				image.setType(node.get("type").asText());
				image.setSrc(java.util.Base64.getDecoder().decode(node.get("src").asText()));
			}
			return image;
		}
        return null;
	}
	
}

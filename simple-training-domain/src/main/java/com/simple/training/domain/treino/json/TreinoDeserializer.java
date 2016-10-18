package com.simple.training.domain.treino.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.service.TreinoService;

public class TreinoDeserializer extends StdDeserializer<Treino>{

	private static final long serialVersionUID = 1L;
	@Autowired
	private TreinoService treinoService;

	public TreinoDeserializer() {
        this(null);
    }
   
    public TreinoDeserializer(Class<Treino> t) {
        super(t);
    }

	@Override
	public Treino deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node != null) {
			return treinoService.get(node.get("id").asLong());
		}
        return null;
	}
	
}

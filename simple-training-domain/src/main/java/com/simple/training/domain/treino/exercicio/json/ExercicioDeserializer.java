package com.simple.training.domain.treino.exercicio.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.simple.training.domain.treino.exercicio.Exercicio;
import com.simple.training.domain.treino.exercicio.service.ExercicioService;

public class ExercicioDeserializer extends StdDeserializer<Exercicio>{

	private static final long serialVersionUID = 1L;
	@Autowired
	private ExercicioService exercicioService;

	public ExercicioDeserializer() {
        this(null);
    }
   
    public ExercicioDeserializer(Class<Exercicio> t) {
        super(t);
    }

	@Override
	public Exercicio deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node != null) {
			return exercicioService.get(node.get("id").asLong());
		}
        return null;
	}
	
}

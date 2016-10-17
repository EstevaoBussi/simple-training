package com.simple.training.domain.treino.exercicio.json;

import java.io.IOException;
import java.util.Base64;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.simple.training.domain.treino.exercicio.Exercicio;

public class ExercicioSerializer extends StdSerializer<Exercicio>{

	private static final long serialVersionUID = 1L;

	public ExercicioSerializer() {
        this(null);
    }
   
    public ExercicioSerializer(Class<Exercicio> t) {
        super(t);
    }

	@Override
	public void serialize(Exercicio exercicio, JsonGenerator json, SerializerProvider arg2) throws IOException {
		if (exercicio != null) {
			json.writeStartObject();
			if (exercicio.getID() != null) {
				json.writeNumberField("id", exercicio.getID());
			}
			if (!StringUtils.isEmpty(exercicio.getDescricao())) {
				json.writeStringField("descricao", exercicio.getDescricao());
			}
			if (!StringUtils.isEmpty(exercicio.getNome())) {
				json.writeStringField("nome", exercicio.getNome());
			}
			if (exercicio.getGrupoMuscular() != null) {
				json.writeStringField("grupoMuscular", exercicio.getGrupoMuscular().name());
			}
			if (exercicio.getImage() != null && exercicio.getImage().getType() != null 
					&& exercicio.getImage().getSrc() != null) {
				json.writeObjectFieldStart("image");
				json.writeStringField("type", exercicio.getImage().getType());
				json.writeStringField("src", Base64.getEncoder().encodeToString(exercicio.getImage().getSrc()));
				json.writeEndObject();
			}
			json.writeEndObject();
		}
	}
	
}


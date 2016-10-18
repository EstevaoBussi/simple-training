package com.simple.training.domain.treino.json;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.simple.training.domain.usuario.Usuario;

public class TreinoSerializer extends StdSerializer<Usuario>{

	private static final long serialVersionUID = 1L;

	public TreinoSerializer() {
        this(null);
    }
   
    public TreinoSerializer(Class<Usuario> t) {
        super(t);
    }

	@Override
	public void serialize(Usuario usuario, JsonGenerator json, SerializerProvider arg2) throws IOException {
		if (usuario != null) {
			json.writeStartObject();
			if (!StringUtils.isEmpty(usuario.getUsername())) {
				json.writeStringField("username",usuario.getUsername());
			}
			if (usuario.getDataNascimento() != null) {
				json.writeStringField("dataNascimento", usuario.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
			if (usuario.getEmail() != null) {
				json.writeStringField("email", usuario.getEmail());
			}
			if (!StringUtils.isEmpty(usuario.getNome())) {
				json.writeStringField("nome", usuario.getNome());
			}
			json.writeEndObject();
		}
	}
	
}

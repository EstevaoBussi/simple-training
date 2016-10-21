package com.simple.training.domain.usuario.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.simple.training.domain.usuario.Usuario;
import com.simple.training.domain.usuario.service.UsuarioService;

public class UsuarioDeserializer extends StdDeserializer<Usuario>{

	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioService usuarioService;

	public UsuarioDeserializer() {
        this(null);
    }
   
    public UsuarioDeserializer(Class<Usuario> t) {
        super(t);
    }

	@Override
	public Usuario deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node != null) {
			return usuarioService.getUsuario(node.get("codigo").asLong());
		}
        return null;
	}
	
}

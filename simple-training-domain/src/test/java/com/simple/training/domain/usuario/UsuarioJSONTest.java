package com.simple.training.domain.usuario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Test;

import com.simple.training.domain.json.support.AbstractJSONTestSupport;

public class UsuarioJSONTest extends AbstractJSONTestSupport {

    @Test
    public void jsonCompareFull() throws URISyntaxException, IOException {
        Usuario entity = new Usuario();
        entity.setUsername("USERNAME");
        entity.setDataNascimento(LocalDate.of(2001, 1, 1));
        entity.setEmail("EMAIL");
        entity.setNome("NOME");
        entity.setPerfil(Perfil.ADMINISTRADOR);
        compareJson("complete.json", entity);
    }

    @Test
    public void jsonCompareEmpty() throws URISyntaxException, IOException {
    	Usuario entity = new Usuario();
        compareJson("empty.json", entity);
    }
    
}

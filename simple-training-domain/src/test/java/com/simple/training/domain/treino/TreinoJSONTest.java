package com.simple.training.domain.treino;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Test;

import com.simple.training.domain.json.support.AbstractJSONTestSupport;
import com.simple.training.domain.usuario.Usuario;

public class TreinoJSONTest extends AbstractJSONTestSupport {

    @Test
    public void jsonCompareFull() throws URISyntaxException, IOException {
        Treino entity = new Treino();
        entity.setID(1L);
        entity.setDataInicio(LocalDate.of(2001, 1, 1));
        entity.setDataFim(LocalDate.of(2001, 1, 10));
        entity.setCategoria(Categoria.ACADEMIA);
        entity.setClassificacao("A");
        entity.setUsuario(new Usuario());
        compareJson("complete.json", entity);
    }

    @Test
    public void jsonCompareEmpty() throws URISyntaxException, IOException {
    	Treino entity = new Treino();
        compareJson("empty.json", entity);
    }
    
}

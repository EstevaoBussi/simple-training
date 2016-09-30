package com.simple.training.domain.treino.exercicio;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.simple.training.domain.json.support.AbstractJSONTestSupport;

public class ExercicioJSONTest extends AbstractJSONTestSupport {

    @Test
    public void jsonCompareFull() throws URISyntaxException, IOException {
        Exercicio exercicio = new Exercicio();
        exercicio.setID(1L);
        exercicio.setNome("NOME");
        exercicio.setGrupoMuscular(GrupoMuscular.ABDOMINAIS);
        exercicio.setDescricao("DESCRICAO");
        compareJson("complete.json", exercicio);
    }

    @Test
    public void jsonCompareEmpty() throws URISyntaxException, IOException {
    	Exercicio exercicio = new Exercicio();
        compareJson("empty.json", exercicio);
    }
    
}

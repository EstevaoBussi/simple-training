package com.simple.training.web.api.treino;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.treino.Categoria;

@RestController
@RequestMapping("/api/v1/treinos/categorias")
public class CategoriaTreinoController {
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Categoria> getAllCategoria() throws Exception {
		return Arrays.asList(Categoria.values());
    }
	
}

package com.simple.training.web.api.treino;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.training.domain.treino.exercicio.Exercicio;
import com.simple.training.domain.treino.exercicio.service.ExercicioService;
import com.simple.training.web.api.exception.CampoObrigatorioException;

@RestController
@RequestMapping("/api/v1/exercicios")
public class ExercicioController {

	@Autowired
	private ExercicioService service;
	
	@RequestMapping(method = RequestMethod.GET)
    public Exercicio get(@RequestParam(value="id") Long id) throws Exception {
		if (id == null) {
    		throw new CampoObrigatorioException("id");
		}
        return service.get(id);
    }
	
	@Transactional
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Exercicio update(@RequestBody final Exercicio exercicio) throws Exception {
        return service.save(exercicio);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Exercicio create(@RequestBody final Exercicio exercicio) throws Exception {
    	return service.save(exercicio);
    }
    
}

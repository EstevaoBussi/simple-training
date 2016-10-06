package com.simple.training.domain.treino.exercicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.training.domain.treino.exercicio.Exercicio;
import com.simple.training.domain.treino.exercicio.ExercicioRepository;

@Service
public class ExercicioServiceImpl implements ExercicioService {

    @Autowired
    private ExercicioRepository repository;

	@Override
	public Exercicio get(Long id) {
		return repository.getOne(id);
	}
	
	@Override
	public Exercicio save(Exercicio exercicio) {
		return repository.save(exercicio);
	}
	
}

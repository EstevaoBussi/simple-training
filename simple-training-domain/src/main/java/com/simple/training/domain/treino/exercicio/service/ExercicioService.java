package com.simple.training.domain.treino.exercicio.service;

import java.util.List;

import com.simple.training.domain.treino.exercicio.Exercicio;

public interface ExercicioService {
    
	List<Exercicio> findByName(String name);
    Exercicio get(Long id);
    Exercicio save(Exercicio exercicio);
    
}

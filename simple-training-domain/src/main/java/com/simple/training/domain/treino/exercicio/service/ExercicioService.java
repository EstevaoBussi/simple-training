package com.simple.training.domain.treino.exercicio.service;

import com.simple.training.domain.treino.exercicio.Exercicio;

public interface ExercicioService {
    
    Exercicio get(Long id);
    Exercicio save(Exercicio exercicio);
    
}

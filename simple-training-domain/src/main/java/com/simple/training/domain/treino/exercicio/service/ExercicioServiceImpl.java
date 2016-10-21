package com.simple.training.domain.treino.exercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.training.domain.treino.exercicio.Exercicio;
import com.simple.training.domain.treino.exercicio.ExercicioRepository;
import com.simple.training.domain.treino.exercicio.ExercicioSpecifications;

@Service
public class ExercicioServiceImpl implements ExercicioService {

    @Autowired
    private ExercicioRepository repository;

    @Override
	public List<Exercicio> findByName(String name) {
    	Specification<Exercicio> spec = ExercicioSpecifications.filterName(name);
		return repository.findAll(spec, new Sort(Direction.ASC,"nome"));
	}
    
	@Override
	public Exercicio get(Long id) {
		return repository.getOne(id);
	}
	
	@Override
	public Exercicio save(Exercicio exercicio) {
		return repository.save(exercicio);
	}
	
}

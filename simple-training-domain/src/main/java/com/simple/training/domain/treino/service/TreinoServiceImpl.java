package com.simple.training.domain.treino.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.training.domain.treino.ExercicioVariante;
import com.simple.training.domain.treino.LocalTreino;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.treino.TreinoRepository;
import com.simple.training.domain.treino.TreinoSpecifications;
import com.simple.training.domain.usuario.Usuario;

@Service
public class TreinoServiceImpl implements TreinoService {

    @Autowired
    private TreinoRepository repository;

	@Override
	public List<Treino> getTreinos(LocalDate data, LocalTreino local, Usuario usuario) {
		Specification<Treino> spec = TreinoSpecifications.withFilters(data, local, usuario);
		return repository.findAll(spec);
	}
	
	@Override
	public Treino save(Treino treino) {
		if (!treino.getExercicios().isEmpty()) {
			for (ExercicioVariante exercicio : treino.getExercicios()) {
				exercicio.setTreino(treino);
			}
		}
		return repository.save(treino);
	}
	
}

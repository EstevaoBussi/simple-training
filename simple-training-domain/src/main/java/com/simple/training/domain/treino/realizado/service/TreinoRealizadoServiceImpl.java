package com.simple.training.domain.treino.realizado.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.treino.realizado.TreinoRealizado;
import com.simple.training.domain.treino.realizado.TreinoRealizadoRepository;
import com.simple.training.domain.treino.realizado.TreinoRealizadoSpecifications;
import com.simple.training.domain.usuario.Usuario;

@Service
public class TreinoRealizadoServiceImpl implements TreinoRealizadoService {

    @Autowired
    private TreinoRealizadoRepository repository;
	
	@Override
	public List<TreinoRealizado> getTreinos(LocalDate dataInicio, LocalDate dataFim, Categoria categoria, Usuario usuario) {
		Specification<TreinoRealizado> spec = TreinoRealizadoSpecifications.withFilters(dataInicio, dataFim, categoria, usuario);
		return repository.findAll(spec, new Sort(Direction.DESC,"data"));
	}

	@Override
	public TreinoRealizado save(TreinoRealizado treinoRealizado) {
		return repository.save(treinoRealizado);
	}
		
}

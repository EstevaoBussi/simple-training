package com.simple.training.domain.nutri.cardapio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.training.domain.nutri.cardapio.Cardapio;
import com.simple.training.domain.nutri.cardapio.CardapioRepository;
import com.simple.training.domain.nutri.cardapio.CardapioSpecifications;
import com.simple.training.domain.usuario.Usuario;

@Service
public class CardapioServiceImpl implements CardapioService {

    @Autowired
    private CardapioRepository repository;

	@Override
	public List<Cardapio> getCardapios(LocalDate date, Usuario usuario) {
		Specification<Cardapio> spec = CardapioSpecifications.withFilters(date, usuario);
		return repository.findAll(spec, new Sort(Direction.ASC,"data"));
	}

	@Override
	public Cardapio save(Cardapio cardapio) {
		return repository.save(cardapio);
	}
	
}

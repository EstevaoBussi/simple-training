package com.simple.training.domain.nutri.cardapio.service;

import java.time.LocalDate;
import java.util.List;

import com.simple.training.domain.nutri.cardapio.Cardapio;
import com.simple.training.domain.usuario.Usuario;

public interface CardapioService {
    
	List<Cardapio> getCardapios(LocalDate date, Usuario usuario);
    Cardapio save(Cardapio cardapio);
    
}

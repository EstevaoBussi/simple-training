package com.simple.training.domain.treino.realizado.service;

import java.time.LocalDate;
import java.util.List;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.treino.realizado.TreinoRealizado;
import com.simple.training.domain.usuario.Usuario;

public interface TreinoRealizadoService {

	List<TreinoRealizado> getTreinos(LocalDate dataInicio, LocalDate dataFim, Categoria local, Usuario usuario);
    TreinoRealizado save(TreinoRealizado treinoRealizado);
    
}

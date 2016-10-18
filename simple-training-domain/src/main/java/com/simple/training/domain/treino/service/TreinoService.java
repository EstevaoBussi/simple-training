package com.simple.training.domain.treino.service;

import java.time.LocalDate;
import java.util.List;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.usuario.Usuario;

public interface TreinoService {

	Treino get(Long id);
    List<Treino> getTreinos(LocalDate data, Categoria local, Usuario usuario);
    Treino save(Treino treino);
    
}

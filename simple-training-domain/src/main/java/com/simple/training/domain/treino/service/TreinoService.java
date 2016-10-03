package com.simple.training.domain.treino.service;

import java.time.LocalDate;
import java.util.List;

import com.simple.training.domain.treino.LocalTreino;
import com.simple.training.domain.treino.Treino;
import com.simple.training.domain.usuario.Usuario;

public interface TreinoService {

    
    List<Treino> getTreinos(LocalDate data, LocalTreino local, Usuario usuario);
    Treino save(Treino treino);
    
}

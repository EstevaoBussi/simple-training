package com.simple.training.domain.treino;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.simple.training.domain.usuario.Usuario;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>, JpaSpecificationExecutor<Treino> {
	List<Treino> findByUsuarioOrderById(LocalDate data, Usuario usuario);
}
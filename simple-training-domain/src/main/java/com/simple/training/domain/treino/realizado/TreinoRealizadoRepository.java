package com.simple.training.domain.treino.realizado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRealizadoRepository extends JpaRepository<TreinoRealizado, Long>, JpaSpecificationExecutor<TreinoRealizado> {
	
}
package com.simple.training.domain.treino.realizado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.usuario.Usuario;

public class TreinoRealizadoSpecifications {

    public static Specification<TreinoRealizado> withFilters(LocalDate dataInicio, LocalDate dataFim, Categoria local, Usuario usuario) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate = cb.lessThanOrEqualTo(root.get("data"), dataInicio);
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("data"), dataFim);
            predicates.add(predicate);

            predicate = cb.equal(root.get("treino.categoria"), local);
            predicates.add(predicate);
            predicate = cb.equal(root.get("treino.usuario"), usuario);
            predicates.add(predicate);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}

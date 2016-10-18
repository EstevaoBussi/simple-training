package com.simple.training.domain.treino.realizado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.simple.training.domain.treino.Categoria;
import com.simple.training.domain.usuario.Usuario;

public class TreinoRealizadoSpecifications {

    public static Specification<TreinoRealizado> withFilters(LocalDate dataInicio, LocalDate dataFim, Categoria categoria, Usuario usuario) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate = cb.lessThanOrEqualTo(root.get("data"), dataFim);
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("data"), dataInicio);
            predicates.add(predicate);

            predicate = cb.equal(root.join("treino",JoinType.INNER).get("categoria"), categoria);
            predicates.add(predicate);
            
            predicate = cb.equal(root.join("treino",JoinType.INNER).get("usuario"), usuario);
            predicates.add(predicate);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}

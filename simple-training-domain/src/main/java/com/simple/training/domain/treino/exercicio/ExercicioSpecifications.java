package com.simple.training.domain.treino.exercicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ExercicioSpecifications {

    public static Specification<Exercicio> filterName(String nome) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (!StringUtils.isEmpty(nome)) {
                Predicate pred = cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
                predicates.add(pred);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

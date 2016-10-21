package com.simple.training.domain.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UsuarioSpecifications {

    public static Specification<Usuario> filterName(String nome) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (!StringUtils.isEmpty(nome)) {
                Predicate pred = cb.like(root.get("nome"), "%" + nome.toUpperCase() + "%");
                predicates.add(pred);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

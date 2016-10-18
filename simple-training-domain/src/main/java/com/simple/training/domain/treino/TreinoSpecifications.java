package com.simple.training.domain.treino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.simple.training.domain.usuario.Usuario;

public class TreinoSpecifications {

    public static Specification<Treino> withFilters(LocalDate dataTreino, Categoria local, Usuario usuario) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate = cb.lessThanOrEqualTo(root.get("dataInicio"), dataTreino);
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("dataFim"), dataTreino);
            predicates.add(predicate);

            predicate = cb.equal(root.get("categoria"), local);
            predicates.add(predicate);
            predicate = cb.equal(root.get("usuario"), usuario);
            predicates.add(predicate);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

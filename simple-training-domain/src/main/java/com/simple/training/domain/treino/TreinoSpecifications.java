package com.simple.training.domain.treino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.simple.training.domain.usuario.Usuario;

public class TreinoSpecifications {

    public static Specification<Treino> withFilters(LocalDate dataTreino, LocalTreino local, Usuario usuario) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate = cb.lessThanOrEqualTo(root.get("data"), dataTreino.plusDays(3L));
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("data"), dataTreino.minusDays(3L));
            predicates.add(predicate);

            predicate = cb.equal(root.get("localTreino"), local);
            predicates.add(predicate);
            predicate = cb.equal(root.get("usuario"), usuario);
            predicates.add(predicate);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

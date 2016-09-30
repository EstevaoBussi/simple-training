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

            Predicate predicate = cb.lessThanOrEqualTo(root.get("DATA"), dataTreino.plusDays(3L));
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("dataFim"), dataTreino.minusDays(3L));
            predicates.add(predicate);

            predicate = cb.equal(root.get("LOCAL_TREINO"), local);
            predicate = cb.equal(root.get("USUARIO"), usuario);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.simple.training.domain.nutri.cardapio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.simple.training.domain.usuario.Usuario;

public class CardapioSpecifications {

    public static Specification<Cardapio> withFilters(LocalDate date, Usuario usuario) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate = cb.lessThanOrEqualTo(root.get("data"), date.plusDays(3L));
            predicates.add(predicate);

            predicate = cb.greaterThanOrEqualTo(root.get("data"), date.minusDays(3L));
            predicates.add(predicate);

            predicate = cb.equal(root.get("usuario"), usuario);
            predicates.add(predicate);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

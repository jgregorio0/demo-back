package dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER};

import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class ${DOMAIN_CAPITAL}Specification {

  private ${DOMAIN_CAPITAL}Specification() {
    throw new IllegalStateException("Class is not instantiable.");
  }

  public static Specification<${DOMAIN_CAPITAL}Entity> search(final ${DOMAIN_CAPITAL}Search criteria) {
    return (root, query, cb) -> {
      final List<Predicate> predicates = searchPredicates(root, query, cb, criteria);
      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  private static List<Predicate> searchPredicates(
      Root<${DOMAIN_CAPITAL}Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb, ${DOMAIN_CAPITAL}Search criteria) {
    final List<Predicate> predicates = new ArrayList<>();
    if (StringUtils.isNotBlank(criteria.term())) { // TODO review criteria
      predicates.add(isNameLike(criteria.term()).toPredicate(root, query, cb));
    }
    return predicates;
  }

  private static Specification<${DOMAIN_CAPITAL}Entity> isNameLike(String term) {
    return (root, query, cb) -> {
      // review predicate
      final String likeTerm = "%" + term.toLowerCase() + "%";
      return cb.like(cb.lower(root.get(${DOMAIN_CAPITAL}Entity_.NAME)), likeTerm);
    };
  }
}
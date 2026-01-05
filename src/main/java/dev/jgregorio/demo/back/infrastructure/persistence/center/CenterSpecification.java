package dev.jgregorio.demo.back.infrastructure.persistence.center;

import dev.jgregorio.demo.back.domain.center.CenterSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

public class CenterSpecification {

  private CenterSpecification() {
    throw new IllegalStateException("Utility class");
  }

  public static Specification<CenterEntity> search(final CenterSearch criteria) {
    return (root, query, cb) -> {
      final List<Predicate> predicates = new ArrayList<>();

      if (criteria.name() != null && !criteria.name().isBlank()) {
        predicates.add(name(criteria.name()).toPredicate(root, query, cb));
      }
      if (criteria.address() != null && !criteria.address().isBlank()) {
        predicates.add(address(criteria.address()).toPredicate(root, query, cb));
      }
      if (criteria.postalCode() != null && !criteria.postalCode().isBlank()) {
        predicates.add(postalCode(criteria.postalCode()).toPredicate(root, query, cb));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  public static Specification<CenterEntity> name(String name) {
    return (root, query, cb) -> collate(cb, root.get(CenterEntity_.name), name);
  }

  public static Specification<CenterEntity> postalCode(String postalCode) {
    return (root, query, cb) -> collate(cb, root.get(CenterEntity_.postalCode), postalCode);
  }

  public static Specification<CenterEntity> address(String address) {
    return (root, query, cb) -> collate(cb, root.get(CenterEntity_.address), address);
  }

  private static Predicate collate(CriteriaBuilder cb, Path<String> path, String value) {
    HibernateCriteriaBuilder hcb = (HibernateCriteriaBuilder) cb;
    return cb.like(hcb.collate(cb.lower(path), "BINARY_AI"), like(value));
  }

  private static String like(String value) {
    return "%" + value.trim().toLowerCase() + "%";
  }
}

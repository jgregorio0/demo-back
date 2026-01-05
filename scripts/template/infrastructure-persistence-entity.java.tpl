package dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER};

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "${TABLE}", schema = "${SCHEMA}")
@Getter
@Setter
public class ${DOMAIN_CAPITAL}Entity {

  @Id
  @Column(name = "ID", nullable = false) // review attributes
  private Long id;

  @Column(name = "NAME")
  private String name;
}
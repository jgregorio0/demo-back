package dev.jgregorio.demo.back.infrastructure.persistence.client;

import dev.jgregorio.demo.back.infrastructure.persistence.audit.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CLIENTS", schema = "TEST")
@Getter
@Setter
public class ClientEntity extends AuditorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQ")
  @SequenceGenerator(name = "CLIENTS_SEQ", sequenceName = "TEST.CLIENTS_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;
}

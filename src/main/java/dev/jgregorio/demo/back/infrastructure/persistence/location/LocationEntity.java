package dev.jgregorio.demo.back.infrastructure.persistence.location;

import dev.jgregorio.demo.back.infrastructure.persistence.audit.AuditorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOCATIONS", schema = "TEST")
@Getter
@Setter
public class LocationEntity extends AuditorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATIONS_SEQ")
  @SequenceGenerator(
      name = "LOCATIONS_SEQ",
      sequenceName = "TEST.LOCATIONS_SEQ",
      allocationSize = 1)
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "NAME")
  private String name;
}

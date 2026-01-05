package dev.jgregorio.demo.back.infrastructure.persistence.user;

import dev.jgregorio.demo.back.infrastructure.persistence.audit.AuditorEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

@Entity
@Table(name = "USERS", schema = "TEST")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AuditorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
  @SequenceGenerator(name = "USERS_SEQ", sequenceName = "TEST.USERS_SEQ", allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SURNAME")
  private String surname;

  @Column(name = "ENABLED")
  @Convert(converter = NumericBooleanConverter.class)
  private Boolean enabled;

  @Column(name = "PASSWORD_RESET_DATE")
  private LocalDateTime lastPasswordResetDate;
}

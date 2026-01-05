package dev.jgregorio.demo.back.infrastructure.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditorEntity {

  @CreatedBy
  @Column(name = "CREATED_BY", nullable = false, updatable = false)
  private Long createdBy;

  @CreatedDate
  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "MODIFIED_BY")
  private Long modifiedBy;

  @LastModifiedDate
  @Column(name = "MODIFIED_DATE")
  private LocalDateTime modifiedDate;
}

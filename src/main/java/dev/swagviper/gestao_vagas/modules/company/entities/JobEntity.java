package dev.swagviper.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

package dev.swagviper.gestao_vagas.modules.company.repositories;

import dev.swagviper.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}

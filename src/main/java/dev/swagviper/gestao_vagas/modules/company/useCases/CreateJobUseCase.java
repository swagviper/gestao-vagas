package dev.swagviper.gestao_vagas.modules.company.useCases;

import dev.swagviper.gestao_vagas.modules.company.entities.JobEntity;
import dev.swagviper.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}

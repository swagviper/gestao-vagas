package dev.swagviper.gestao_vagas.modules.company.controllers;

import dev.swagviper.gestao_vagas.modules.company.entities.JobEntity;
import dev.swagviper.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity) {
        var result = this.createJobUseCase.execute(jobEntity);

        return ResponseEntity.ok().body(result);
    }
}

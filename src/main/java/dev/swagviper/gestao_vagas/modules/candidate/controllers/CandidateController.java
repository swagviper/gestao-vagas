package dev.swagviper.gestao_vagas.modules.candidate.controllers;

import dev.swagviper.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import dev.swagviper.gestao_vagas.modules.candidate.entity.CandidateEntity;
import dev.swagviper.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import dev.swagviper.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(@Valid HttpServletRequest request) {
        var id = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(id.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}

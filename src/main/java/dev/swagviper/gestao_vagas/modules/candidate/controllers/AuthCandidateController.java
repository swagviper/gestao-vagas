package dev.swagviper.gestao_vagas.modules.candidate.controllers;

import dev.swagviper.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import dev.swagviper.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {

        try {
            var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }
}

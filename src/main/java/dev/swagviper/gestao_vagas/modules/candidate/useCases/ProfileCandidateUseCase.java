package dev.swagviper.gestao_vagas.modules.candidate.useCases;

import dev.swagviper.gestao_vagas.modules.candidate.CandidateRepository;
import dev.swagviper.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        var candidateDTO = ProfileCandidateResponseDTO.builder()
            .description(candidate.getDescription())
            .name(candidate.getName())
            .username(candidate.getUsername())
            .email(candidate.getEmail())
            .id(candidate.getId())
            .build();

        return candidateDTO;
    }
}

package dev.swagviper.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

    private String description;
    private String name;
    private String username;
    private String email;
    private UUID id;
}

package dev.swagviper.gestao_vagas.modules.candidate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços.")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido.")
    private String email;

    @Length(min = 8, max = 100, message = "A sua senha deve ter entre (8) a (100) caracteres.")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

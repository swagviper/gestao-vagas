package dev.swagviper.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.swagviper.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import dev.swagviper.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import dev.swagviper.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Usuário ou senha está incorreta.");
            }
        );

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var expiresIn = Instant.now().plus(Duration.ofHours(10));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("swagviper")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(company.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();

        return authCompanyResponseDTO;
    }
}

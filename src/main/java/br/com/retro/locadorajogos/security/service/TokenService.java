package br.com.retro.locadorajogos.security.service;

import br.com.retro.locadorajogos.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String criarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256("1234");
            LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(2);

            return JWT.create()
                    .withIssuer("Locadora Jogos")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao.toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritmo);
        } catch(JWTCreationException ex) {
            throw new RuntimeException("Erro ao Criar Token", ex);
        }
    }

    public String buscarUsuarioToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256("1234");

            return JWT.require(algoritmo)
                    .withIssuer("Locadora Jogos")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Token Incorreto!", ex);
        }
    }

}

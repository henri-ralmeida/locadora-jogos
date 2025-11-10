package br.com.retro.locadorajogos.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografiaSenha {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String criptografia(String password) {
        return encoder.encode(password);
    }
}

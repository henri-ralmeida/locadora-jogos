package br.com.retro.locadorajogos.config;

import br.com.retro.locadorajogos.exception.SenhaIncorretaException;
import br.com.retro.locadorajogos.exception.UsuarioNaoEncontradoException;
import br.com.retro.locadorajogos.exception.UsuarioJaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsuarioJaExisteException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<Object> handleSenhaIncorreta(SenhaIncorretaException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Credenciais inválidas");
    }
}

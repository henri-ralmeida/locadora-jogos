package br.com.retro.locadorajogos.exception;

public class NenhumJogoEncontradoException extends RuntimeException {
    public NenhumJogoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

package br.com.acrani.cadastroCliente.services.exceptions;

public class DuplicatedDataException extends RuntimeException{

    public DuplicatedDataException(String message) {
        super(message);
    }
}

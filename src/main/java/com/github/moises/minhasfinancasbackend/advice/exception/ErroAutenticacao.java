package com.github.moises.minhasfinancasbackend.advice.exception;

public class ErroAutenticacao extends RuntimeException{
    public ErroAutenticacao(String message){
        super(message);
    }
}

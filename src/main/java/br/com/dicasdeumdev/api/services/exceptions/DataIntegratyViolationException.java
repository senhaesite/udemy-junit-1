package br.com.dicasdeumdev.api.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException{

    public DataIntegratyViolationException(String msg){
        super(msg);
    }

}

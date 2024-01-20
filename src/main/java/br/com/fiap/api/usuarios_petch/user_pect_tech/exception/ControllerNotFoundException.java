package br.com.fiap.api.usuarios_petch.user_pect_tech.exception;

public class ControllerNotFoundException  extends RuntimeException{

    public ControllerNotFoundException(String message){
        super(message);
    }
}

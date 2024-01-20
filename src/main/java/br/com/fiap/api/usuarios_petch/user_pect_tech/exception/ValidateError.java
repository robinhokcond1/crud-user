package br.com.fiap.api.usuarios_petch.user_pect_tech.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError{

    private List<ValidateMessage> mensagens = new ArrayList<ValidateMessage>();

    public List<ValidateMessage> getMensagens() {
        return mensagens;
    }
    public  void addMensagens(String campo, String mensagem){
        mensagens.add(new ValidateMessage(campo, mensagem));
    }
}

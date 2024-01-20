package br.com.fiap.api.usuarios_petch.user_pect_tech.exception;

public class ValidateMessage {

    private String campo;
    private String messagem;

    public ValidateMessage(){

    }

    public ValidateMessage(String campo, String messagem) {
        this.campo = campo;
        this.messagem = messagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}

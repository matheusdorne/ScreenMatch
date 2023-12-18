package br.com.screenmatch.excecao;

public class ErrorDeConversaoDeAnoException extends RuntimeException {
    //Extends de runtimeException já que ela é unchecked
    private  String mensagem;
    public ErrorDeConversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}

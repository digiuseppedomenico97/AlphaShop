package com.xantrix.webapp.exception;

public class NotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    private String messaggio = "Elemento ricercato non trovato";

    public NotFoundException() {
        super();
    }
    public NotFoundException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }
    public String getMessaggio() {
        return messaggio;
    }
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}

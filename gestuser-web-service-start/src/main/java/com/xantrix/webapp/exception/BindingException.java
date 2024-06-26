package com.xantrix.webapp.exception;

public class BindingException extends Exception{
    private static final long serialVersionUID = 1L;
    private String messagio;

    public BindingException() {
        super();
    }
    public BindingException(String messagio) {
        super(messagio);
        this.messagio = messagio;
    }
    public String getMessagio() {
        return messagio;
    }
    public void setMessagio(String messagio) {
        this.messagio = messagio;

    }
}

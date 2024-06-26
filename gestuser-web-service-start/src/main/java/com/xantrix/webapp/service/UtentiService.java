package com.xantrix.webapp.service;

import com.xantrix.webapp.model.Utenti;

import java.util.List;

public interface UtentiService {
    public List<Utenti> SelTutti();
    public Utenti SelUser(String UserId);
    public void Save(Utenti Utente);
    public void Delete(Utenti utente);
}

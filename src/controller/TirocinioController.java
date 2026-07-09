package controller;

import dao.TirocinioDAO;
import model.Tirocinio;

import java.util.List;

public class TirocinioController {

    private TirocinioDAO tirocinioDAO;

    public TirocinioController(TirocinioDAO tirocinioDAO) {
        this.tirocinioDAO = tirocinioDAO;
    }

    // AGGIUNGI NUOVO TIROCINIO
    public boolean aggiungiTirocinio(String idTirocinio, String titolo, String descrizione, String idDocente) {

        // Controllo se esiste già un tirocinio con lo stesso ID
        Tirocinio esistente = tirocinioDAO.getTirocinioById(idTirocinio);
        if (esistente != null) {
            System.out.println("Errore: ID tirocinio già esistente.");
            return false;
        }

        Tirocinio nuovo = new Tirocinio(idTirocinio, titolo, descrizione, idDocente);
        return tirocinioDAO.aggiungiTirocinio(nuovo);
    }

    // RECUPERA TIROCINIO PER ID
    public Tirocinio getTirocinioById(String idTirocinio) {
        return tirocinioDAO.getTirocinioById(idTirocinio);
    }

    // LISTA COMPLETA TIROCINI
    public List<Tirocinio> getAllTirocini() {
        return tirocinioDAO.getAllTirocini();
    }

    // AGGIORNA TIROCINIO
    public boolean aggiornaTirocinio(String idTirocinio, String titolo, String descrizione, String idDocente) {

        Tirocinio tirocinio = new Tirocinio(idTirocinio, titolo, descrizione, idDocente);
        return tirocinioDAO.aggiornaTirocinio(tirocinio);
    }

    // ELIMINA TIROCINIO
    public boolean eliminaTirocinio(String idTirocinio) {
        return tirocinioDAO.eliminaTirocinio(idTirocinio);
    }
}
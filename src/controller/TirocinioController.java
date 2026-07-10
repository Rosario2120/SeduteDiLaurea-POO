package controller;

import dao.TirocinioDAO;
import model.Tirocinio;

import java.util.List;

public class TirocinioController {

    private final TirocinioDAO tirocinioDAO;

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

    // LISTA COMPLETA TIROCINI
    public List<Tirocinio> getAllTirocini() {
        return tirocinioDAO.getAllTirocini();
    }

}
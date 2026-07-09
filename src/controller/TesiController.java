package controller;

import dao.TesiDAO;
import model.Tesi;

import java.util.List;

public class TesiController {

    private TesiDAO tesiDAO;

    public TesiController(TesiDAO tesiDAO) {
        this.tesiDAO = tesiDAO;
    }

    // AGGIUNGI NUOVA TESI
    public boolean aggiungiTesi(String idTesi, String matricolaStudente, String idDocenteRelatore, String titolo, String stato) {

        // Controllo se esiste già una tesi con lo stesso ID
        Tesi esistente = tesiDAO.getTesiById(idTesi);
        if (esistente != null) {
            System.out.println("Errore: ID tesi già esistente.");
            return false;
        }

        Tesi nuova = new Tesi(idTesi, matricolaStudente, idDocenteRelatore, titolo, stato);
        return tesiDAO.aggiungiTesi(nuova);
    }

    // RECUPERA TESI PER ID
    public Tesi getTesiById(String idTesi) {
        return tesiDAO.getTesiById(idTesi);
    }

    // LISTA COMPLETA TESI
    public List<Tesi> getAllTesi() {
        return tesiDAO.getAllTesi();
    }

    // RECUPERA TESI DI UNO STUDENTE
    public Tesi getTesiByStudente(String matricolaStudente) {
        return tesiDAO.getTesiByStudente(matricolaStudente);
    }

    // AGGIORNA TESI
    public boolean aggiornaTesi(String idTesi, String matricolaStudente, String idDocenteRelatore, String titolo, String stato) {

        Tesi tesi = new Tesi(idTesi, matricolaStudente, idDocenteRelatore, titolo, stato);
        return tesiDAO.aggiornaTesi(tesi);
    }

    // AGGIORNA SOLO LO STATO
    public boolean aggiornaStatoTesi(String idTesi, String nuovoStato) {
        return tesiDAO.aggiornaStatoTesi(idTesi, nuovoStato);
    }

    // ELIMINA TESI
    public boolean eliminaTesi(String idTesi) {
        return tesiDAO.eliminaTesi(idTesi);
    }
}
package controller;

import dao.TesiDAO;
import model.Tesi;

import java.util.List;

public class TesiController {

    private final TesiDAO tesiDAO;

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

    // LISTA COMPLETA TESI
    public List<Tesi> getAllTesi() {
        return tesiDAO.getAllTesi();
    }

    // RECUPERA TESI DI UNO STUDENTE
    public Tesi getTesiByStudente(String matricolaStudente) {
        return tesiDAO.getTesiByStudente(matricolaStudente);
    }

}
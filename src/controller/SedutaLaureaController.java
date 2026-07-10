package controller;

import dao.SedutaLaureaDAO;
import model.SedutaLaurea;
import model.Studente;

import java.util.List;

public class SedutaLaureaController {

    private final SedutaLaureaDAO sedutaDAO;

    public SedutaLaureaController(SedutaLaureaDAO sedutaDAO) {
        this.sedutaDAO = sedutaDAO;
    }

    // AGGIUNGI NUOVA SEDUTA
    public boolean aggiungiSeduta(String idSeduta, String dataSeduta, String oraSeduta, String luogo, String idCommissione) {

        // Controllo se esiste già una seduta con lo stesso ID
        SedutaLaurea esistente = sedutaDAO.getSedutaById(idSeduta);
        if (esistente != null) {
            System.out.println("Errore: ID seduta già esistente.");
            return false;
        }

        SedutaLaurea nuova = new SedutaLaurea(idSeduta, dataSeduta, oraSeduta, luogo, idCommissione);
        return sedutaDAO.aggiungiSeduta(nuova);
    }

    // RECUPERA SEDUTA PER ID
    public SedutaLaurea getSedutaById(String idSeduta) {
        return sedutaDAO.getSedutaById(idSeduta);
    }

    // LISTA COMPLETA SEDUTE
    public List<SedutaLaurea> getAllSedute() {
        return sedutaDAO.getAllSedute();
    }

    // PRENOTA STUDENTE
    public boolean prenotaStudente(String idSeduta, Studente studente) {
        return sedutaDAO.prenotaStudente(idSeduta, studente);
    }

}
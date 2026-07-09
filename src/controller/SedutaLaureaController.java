package controller;

import dao.SedutaLaureaDAO;
import model.SedutaLaurea;
import model.Studente;

import java.util.List;

public class SedutaLaureaController {

    private SedutaLaureaDAO sedutaDAO;

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

    // AGGIORNA SEDUTA
    public boolean aggiornaSeduta(String idSeduta, String dataSeduta, String oraSeduta, String luogo, String idCommissione) {

        SedutaLaurea seduta = new SedutaLaurea(idSeduta, dataSeduta, oraSeduta, luogo, idCommissione);
        return sedutaDAO.aggiornaSeduta(seduta);
    }

    // ELIMINA SEDUTA
    public boolean eliminaSeduta(String idSeduta) {
        return sedutaDAO.eliminaSeduta(idSeduta);
    }

    // PRENOTA STUDENTE
    public boolean prenotaStudente(String idSeduta, Studente studente) {
        return sedutaDAO.prenotaStudente(idSeduta, studente);
    }

    // RIMUOVI STUDENTE
    public boolean rimuoviStudente(String idSeduta, Studente studente) {
        return sedutaDAO.rimuoviStudente(idSeduta, studente);
    }

    // LISTA STUDENTI PRENOTATI
    public List<Studente> getStudentiPrenotati(String idSeduta) {
        return sedutaDAO.getStudentiPrenotati(idSeduta);
    }
}
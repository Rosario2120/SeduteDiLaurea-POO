package controller;

import dao.CommissioneDAO;
import model.Commissione;
import model.Docente;

import java.util.List;

public class CommissioneController {

    private CommissioneDAO commissioneDAO;

    public CommissioneController(CommissioneDAO commissioneDAO) {
        this.commissioneDAO = commissioneDAO;
    }

    // CREA NUOVA COMMISSIONE
    public boolean aggiungiCommissione(String idCommissione, String idPresidente) {

        // Controllo se esiste già una commissione con lo stesso ID
        Commissione esistente = commissioneDAO.getCommissioneById(idCommissione);
        if (esistente != null) {
            System.out.println("Errore: ID commissione già esistente.");
            return false;
        }

        Commissione nuova = new Commissione(idCommissione, idPresidente);
        return commissioneDAO.aggiungiCommissione(nuova);
    }

    // RECUPERA COMMISSIONE PER ID
    public Commissione getCommissioneById(String idCommissione) {
        return commissioneDAO.getCommissioneById(idCommissione);
    }

    // LISTA COMPLETA COMMISSIONI
    public List<Commissione> getAllCommissioni() {
        return commissioneDAO.getAllCommissioni();
    }

    // AGGIORNA PRESIDENTE COMMISSIONE
    public boolean aggiornaPresidente(String idCommissione, String nuovoPresidente) {

        Commissione c = commissioneDAO.getCommissioneById(idCommissione);
        if (c == null) {
            System.out.println("Errore: commissione non trovata.");
            return false;
        }

        c.setIdPresidente(nuovoPresidente);
        return commissioneDAO.aggiornaCommissione(c);
    }

    // AGGIUNGI MEMBRO ALLA COMMISSIONE
    public boolean aggiungiMembro(String idCommissione, Docente docente) {
        return commissioneDAO.aggiungiMembro(idCommissione, docente);
    }

    // RIMUOVI MEMBRO DALLA COMMISSIONE
    public boolean rimuoviMembro(String idCommissione, Docente docente) {
        return commissioneDAO.rimuoviMembro(idCommissione, docente);
    }

    // RECUPERA LISTA MEMBRI
    public List<Docente> getMembriCommissione(String idCommissione) {
        return commissioneDAO.getMembriCommissione(idCommissione);
    }

    // ELIMINA COMMISSIONE
    public boolean eliminaCommissione(String idCommissione) {
        return commissioneDAO.eliminaCommissione(idCommissione);
    }
}
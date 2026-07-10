package controller;

import dao.CommissioneDAO;
import model.Commissione;

import java.util.List;

public class CommissioneController {

    private final CommissioneDAO commissioneDAO;

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

    // LISTA COMPLETA COMMISSIONI
    public List<Commissione> getAllCommissioni() {
        return commissioneDAO.getAllCommissioni();
    }

}
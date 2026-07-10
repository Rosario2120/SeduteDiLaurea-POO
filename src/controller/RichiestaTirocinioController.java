package controller;

import dao.RichiestaTirocinioDAO;
import model.RichiestaTirocinio;

import java.util.List;

public class RichiestaTirocinioController {

    private final RichiestaTirocinioDAO richiestaDAO;

    public RichiestaTirocinioController(RichiestaTirocinioDAO richiestaDAO) {
        this.richiestaDAO = richiestaDAO;
    }

    // AGGIUNGI NUOVA RICHIESTA
    public boolean aggiungiRichiesta(String idRichiesta, String matricolaStudente, String idTirocinio, String stato) {

        // Controllo se esiste già una richiesta con lo stesso ID
        RichiestaTirocinio esistente = richiestaDAO.getRichiestaById(idRichiesta);
        if (esistente != null) {
            System.out.println("Errore: ID richiesta già esistente.");
            return false;
        }

        RichiestaTirocinio nuova = new RichiestaTirocinio(idRichiesta, matricolaStudente, idTirocinio, stato);
        return richiestaDAO.aggiungiRichiesta(nuova);
    }

    // LISTA COMPLETA RICHIESTE
    public List<RichiestaTirocinio> getAllRichieste() {
        return richiestaDAO.getAllRichieste();
    }

    // RICHIESTE DI UNO STUDENTE
    public List<RichiestaTirocinio> getRichiesteByStudente(String matricolaStudente) {
        return richiestaDAO.getRichiesteByStudente(matricolaStudente);
    }

    // AGGIORNA STATO RICHIESTA
    public boolean aggiornaStatoRichiesta(String idRichiesta, String nuovoStato) {
        return richiestaDAO.aggiornaStatoRichiesta(idRichiesta, nuovoStato);
    }

}
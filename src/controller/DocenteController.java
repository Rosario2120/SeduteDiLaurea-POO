package controller;

import dao.DocenteDAO;
import model.Docente;

import java.util.List;

public class DocenteController {

    private DocenteDAO docenteDAO;

    public DocenteController(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    // REGISTRAZIONE DOCENTE
    public boolean registraDocente(String idDocente, String nome, String cognome, String email, String idUser) {

        // Controllo se esiste già un docente con lo stesso id
        Docente esistente = docenteDAO.getDocenteByIdDocente(idDocente);
        if (esistente != null) {
            System.out.println("Errore: id docente già registrato.");
            return false;
        }

        Docente nuovo = new Docente(idDocente, nome, cognome, email, idUser);
        return docenteDAO.registraDocente(nuovo);
    }

    // RECUPERA DOCENTE PER ID DOCENTE
    public Docente getDocenteByIdDocente(String idDocente) {
        return docenteDAO.getDocenteByIdDocente(idDocente);
    }

    // RECUPERA DOCENTE PER ID USER
    public Docente getDocenteByIdUser(String idUser) {
        return docenteDAO.getDocenteByIdUser(idUser);
    }

    // LISTA COMPLETA DOCENTI
    public List<Docente> getAllDocenti() {
        return docenteDAO.getAllDocenti();
    }

    // RECUPERA COORDINATORE
    public Docente getCoordinatore() {
        return docenteDAO.getCoordinatore();
    }

    // AGGIORNA DOCENTE
    public boolean aggiornaDocente(String idDocente, String nome, String cognome, String email, String idUser) {

        Docente docente = new Docente(idDocente, nome, cognome, email, idUser);
        return docenteDAO.aggiornaDocente(docente);
    }

    // ELIMINA DOCENTE
    public boolean eliminaDocente(String idDocente) {
        return docenteDAO.eliminaDocente(idDocente);
    }
}
package controller;

import dao.StudenteDAO;
import model.Studente;

import java.util.List;

public class StudenteController {

    private StudenteDAO studenteDAO;

    public StudenteController(StudenteDAO studenteDAO) {
        this.studenteDAO = studenteDAO;
    }

    // REGISTRAZIONE STUDENTE
    public boolean registraStudente(String matricola, String nome, String cognome, String email, String idUser) {

        // Controllo se esiste già uno studente con la stessa matricola
        Studente esistente = studenteDAO.getStudenteByMatricola(matricola);
        if (esistente != null) {
            System.out.println("Errore: matricola già registrata.");
            return false;
        }

        Studente nuovo = new Studente(matricola, nome, cognome, email, idUser);
        return studenteDAO.registraStudente(nuovo);
    }

    // RECUPERA STUDENTE PER MATRICOLA
    public Studente getStudenteByMatricola(String matricola) {
        return studenteDAO.getStudenteByMatricola(matricola);
    }

    // RECUPERA STUDENTE PER ID USER
    public Studente getStudenteByIdUser(String idUser) {
        return studenteDAO.getStudenteByIdUser(idUser);
    }

    // LISTA COMPLETA STUDENTI
    public List<Studente> getAllStudenti() {
        return studenteDAO.getAllStudenti();
    }

    // AGGIORNA STUDENTE
    public boolean aggiornaStudente(String matricola, String nome, String cognome, String email, String idUser) {

        Studente studente = new Studente(matricola, nome, cognome, email, idUser);
        return studenteDAO.aggiornaStudente(studente);
    }

    // ELIMINA STUDENTE
    public boolean eliminaStudente(String matricola) {
        return studenteDAO.eliminaStudente(matricola);
    }
}
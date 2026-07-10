package controller;

import dao.DocenteDAO;
import model.Docente;

import java.util.List;

public class DocenteController {

    private final DocenteDAO docenteDAO;

    public DocenteController(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    // RECUPERA DOCENTE PER ID USER
    public Docente getDocenteByIdUser(String idUser) {
        return docenteDAO.getDocenteByIdUser(idUser);
    }

    // LISTA COMPLETA DOCENTI
    public List<Docente> getAllDocenti() {
        return docenteDAO.getAllDocenti();
    }

}
package controller;

import dao.StudenteDAO;
import model.Studente;

public class StudenteController {

    private final StudenteDAO studenteDAO;

    public StudenteController(StudenteDAO studenteDAO) {
        this.studenteDAO = studenteDAO;
    }

    // RECUPERA STUDENTE PER ID USER
    public Studente getStudenteByIdUser(String idUser) {
        return studenteDAO.getStudenteByIdUser(idUser);
    }

}
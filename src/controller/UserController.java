package controller;

import dao.UserDAO;
import model.User;

public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // REGISTRAZIONE UTENTE
    public boolean registraUtente(String idUser, String username, String passwordHash, String ruolo) {

        if (userDAO.usernameEsiste(username)) {
            System.out.println("Errore: username già esistente.");
            return false;
        }

        User nuovoUtente = new User(idUser, username, passwordHash, ruolo);
        return userDAO.registraUtente(nuovoUtente);
    }

    // LOGIN
    public User login(String username, String passwordHash) {
        return userDAO.login(username, passwordHash);
    }

    // RECUPERA UTENTE PER ID
    public User getUserById(String idUser) {
        return userDAO.getUserById(idUser);
    }

    // RECUPERA UTENTE PER USERNAME
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
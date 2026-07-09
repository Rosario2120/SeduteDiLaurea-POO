package dao;

import model.User;

public interface UserDAO {

    // Registra un nuovo utente nel sistema
    boolean registraUtente(User user);

    // Effettua il login e restituisce l'utente se le credenziali sono corrette
    User login(String username, String passwordHash);

    // Recupera un utente tramite il suo ID
    User getUserById(String idUser);

    // Recupera un utente tramite username
    User getUserByUsername(String username);

    // Controlla se un username è già presente nel sistema
    boolean usernameEsiste(String username);
}
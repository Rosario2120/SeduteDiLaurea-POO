package app;

import controller.*;
import dao.*;
import postgresdao.*;
import database.DatabaseConnection;
import gui.LoginGUI;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

        // Connessione al database
        Connection conn = DatabaseConnection.getConnection();

        // DAO con connessione
        UserDAO userDAO = new UserPostgresDAO(conn);
        StudenteDAO studenteDAO = new StudentePostgresDAO(conn);
        DocenteDAO docenteDAO = new DocentePostgresDAO(conn);
        TirocinioDAO tirocinioDAO = new TirocinioPostgresDAO(conn);
        RichiestaTirocinioDAO richiestaDAO = new RichiestaTirocinioPostgresDAO(conn);
        TesiDAO tesiDAO = new TesiPostgresDAO(conn);
        SedutaLaureaDAO sedutaDAO = new SedutaLaureaPostgresDAO(conn);
        CommissioneDAO commissioneDAO = new CommissionePostgresDAO(conn);

        // CONTROLLER
        UserController userController = new UserController(userDAO);
        StudenteController studenteController = new StudenteController(studenteDAO);
        DocenteController docenteController = new DocenteController(docenteDAO);
        TirocinioController tirocinioController = new TirocinioController(tirocinioDAO);
        RichiestaTirocinioController richiestaController = new RichiestaTirocinioController(richiestaDAO);
        TesiController tesiController = new TesiController(tesiDAO);
        SedutaLaureaController sedutaController = new SedutaLaureaController(sedutaDAO);
        CommissioneController commissioneController = new CommissioneController(commissioneDAO);

        // GUI LOGIN
        LoginGUI login;
        login = new LoginGUI(
                userController
        );

        login.setVisible(true);
    }
}
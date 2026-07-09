package gui;

import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    // PANNELLI
    private JPanel loginPanel;
    private JPanel logoutPanel;
    private JPanel docentePanel;
    private JPanel studentePanel;
    private JPanel segreteriaPanel;

    // COMPONENTI LOGIN
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGUI(UserController userController) {

        // FRAME
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        // ============================
        // 1) LOGIN PANEL
        // ============================
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Accedi");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        // ============================
        // 2) LOGOUT PANEL
        // ============================
        logoutPanel = new JPanel();
        logoutPanel.add(new JLabel("Sei loggato!"));
        JButton logoutButton = new JButton("Logout");
        logoutPanel.add(logoutButton);

        // ============================
        // 3) DOCENTE PANEL
        // ============================
        docentePanel = new JPanel();
        docentePanel.add(new JLabel("Area Docente"));

        // ============================
        // 4) STUDENTE PANEL
        // ============================
        studentePanel = new JPanel();
        studentePanel.add(new JLabel("Area Studente"));

        // ============================
        // 5) SEGRETERIA PANEL
        // ============================
        segreteriaPanel = new JPanel();
        segreteriaPanel.add(new JLabel("Area Segreteria"));

        // AGGIUNTA PANNELLI AL FRAME
        add(loginPanel, "LOGIN");
        add(logoutPanel, "LOGOUT");
        add(docentePanel, "DOCENTE");
        add(studentePanel, "STUDENTE");
        add(segreteriaPanel, "SEGRETERIA");

        // VISIBILITÀ INIZIALE
        loginPanel.setVisible(true);
        logoutPanel.setVisible(false);
        docentePanel.setVisible(false);
        studentePanel.setVisible(false);
        segreteriaPanel.setVisible(false);

        // LOGIN BUTTON ACTION
        loginButton.addActionListener(e -> {
            String userType = usernameField.getText().toUpperCase();

            // Simulazione login
            loginLogout(userType);
        });

        // LOGOUT BUTTON ACTION
        logoutButton.addActionListener(e -> {
            logout();
        });
    }

    // ============================
    // METODO LOGIN/LOGOUT
    // ============================
    public void loginLogout(String user) {

        // Nascondo tutto
        loginPanel.setVisible(false);
        logoutPanel.setVisible(true);
        docentePanel.setVisible(false);
        studentePanel.setVisible(false);
        segreteriaPanel.setVisible(false);

        JOptionPane.showMessageDialog(null, "Benvenuto!");

        switch (user) {
            case "DOCENTE":
                docentePanel.setVisible(true);
                break;

            case "STUDENTE":
                studentePanel.setVisible(true);
                break;

            case "SEGRETERIA":
                segreteriaPanel.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Ruolo non valido!");
                logout();
                break;
        }
    }

    // ============================
    // METODO LOGOUT
    // ============================
    private void logout() {
        loginPanel.setVisible(true);
        logoutPanel.setVisible(false);
        docentePanel.setVisible(false);
        studentePanel.setVisible(false);
        segreteriaPanel.setVisible(false);
    }
}
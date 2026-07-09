package gui;

import controller.RichiestaTirocinioController;
import controller.TesiController;
import controller.SedutaLaureaController;
import controller.StudenteController;

import model.User;
import model.Studente;
import model.Tirocinio;
import model.RichiestaTirocinio;
import model.Tesi;
import model.SedutaLaurea;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudenteGUI extends JFrame {

    private User user;
    private Studente studente;

    private StudenteController studenteController;
    private RichiestaTirocinioController richiestaController;
    private TesiController tesiController;
    private SedutaLaureaController sedutaController;

    public StudenteGUI(
            User user,
            StudenteController studenteController,
            RichiestaTirocinioController richiestaController,
            TesiController tesiController,
            SedutaLaureaController sedutaController
    ) {

        this.user = user;
        this.studenteController = studenteController;
        this.richiestaController = richiestaController;
        this.tesiController = tesiController;
        this.sedutaController = sedutaController;

        this.studente = studenteController.getStudenteByIdUser(user.getIdUser());

        setTitle("Area Studente - " + studente.getNome() + " " + studente.getCognome());
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnRichiestaTirocinio = new JButton("Richiedi Tirocinio");
        JButton btnRichiestaTesi = new JButton("Richiedi Tesi");
        JButton btnPrenotaSeduta = new JButton("Prenota Seduta di Laurea");
        JButton btnVisualizzaRichieste = new JButton("Visualizza Richieste");
        JButton btnEsci = new JButton("Esci");

        panel.add(btnRichiestaTirocinio);
        panel.add(btnRichiestaTesi);
        panel.add(btnPrenotaSeduta);
        panel.add(btnVisualizzaRichieste);
        panel.add(btnEsci);

        add(panel);

        // --- RICHIESTA TIROCINIO ---
        btnRichiestaTirocinio.addActionListener(e -> {
            String idTirocinio = JOptionPane.showInputDialog(this, "Inserisci ID Tirocinio:");
            if (idTirocinio == null || idTirocinio.isEmpty()) return;

            String idRichiesta = "RICH-" + System.currentTimeMillis();

            boolean ok = richiestaController.aggiungiRichiesta(
                    idRichiesta,
                    studente.getMatricola(),
                    idTirocinio,
                    "IN_ATTESA"
            );

            if (ok)
                JOptionPane.showMessageDialog(this, "Richiesta tirocinio inviata!");
            else
                JOptionPane.showMessageDialog(this, "Errore: impossibile inviare la richiesta.");
        });

        // --- RICHIESTA TESI ---
        btnRichiestaTesi.addActionListener(e -> {
            String titolo = JOptionPane.showInputDialog(this, "Titolo della tesi:");
            if (titolo == null || titolo.isEmpty()) return;

            String idDocente = JOptionPane.showInputDialog(this, "ID Docente Relatore:");
            if (idDocente == null || idDocente.isEmpty()) return;

            String idTesi = "TESI-" + System.currentTimeMillis();

            boolean ok = tesiController.aggiungiTesi(
                    idTesi,
                    studente.getMatricola(),
                    idDocente,
                    titolo,
                    "IN_ATTESA"
            );

            if (ok)
                JOptionPane.showMessageDialog(this, "Richiesta tesi inviata!");
            else
                JOptionPane.showMessageDialog(this, "Errore: impossibile inviare la richiesta.");
        });

        // --- PRENOTA SEDUTA ---
        btnPrenotaSeduta.addActionListener(e -> {
            String idSeduta = JOptionPane.showInputDialog(this, "Inserisci ID Seduta:");
            if (idSeduta == null || idSeduta.isEmpty()) return;

            SedutaLaurea seduta = sedutaController.getSedutaById(idSeduta);

            if (seduta == null) {
                JOptionPane.showMessageDialog(this, "Seduta non trovata.");
                return;
            }

            boolean ok = sedutaController.prenotaStudente(idSeduta, studente);

            if (ok)
                JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");
            else
                JOptionPane.showMessageDialog(this, "Errore: impossibile prenotarsi.");
        });

        // --- VISUALIZZA RICHIESTE ---
        btnVisualizzaRichieste.addActionListener(e -> {
            List<RichiestaTirocinio> richiesteTirocinio =
                    richiestaController.getRichiesteByStudente(studente.getMatricola());

            Tesi tesi = tesiController.getTesiByStudente(studente.getMatricola());

            StringBuilder sb = new StringBuilder();

            sb.append("Richieste Tirocinio:\n");
            for (RichiestaTirocinio r : richiesteTirocinio) {
                sb.append("- ").append(r.getIdRichiesta())
                        .append(" | Tirocinio: ").append(r.getIdTirocinio())
                        .append(" | Stato: ").append(r.getStato())
                        .append("\n");
            }

            sb.append("\nRichiesta Tesi:\n");
            if (tesi != null) {
                sb.append("- ").append(tesi.getIdTesi())
                        .append(" | Titolo: ").append(tesi.getTitolo())
                        .append(" | Stato: ").append(tesi.getStato())
                        .append("\n");
            } else {
                sb.append("Nessuna richiesta tesi.\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- ESCI ---
        btnEsci.addActionListener(e -> dispose());
    }
}
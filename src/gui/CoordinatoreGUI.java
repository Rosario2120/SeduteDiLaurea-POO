package gui;

import controller.CommissioneController;
import controller.SedutaLaureaController;
import controller.TesiController;
import controller.RichiestaTirocinioController;
import controller.TirocinioController;
import controller.DocenteController;

import model.User;
import model.Commissione;
import model.SedutaLaurea;
import model.RichiestaTirocinio;
import model.Tesi;
import model.Tirocinio;
import model.Docente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CoordinatoreGUI extends JFrame {

    private User user;

    private CommissioneController commissioneController;
    private SedutaLaureaController sedutaController;
    private TesiController tesiController;
    private RichiestaTirocinioController richiestaController;
    private TirocinioController tirocinioController;
    private DocenteController docenteController;

    public CoordinatoreGUI(
            User user,
            CommissioneController commissioneController,
            SedutaLaureaController sedutaController,
            TesiController tesiController,
            RichiestaTirocinioController richiestaController,
            TirocinioController tirocinioController,
            DocenteController docenteController
    ) {

        this.user = user;
        this.commissioneController = commissioneController;
        this.sedutaController = sedutaController;
        this.tesiController = tesiController;
        this.richiestaController = richiestaController;
        this.tirocinioController = tirocinioController;
        this.docenteController = docenteController;

        setTitle("Area Coordinatore");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

        JButton btnCommissioni = new JButton("Gestisci Commissioni");
        JButton btnSedute = new JButton("Gestisci Sedute di Laurea");
        JButton btnTesi = new JButton("Gestisci Tesi");
        JButton btnRichiesteTirocinio = new JButton("Gestisci Richieste Tirocinio");
        JButton btnTirocini = new JButton("Gestisci Tirocini");
        JButton btnDocenti = new JButton("Visualizza Docenti");
        JButton btnEsci = new JButton("Esci");

        panel.add(btnCommissioni);
        panel.add(btnSedute);
        panel.add(btnTesi);
        panel.add(btnRichiesteTirocinio);
        panel.add(btnTirocini);
        panel.add(btnDocenti);
        panel.add(btnEsci);

        add(panel);

        // --- COMMISSIONI ---
        btnCommissioni.addActionListener(e -> {
            List<Commissione> commissioni = commissioneController.getAllCommissioni();

            StringBuilder sb = new StringBuilder("Commissioni:\n\n");

            for (Commissione c : commissioni) {
                sb.append("- ").append(c.getIdCommissione())
                        .append(" | Presidente: ").append(c.getIdPresidente())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());

            String idCommissione = JOptionPane.showInputDialog(this, "ID commissione da creare:");
            if (idCommissione == null || idCommissione.isEmpty()) return;

            String idPresidente = JOptionPane.showInputDialog(this, "ID presidente:");
            if (idPresidente == null || idPresidente.isEmpty()) return;

            boolean ok = commissioneController.aggiungiCommissione(idCommissione, idPresidente);

            JOptionPane.showMessageDialog(this, ok ?
                    "Commissione creata!" :
                    "Errore: impossibile creare la commissione.");
        });

        // --- SEDUTE ---
        btnSedute.addActionListener(e -> {
            List<SedutaLaurea> sedute = sedutaController.getAllSedute();

            StringBuilder sb = new StringBuilder("Sedute di Laurea:\n\n");

            for (SedutaLaurea s : sedute) {
                sb.append("- ").append(s.getIdSeduta())
                        .append(" | ").append(s.getDataSeduta())
                        .append(" | ").append(s.getOraSeduta())
                        .append(" | ").append(s.getLuogo())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());

            String idSeduta = JOptionPane.showInputDialog(this, "ID seduta da creare:");
            if (idSeduta == null || idSeduta.isEmpty()) return;

            String data = JOptionPane.showInputDialog(this, "Data:");
            String ora = JOptionPane.showInputDialog(this, "Ora:");
            String luogo = JOptionPane.showInputDialog(this, "Luogo:");
            String idCommissione = JOptionPane.showInputDialog(this, "ID commissione:");

            boolean ok = sedutaController.aggiungiSeduta(idSeduta, data, ora, luogo, idCommissione);

            JOptionPane.showMessageDialog(this, ok ?
                    "Seduta creata!" :
                    "Errore: impossibile creare la seduta.");
        });

        // --- TESI ---
        btnTesi.addActionListener(e -> {
            List<Tesi> tesi = tesiController.getAllTesi();

            StringBuilder sb = new StringBuilder("Tesi:\n\n");

            for (Tesi t : tesi) {
                sb.append("- ").append(t.getIdTesi())
                        .append(" | Studente: ").append(t.getMatricolaStudente())
                        .append(" | Titolo: ").append(t.getTitolo())
                        .append(" | Stato: ").append(t.getStato())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- RICHIESTE TIROCINIO ---
        btnRichiesteTirocinio.addActionListener(e -> {
            List<RichiestaTirocinio> richieste = richiestaController.getAllRichieste();

            StringBuilder sb = new StringBuilder("Richieste Tirocinio:\n\n");

            for (RichiestaTirocinio r : richieste) {
                sb.append("- ").append(r.getIdRichiesta())
                        .append(" | Studente: ").append(r.getMatricolaStudente())
                        .append(" | Tirocinio: ").append(r.getIdTirocinio())
                        .append(" | Stato: ").append(r.getStato())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- TIROCINI ---
        btnTirocini.addActionListener(e -> {
            List<Tirocinio> tirocini = tirocinioController.getAllTirocini();

            StringBuilder sb = new StringBuilder("Tirocini:\n\n");

            for (Tirocinio t : tirocini) {
                sb.append("- ").append(t.getIdTirocinio())
                        .append(" | ").append(t.getTitolo())
                        .append(" | ").append(t.getDescrizione())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- DOCENTI ---
        btnDocenti.addActionListener(e -> {
            List<Docente> docenti = docenteController.getAllDocenti();

            StringBuilder sb = new StringBuilder("Docenti:\n\n");

            for (Docente d : docenti) {
                sb.append("- ").append(d.getIdDocente())
                        .append(" | ").append(d.getNome())
                        .append(" ").append(d.getCognome())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- ESCI ---
        btnEsci.addActionListener(e -> dispose());
    }
}
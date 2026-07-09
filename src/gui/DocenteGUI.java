package gui;

import controller.TirocinioController;
import controller.RichiestaTirocinioController;
import controller.TesiController;
import controller.SedutaLaureaController;
import controller.CommissioneController;
import controller.DocenteController;

import model.User;
import model.Docente;
import model.RichiestaTirocinio;
import model.Tesi;
import model.SedutaLaurea;
import model.Commissione;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DocenteGUI extends JFrame {

    private User user;
    private Docente docente;

    private DocenteController docenteController;
    private TirocinioController tirocinioController;
    private RichiestaTirocinioController richiestaController;
    private TesiController tesiController;
    private SedutaLaureaController sedutaController;
    private CommissioneController commissioneController;

    public DocenteGUI(
            User user,
            DocenteController docenteController,
            TirocinioController tirocinioController,
            RichiestaTirocinioController richiestaController,
            TesiController tesiController,
            SedutaLaureaController sedutaController,
            CommissioneController commissioneController
    ) {

        this.user = user;
        this.docenteController = docenteController;
        this.tirocinioController = tirocinioController;
        this.richiestaController = richiestaController;
        this.tesiController = tesiController;
        this.sedutaController = sedutaController;
        this.commissioneController = commissioneController;

        this.docente = docenteController.getDocenteByIdUser(user.getIdUser());

        setTitle("Area Docente - " + docente.getNome() + " " + docente.getCognome());
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton btnGestisciTirocini = new JButton("Gestisci Tirocini");
        JButton btnGestisciRichieste = new JButton("Gestisci Richieste Tirocinio");
        JButton btnGestisciTesi = new JButton("Gestisci Tesi");
        JButton btnGestisciSedute = new JButton("Gestisci Sedute di Laurea");
        JButton btnGestisciCommissioni = new JButton("Gestisci Commissioni");
        JButton btnEsci = new JButton("Esci");

        panel.add(btnGestisciTirocini);
        panel.add(btnGestisciRichieste);
        panel.add(btnGestisciTesi);
        panel.add(btnGestisciSedute);
        panel.add(btnGestisciCommissioni);
        panel.add(btnEsci);

        add(panel);

        // --- GESTIONE TIROCINI ---
        btnGestisciTirocini.addActionListener(e -> {
            String idTirocinio = JOptionPane.showInputDialog(this, "ID Tirocinio da creare:");
            if (idTirocinio == null || idTirocinio.isEmpty()) return;

            String titolo = JOptionPane.showInputDialog(this, "Titolo:");
            if (titolo == null || titolo.isEmpty()) return;

            String descrizione = JOptionPane.showInputDialog(this, "Descrizione:");
            if (descrizione == null || descrizione.isEmpty()) return;

            boolean ok = tirocinioController.aggiungiTirocinio(
                    idTirocinio,
                    titolo,
                    descrizione,
                    docente.getIdDocente()
            );

            JOptionPane.showMessageDialog(this, ok ?
                    "Tirocinio creato!" :
                    "Errore: impossibile creare il tirocinio.");
        });

        // --- GESTIONE RICHIESTE TIROCINIO ---
        btnGestisciRichieste.addActionListener(e -> {
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

            String idRichiesta = JOptionPane.showInputDialog(this, "ID richiesta da aggiornare:");
            if (idRichiesta == null || idRichiesta.isEmpty()) return;

            String nuovoStato = JOptionPane.showInputDialog(this, "Nuovo stato (APPROVATA / RIFIUTATA):");
            if (nuovoStato == null || nuovoStato.isEmpty()) return;

            boolean ok = richiestaController.aggiornaStatoRichiesta(idRichiesta, nuovoStato);

            JOptionPane.showMessageDialog(this, ok ?
                    "Richiesta aggiornata!" :
                    "Errore: impossibile aggiornare la richiesta.");
        });

        // --- GESTIONE TESI ---
        btnGestisciTesi.addActionListener(e -> {
            String matricola = JOptionPane.showInputDialog(this, "Matricola studente:");
            if (matricola == null || matricola.isEmpty()) return;

            String titolo = JOptionPane.showInputDialog(this, "Titolo tesi:");
            if (titolo == null || titolo.isEmpty()) return;

            String idTesi = "TESI-" + System.currentTimeMillis();

            boolean ok = tesiController.aggiungiTesi(
                    idTesi,
                    matricola,
                    docente.getIdDocente(),
                    titolo,
                    "IN_ATTESA"
            );

            JOptionPane.showMessageDialog(this, ok ?
                    "Tesi registrata!" :
                    "Errore: impossibile registrare la tesi.");
        });

        // --- GESTIONE SEDUTE ---
        btnGestisciSedute.addActionListener(e -> {
            String idSeduta = JOptionPane.showInputDialog(this, "ID seduta da creare:");
            if (idSeduta == null || idSeduta.isEmpty()) return;

            String data = JOptionPane.showInputDialog(this, "Data seduta:");
            String ora = JOptionPane.showInputDialog(this, "Ora seduta:");
            String luogo = JOptionPane.showInputDialog(this, "Luogo:");
            String idCommissione = JOptionPane.showInputDialog(this, "ID commissione:");

            boolean ok = sedutaController.aggiungiSeduta(idSeduta, data, ora, luogo, idCommissione);

            JOptionPane.showMessageDialog(this, ok ?
                    "Seduta creata!" :
                    "Errore: impossibile creare la seduta.");
        });

        // --- GESTIONE COMMISSIONI ---
        btnGestisciCommissioni.addActionListener(e -> {
            if (!docente.getIdDocente().equals("COORDINATORE")) {
                JOptionPane.showMessageDialog(this, "Solo il coordinatore può gestire le commissioni.");
                return;
            }

            List<Commissione> commissioni = commissioneController.getAllCommissioni();

            StringBuilder sb = new StringBuilder("Commissioni:\n\n");

            for (Commissione c : commissioni) {
                sb.append("- ").append(c.getIdCommissione())
                        .append(" | Presidente: ").append(c.getIdPresidente())
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        });

        // --- ESCI ---
        btnEsci.addActionListener(e -> dispose());
    }

    public DocenteGUI(User user) {
    }
}
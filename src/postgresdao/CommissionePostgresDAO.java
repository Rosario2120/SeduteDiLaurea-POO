package postgresdao;

import dao.CommissioneDAO;
import model.Commissione;
import model.Docente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommissionePostgresDAO implements CommissioneDAO {

    private Connection connection;

    public CommissionePostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean aggiungiCommissione(Commissione commissione) {
        String query = "INSERT INTO commissioni (id_commissione, id_presidente) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, commissione.getIdCommissione());
            stmt.setString(2, commissione.getIdPresidente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Commissione getCommissioneById(String idCommissione) {
        String query = "SELECT * FROM commissioni WHERE id_commissione = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCommissione);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Commissione c = new Commissione(
                        rs.getString("id_commissione"),
                        rs.getString("id_presidente")
                );

                // carica i membri
                List<Docente> membri = getMembriCommissione(idCommissione);
                for (Docente idDocente : membri) {
                    c.aggiungiMembro(idDocente);
                }

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Commissione> getAllCommissioni() {
        String query = "SELECT * FROM commissioni";
        List<Commissione> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Commissione c = new Commissione(
                        rs.getString("id_commissione"),
                        rs.getString("id_presidente")
                );

                List<Docente> membri = getMembriCommissione(c.getIdCommissione());
                for (Docente idDocente : membri) {
                    c.aggiungiMembro(idDocente);
                }

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean aggiornaCommissione(Commissione commissione) {
        String query = "UPDATE commissioni SET id_presidente = ? WHERE id_commissione = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, commissione.getIdPresidente());
            stmt.setString(2, commissione.getIdCommissione());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaCommissione(String idCommissione) {
        String query = "DELETE FROM commissioni WHERE id_commissione = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCommissione);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean aggiungiMembro(String idCommissione, Docente docente) {
        String query = "INSERT INTO membri_commissione (id_commissione, id_docente) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCommissione);
            stmt.setString(2, docente.getIdDocente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rimuoviMembro(String idCommissione, Docente docente) {
        String query = "DELETE FROM membri_commissione WHERE id_commissione = ? AND id_docente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCommissione);
            stmt.setString(2, docente.getIdDocente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Docente> getMembriCommissione(String idCommissione) {
        String query = "SELECT d.id_docente, d.nome, d.cognome, d.email, d.id_user " +
                "FROM membri_commissione mc JOIN docenti d ON mc.id_docente = d.id_docente " +
                "WHERE mc.id_commissione = ?";

        List<Docente> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCommissione);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Docente docente = new Docente(
                        rs.getString("id_docente"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
                lista.add(docente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
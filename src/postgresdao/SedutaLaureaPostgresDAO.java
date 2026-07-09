package postgresdao;

import dao.SedutaLaureaDAO;
import model.SedutaLaurea;
import model.Studente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SedutaLaureaPostgresDAO implements SedutaLaureaDAO {

    private Connection connection;

    public SedutaLaureaPostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean aggiungiSeduta(SedutaLaurea seduta) {
        String query = "INSERT INTO sedute_laurea (id_seduta, data_seduta, ora_seduta, luogo, id_commissione) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, seduta.getIdSeduta());
            stmt.setString(2, seduta.getDataSeduta());
            stmt.setString(3, seduta.getOraSeduta());
            stmt.setString(4, seduta.getLuogo());
            stmt.setString(5, seduta.getIdCommissione());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SedutaLaurea getSedutaById(String idSeduta) {
        String query = "SELECT * FROM sedute_laurea WHERE id_seduta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSeduta);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SedutaLaurea(
                        rs.getString("id_seduta"),
                        rs.getString("data_seduta"),
                        rs.getString("ora_seduta"),
                        rs.getString("luogo"),
                        rs.getString("id_commissione")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SedutaLaurea> getAllSedute() {
        String query = "SELECT * FROM sedute_laurea";
        List<SedutaLaurea> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SedutaLaurea s = new SedutaLaurea(
                        rs.getString("id_seduta"),
                        rs.getString("data_seduta"),
                        rs.getString("ora_seduta"),
                        rs.getString("luogo"),
                        rs.getString("id_commissione")
                );
                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean aggiornaSeduta(SedutaLaurea seduta) {
        String query = "UPDATE sedute_laurea SET data_seduta = ?, ora_seduta = ?, luogo = ?, id_commissione = ? WHERE id_seduta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, seduta.getDataSeduta());
            stmt.setString(2, seduta.getOraSeduta());
            stmt.setString(3, seduta.getLuogo());
            stmt.setString(4, seduta.getIdCommissione());
            stmt.setString(5, seduta.getIdSeduta());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaSeduta(String idSeduta) {
        String query = "DELETE FROM sedute_laurea WHERE id_seduta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSeduta);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean prenotaStudente(String idSeduta, Studente studente) {
        String query = "INSERT INTO prenotazioni_seduta (id_seduta, matricola_studente) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSeduta);
            stmt.setString(2, studente.getMatricola());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rimuoviStudente(String idSeduta, Studente studente) {
        String query = "DELETE FROM prenotazioni_seduta WHERE id_seduta = ? AND matricola_studente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSeduta);
            stmt.setString(2, studente.getMatricola());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Studente> getStudentiPrenotati(String idSeduta) {
        String query = "SELECT s.matricola, s.nome, s.cognome, s.email, s.id_user " +
                "FROM prenotazioni_seduta p JOIN studenti s ON p.matricola_studente = s.matricola " +
                "WHERE p.id_seduta = ?";

        List<Studente> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idSeduta);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Studente studente = new Studente(
                        rs.getString("matricola"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
                lista.add(studente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
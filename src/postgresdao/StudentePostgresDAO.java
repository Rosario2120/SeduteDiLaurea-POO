package postgresdao;

import dao.StudenteDAO;
import model.Studente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentePostgresDAO implements StudenteDAO {

    private Connection connection;

    public StudentePostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean registraStudente(Studente studente) {
        String query = "INSERT INTO studenti (matricola, nome, cognome, email, id_user) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studente.getMatricola());
            stmt.setString(2, studente.getNome());
            stmt.setString(3, studente.getCognome());
            stmt.setString(4, studente.getEmail());
            stmt.setString(5, studente.getIdUser());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Studente getStudenteByMatricola(String matricola) {
        String query = "SELECT * FROM studenti WHERE matricola = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricola);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Studente(
                        rs.getString("matricola"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Studente getStudenteByIdUser(String idUser) {
        String query = "SELECT * FROM studenti WHERE id_user = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idUser);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Studente(
                        rs.getString("matricola"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Studente> getAllStudenti() {
        String query = "SELECT * FROM studenti";
        List<Studente> studenti = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Studente s = new Studente(
                        rs.getString("matricola"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
                studenti.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studenti;
    }

    @Override
    public boolean aggiornaStudente(Studente studente) {
        String query = "UPDATE studenti SET nome = ?, cognome = ?, email = ?, id_user = ? WHERE matricola = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studente.getNome());
            stmt.setString(2, studente.getCognome());
            stmt.setString(3, studente.getEmail());
            stmt.setString(4, studente.getIdUser());
            stmt.setString(5, studente.getMatricola());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaStudente(String matricola) {
        String query = "DELETE FROM studenti WHERE matricola = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricola);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
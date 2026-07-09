package postgresdao;

import dao.DocenteDAO;
import model.Docente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocentePostgresDAO implements DocenteDAO {

    private Connection connection;

    public DocentePostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean registraDocente(Docente docente) {
        String query = "INSERT INTO docenti (id_docente, nome, cognome, email, id_user) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, docente.getIdDocente());
            stmt.setString(2, docente.getNome());
            stmt.setString(3, docente.getCognome());
            stmt.setString(4, docente.getEmail());
            stmt.setString(5, docente.getIdUser());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Docente getDocenteByIdDocente(String idDocente) {
        String query = "SELECT * FROM docenti WHERE id_docente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idDocente);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Docente(
                        rs.getString("id_docente"),
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
    public Docente getDocenteByIdUser(String idUser) {
        String query = "SELECT * FROM docenti WHERE id_user = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idUser);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Docente(
                        rs.getString("id_docente"),
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
    public List<Docente> getAllDocenti() {
        String query = "SELECT * FROM docenti";
        List<Docente> docenti = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Docente d = new Docente(
                        rs.getString("id_docente"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("email"),
                        rs.getString("id_user")
                );
                docenti.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docenti;
    }

    @Override
    public Docente getCoordinatore() {
        String query = "SELECT * FROM docenti WHERE ruolo = 'COORDINATORE'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Docente(
                        rs.getString("id_docente"),
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
    public boolean aggiornaDocente(Docente docente) {
        String query = "UPDATE docenti SET nome = ?, cognome = ?, email = ?, id_user = ? WHERE id_docente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, docente.getNome());
            stmt.setString(2, docente.getCognome());
            stmt.setString(3, docente.getEmail());
            stmt.setString(4, docente.getIdUser());
            stmt.setString(5, docente.getIdDocente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaDocente(String idDocente) {
        String query = "DELETE FROM docenti WHERE id_docente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idDocente);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
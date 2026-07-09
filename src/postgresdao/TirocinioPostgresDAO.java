package postgresdao;

import dao.TirocinioDAO;
import model.Tirocinio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TirocinioPostgresDAO implements TirocinioDAO {

    private Connection connection;

    public TirocinioPostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean aggiungiTirocinio(Tirocinio tirocinio) {
        String query = "INSERT INTO tirocini (id_tirocinio, titolo, descrizione, id_docente) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tirocinio.getIdTirocinio());
            stmt.setString(2, tirocinio.getTitolo());
            stmt.setString(3, tirocinio.getDescrizione());
            stmt.setString(4, tirocinio.getIdDocente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tirocinio getTirocinioById(String idTirocinio) {
        String query = "SELECT * FROM tirocini WHERE id_tirocinio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idTirocinio);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tirocinio(
                        rs.getString("id_tirocinio"),
                        rs.getString("titolo"),
                        rs.getString("descrizione"),
                        rs.getString("id_docente")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tirocinio> getAllTirocini() {
        String query = "SELECT * FROM tirocini";
        List<Tirocinio> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tirocinio t = new Tirocinio(
                        rs.getString("id_tirocinio"),
                        rs.getString("titolo"),
                        rs.getString("descrizione"),
                        rs.getString("id_docente")
                );
                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean aggiornaTirocinio(Tirocinio tirocinio) {
        String query = "UPDATE tirocini SET titolo = ?, descrizione = ?, id_docente = ? WHERE id_tirocinio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tirocinio.getTitolo());
            stmt.setString(2, tirocinio.getDescrizione());
            stmt.setString(3, tirocinio.getIdDocente());
            stmt.setString(4, tirocinio.getIdTirocinio());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaTirocinio(String idTirocinio) {
        String query = "DELETE FROM tirocini WHERE id_tirocinio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idTirocinio);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
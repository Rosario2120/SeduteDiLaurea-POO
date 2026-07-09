package postgresdao;

import dao.TesiDAO;
import model.Tesi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TesiPostgresDAO implements TesiDAO {

    private Connection connection;

    public TesiPostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean aggiungiTesi(Tesi tesi) {
        String query = "INSERT INTO tesi (id_tesi, matricola_studente, id_docente_relatore, titolo, stato) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tesi.getIdTesi());
            stmt.setString(2, tesi.getMatricolaStudente());
            stmt.setString(3, tesi.getIdDocenteRelatore());
            stmt.setString(4, tesi.getTitolo());
            stmt.setString(5, tesi.getStato());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tesi getTesiById(String idTesi) {
        String query = "SELECT * FROM tesi WHERE id_tesi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idTesi);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tesi(
                        rs.getString("id_tesi"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_docente_relatore"),
                        rs.getString("titolo"),
                        rs.getString("stato")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Tesi> getAllTesi() {
        String query = "SELECT * FROM tesi";
        List<Tesi> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tesi t = new Tesi(
                        rs.getString("id_tesi"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_docente_relatore"),
                        rs.getString("titolo"),
                        rs.getString("stato")
                );
                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Tesi getTesiByStudente(String matricolaStudente) {
        String query = "SELECT * FROM tesi WHERE matricola_studente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricolaStudente);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Tesi(
                        rs.getString("id_tesi"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_docente_relatore"),
                        rs.getString("titolo"),
                        rs.getString("stato")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean aggiornaTesi(Tesi tesi) {
        String query = "UPDATE tesi SET matricola_studente = ?, id_docente_relatore = ?, titolo = ?, stato = ? WHERE id_tesi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tesi.getMatricolaStudente());
            stmt.setString(2, tesi.getIdDocenteRelatore());
            stmt.setString(3, tesi.getTitolo());
            stmt.setString(4, tesi.getStato());
            stmt.setString(5, tesi.getIdTesi());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean aggiornaStatoTesi(String idTesi, String nuovoStato) {
        String query = "UPDATE tesi SET stato = ? WHERE id_tesi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuovoStato);
            stmt.setString(2, idTesi);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaTesi(String idTesi) {
        String query = "DELETE FROM tesi WHERE id_tesi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idTesi);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
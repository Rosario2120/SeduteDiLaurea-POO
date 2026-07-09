package postgresdao;

import dao.RichiestaTirocinioDAO;
import model.RichiestaTirocinio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RichiestaTirocinioPostgresDAO implements RichiestaTirocinioDAO {

    private Connection connection;

    public RichiestaTirocinioPostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean aggiungiRichiesta(RichiestaTirocinio richiesta) {
        String query = "INSERT INTO richieste_tirocinio (id_richiesta, matricola_studente, id_tirocinio, stato) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, richiesta.getIdRichiesta());
            stmt.setString(2, richiesta.getMatricolaStudente());
            stmt.setString(3, richiesta.getIdTirocinio());
            stmt.setString(4, richiesta.getStato());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RichiestaTirocinio getRichiestaById(String idRichiesta) {
        String query = "SELECT * FROM richieste_tirocinio WHERE id_richiesta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idRichiesta);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RichiestaTirocinio(
                        rs.getString("id_richiesta"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_tirocinio"),
                        rs.getString("stato")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<RichiestaTirocinio> getAllRichieste() {
        String query = "SELECT * FROM richieste_tirocinio";
        List<RichiestaTirocinio> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RichiestaTirocinio r = new RichiestaTirocinio(
                        rs.getString("id_richiesta"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_tirocinio"),
                        rs.getString("stato")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<RichiestaTirocinio> getRichiesteByStudente(String matricolaStudente) {
        String query = "SELECT * FROM richieste_tirocinio WHERE matricola_studente = ?";
        List<RichiestaTirocinio> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, matricolaStudente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RichiestaTirocinio r = new RichiestaTirocinio(
                        rs.getString("id_richiesta"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_tirocinio"),
                        rs.getString("stato")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<RichiestaTirocinio> getRichiesteByTirocinio(String idTirocinio) {
        String query = "SELECT * FROM richieste_tirocinio WHERE id_tirocinio = ?";
        List<RichiestaTirocinio> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idTirocinio);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RichiestaTirocinio r = new RichiestaTirocinio(
                        rs.getString("id_richiesta"),
                        rs.getString("matricola_studente"),
                        rs.getString("id_tirocinio"),
                        rs.getString("stato")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean aggiornaStatoRichiesta(String idRichiesta, String nuovoStato) {
        String query = "UPDATE richieste_tirocinio SET stato = ? WHERE id_richiesta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuovoStato);
            stmt.setString(2, idRichiesta);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean aggiornaRichiesta(RichiestaTirocinio richiesta) {
        String query = "UPDATE richieste_tirocinio SET matricola_studente = ?, id_tirocinio = ?, stato = ? WHERE id_richiesta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, richiesta.getMatricolaStudente());
            stmt.setString(2, richiesta.getIdTirocinio());
            stmt.setString(3, richiesta.getStato());
            stmt.setString(4, richiesta.getIdRichiesta());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaRichiesta(String idRichiesta) {
        String query = "DELETE FROM richieste_tirocinio WHERE id_richiesta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idRichiesta);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
package postgresdao;

import dao.UserDAO;
import model.User;

import java.sql.*;

public class UserPostgresDAO implements UserDAO {

    private Connection connection;

    public UserPostgresDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean registraUtente(User user) {
        String query = "INSERT INTO utenti (id_user, username, password_hash, ruolo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getIdUser());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getRuolo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User login(String username, String passwordHash) {
        String query = "SELECT * FROM utenti WHERE username = ? AND password_hash = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id_user"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("ruolo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(String idUser) {
        String query = "SELECT * FROM utenti WHERE id_user = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idUser);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id_user"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("ruolo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM utenti WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id_user"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("ruolo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean usernameEsiste(String username) {
        String query = "SELECT username FROM utenti WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
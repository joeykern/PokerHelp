package persist;

import exceptions.CrudException;
import model.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdministratorCRUDPersister implements CRUDPersister<Administrator> {

    private Connection conn;

    public JDBCAdministratorCRUDPersister(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Administrator admin) throws CrudException, SQLException {
        try {
            String groupId = admin.getGroupId();
            if (groupId == null) {
                throw new SQLException("null group id");
            }
        } catch (SQLException e) {
            throw new CrudException("Unable to create the administrator due to null id", e);
        }

        try {
            String name = admin.getUsername();
            if (name == null) {
                throw new SQLException("null username");
            }
        } catch (SQLException e) {
            throw new CrudException("Unable to create the administrator due to null username", e);
        }

        String sql = "INSERT INTO Administrators(username, groupId) VALUES (?, ?);";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, admin.getUsername());
            pst.setString(2, admin.getGroupId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new CrudException("Unable to create the administrator", e);
        }

    }

    @Override
    public Administrator read(String name) throws CrudException {
        String sql = "SELECT * FROM Administrators WHERE username = ?;";
        PreparedStatement pst = null;
        Administrator admin = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) return null;

            admin = new Administrator(rs.getString("username"),
                    rs.getString("groupId")
            );

        } catch (SQLException e) {
            throw new CrudException("Unable to read the administrator", e);
        }

        return admin;
    }

    @Override
    public void update(Administrator admin) {
        String sql = "UPDATE Administrators SET username = ? WHERE groupId = ?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, admin.getUsername());
            pst.setString(2, admin.getGroupId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new CrudException("Unable to update the administrator", e);
        }
    }

    @Override
    public void delete(String group) throws CrudException {
        String sql = "DELETE FROM Administrators WHERE groupId = ?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, group);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new CrudException("Unable to delete administrator", e);
        }
    }

    /**
     * Return a list of all administrators where their name is or contains the given name.
     *
     * @param name must be non null.
     * @return a list of courses.
     */
    public List<Administrator> readAll(String name) throws CrudException {
        List<Administrator> administrators = new ArrayList<Administrator>();

        if (name == null) {
            return null;
        }

        String sql = "SELECT * FROM Administrators;";
        PreparedStatement pst = null;
        Administrator admin = null;

        try {
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                admin = new Administrator(rs.getString("username"),
                        rs.getString("groupId")
                );

                administrators.add(admin);
            }

        } catch (SQLException e) {
            throw new CrudException("Unable to read the professor", e);
        }

        return administrators;
    }

}

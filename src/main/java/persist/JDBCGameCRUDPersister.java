package persist;

import exceptions.CrudException;
import model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCGameCRUDPersister implements CRUDPersister<Game> {

    private Connection conn;

    public JDBCGameCRUDPersister(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Game game) throws CrudException, SQLException {
        try {
            String gameId = game.getGameId();
            if (gameId == null) {
                throw new SQLException("null game id");
            }
        } catch (SQLException e) {
            throw new CrudException("Unable to create the game due to null id", e);
        }

        String sql = "INSERT INTO Games(gameId, gameName, gameTime, entryFee, registrants) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, game.getGameId());
            pst.setString(2, game.getGameName());
            pst.setString(3, game.getGameTime());
            pst.setString(4, game.getEntryFee());
            pst.setString(5, game.getRegistrants());
            //pst.setString(5, assignment.getLink());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new CrudException("Unable to create the Game", e);
        }
    }

    @Override
    public Game read(String gameId) throws CrudException {
        String sql = "SELECT * FROM Games WHERE gameId = ?;";
        PreparedStatement pst = null;
        Game game = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gameId);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) return null;

            game = new Game(
                    rs.getString("gameId"),
                    rs.getString("gameName"),
                    rs.getString("gameTime"),
                    rs.getString("entryFee"),
                    rs.getString("registrants")
            );
            //course.setId(rs.getString("id"));
        } catch (SQLException e) {
            throw new CrudException("Unable to read the game", e);
        }

        return game;
    }

    @Override
    public void update(Game game) {
        String sql = "UPDATE Games SET gameName = ?, gameTime = ?, entryFee = ?, registrants = ? WHERE gameId = ?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, game.getGameName());

            pst.setString(2, game.getGameTime());
            pst.setString(3, game.getEntryFee());
            pst.setString(4, game.getRegistrants());
            pst.setString(5, game.getGameId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new CrudException("Unable to update the game", e);
        }
    }

    /*public List<Assignment> findByCD(String courseId) throws CrudException {
        List<Assignment> assignments = new ArrayList<Assignment>();


        String sql = "SELECT * FROM Assignments WHERE assignmentId = ?;";
        PreparedStatement pst = null;

        Assignment assignment = null;

        try {
            pst = conn.prepareStatement(sql);
            //use % for formatting to use LIKE command
            //String search = "%" + professorName + "%";
            pst.setString(1, courseId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                assignment = new Assignment(
                        rs.getString("assignmentId"),
                        rs.getString("assignmentNumber"),
                        rs.getString("studentTask"),
                        rs.getString("dueDate"),
                        rs.getString("added"),
                        rs.getString("materialType")

                );
                //course.setId(rs.getString("id"));
                assignments.add(assignment);
            }

        } catch (SQLException e) {
            throw new CrudException("Unable to read the course", e);
        }

        return assignments;
    }*/

    @Override
    public void delete(String gameName) throws CrudException {
        String sql = "DELETE FROM Games WHERE gameName = ?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gameName);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new CrudException("Unable to delete game", e);
        }
    }

    /**
     * Return a list of all games where their gameName is the given Name.
     *
     * @param gameName must be non null.
     * @return a list of games.
     */

    public List<Game> readAll(String gameName) throws CrudException {
        List<Game> games = new ArrayList<Game>();


        if (gameName == null) {
            return null;
        }

        String sql = "SELECT * FROM Games;";
        PreparedStatement pst = null;
        Game game = null;

        try {
            pst = conn.prepareStatement(sql);
            //use % for formatting to use LIKE command
            //String search = "%" + assignmentId + "%";
            //pst.setString(1, search);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                game = new Game(
                        rs.getString("gameId"),
                        rs.getString("gameName"),
                        rs.getString("gameTime"),
                        rs.getString("entryFee"),
                        rs.getString("registrants")
                );

                games.add(game);
            }

        } catch (SQLException e) {
            throw new CrudException("Unable to read the game", e);
        }

        return games;
    }

}

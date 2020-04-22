package dao;

import model.Game;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GameDao {
    void add (Game game);
    List<Game> findAll();
    //void updateDoc(String studentTask, byte[] fileBytes, Connection conn);
    //Response download(String filename, Connection conn, Response response) throws IOException, SQLException;


}

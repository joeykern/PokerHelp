package dao;

import model.Administrator;
import model.Game;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AdministratorDao {
    void add (Administrator administrator);
    List<Administrator> findAll();
}

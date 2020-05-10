package server;

import dao.*;
import model.Administrator;
import model.Game;
import persist.JDBCAdministratorCRUDPersister;
import persist.JDBCGameCRUDPersister;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class webServer {
    public static void main(String[] args) throws SQLException {

        /* HEROKU ASSIGNED PORT GETTER HERE */

        /* TWILIO ISH IF WANT NOTIFICATIONS */

        final String URI = "jdbc:sqlite:./Store.db";
        Connection conn = DriverManager.getConnection(URI);

        String sql;
        Statement st = conn.createStatement();

        sql = "CREATE TABLE IF NOT EXISTS Administrators (username VARCHAR(20), " +
                "groupId VARCHAR(20) PRIMARY KEY);";
        st.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Games (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "groupId VARCHAR(20), gameName VARCHAR(100), gameTime VARCHAR(10), " +
                "entryFee VARCHAR(15), registrants VARCHAR(10), FOREIGN KEY(groupId) REFERENCES " +
                "Administrators(groupId) ON DELETE CASCADE);";
        st.execute(sql);

        sql = "PRAGMA foreign_keys = ON;";
        st.execute(sql);


        JDBCAdministratorCRUDPersister administratorCRUD = new JDBCAdministratorCRUDPersister(conn);
        JDBCGameCRUDPersister gameCRUD = new JDBCGameCRUDPersister(conn);

        GameDao gameDao = new InMemoryGameDao();
        AdministratorDao administratorDao = new InMemoryAdministratorDao();

        staticFiles.location("/public");

        getSignUpPage(administratorDao);
        getLogInPage(administratorDao);

    }

    private static void getSignUpPage(AdministratorDao administratorDao) {
        get("/signup", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("adminList", administratorDao.findAll());
            return new ModelAndView(model, "signup.hbs");
        }), new HandlebarsTemplateEngine());
    }

    private static void getLogInPage(AdministratorDao administratorDao) {
        get("/", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("adminList", administratorDao.findAll());
            return new ModelAndView(model, "login.hbs");
        }), new HandlebarsTemplateEngine());
    }

}

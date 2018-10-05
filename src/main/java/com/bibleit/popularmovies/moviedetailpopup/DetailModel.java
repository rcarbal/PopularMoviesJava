package moviedetailpopup;

import interfaces.DatabaseConnectObserver;
import javafx.concurrent.Service;
import services.GetDatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class DetailModel {

    private static Connection connection;
    private ArrayList<DatabaseConnectObserver> observers = new ArrayList<>();

    public void connectDatabase() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            getConnection();
        }else {

            notifyAllObservers(true);
        }


    }

    private void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");

        Service<Connection> service = new GetDatabaseConnection();
        service.setOnSucceeded(e ->{
            connection = service.getValue();
            try {
                initialize();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            notifyAllObservers(true);
        });
        service.restart();
    }

    private void initialize() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='FAVORITES'"
        );
        if (!res.next()) {

            Statement statement1 = connection.createStatement();
            statement1.execute("CREATE TABLE FAVORITES " +
                    "(ID INTEGER PRIMARY KEY NOT NULL, " +
                    "movieId           INTEGER (60)   NOT NULL)");


        }
    }

    int addMovie(int movieId) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            getConnection();
        }

        PreparedStatement prep = connection.prepareStatement("INSERT INTO FAVORITES(movieId) VALUES(?);");
        prep.setInt(1, movieId);
        return prep.executeUpdate();
    }

    int isFavorite(int id) {
        int index = -1;
        if (connection == null) {
            try {
                getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            String query = "SELECT ID FROM FAVORITES WHERE movieId = "+id;
            Statement state = connection.createStatement();
            ResultSet res = null;
            res =state.executeQuery(query);
            if (res.next())index = res.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }


    public int removeMovie(int id) {
        int index = -1;
        if (connection == null){
            try {
                getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        String query = "DELETE FROM FAVORITES WHERE movieId = " + id;
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public void attach(DatabaseConnectObserver movieDataObserver){
        observers.add(movieDataObserver);
    }

    public void notifyAllObservers(boolean conneted){
        for(DatabaseConnectObserver databaseObserver : observers){
            databaseObserver.update(conneted);
        }
    }
}


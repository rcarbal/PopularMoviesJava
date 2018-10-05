package moviedetailpopup;

import java.sql.*;

public class DetailModel {

    private static Connection connection;

    public void connectDatabase() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            getConnection();
        }

    }

    private void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:favorite_database.db");

        initialize();
    }

    private void initialize() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='FAVORITES'"
        );
        if (!res.next()) {

            Statement statement1 = connection.createStatement();
            statement1.execute("CREATE TABLE FAVORITES " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
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

    ResultSet isFavorite(int id) {
        ResultSet res = null;
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
            res = state.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}


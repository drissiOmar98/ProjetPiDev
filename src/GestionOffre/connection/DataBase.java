package GestionOffre.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aero_space", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }
    }
/*
    public static ObservableList<Clients> getDataClients()
    {
        Connection conn = conDB();
        ObservableList<Clients> list = FXCollections.observableArrayList();
        return null;

    }


 */
}

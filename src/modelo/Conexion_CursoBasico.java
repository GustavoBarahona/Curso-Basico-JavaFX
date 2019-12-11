
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SISTEMAS
 */
public class Conexion_CursoBasico {

    private Connection connection;
    //private String url = "jdbc:mysql://localhost/dbregistrofx";
    //private String url = "jdbc:mysql://localhost/dbregistrofx?autoReconnect=true&useSSL=false";
    private String url = "jdbc:mysql://34.95.173.255:3306/dbregistrofx?autoReconnect=true&useSSL=false";
    private String usuario = "root";
    private String password = "7Xor&x#.hFfP";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void establecerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Conexion_CursoBasico cb = new Conexion_CursoBasico();
//    }

}

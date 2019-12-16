
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
    //Conexión local normal:
    //private String url = "jdbc:mysql://localhost/dbregistrofx";
    
    //Conexión local con mysql versión avanzada:
    //private String url = "jdbc:mysql://localhost/dbregistrofx?autoReconnect=true&useSSL=false";
    
    //Conexión Google Cloud:
    //private String url = "jdbc:mysql://34.95.173.255:3306/dbregistrofx?autoReconnect=true&useSSL=false";
    
    //Conexión Clever cloud:
    private String url = "jdbc:mysql://b78su5y6rebprkruiwnu-mysql.services.clever-cloud.com:3306/b78su5y6rebprkruiwnu?autoReconnect=true&useSSL=false";
    private String usuario = "ugwiimutpwayi1px";
    private String password = "yhV8PK6hRRPg8f53d6xG";

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

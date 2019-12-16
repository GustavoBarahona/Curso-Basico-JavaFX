
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author SISTEMAS
 */
public class CentroEstudios {
    private IntegerProperty codigo_centro;
    private StringProperty nombre_centro_estudio;
    
    public CentroEstudios(Integer codigo_centro, String nombre_centro){
        this.codigo_centro = new SimpleIntegerProperty(codigo_centro);
        this.nombre_centro_estudio = new SimpleStringProperty(nombre_centro);
    }
    
    public Integer getCodigoCentro(){
        return codigo_centro.get();
    }
    
    public void setCodigoCentro(Integer codigo_centro){
        this.codigo_centro = new SimpleIntegerProperty(codigo_centro);
    }
    
    public String getNombreCentro(){
        return nombre_centro_estudio.get();
    }
    
    public void setNombreCentro(String nombre_centro){
        this.nombre_centro_estudio = new SimpleStringProperty(nombre_centro);
    }
    
    public IntegerProperty codigoCentroProperty(){
        return codigo_centro;
    }
    
    public StringProperty nombreCentroProperty(){
        return nombre_centro_estudio;
    }
    
    public static void llenarInformacion(Connection connection, ObservableList<CentroEstudios> lista){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT codigo_centro, nombre_centro_estudio FROM tbl_centros_estudio;"
            );
            while(resultado.next()){
                lista.add(new CentroEstudios(
                        resultado.getInt("codigo_centro"), 
                        resultado.getString("nombre_centro_estudio"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        return codigo_centro.get() +" "+ nombre_centro_estudio.get();
    }
}

package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class Alumno {

    private IntegerProperty codigoAlumno;
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty edad;
    private StringProperty genero;
    private Date fechaIngreso;
    private CentroEstudios centroEstudios;
    private Carrera carrera;

    public Alumno(Integer codigoAlumno, String nombre, String apellido, Integer edad, String genero, Date fechaIngreso, CentroEstudios centroEstudio, Carrera carrera) {
        this.codigoAlumno = new SimpleIntegerProperty(codigoAlumno);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.edad = new SimpleIntegerProperty(edad);
        this.genero = new SimpleStringProperty(genero);
        this.fechaIngreso = fechaIngreso;
        this.centroEstudios = centroEstudio;
        this.carrera = carrera;

    }

    public Integer getCodigoAlumno() {
        return codigoAlumno.get();
    }

    public void setCodigoAlumno(Integer codigoAlumno) {
        this.codigoAlumno = new SimpleIntegerProperty(codigoAlumno);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido = new SimpleStringProperty(apellido);
    }

    public Integer getEdad() {
        return edad.get();
    }

    public void setEdad(Integer edad) {
        this.edad = new SimpleIntegerProperty(edad);
    }

    public String getGetero() {
        return genero.get();
    }

    public void setGenero(String genero) {
        this.genero = new SimpleStringProperty(genero);
    }

    /**
     * ***********************************************************************
     */
    public IntegerProperty codigoAlumnoProperty() {
        return codigoAlumno;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public StringProperty generoProperty() {
        return genero;
    }

    /**
     * *******************************************************************
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CentroEstudios getCentroEstudios() {
        return centroEstudios;
    }

    public void setCentroEstudios(CentroEstudios centroEstudios) {
        this.centroEstudios = centroEstudios;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * **************************** METODOS *********************************
     * *************************************************************************
     */
    public int guardarRegistro(Connection conexion) {
        try {
            PreparedStatement instruccion
                    = conexion.prepareStatement("INSERT INTO tbl_alumnos (nombre, apellido, edad, genero,"
                            + "fecha_ingreso, codigo_carrera, codigo_centro)"
                            + "values(?,?,?,?,?,?,?)");
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, apellido.get());
            instruccion.setInt(3, edad.get());
            instruccion.setString(4, genero.get());
            instruccion.setDate(5, fechaIngreso);
            instruccion.setInt(6, carrera.getCodigoCarrera());
            instruccion.setInt(7, centroEstudios.getCodigoCentro());
            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int actualizarRegistro(Connection conexion) {

        try {
            PreparedStatement instruccion
                    = conexion.prepareStatement("UPDATE tbl_alumnos SET "
                            + "nombre = ?,"
                            + "apellido = ?,"
                            + "edad = ?,"
                            + "fecha_ingreso = ?,"
                            + "codigo_carrera = ?,"
                            + "codigo_centro = ?,"
                            + "genero = ?"
                            + "WHERE codigo_alumno = ?");
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, apellido.get());
            instruccion.setInt(3, edad.get());

            instruccion.setDate(4, fechaIngreso);
            instruccion.setInt(5, carrera.getCodigoCarrera());
            instruccion.setInt(6, centroEstudios.getCodigoCentro());
            instruccion.setString(7, genero.get());
            instruccion.setInt(8, codigoAlumno.get());
            return instruccion.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int eliminarRegistro(Connection conexion) {
        try {
            PreparedStatement instruccion = conexion.prepareStatement(
            "DELETE FROM tbl_alumnos WHERE codigo_alumno = ?");
            instruccion.setInt(1, codigoAlumno.get());
            return instruccion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void llenarInformacion(Connection conexion, ObservableList<Alumno> lista) {
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(
                    "SELECT tbl_alm.codigo_alumno, \n"
                    + "		tbl_alm.nombre, \n"
                    + "		tbl_alm.apellido, \n"
                    + "         tbl_alm.edad, \n"
                    + "         tbl_alm.genero, \n"
                    + "         tbl_alm.fecha_ingreso, \n"
                    + "         tbl_alm.codigo_centro, \n"
                    + "         tbl_alm.codigo_carrera, \n"
                    + "         tbl_ce.nombre_centro_estudio, \n"
                    + "         tbl_ca.nombre_carrera, \n"
                    + "         tbl_ca.cantidad_asignaturas \n"
                    + "         FROM tbl_alumnos tbl_alm\n"
                    + "         INNER JOIN tbl_centros_estudio tbl_ce ON tbl_ce.codigo_centro = tbl_alm.codigo_centro\n"
                    + "         INNER JOIN tbl_carreras tbl_ca ON tbl_ca.codigo_carrera = tbl_alm.codigo_carrera;"
            );
            while (resultado.next()) {
                lista.add(new Alumno(
                        resultado.getInt("codigo_alumno"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getInt("edad"),
                        resultado.getString("genero"),
                        resultado.getDate("fecha_ingreso"),
                        new CentroEstudios(resultado.getInt("codigo_centro"), resultado.getString("nombre_centro_estudio")),
                        new Carrera(resultado.getInt("codigo_carrera"), resultado.getString("nombre_carrera"), resultado.getInt("cantidad_asignaturas"))
                )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curso.básico.javafx;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alumno;
import modelo.Carrera;
import modelo.CentroEstudios;
import modelo.Conexion_CursoBasico;

/**
 *
 * @author SISTEMAS
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableColumn<Alumno, Number> clmnCodigoAlumno;
    @FXML
    private TableColumn<Alumno, String> clmnNombre;
    @FXML
    private TableColumn<Alumno, String> clmnApellido;
    @FXML
    private TableColumn<Alumno, Number> clmnEdad;
    @FXML
    private TableColumn<Alumno, String> clmnGenero;
    @FXML
    private TableColumn<Alumno, Date> clmnFechaIngreso;
    @FXML
    private TableColumn<Alumno, CentroEstudios> clmnCentroEstudio;
    @FXML
    private TableColumn<Alumno, Carrera> clmnCarrera;
    
    
    
    private Label label;
    @FXML
    private ToggleGroup GrupoGenero;
    @FXML
    private ComboBox<Carrera> cbxCarrera;

    @FXML
    private ComboBox<CentroEstudios> cbxCentroEstudio;
    
    @FXML
    private TableView<Alumno> tblViewAlumno;

    private ObservableList<Carrera> listaCarreras;
    private ObservableList<CentroEstudios> listaCentroEstudios;
    private ObservableList<Alumno> listaAlumnos;

    private Conexion_CursoBasico conexion;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion_CursoBasico();
        conexion.establecerConexion();

        listaCarreras = FXCollections.observableArrayList();
        listaCentroEstudios = FXCollections.observableArrayList();
        listaAlumnos = FXCollections.observableArrayList();

        try {
            Carrera.llenarInformacion(conexion.getConnection(), listaCarreras);
            CentroEstudios.llenarInformacion(conexion.getConnection(), listaCentroEstudios);
            Alumno.llenarInformacion(conexion.getConnection(), listaAlumnos);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cbxCarrera.setItems(listaCarreras);
        //listaCarreras.add(new Carrera(1, "Carrera 1", 56));        
        cbxCentroEstudio.setItems(listaCentroEstudios);
        tblViewAlumno.setItems(listaAlumnos);
            
        //Enlazar columnas con atributos
        clmnCodigoAlumno.setCellValueFactory(new PropertyValueFactory<Alumno, Number>("codigoAlumno"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Alumno, String>("apellido"));
        clmnEdad.setCellValueFactory(new PropertyValueFactory<Alumno, Number>("edad"));
        clmnGenero.setCellValueFactory(new PropertyValueFactory<Alumno, String>("genero"));
        clmnFechaIngreso.setCellValueFactory(new PropertyValueFactory<Alumno, Date>("fechaIngreso"));
        clmnCarrera.setCellValueFactory(new PropertyValueFactory<Alumno, Carrera>("carrera"));
        clmnCentroEstudio.setCellValueFactory(new PropertyValueFactory<Alumno, CentroEstudios>("centroEstudios"));

        conexion.cerrarConexion();
    }

}

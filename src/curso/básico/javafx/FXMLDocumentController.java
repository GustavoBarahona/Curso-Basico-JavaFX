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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField txt_codigo;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_apellido;
    @FXML
    private TextField txt_edad;
    @FXML
    private DatePicker dtpkr_fecha;
    @FXML
    private RadioButton rbt_femenino;
    @FXML
    private RadioButton rbt_masculino;
    @FXML
    private Button btn_guardar;
    @FXML
    private Button btn_actualizar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_nuevo;
    

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

        gestionarEventos();
        
        conexion.cerrarConexion();
    }

    private void gestionarEventos() {
        tblViewAlumno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Alumno>() {
            @Override
            public void changed(ObservableValue<? extends Alumno> observable, 
                    Alumno oldValue, Alumno newValue) {                
                txt_codigo.setText(newValue.getCodigoAlumno().toString());
                txt_nombre.setText(newValue.getNombre());
                txt_apellido.setText(newValue.getApellido());
                txt_edad.setText(newValue.getEdad().toString());
                if(newValue.getGetero().equals("F")){
                    rbt_femenino.setSelected(true);
                    rbt_masculino.setSelected(false);
                }else{
                    rbt_femenino.setSelected(false);
                    rbt_masculino.setSelected(true);
                }
                dtpkr_fecha.setValue(newValue.getFechaIngreso().toLocalDate());
                cbxCarrera.setValue(newValue.getCarrera());
                cbxCentroEstudio.setValue(newValue.getCentroEstudios());
                
                btn_guardar.setDisable(true);
                btn_actualizar.setDisable(false);
                btn_eliminar.setDisable(false);
            }
        });
    }
    
    @FXML
    public void limpiarComponentes(){
        txt_codigo.setText(null);
        txt_nombre.setText(null);
        txt_apellido.setText(null);
        txt_edad.setText(null);
        rbt_femenino.setSelected(false);
        rbt_masculino.setSelected(false);
        dtpkr_fecha.setValue(null);
        cbxCarrera.setValue(null);
        cbxCentroEstudio.setValue(null);
        
        btn_guardar.setDisable(false);
        btn_eliminar.setDisable(true);
        btn_actualizar.setDisable(true);        
    }

}


package curso.básico.javafx;

import com.sun.javaws.Main;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author SISTEMAS
 */
public class CursoBásicoJavaFX extends Application {

    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mainWindow();
    }

    public void mainWindow() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(CursoBásicoJavaFX.class.getResource("FXMLDocument.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setResizable(true);
            stage.setTitle("Titulo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

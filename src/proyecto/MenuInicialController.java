/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.File;
import static java.lang.System.gc;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author Juan
 */
public class MenuInicialController implements Initializable {
    
    private boolean dibujo = false;
    
    @FXML
    private Button botonRedo;
    @FXML
    private Button botonUndo;
    @FXML
    private ImageView imagenPDF;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button button;
    @FXML
    private Canvas canv;

    private GraphicsContext gc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscar.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            File imgFile = fileChooser.showOpenDialog(null);

            // Mostar la imagen
            if (imgFile != null) {
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                imagenPDF.setImage(image);
            }
        });

        gc = canv.getGraphicsContext2D();
        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());

    }

    @FXML
    private void handleRedo(ActionEvent event) {
    }

    @FXML
    private void handleUndo(ActionEvent event) {
    }

    @FXML
    private void handleImagenPdf(MouseEvent event) {
    }

    @FXML
    private void ModoRect(ActionEvent event) {
        dibujo = !dibujo;
        if (dibujo) {
            dibujar();
        } else {
            canv.setOnMousePressed(null);
            canv.setOnMouseReleased(null);
        }
    }
    
    private void dibujar(){
        canv.setOnMousePressed((MouseEvent e)->{
            gc.setStroke(Color.RED);
            canv.setOnMouseReleased((MouseEvent a)->{
                double ex1=e.getX();
                double ey1=e.getY();
                if (ex1<=a.getX()) {
                    if (ey1<=a.getY()) {
                        gc.strokeRect(ex1, ey1, a.getX()-ex1,a.getY()-ey1);
                    }
                    else{
                       gc.strokeRect(ex1, a.getY(), a.getX()-ex1,ey1-a.getY());
                    }
                }
                else{
                    if (ey1<=a.getY()) {
                        gc.strokeRect(a.getX(), ey1,ex1-a.getX(),a.getY()-ey1);
                    }
                    else{
                       gc.strokeRect(a.getX(), a.getY(),ex1-a.getX(),ey1-a.getY());
                    }
                }


            });
        });

    }
}

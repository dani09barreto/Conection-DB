package org.example.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.Controller.FacadeOCR;
import org.example.Model.DTOReporte;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerReporte implements Initializable {

    private final FacadeOCR facadeOCR = new FacadeOCR();

    @FXML
    private TableColumn<DTOReporte, String> mesAnioTabla;

    @FXML
    private TableView<DTOReporte> reporteTabla;

    @FXML
    private TableColumn<DTOReporte, Integer> totalTabla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reporteTabla.getItems().addAll(facadeOCR.consultarAcomulados());
    }

}

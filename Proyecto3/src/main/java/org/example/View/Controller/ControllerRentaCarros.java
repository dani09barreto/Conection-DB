package org.example.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Model.Renta;

public class ControllerRentaCarros {

    private Renta rentaActual = new Renta();

    @FXML
    private Button Button_AgregarBillete;

    @FXML
    private Button Button_AgregarLinea;

    @FXML
    private Button Button_EliminarLinea;

    @FXML
    private Button Button_GenerarReporte;

    @FXML
    private Button Button_NuevaRenta;

    @FXML
    private Button Button_TerminarRenta;

    @FXML
    private TextField cantidadBilletes;

    @FXML
    private TextField cantidadCarro;

    @FXML
    private ChoiceBox<?> carroXPuestos;

    @FXML
    private ChoiceBox<?> denominaciones;

    @FXML
    private Label fecha;

    @FXML
    private Label saldoBilletes;

    @FXML
    private Label totalRenta;

    @FXML
    private Label vueltas;

    @FXML
    void agregarBillete(ActionEvent event) {

    }

    @FXML
    void agregarLinea(ActionEvent event) {

    }

    @FXML
    void eliminarLinea(ActionEvent event) {

    }

    @FXML
    void generarReporte(ActionEvent event) {

    }

    @FXML
    void nuevaRenta(ActionEvent event) {

    }

    @FXML
    void terminarRenta(ActionEvent event) {

    }

}

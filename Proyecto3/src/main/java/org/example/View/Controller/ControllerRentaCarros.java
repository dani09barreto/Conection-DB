package org.example.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Controller.FacadeOCR;
import org.example.Model.Carro;
import org.example.Model.Linea;
import org.example.Model.Renta;
import org.example.Model.TablaLinea;
import org.example.Utils.AlertUtils;
import org.example.Utils.Exeptions.CarroNoExiste;
import org.example.Utils.Exeptions.CarroSinExistencias;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerRentaCarros implements Initializable {

    private final FacadeOCR facadeOCR = new FacadeOCR();
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
    private ComboBox<?> carroXPuestos;

    @FXML
    private ChoiceBox<?> denominaciones;

    @FXML
    private Label fecha;

    @FXML
    private Label puestosLabel;

    @FXML
    private Label saldoBilletes;

    @FXML
    private Label totalRenta;

    @FXML
    private Label vueltas;

    @FXML
    private TableView<TablaLinea> tablaLinea;

    @FXML
    private TableColumn<TablaLinea, Integer> columCantidad;

    @FXML
    private TableColumn<TablaLinea, String> columPlaca;

    @FXML
    private TableColumn<TablaLinea, Integer> columPrecio;

    @FXML
    private TableColumn<TablaLinea, Integer> columSubTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFecha();
    }

    @FXML
    void agregarBillete(ActionEvent event) {

    }

    @FXML
    void agregarLinea(ActionEvent event) {
        Linea linea = new Linea();
        linea.setCarroRentado(new Carro("NNI615",  2, 45, 6));
        linea.setCantidad(5);
        linea.setNumero(1);

        try{
            facadeOCR.agregarLinea(linea);
        }catch (CarroNoExiste ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        } catch (CarroSinExistencias ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
    }

    @FXML
    void eliminarLinea(ActionEvent event) {

    }

    @FXML
    void generarReporte(ActionEvent event) {

    }

    @FXML
    void mostrarPuestosXPlaca(ActionEvent event) {

    }

    @FXML
    void nuevaRenta(ActionEvent event) {
        this.rentaActual = new Renta();
        setFecha();
    }

    @FXML
    void terminarRenta(ActionEvent event) {

    }
    public void setFecha (){
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaActual = Calendar.getInstance();
        this.fecha.setText(Fecha.format(fechaActual.getTime()));
    }
}

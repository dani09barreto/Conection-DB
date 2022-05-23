package org.example.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Controller.FacadeOCR;
import org.example.Model.*;
import org.example.Utils.AlertUtils;
import org.example.Utils.Exeptions.ErrorAgregarLinea;
import org.example.Utils.Exeptions.ErrorPago;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerRentaCarros implements Initializable {

    private final FacadeOCR facadeOCR = new FacadeOCR();

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
    private TableView<Linea> tablaLinea;

    @FXML
    private TableColumn<Linea, Integer> columCantidad;

    @FXML
    private TableColumn<Linea, String> columPlaca;

    @FXML
    private TableColumn<Linea, Integer> columPrecio;

    @FXML
    private TableColumn<Linea, Integer> columSubTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFecha();
    }

    @FXML
    void agregarBillete(ActionEvent event) {

    }

    @FXML
    void agregarLinea(ActionEvent event) {
        DTOResumen resumen;
        Linea linea = new Linea(
                5,
                facadeOCR.getCarroContro().existeCarro("ZJK064"),
                this.facadeOCR.getRentaActual()
        );
        System.out.println("a");
        try{
            resumen = facadeOCR.agregarLinea(linea);
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarLinea(resumen.getMensajeError());
            System.out.println(resumen.getTotalRenta());
        }catch (ErrorAgregarLinea ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
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
        this.facadeOCR.setRentaActual(new Renta());
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

package org.example.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Controller.FacadeOCR;
import org.example.Model.*;
import org.example.Utils.AlertUtils;
import org.example.Utils.Exeptions.ErrorAgregarBillete;
import org.example.Utils.Exeptions.ErrorAgregarLinea;
import org.example.Utils.Exeptions.ErrorPago;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerRentaCarros implements Initializable {

    private final FacadeOCR facadeOCR = new FacadeOCR();
    Integer numeroRenta = 0;

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
    private ComboBox<String> carroXPuestos;

    @FXML
    private ChoiceBox<Integer> denominaciones;

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
    }

    @FXML
    void agregarBillete(ActionEvent event) {
       

    }

    @FXML
    void agregarLinea(ActionEvent event) {
        DTOResumen resumen;
        Double descuento;
        Integer lineas;
        Linea linea = new Linea(
                Integer.parseInt(cantidadCarro.getText()),
                facadeOCR.getCarroContro().existeCarro(carroXPuestos.getSelectionModel().getSelectedItem())
        );
        try{
            resumen = facadeOCR.agregarLinea(linea);
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarLinea(resumen.getMensajeError());
            lineas = facadeOCR.getCarroContro().cantidadCarrosRenta(numeroRenta);
            System.out.println();
            renderTable(resumen);

        }catch (ErrorAgregarLinea ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
    }

    @FXML
    void eliminarLinea(ActionEvent event) {
        DTOResumen resumen;
        Double descuento;
        Integer lineas;
        try{
            resumen = facadeOCR.eliminarLinea(this.tablaLinea.getSelectionModel().getSelectedItem());
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarLinea(resumen.getMensajeError());
            lineas = facadeOCR.getCarroContro().cantidadCarrosRenta(numeroRenta);
            System.out.println();
            renderTable(resumen);


        }catch (ErrorAgregarLinea ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }

    }

    @FXML
    void generarReporte(ActionEvent event) {

    }

    @FXML
    void mostrarPuestosXPlaca(ActionEvent event) {
        /*
        * cada vez que se seleccione una placa del combobox se debe actualizar los puestos en la variable puestosLabel
        * para buscar los puestos se puede usar la consulta existe carro, devuelve todos los atributos con tan solo dar la placa
        * */
        Integer puestos = facadeOCR.getCarroContro().existeCarro(carroXPuestos.getSelectionModel().getSelectedItem()).getPuestos();
        puestosLabel.setText(String.valueOf(puestos));
    }

    @FXML
    void nuevaRenta(ActionEvent event) {
        /*
         * es necesario que cuando se inicie el programa darle al boton nueva renta, este crea una instancia de renta
         * para poder agregar las lineas
         * en este parte del codigo se puso siempre la renta 1 hay que llevar un consecutivo de rentas
         *
         * se deben crear metodos para llenar los combox de placa, y billetes y esto solo cada vez que se haga una nueva renta
         * */
        this.numeroRenta ++;
        this.facadeOCR.buildNuevaRenta(numeroRenta, new Renta());
        setFecha();
        for (Carro c : facadeOCR.consultarCarros()){
            carroXPuestos.getItems().add(c.getPlaca());
        }
        for (Billete b : facadeOCR.consultarBilletes()){
            denominaciones.getItems().add(b.getDenominacion());
        }
    }

    @FXML
    void terminarRenta(ActionEvent event) {

    }
    public void setFecha (){
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaActual = Calendar.getInstance();
        this.fecha.setText(Fecha.format(fechaActual.getTime()));
    }
    public void clearTable (){
        tablaLinea.getItems().clear();
        totalRenta.setText("0");
    }
    public void renderTable (DTOResumen resumen){
        clearTable();
        tablaLinea.getItems().addAll(resumen.getLineas());
        totalRenta.setText(String.valueOf(resumen.getTotalRenta()));
    }
}

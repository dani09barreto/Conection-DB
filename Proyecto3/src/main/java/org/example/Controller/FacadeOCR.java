package org.example.Controller;

import org.example.Integration.RepositorioCarro;
import org.example.Integration.RepositorioRenta;
import org.example.Model.*;
import org.example.Utils.Exeptions.ErrorPago;

import java.util.ArrayList;

public class FacadeOCR {
    private Integer numeroLinea = 0;
    private Integer IDRenta = 0;
    private Renta rentaActual;
    private RepositorioCarro carroContro = new RepositorioCarro();
    private RepositorioRenta rentaContro = new RepositorioRenta();

    public DTOResumen respuestaRenta (Renta renta) throws ErrorPago {
        DTOResumen resumen = new DTOResumen();
        Integer totalRenta = 0;
        Integer saldoBilletes = 0;
        resumen.setMensajeError(null);
        resumen.setLineas(renta.getLineas());
        for (Linea ln : renta.getLineas()){
            totalRenta += ln.getSubTotal();
        }
        resumen.setTotalRenta(totalRenta);

        for (Billete bll: renta.getPagoBilletes()){
            saldoBilletes += (bll.getDenominacion()*bll.getCantidad());
        }
        resumen.setSaldoBilletes(saldoBilletes);
        if (renta.getPagoBilletes().size() != 0){
            if (saldoBilletes < totalRenta)
                throw new ErrorPago("La cantidad de billetes ingresados no es suficiente");
            resumen.setVueltas(saldoBilletes-totalRenta);
        }
        return resumen;
    }

    public Renta crearRenta (Renta dtoRenta){
        return null;
    }


    public DTOResumen agregarLinea (Linea dtoLinea) throws ErrorPago {
        this.numeroLinea ++;
        dtoLinea.setNumero(numeroLinea);
        dtoLinea.getMiRenta().setID(IDRenta + 1);
        DTOResumen resumen;
        Linea lineaTemp;
        if (carroContro.existeCarro(dtoLinea.getCarroRentado().getPlaca()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El carro seleccionado no se encuentra en la Base de Datos");
            return resumen;
        }
        if (carroContro.verificarExistencias(dtoLinea.getCarroRentado().getPlaca(), dtoLinea.getCantidad()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El carro seleccionado no cuenta con las existencias necesarias");
            return resumen;
        }
        if ((lineaTemp = carroContro.existeCarroEnLinea(dtoLinea.getCarroRentado().getPlaca())) != null){
            this.numeroLinea --;
            Integer cantidad = lineaTemp.getCantidad() + dtoLinea.getCantidad();
            carroContro.updateLinea(cantidad, lineaTemp.getID());
            System.out.println("Actualizada");
            for (Linea ln : this.rentaActual.getLineas()){
                if (ln.equals(lineaTemp)){
                    ln.setCantidad(cantidad);
                }
            }
            resumen = respuestaRenta(this.rentaActual);
            System.out.println("a");
            return resumen;
        }
        carroContro.insertarLinea(dtoLinea);
        System.out.println("Insertada");
        this.rentaActual.getLineas().add(dtoLinea);
        resumen = respuestaRenta(this.rentaActual);
        return resumen;
    }

    public DTOResumen eliminarLinea (Linea dtoLinea){
        return null;
    }
    public DTOResumen agregarBillete (Billete dtoBillete){
        return null;
    }
    public DTOResumen terminarRenta (){
        return null;
    }
    public DTOResumen consultarRenta (Renta dtoRenta){
        return null;
    }
    public DTOReporte consultarAcomulados (){
        return null;
    }
    public ArrayList <Carro> consultarCarros (){
        return null;
    }
    public ArrayList <Billete> consultarBilletes (){
        return null;
    }
    /*
    public List<Libro> ConsultarLibrosPorAutor(String p_author, int p_rating) {
        RepositorioLibro repo = new RepositorioLibro();
        return repo.ConsultarLibrosPorAutor(p_author, p_rating);
    }


    public List<Libro> ConsultarLibros() {
        RepositorioLibro repo = new RepositorioLibro();
        return repo.ConsultarLibros();
    }*/

    public RepositorioCarro getCarroContro() {
        return carroContro;
    }

    public RepositorioRenta getRentaContro() {
        return rentaContro;
    }

    public Renta getRentaActual() {
        return rentaActual;
    }

    public void setRentaActual(Renta rentaActual) {
        this.rentaActual = rentaActual;
    }

    public void setIDRenta(Integer IDRenta) {
        this.IDRenta = IDRenta;
    }
}
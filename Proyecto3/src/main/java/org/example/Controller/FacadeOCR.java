package org.example.Controller;

import org.example.Integration.RepositorioCarro;
import org.example.Integration.RepositorioRenta;
import org.example.Model.*;
import org.example.Utils.Exeptions.CarroNoExiste;
import org.example.Utils.Exeptions.CarroSinExistencias;

import java.util.ArrayList;

public class FacadeOCR {
    private RepositorioCarro carroContro = new RepositorioCarro();
    private RepositorioRenta rentaContro = new RepositorioRenta();

    public DTOResumen respuestaRenta (Renta renta){
        return null;
    }

    public Renta crearRenta (Renta dtoRenta){
        return null;
    }


    public DTOResumen agregarLinea (Linea dtoLinea) throws CarroNoExiste, CarroSinExistencias {
        DTOResumen resumen = new DTOResumen();
        Linea lineaTemp;
        if (carroContro.existeCarro(dtoLinea.getCarroRentado().getPlaca()) == null){
            throw new CarroNoExiste("El carro seleccionado no se encuentra en la Base de Datos");
        }
        if (carroContro.verificarExistencias(dtoLinea.getCarroRentado().getPlaca(), dtoLinea.getCantidad()) == null){
            throw new CarroSinExistencias("El carro seleccionado no cuenta con las existencias necesarias");
        }
        if ((lineaTemp = carroContro.existeCarroEnLinea(dtoLinea.getCarroRentado().getPlaca())) != null){
            Integer cantidad = lineaTemp.getCantidad() + dtoLinea.getCantidad();
            carroContro.updateLinea(cantidad, lineaTemp.getID());
            System.out.println("Actualizada");
        }
        return null;
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
}
package org.example.Model;

import java.text.SimpleDateFormat;

/*
 * @Integrantes:
 * Daniel Barreto
 * Angela Ospina
 * Natali Gaona
 * Laura Jimenez
 * Sebastian Martinez
 * Alvaro Betancour
 * */
public class DTOReporte {

    private String fechaAnioMes;
    private Integer carrosRentados;

    public DTOReporte(String fechaAnioMes, Integer carrosRentados) {
        this.fechaAnioMes = fechaAnioMes;
        this.carrosRentados = carrosRentados;
    }

    public String getFechaString (){
        return fechaAnioMes;
    }

    public Integer getCarrosRentados() {
        return carrosRentados;
    }

    public void setCarrosRentados(Integer carrosRentados) {
        this.carrosRentados = carrosRentados;
    }

    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("MM/yyyy");
        return "DTOReporte{" +
                "fechaAnioMes=" + fechaAnioMes +
                ", carrosRentados=" + carrosRentados +
                '}';
    }

}

package org.example.Model;

public class Billete {
    private Integer cantidad;
    private Integer denominacion;

    public Billete(Integer cantidad, Integer denominacion) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Integer denominacion) {
        this.denominacion = denominacion;
    }
}

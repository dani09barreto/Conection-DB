package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class RepositorioRenta {

    public int insertarRenta(DTOResumen resumen, Calendar fecha) {
        int afectadas = 0;
        String SQL = "INSERT INTO Renta(fecha) VALUES (?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {
            ps.setDate(1,new java.sql.Date(fecha.getTimeInMillis()));
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public Renta ConsultarRenta (Integer numeroRenta){
        Renta rentaConsultada = new Renta();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        "       c.PLACA,\n" +
                        "       c.UNIDADESDISPONIBLES,\n" +
                        "       c.PUESTOS,\n" +
                        "       c.PRECIO,\n" +
                        "       l.CANTIDAD as CANTIDADCARRO,\n" +
                        "       l.NUMERO, r.ID as IDRENTA,\n" +
                        "       r.FECHA, b.DENOMINACION,\n" +
                        "       cpb.CANTIDAD as CANTIDADBILLETE\n" +
                        "from carro c, linea l, renta r, CANTIDADPORBILLETE cpb, BILLETE b\n" +
                        "where c.ID = l.CARROID and r.ID = l.RENTAID and\n" +
                        "      b.ID = cpb.BILLETEID and r.ID = cpb.RENTAID\n" +
                        "      and r.ID = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
                ps.setInt(1, numeroRenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    buildRenta(rs, rentaConsultada);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return rentaConsultada;
    }

    public void buildRenta (ResultSet rs, Renta renta) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        Billete billeteRenta;
        renta.setNumero(rs.getInt("IDRENTA"));
        calendar.setTime(rs.getDate("FECHA"));
        //Linea
        renta.getLineas().add(buildLinea(rs));
        //billete
        billeteRenta = builBillete(rs);
        //Verificar que le billete y el monto ya existen para no insertar el mismo

        if (renta.getPagoBilletes().size() == 0){
            renta.getPagoBilletes().add(billeteRenta);
        }
        for (Billete bll : renta.getPagoBilletes()){
            if (!(bll.getDenominacion().equals(billeteRenta.getDenominacion()))){
                renta.getPagoBilletes().add(billeteRenta);
            }
        }

        renta.setFechaHora(calendar);
    }
    public Linea buildLinea (ResultSet rs) throws SQLException {
        Carro carro = new Carro(
                rs.getString("PLACA"),
                rs.getInt("UNIDADESDISPONIBLES"),
                rs.getInt("PRECIO"),
                rs.getInt("PUESTOS")
        );
        return new Linea(
                rs.getInt("NUMERO"),
                rs.getInt("CANTIDADCARRO"),
                carro
        );
    }

    public Billete builBillete (ResultSet rs) throws SQLException {
        return new Billete(
                rs.getInt("CANTIDADBILLETE"),
                rs.getInt("DENOMINACION")
        );

    }
}

package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.DTOResumen;
import org.example.Model.Linea;

import java.sql.*;
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
            //ps.setDate(1, Date.valueOf(fecha.getCalendarType()), fecha);
            ps.setDate(1,new java.sql.Date(fecha.getTimeInMillis()));
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }
}

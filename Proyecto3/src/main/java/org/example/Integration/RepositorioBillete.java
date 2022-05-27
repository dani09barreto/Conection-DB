package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.Billete;
import org.example.Model.Carro;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioBillete{
    public ArrayList<Billete> consultarTiposBillete(){
        ArrayList <Billete> billetes = new ArrayList<>();
        String SQL = "select *\n" +
                "from billete";
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                billetes.add(crearBillete(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return billetes;
    }

    public Billete crearBillete(ResultSet rs) throws SQLException {
        return new Billete(
                rs.getInt("ID"),
                rs.getInt("DENOMINACION")
        );
    }
}



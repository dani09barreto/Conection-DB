package org.example.Integration;


import org.example.Model.Libro;
import org.example.Controller.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioLibro {

    public int InsertarLibro(Libro libro) {
        int afectadas = 0;
        String SQL = "insert into books ISBN, TITLE, AUTHOR_LAST_NAME, AUTHOR_FIRST_NAME, RATING) values(?,?,?,?,?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {

            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitle());
            ps.setString(3, libro.getLastName());
            ps.setString(4, libro.getFirstName());
            ps.setBigDecimal(5, libro.getRating());
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public int ActualizarLibro(Libro libro) {
        int afectadas = 0;
        String SQL = "update books set rating = ? where ID = ?";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {

            ps.setBigDecimal(1, libro.getRating());
            ps.setBigDecimal(2, libro.getId());
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public List<Libro> ConsultarLibros() {
        List<Libro> libros = new ArrayList<>();
        String SQL = "select ID, ISBN, TITLE, AUTHOR_LAST_NAME, AUTHOR_FIRST_NAME, RATING from books";
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                buildBook(rs, libros);
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return libros;
    }

    private void buildBook(final ResultSet rs, List<Libro> libros) throws SQLException {
        Libro libro = new Libro();
        libro.setId(rs.getBigDecimal("ID"));
        libro.setIsbn(rs.getString("ISBN"));
        libro.setTitle(rs.getString("TITLE"));
        libro.setLastName(rs.getString("AUTHOR_LAST_NAME"));
        libro.setFirstName(rs.getString("AUTHOR_FIRST_NAME"));
        libro.setRating(rs.getBigDecimal("RATING"));
        libros.add(libro);
    }

    public List<Libro> ConsultarLibrosPorAutor(String p_author, int p_rating) {
        List<Libro> libros = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select ID, ISBN, TITLE, AUTHOR_LAST_NAME, AUTHOR_FIRST_NAME, RATING ");
        SQL.append(" from books ");
        SQL.append(" WHERE AUTHOR_LAST_NAME like ? or RATING >= ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setString(1, "%" + p_author + "%");
            ps.setInt(2, p_rating);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    buildBook(rs, libros);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return libros;
    }



}

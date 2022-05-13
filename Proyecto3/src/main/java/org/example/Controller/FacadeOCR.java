package org.example.Controller;

import org.example.Integration.RepositorioLibro;
import org.example.Model.Libro;

import java.util.List;

public class FacadeOCR {

    public List<Libro> ConsultarLibrosPorAutor(String p_author, int p_rating) {
        RepositorioLibro repo = new RepositorioLibro();
        return repo.ConsultarLibrosPorAutor(p_author, p_rating);
    }


    public List<Libro> ConsultarLibros() {
        RepositorioLibro repo = new RepositorioLibro();
        return repo.ConsultarLibros();
    }
}
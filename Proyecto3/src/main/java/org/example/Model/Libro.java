package org.example.Model;

import java.math.BigDecimal;

public class Libro {

    private BigDecimal id;
    private String isbn;
    private String title;
    private String lastName;
    private String firstName;
    private BigDecimal rating;

    public Libro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }






    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Libro(String bookId, String title, String lastName, String firstName, BigDecimal rating) {
        this.isbn = bookId;
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
        this.rating = rating;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", title=" + title + ", lastName=" + lastName + ", firstName=" + firstName + ", rating=" + rating + '}';
    }






}
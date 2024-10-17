package org.adtproject.libreria;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "editorial", "isbn"})
public class Libro {
    private String autor;
    private String editorial;
    private String isbn;

    public Libro(final String autor, final String editorial, final String isbn) {
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
package org.adtproject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Libreria {
    private ArrayList<Libro> listalibros;
    private String nombre;
    private String lugar;

    public Libreria() {

    }

    public Libreria(ArrayList<Libro> listalibros, String nombre, String lugar) {
        this.listalibros = listalibros;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "listalibros")
    @XmlElement(name = "Libro")
    public ArrayList<Libro> getListaLibros() {
        return listalibros;
    }
}
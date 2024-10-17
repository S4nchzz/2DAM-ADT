package org.adtproject.ficherosEjXml.ej3;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "dni", "telf"})
public class Persona {
    private String nombre;
    private String dni;
    private String telf;

    public Persona(final String nombre, final String dni, final String telf) {
        this.nombre = nombre;
        this.dni = dni;
        this.telf = telf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }
}

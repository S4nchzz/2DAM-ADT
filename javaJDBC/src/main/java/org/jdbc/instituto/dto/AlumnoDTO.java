package org.jdbc.instituto.dto;

import java.io.Serializable;

public class AlumnoDTO implements Serializable {
    private String idAlumno;
    private String nombreAlumno;
    private String apellidosAlumno;
    private int edad;
    private char grupo;

    public AlumnoDTO(String idAlumno, String nombreAlumno, String apellidosAlumno, int edad, char grupo) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.edad = edad;
        this.grupo = grupo;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public int getEdad() {
        return edad;
    }

    public char getGrupo() {
        return grupo;
    }
}

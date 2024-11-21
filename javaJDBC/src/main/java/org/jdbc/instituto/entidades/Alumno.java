package org.jdbc.instituto.entidades;

import java.io.Serializable;
import java.util.Date;

public class Alumno {
    private String codAlumno;
    private String nombreAlumno;
    private String apellidosAlumno;
    private Date fechaNacimiento;
    private char grupo;

    public Alumno(final String codAlumno, final String nombreAlumno, final String apellidosAlumno, final Date fechaNacimiento, final char grupo) {
        this.codAlumno = codAlumno;
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = grupo;
    }

    public char getGrupo() {
        return grupo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreAlumno() {
        return this.nombreAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public String getCodAlumno() {
        return codAlumno;
    }
}
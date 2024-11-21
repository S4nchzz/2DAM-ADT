package org.jdbc.instituto.dao;

import org.jdbc.instituto.entidades.Alumno;

import java.util.List;

public interface AlumnoDAO {
    void crearAlumno(Alumno alumno);
    Alumno obtenerAlumnoPorId(String id);
    List<Alumno> obtenerTodosLosAlumnos();
    void actualizarAlumno(Alumno alumno);
    void eliminarAlumno(String id);
}

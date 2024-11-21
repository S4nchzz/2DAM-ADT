package org.jdbc.instituto.service;

import org.jdbc.instituto.dao.AlumnoDaoImplementacion;
import org.jdbc.instituto.dto.AlumnoDTO;
import org.jdbc.instituto.entidades.Alumno;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnoService {
    private final String url;
    private final DataSource dataSource;

    public AlumnoService(DataSource dataSource) {
        url = "jdbc:mysql://localhost:3306/instituto";
        this.dataSource = dataSource;
    }

    public void crearAlumno(AlumnoDTO alumno) {
        AlumnoDaoImplementacion dao = AlumnoDaoImplementacion.getInstance(dataSource);
        Alumno a = mapearDtoToAlumno(alumno);
        dao.crearAlumno(a);
    }

    private Alumno mapearDtoToAlumno(AlumnoDTO alumno) {
        return new Alumno(alumno.getIdAlumno(), alumno.getNombreAlumno(), alumno.getApellidosAlumno(), convertirEdadAFecha(alumno.getEdad()), alumno.getGrupo());
    }

    private Date convertirEdadAFecha(final int edad) {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(edad);
        return Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public AlumnoDTO obtenerAlumnoPorId(String id) {
        AlumnoDaoImplementacion dao = AlumnoDaoImplementacion.getInstance(dataSource);
        Alumno a = dao.obtenerAlumnoPorId(id);

        return mapearAlumnoToDto(a);
    }

    private AlumnoDTO mapearAlumnoToDto(Alumno a) {
        return new AlumnoDTO(a.getCodAlumno(), a.getNombreAlumno(), a.getApellidosAlumno(), calcularEdad(a.getFechaNacimiento()), a.getGrupo());
    }

    private int calcularEdad(Date fechaNacimiento) {
        LocalDate nacimiento = new java.util.Date(fechaNacimiento.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(nacimiento, LocalDate.now()).getYears();
    }

    public List<AlumnoDTO> obtenerTodosLosAlumnos() {
        AlumnoDaoImplementacion dao = AlumnoDaoImplementacion.getInstance(dataSource);
        List<Alumno> alumnos = dao.obtenerTodosLosAlumnos();

        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        for (Alumno a : alumnos) {
            alumnosDTO.add(mapearAlumnoToDto(a));
        }

        return alumnosDTO;
    }

    public void actualizarAlumno(String id, AlumnoDTO alumnoDTO) {
        AlumnoDaoImplementacion dao = AlumnoDaoImplementacion.getInstance(dataSource);
        dao.actualizarAlumno(mapearDtoToAlumno(alumnoDTO));
    }

    public void eliminarAlumno(String id) {
        AlumnoDaoImplementacion dao = AlumnoDaoImplementacion.getInstance(dataSource);
        dao.eliminarAlumno(id);

    }
}

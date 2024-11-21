package org.jdbc.instituto.dao;

import org.jdbc.instituto.entidades.Alumno;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnoDaoImplementacion implements AlumnoDAO {
    private static AlumnoDaoImplementacion instance;
    private final String url;
    private DataSource dataSource;

    private AlumnoDaoImplementacion(final DataSource dataSource){
        url = "jdbc:mysql://localhost:3306/instituto";
        this.dataSource = dataSource;
    }

    public static AlumnoDaoImplementacion getInstance(final DataSource dataSource) {
        if (instance == null) {
            instance = new AlumnoDaoImplementacion(dataSource);
        }

        return instance;
    }

    @Override
    public void crearAlumno(Alumno alumno) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ALUMNO (COD_ALUMNO, NOMBRE_ALUMNO, APELLIDOS_ALUMNO, FECHA_NACIMIENTO, GRUPO) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, alumno.getCodAlumno());
            ps.setString(2, alumno.getNombreAlumno());
            ps.setString(3, alumno.getApellidosAlumno());
            ps.setString(4, String.valueOf(alumno.getFechaNacimiento().getTime()));
            ps.setString(5, String.valueOf(alumno.getGrupo()));

            ps.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Alumno obtenerAlumnoPorId(String id) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALUMNO WHERE ID = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return new Alumno(rs.getString("cod_alumno"), rs.getString("nombre_alumno"), rs.getString("apellidos_alumno"), new Date(rs.getString("fecha_nacimiento")), rs.getString("grupo").charAt(0));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> obtenerTodosLosAlumnos() {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALUMNO");

            final ArrayList<Alumno> aList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aList.add(new Alumno(rs.getString("cod_alumno"), rs.getString("nombre_alumno"), rs.getString("apellidos_alumno"), new Date(rs.getString("fecha_nacimiento")), rs.getString("grupo").charAt(0)));
            }

            return aList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE ALUMNO SET COD_ALUMNO = ?, NOMBRE_ALUMNO = ?, APELLIDOS_ALUMNO = ?, FECHA_NACIMIENTO = ?, GRUPO = ?;");
            ps.setString(1, alumno.getCodAlumno());
            ps.setString(2, alumno.getNombreAlumno());
            ps.setString(3, alumno.getApellidosAlumno());
            ps.setString(4, String.valueOf(alumno.getFechaNacimiento().getTime()));
            ps.setString(5, String.valueOf(alumno.getGrupo()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminarAlumno(String id) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ALUMNO WHERE COD_ALUMNO = ?");
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sgu.model.Materia;
import sgu.model.Profesor;

public class ProfesorDAO extends Conexion {
    private final String SQL_INSERT = "INSERT INTO profesor (prof_dni, prof_nombre,prof_apellido,prof_fec_nac,prof_domicilio, prof_telefono) VALUES (?,?,?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM profesor";
    private final String SQL_DELETE = "DELETE FROM profesor WHERE prof_dni=?";
    private final String SQL_UPDATE = "UPDATE profesor SET prof_nombre=?,prof_apellido=?,prof_fec_nac=?,prof_domicilio=?, prof_telefono=? WHERE prof_dni=?";
    private final String SQL_FIND = "SELECT * FROM profesor WHERE prof_dni = ?";
    private final String SQL_FINDALL = "SELECT *  FROM materia WHERE mat_prof_dni=?";
    
    public boolean create(Profesor profesor) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, profesor.getDni());
            ps.setString(2, profesor.getNombre());
            ps.setString(3, profesor.getApellido());
            ps.setDate(4, profesor.getFechaNacimiento());
            ps.setString(5, profesor.getDomicilio());
            ps.setString(6, profesor.getTelefono());

            ps.executeUpdate();


            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear\n " + e);
            return false;

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
        }
    }
    public List<Profesor> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profesor profesor;
        List<Profesor> listaProfesores = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                profesor = new Profesor();

                profesor.setDni(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setFechaNacimiento(rs.getDate(4));
                profesor.setDomicilio(rs.getString(5));
                profesor.setTelefono(rs.getString(6));

                listaProfesores.add(profesor);
            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return listaProfesores;
    }

    public boolean update(Profesor profesor) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setDate(3, profesor.getFechaNacimiento());
            ps.setString(4, profesor.getDomicilio());
            ps.setString(5, profesor.getTelefono());

            ps.setInt(6, profesor.getDni());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar\n " + e);
            return false;

        } finally {

            Conexion.close(conn);
            Conexion.close(ps);

        }
    }

    public boolean delete(int idProf) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, idProf);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar\n " + e);
            return false;

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);

        }
    }

    public boolean exist(int dniProf) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dniProf);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar\n " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return false;
    }
    public Profesor find(int dniProfesor) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Profesor profesor = new Profesor();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, dniProfesor);
            rs = ps.executeQuery();

            while (rs.next()) {
                profesor.setDni(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setFechaNacimiento(rs.getDate(4));
                profesor.setDomicilio(rs.getString(5));
                profesor.setTelefono(rs.getString(6));
            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return profesor;
    }
    
    public List<Materia> findAll(int dniProfesor){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia materia;
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(SQL_FINDALL);
            ps.setInt(1, dniProfesor);
            rs = ps.executeQuery();

            while (rs.next()) {
                materia = new Materia();

                materia.setCodMateria(rs.getInt(1));
                materia.setNombreMateria(rs.getString(2));
                materia.setProfesor(new Profesor(dniProfesor));

                listaMaterias.add(materia);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return listaMaterias;
    }
}

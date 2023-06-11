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
import sgu.model.Alumno;
import sgu.model.Cursado;
import sgu.model.Materia;
import sgu.model.Profesor;


public class MateriaDAO extends Conexion {
    private final String SQL_INSERT = "INSERT INTO materia (mat_cod,mat_nombre, mat_prof_dni) VALUES (?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM materia";
    private final String SQL_DELETE = "DELETE FROM materia WHERE mat_cod=?";
    private final String SQL_UPDATE = "UPDATE materia SET mat_nombre =?, mat_prof_dni=? WHERE mat_cod=?";
    private final String SQL_FIND = "SELECT * FROM materia WHERE mat_cod =?";
    private final String SQL_FINDALL = "SELECT *  FROM cursado WHERE cur_mat_cod=?";

    public boolean create(Materia materia) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, materia.getCodMateria());
            ps.setString(2, materia.getNombreMateria());
            ps.setInt(3, materia.getProfesor().getDni());

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

    public List<Materia> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia materia;
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                materia = new Materia();

                materia.setCodMateria(rs.getInt(1));
                materia.setNombreMateria(rs.getString(2));
                materia.setProfesor(new Profesor(rs.getInt(3)));

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

    public boolean update(Materia materia) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setString(1, materia.getNombreMateria());
            ps.setInt(2, materia.getProfesor().getDni());

            ps.setInt(3, materia.getCodMateria());
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

    public boolean delete(int idMateria) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, idMateria);
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
    
     public boolean exist(int codMate) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codMate);
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
     
     public Materia find(int codMateria) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Materia mat = new Materia();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codMateria);
            rs = ps.executeQuery();

            while (rs.next()) {
                mat.setCodMateria(codMateria);
                mat.setNombreMateria(rs.getString(2));
                mat.setProfesor(new Profesor(Integer.parseInt(rs.getString(3))));
            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return mat;
    }
     
     public List<Cursado> findAll(int codMat){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cursado cursado;
        List<Cursado> listaCursados = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(SQL_FINDALL);
            ps.setInt(1, codMat);
            rs = ps.executeQuery();

            while (rs.next()) {
                cursado = new Cursado();

                cursado.setAlumno(new Alumno(rs.getInt(1)));
                cursado.setMateria(new Materia(codMat));
                cursado.setNota(rs.getDouble(3));

                listaCursados.add(cursado);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return listaCursados;
    }
     
     
}

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


public class CursadoDAO extends Conexion{
    private final String SQL_INSERT = "INSERT INTO cursado (cur_alu_dni, cur_mat_cod,cur_nota) VALUES (?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM cursado";
    private final String SQL_DELETE = "DELETE FROM cursado WHERE cur_alu_dni=?";
    private final String SQL_UPDATE = "UPDATE cursado SET cur_mat_cod=?, cur_nota=? WHERE cur_alu_dni=?";
    private final String SQL_FIND = "SELECT * FROM cursado WHERE cur_alu_dni=?";

    public boolean create(Cursado cursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, cursado.getAlumno().getDni());
            ps.setInt(2, cursado.getMateria().getCodMateria());
            ps.setDouble(3, cursado.getNota());

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

    public List<Cursado> read() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cursado cursado;
        List<Cursado> listaCursados = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                cursado = new Cursado();

                cursado.setAlumno(new Alumno(rs.getInt(1)));
                cursado.setMateria(new Materia(rs.getInt(2)));
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

    public boolean update(Cursado cursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setInt(1, cursado.getMateria().getCodMateria());
            ps.setDouble(2, cursado.getNota());

            ps.setInt(3, cursado.getAlumno().getDni());
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

    public boolean delete(int codCursado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, codCursado);
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

    public Cursado find(int codCursado) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cursado cursado = new Cursado();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_FIND);
            ps.setInt(1, codCursado);
            rs = ps.executeQuery();

            while (rs.next()) {
                cursado.setAlumno(new Alumno(rs.getInt(1)));
                cursado.setMateria(new Materia(rs.getInt(2)));
                cursado.setNota(rs.getDouble(3));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar\n " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return cursado;
    }
}

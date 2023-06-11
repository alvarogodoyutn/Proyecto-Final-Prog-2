/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.model;

import java.util.List;
import sgu.dao.CursadoDAO;


public class Cursado {
    private Alumno alumno;
    private Materia materia;
    private double nota;
    private CursadoDAO cursadoDAO = new CursadoDAO();

    public Cursado() {
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alu) {
        this.alumno= alu;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia mat) {
        this.materia = mat;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    
    public boolean create(Cursado cursado) {
        return cursadoDAO.create(cursado);
    }

    public List<Cursado> read() {
        return cursadoDAO.read();
    }

    public boolean update(Cursado cursado) {
        return cursadoDAO.update(cursado);
    }

    public boolean delete(int idCursado) {
        return cursadoDAO.delete(idCursado);
    }

   public Cursado find(int idCursado) {
        return cursadoDAO.find(idCursado);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.model;

import java.util.List;
import sgu.dao.MateriaDAO;


public class Materia {
    private int codMateria;
    private String nombreMateria;
    private Profesor profesor;
    private MateriaDAO materiaDAO = new MateriaDAO();

    public Materia() {
    }

    public Materia(int codMateria) {
        this.codMateria = codMateria;
    }
    
    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public MateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    public void setMateriaDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }


    public boolean create(Materia materia) {
        return materiaDAO.create(materia);
    }

    public List<Materia> read() {
        return materiaDAO.read();
    }

    public boolean update(Materia materia) {
        return materiaDAO.update(materia);
    }

    public boolean delete(int idMat) {
        List<Cursado> cursados = findGrades(idMat);
        
        for (Cursado cur: cursados){
            cur.delete(cur.getAlumno().getDni());
        }
        return materiaDAO.delete(idMat);
    }

    public boolean materia(int codMat) {
        return materiaDAO.exist(codMat);
    }
    
    public Materia find(int codMat){
        return materiaDAO.find(codMat);
    }
    
    public List<Cursado> findGrades(int codMat){
        return materiaDAO.findAll(codMat);
    }
    
    public boolean exist(int codMat){
        return materiaDAO.exist(codMat);
    }
}

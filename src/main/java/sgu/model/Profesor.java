/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.model;

import java.sql.Date;
import java.util.List;
import sgu.dao.ProfesorDAO;


public class Profesor {
    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String domicilio;
    private String telefono;
    private ProfesorDAO profesorDAO = new ProfesorDAO();
   

    public Profesor() {
    }

    public Profesor(int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ProfesorDAO getProfesorDAO() {
        return profesorDAO;
    }

    public void setProfesorDAO(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }
    
    
    public boolean create(Profesor profesor) {
        return profesorDAO.create(profesor);
    }

    public List<Profesor> read() {
        return profesorDAO.read();
    }

    public boolean update(Profesor profesor) {
        return profesorDAO.update(profesor);
    }

    public boolean delete(int idProfesor) {
        Profesor prof = find(idProfesor);
        
        List<Materia> materias = findAll(idProfesor);
        
        for (Materia mat: materias){
            int cod = mat.getCodMateria();
            mat.delete(cod);
        }
        
        return profesorDAO.delete(idProfesor);
    }

    public boolean exist(int dniProf) {
        return profesorDAO.exist(dniProf);
    }
    public Profesor find(int dniProf) {
        return profesorDAO.find(dniProf);
    }
    public List<Materia> findAll(int dniProf){
        return profesorDAO.findAll(dniProf);
    }
}

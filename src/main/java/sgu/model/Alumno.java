/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.model;

import java.sql.Date;
import java.util.List;
import sgu.dao.AlumnoDAO;


public class Alumno {
    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String domicilio;
    private String telefono;
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public Alumno() {
    }

    public Alumno(int dni) {
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
    
    
    public boolean create(Alumno alumno) {
        return alumnoDAO.create(alumno);
    }

    public List<Alumno> read() {
        return alumnoDAO.read();
    }

    public boolean update(Alumno alumno) {
        return alumnoDAO.update(alumno);
    }

    public boolean delete(int idAlumno) {
        List<Cursado> cursados = findGrades(idAlumno);
        
        for (Cursado cur: cursados){
            cur.delete(idAlumno);
        }
        return alumnoDAO.delete(idAlumno);
    }

    public Alumno find(int dniAlumno) {
        return alumnoDAO.find(dniAlumno);
    }

    public boolean exist(int dniAlumno) {
        return alumnoDAO.exist(dniAlumno);
    }
    
    public List<Cursado> findGrades(int dniAlumno){
        return alumnoDAO.findAll(dniAlumno);
    }
}

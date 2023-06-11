/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import sgu.model.Materia;
import sgu.model.Profesor;
import sgu.view.AgregarProfesor;
import sgu.view.Menu;
import sgu.view.PanelProfesores;


public class ProfesoresController implements ActionListener {
    private PanelProfesores panelProfesores = new PanelProfesores();
    private AgregarProfesor agregarProfesor = new AgregarProfesor();
    private Profesor profesor = new Profesor();
    private Materia materia = new Materia();
    private Menu menu;
    private MenuController menuController;
    private DefaultTableModel modelo;
    private Validaciones validacion = new Validaciones();
    
    public ProfesoresController(Menu menu, MenuController menuController){
        this.menu = menu;
        this.menuController = menuController;
        
        this.agregarProfesor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menu.mainPanel.add(panelProfesores, "panelProfesores");
        this.menuController.getCardLayout().show(menu.mainPanel, "panelProfesores");
        SwingUtilities.updateComponentTreeUI(menu);
        menu.repaint();
        lista();
         
        panelProfesores.getBtnDel().addActionListener(this);
        panelProfesores.getBtnAdd().addActionListener(this);
        panelProfesores.getBtnEdit().addActionListener(this);
        agregarProfesor.getBtnCreate().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelProfesores.getBtnDel())){
            int selectedRow = panelProfesores.getTableProfesores().getSelectedRow(); 
            if (selectedRow== -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                delete();
            }
        } else if(e.getSource().equals(panelProfesores.getBtnAdd())){
            this.agregarProfesor.setVisible(true);
            
            agregarProfesor.getBtnCreate().setText("Agregar");
            agregarProfesor.getBtnCreate().setBackground(new Color(70,155,70));
            agregarProfesor.getjLabel1().setText("Creación de Profesor");
            agregarProfesor.getFieldDNI().setEditable(true);
        } else if(e.getSource().equals(panelProfesores.getBtnEdit())){
            int selectedRow = panelProfesores.getTableProfesores().getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                Object DNI = panelProfesores.getTableProfesores().getValueAt(selectedRow, 0);
                Profesor prof = profesor.find((int) DNI);

                clearFrame();
                this.agregarProfesor.setVisible(true);
                agregarProfesor.getFieldDNI().setText(String.valueOf(prof.getDni()));
                agregarProfesor.getFieldNombre().setText(prof.getNombre());
                agregarProfesor.getFieldApellido().setText(prof.getApellido());
                agregarProfesor.getFieldDomicilio().setText(prof.getDomicilio());
                agregarProfesor.getFieldTelefono().setText(prof.getTelefono());
                agregarProfesor.getFieldFecha().setDate(prof.getFechaNacimiento());
                agregarProfesor.getBtnCreate().setText("Editar");
                agregarProfesor.getBtnCreate().setBackground(new Color(75,65,60));
                agregarProfesor.getjLabel1().setText("Editar Profesor");
                agregarProfesor.getFieldDNI().setEditable(false);
            }
        }
        else if(e.getSource().equals(agregarProfesor.getBtnCreate())){
            if (agregarProfesor.getFieldDNI().isEditable() == false){
                editProfesor();
            } else{
                addProfesor();
                clearFrame();
            } 
        } 
        else agregarProfesor.dispose();
    }
    
    public void lista(){
        List<Profesor> profesores = profesor.read();
        modelo = (DefaultTableModel) panelProfesores.getTableProfesores().getModel();
        Object[] row = new Object[6];
        panelProfesores.getTableProfesores().setRowHeight(30);
        
        for (int i =0;i < profesores.size();i++){
            row[0]= profesores.get(i).getDni();
            row[1] = profesores.get(i).getNombre();
            row[2] = profesores.get(i).getApellido();
            row[3] = profesores.get(i).getFechaNacimiento();
            row[4] = profesores.get(i).getTelefono();
            row[5] = profesores.get(i).getDomicilio();
            modelo.addRow(row);
        }
        
        panelProfesores.getTableProfesores().setModel(modelo);
    }
    
    public void clear() {
        for (int i = 0; i < panelProfesores.getTableProfesores().getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void addProfesor(){
        if (!validacion.checkDNI(agregarProfesor.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI debe contener 8 caracteres");
            return;
        }
        if (!validacion.checkDNILetras(agregarProfesor.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI no puede contener letras");
            return;
        }
        if (!validacion.checkTelefono(agregarProfesor.getFieldTelefono().getText())) {
            JOptionPane.showMessageDialog(null, "El telefono debe tener 12 o mas caracteres alfanumericos");
            return;
        }
        if (agregarProfesor.getFieldNombre().getText().isEmpty() || agregarProfesor.getFieldApellido().getText().isEmpty() || agregarProfesor.getFieldDomicilio().getText().isEmpty() || agregarProfesor.getFieldFecha().getDate() == null){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        if (validacion.checkNombreNumeros(agregarProfesor.getFieldNombre().getText())){
            JOptionPane.showMessageDialog(null, "El nombre no puede contener numeros");
            return;
        }
        if (validacion.checkNombreNumeros(agregarProfesor.getFieldApellido().getText())){
            JOptionPane.showMessageDialog(null, "El apellido no puede contener numeros");
            return;
        }
        if (profesor.exist(Integer.parseInt(agregarProfesor.getFieldDNI().getText()))){
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya esta registrado");
            return;
        }
        
        String DNI = agregarProfesor.getFieldDNI().getText();
        String nombre = agregarProfesor.getFieldNombre().getText();
        String apellido = agregarProfesor.getFieldApellido().getText();
        String telefono = agregarProfesor.getFieldTelefono().getText();
        String domicilio = agregarProfesor.getFieldDomicilio().getText();
        long fecha = agregarProfesor.getFieldFecha().getDate().getTime();

        Profesor prof = new Profesor();
        prof.setDni(Integer.parseInt(DNI));
        prof.setNombre(nombre);
        prof.setApellido(apellido);
        prof.setTelefono(telefono);
        prof.setDomicilio(domicilio);
        prof.setFechaNacimiento(new java.sql.Date(fecha));

        profesor.create(prof);

        clear();
        lista();
        agregarProfesor.dispose();
        clearFrame();
    }
    
    public void editProfesor(){
        if (!validacion.checkDNI(agregarProfesor.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI debe contener 8 caracteres");
            return;
        }
        if (!validacion.checkDNILetras(agregarProfesor.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI no puede contener letras");
            return;
        }
        if (!validacion.checkTelefono(agregarProfesor.getFieldTelefono().getText())) {
            JOptionPane.showMessageDialog(null, "El telefono debe tener 12 o mas caracteres alfanumericos");
            return;
        }
        if (agregarProfesor.getFieldNombre().getText().isEmpty() || agregarProfesor.getFieldApellido().getText().isEmpty() || agregarProfesor.getFieldDomicilio().getText().isEmpty() || agregarProfesor.getFieldFecha().getDate() == null){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        if (validacion.checkNombreNumeros(agregarProfesor.getFieldNombre().getText())){
            JOptionPane.showMessageDialog(null, "El nombre no puede contener numeros");
            return;
        }
        if (validacion.checkNombreNumeros(agregarProfesor.getFieldApellido().getText())){
            JOptionPane.showMessageDialog(null, "El apellido no puede contener numeros");
            return;
        }
        
        String DNI = agregarProfesor.getFieldDNI().getText();
        String nombre = agregarProfesor.getFieldNombre().getText();
        String apellido = agregarProfesor.getFieldApellido().getText();
        String telefono = agregarProfesor.getFieldTelefono().getText();
        String domicilio = agregarProfesor.getFieldDomicilio().getText();
        long fecha = agregarProfesor.getFieldFecha().getDate().getTime();

        Profesor prof = new Profesor();
        prof.setDni(Integer.parseInt(DNI));
        prof.setNombre(nombre);
        prof.setApellido(apellido);
        prof.setTelefono(telefono);
        prof.setDomicilio(domicilio);
        prof.setFechaNacimiento(new java.sql.Date(fecha));
        
        profesor.update(prof);
        
        clear();
        lista();
        agregarProfesor.dispose();
        clearFrame();
    }
    
    public void delete(){
        Object DNI = panelProfesores.getTableProfesores().getValueAt(panelProfesores.getTableProfesores().getSelectedRow(), 0);
        
        profesor.delete((int) DNI);
        
        clear();
        lista();
    }
    
    public void clearFrame(){
        agregarProfesor.getFieldDNI().setText("");
        agregarProfesor.getFieldNombre().setText("");
        agregarProfesor.getFieldApellido().setText("");
        agregarProfesor.getFieldDomicilio().setText("");
        agregarProfesor.getFieldTelefono().setText("");
        agregarProfesor.getFieldFecha().setDate(null);
    }
}

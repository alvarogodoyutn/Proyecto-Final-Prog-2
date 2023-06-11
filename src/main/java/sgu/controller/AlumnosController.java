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
import sgu.model.Alumno;
import sgu.model.Cursado;
import sgu.model.Materia;
import sgu.view.AgregarAlumno;
import sgu.view.Menu;
import sgu.view.PanelAlumnos;
import sgu.view.PanelNotas;


public class AlumnosController implements ActionListener{
    private PanelAlumnos panelAlumnos = new PanelAlumnos();
    private PanelNotas panelNotas = new PanelNotas();
    private AgregarAlumno agregarAlumno = new AgregarAlumno();
    private Alumno alumno = new Alumno();
    private Materia materia = new Materia();
    private Menu menu;
    private MenuController menuController;
    private DefaultTableModel modelo;
    private Validaciones validacion = new Validaciones();
    
    public AlumnosController(Menu menu, MenuController menuController){
        this.menu = menu;
        this.menuController = menuController;
        
        this.agregarAlumno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menu.mainPanel.add(panelAlumnos, "panelAlumnos");
        this.menuController.getCardLayout().show(menu.mainPanel, "panelAlumnos");
        SwingUtilities.updateComponentTreeUI(menu);
        menu.repaint();
        lista();
                
        panelAlumnos.getBtnDel().addActionListener(this);
        panelAlumnos.getBtnAdd().addActionListener(this);
        panelAlumnos.getBtnEdit().addActionListener(this);
        panelAlumnos.getBtnGrades().addActionListener(this);
        agregarAlumno.getBtnCreate().addActionListener(this);
        panelNotas.getBtnBack().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelAlumnos.getBtnDel())){
            int selectedRow = panelAlumnos.getTableAlumnos().getSelectedRow(); 
            if (selectedRow== -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                Object DNI = panelAlumnos.getTableAlumnos().getValueAt(selectedRow, 0);
                alumno.delete((int) DNI);
                clear();
                lista();
            }
        } 
        else if(e.getSource().equals(panelAlumnos.getBtnAdd())){
            this.agregarAlumno.setVisible(true);
            clearFrame();
            
            agregarAlumno.getBtnCreate().setText("Agregar");
            agregarAlumno.getBtnCreate().setBackground(new Color(70,155,70));
            agregarAlumno.getjLabel1().setText("Creación de Alumno");
            agregarAlumno.getFieldDNI().setEditable(true);
        }
        else if(e.getSource().equals(panelAlumnos.getBtnEdit())){
            int selectedRow = panelAlumnos.getTableAlumnos().getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                Object DNI = panelAlumnos.getTableAlumnos().getValueAt(selectedRow, 0);
                Alumno alu = alumno.find((int) DNI);

                this.agregarAlumno.setVisible(true);
                agregarAlumno.getFieldDNI().setText(String.valueOf(alu.getDni()));
                agregarAlumno.getFieldNombre().setText(alu.getNombre());
                agregarAlumno.getFieldApellido().setText(alu.getApellido());
                agregarAlumno.getFieldDomicilio().setText(alu.getDomicilio());
                agregarAlumno.getFieldTelefono().setText(alu.getTelefono());
                agregarAlumno.getFieldFecha().setDate(alu.getFechaNacimiento());
                agregarAlumno.getBtnCreate().setText("Editar");
                agregarAlumno.getBtnCreate().setBackground(new Color(75,65,60));
                agregarAlumno.getjLabel1().setText("Editar Alumno");
                agregarAlumno.getFieldDNI().setEditable(false);
            }
        } 
        else if(e.getSource().equals(panelAlumnos.getBtnGrades())){
            if (panelAlumnos.getTableAlumnos().getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                menu.mainPanel.add(panelNotas, "panelNotas");
                this.menuController.getCardLayout().show(menu.mainPanel, "panelNotas");
                SwingUtilities.updateComponentTreeUI(menu);
                menu.repaint();
                clearNotas();
                listaNotas();
            }
        } 
        else if(e.getSource().equals(agregarAlumno.getBtnCreate())){
            if (agregarAlumno.getFieldDNI().isEditable() == false){
                editAlumno();
            } else{
                addAlumno();
                clearFrame();
            } 
        } 
        else if(e.getSource().equals(panelNotas.getBtnBack())){
            menu.mainPanel.add(panelAlumnos, "panelAlumnos");
            this.menuController.getCardLayout().show(menu.mainPanel, "panelAlumnos");
            SwingUtilities.updateComponentTreeUI(menu);
            menu.repaint();
        }
        else {
            this.agregarAlumno.dispose();
        }
    }
    
    public void lista(){
        List<Alumno> alumnos = alumno.read();
        modelo = (DefaultTableModel) panelAlumnos.getTableAlumnos().getModel();
        Object[] row = new Object[6];
        panelAlumnos.getTableAlumnos().setRowHeight(30);     
        for (int i =0;i < alumnos.size();i++){
            row[0]= alumnos.get(i).getDni();
            row[1] = alumnos.get(i).getNombre();
            row[2] = alumnos.get(i).getApellido();
            row[3] = alumnos.get(i).getFechaNacimiento();
            row[4] = alumnos.get(i).getTelefono();
            row[5] = alumnos.get(i).getDomicilio();
            modelo.addRow(row);
        }
        panelAlumnos.getTableAlumnos().setModel(modelo);
    }
    
    public void clear() {
        for (int i = 0; i < panelAlumnos.getTableAlumnos().getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void addAlumno(){
        if (!validacion.checkDNI(agregarAlumno.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI debe contener 8 caracteres");
            return;
        }
        if (!validacion.checkDNILetras(agregarAlumno.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI no puede contener letras");
            return;
        }
        if (!validacion.checkTelefono(agregarAlumno.getFieldTelefono().getText())) {
            JOptionPane.showMessageDialog(null, "El telefono debe tener 12 o mas caracteres alfanumericos");
            return;
        }
        if (agregarAlumno.getFieldNombre().getText().isEmpty() || agregarAlumno.getFieldApellido().getText().isEmpty() || agregarAlumno.getFieldDomicilio().getText().isEmpty() || agregarAlumno.getFieldFecha().getDate() == null){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        if (validacion.checkNombreNumeros(agregarAlumno.getFieldNombre().getText())){
            JOptionPane.showMessageDialog(null, "El nombre no puede contener numeros");
            return;
        }
        if (validacion.checkNombreNumeros(agregarAlumno.getFieldApellido().getText())){
            JOptionPane.showMessageDialog(null, "El apellido no puede contener numeros");
            return;
        }
        
        if (alumno.exist(Integer.parseInt(agregarAlumno.getFieldDNI().getText()))){
            JOptionPane.showMessageDialog(null, "El DNI ingresado ya esta registrado");
            return;
        }
        
        String DNI = agregarAlumno.getFieldDNI().getText();
        String nombre = agregarAlumno.getFieldNombre().getText();
        String apellido = agregarAlumno.getFieldApellido().getText();
        String telefono = agregarAlumno.getFieldTelefono().getText();
        String domicilio = agregarAlumno.getFieldDomicilio().getText();
        long fecha = agregarAlumno.getFieldFecha().getDate().getTime();

        Alumno alu = new Alumno();
        alu.setDni(Integer.parseInt(DNI));
        alu.setNombre(nombre);
        alu.setApellido(apellido);
        alu.setTelefono(telefono);
        alu.setDomicilio(domicilio);
        alu.setFechaNacimiento(new java.sql.Date(fecha));

        alumno.create(alu);

        clear();
        lista();
        agregarAlumno.dispose();
        clearFrame();
    }
    
    public void editAlumno(){
        if (!validacion.checkDNI(agregarAlumno.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI debe contener 8 caracteres");
            return;
        }
        if (!validacion.checkDNILetras(agregarAlumno.getFieldDNI().getText())){
            JOptionPane.showMessageDialog(null, "El DNI no puede contener letras");
            return;
        }
        if (!validacion.checkTelefono(agregarAlumno.getFieldTelefono().getText())) {
            JOptionPane.showMessageDialog(null, "El telefono debe tener 12 o mas caracteres alfanumericos");
            return;
        }
        if (agregarAlumno.getFieldNombre().getText().isEmpty() || agregarAlumno.getFieldApellido().getText().isEmpty() || agregarAlumno.getFieldDomicilio().getText().isEmpty() || agregarAlumno.getFieldFecha().getDate() == null){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        if (validacion.checkNombreNumeros(agregarAlumno.getFieldNombre().getText())){
            JOptionPane.showMessageDialog(null, "El nombre no puede contener numeros");
            return;
        }
        if (validacion.checkNombreNumeros(agregarAlumno.getFieldApellido().getText())){
            JOptionPane.showMessageDialog(null, "El apellido no puede contener numeros");
            return;
        }
        
        String DNI = agregarAlumno.getFieldDNI().getText();
        String nombre = agregarAlumno.getFieldNombre().getText();
        String apellido = agregarAlumno.getFieldApellido().getText();
        String telefono = agregarAlumno.getFieldTelefono().getText();
        String domicilio = agregarAlumno.getFieldDomicilio().getText();
        long fecha = agregarAlumno.getFieldFecha().getDate().getTime();

        Alumno alu = new Alumno();
        alu.setDni(Integer.parseInt(DNI));
        alu.setNombre(nombre);
        alu.setApellido(apellido);
        alu.setTelefono(telefono);
        alu.setDomicilio(domicilio);
        alu.setFechaNacimiento(new java.sql.Date(fecha));
        
        alumno.update(alu);
        
        clear();
        lista();
        agregarAlumno.dispose();
        clearFrame();
    }
    
    public void clearFrame(){
        agregarAlumno.getFieldDNI().setText("");
        agregarAlumno.getFieldNombre().setText("");
        agregarAlumno.getFieldApellido().setText("");
        agregarAlumno.getFieldDomicilio().setText("");
        agregarAlumno.getFieldTelefono().setText("");
        agregarAlumno.getFieldFecha().setDate(null);
    }
    
    public void listaNotas(){
        int selectedRow = panelAlumnos.getTableAlumnos().getSelectedRow();

         Object DNI = panelAlumnos.getTableAlumnos().getValueAt(selectedRow, 0);
        
        List<Cursado> notas = alumno.findGrades((int) DNI);
        modelo = (DefaultTableModel) panelNotas.getTableCursados().getModel();
        Object[] row = new Object[3];
        panelNotas.getTableCursados().setRowHeight(30);
        
        
        
        for (int i =0;i < notas.size();i++){
            row[0]= notas.get(i).getMateria().getCodMateria();
            row[1] = materia.find(notas.get(i).getMateria().getCodMateria()).getNombreMateria();
            row[2] = notas.get(i).getNota();
            modelo.addRow(row);
        }
        
        panelNotas.getTableCursados().setModel(modelo);
    }
    
    public void clearNotas(){
        for (int i = 0; i < panelNotas.getTableCursados().getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
}

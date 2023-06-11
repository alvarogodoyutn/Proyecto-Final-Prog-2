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
import sgu.dao.ProfesorDAO;
import sgu.model.Cursado;
import sgu.model.Materia;
import sgu.model.Profesor;
import sgu.view.AgregarMateria;
import sgu.view.Menu;
import sgu.view.PanelMaterias;


public class MateriasController implements ActionListener{
    private PanelMaterias panelMaterias = new PanelMaterias();
    private AgregarMateria agregarMateria = new AgregarMateria();
    private Cursado cursado = new Cursado();
    private Materia materia = new Materia();
    private Menu menu;
    private MenuController menuController;
    private DefaultTableModel modelo;
    private Profesor profesor = new Profesor();
    private ProfesorDAO profesorDAO = new ProfesorDAO();
    
    public MateriasController(Menu menu, MenuController menuController){
        this.menu = menu;
        this.menuController = menuController;
        
        this.agregarMateria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menu.mainPanel.add(panelMaterias, "panelMaterias");
        this.menuController.getCardLayout().show(menu.mainPanel, "panelMaterias");
        SwingUtilities.updateComponentTreeUI(menu);
        menu.repaint();
        lista();
         
        panelMaterias.getBtnAdd().addActionListener(this);
        panelMaterias.getBtnDel().addActionListener(this);
        panelMaterias.getBtnEdit().addActionListener(this);
        agregarMateria.getBtnCreate().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelMaterias.getBtnAdd())){
            cargarCB();
            this.agregarMateria.setVisible(true);
            
            agregarMateria.getBtnCreate().setText("Agregar");
            agregarMateria.getBtnCreate().setBackground(new Color(70,155,70));
            agregarMateria.getjLabel1().setText("Creación de Materia");
            agregarMateria.getFieldCodigo().setEditable(true);
        } else if(e.getSource().equals(panelMaterias.getBtnDel())){
            int selectedRow = panelMaterias.getTableMaterias().getSelectedRow(); 
            if (selectedRow== -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                delete();
            }
        } else if(e.getSource().equals(panelMaterias.getBtnEdit())){
            int selectedRow = panelMaterias.getTableMaterias().getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                cargarCB();
                Object cod = panelMaterias.getTableMaterias().getValueAt(selectedRow, 0);
                Materia mat = materia.find((int) cod);

                clearFrame();
                this.agregarMateria.setVisible(true);
                agregarMateria.getFieldCodigo().setText(String.valueOf(cod));
                agregarMateria.getFieldNombre().setText(mat.getNombreMateria());
                agregarMateria.getBtnCreate().setText("Editar");
                agregarMateria.getBtnCreate().setBackground(new Color(75,65,60));
                agregarMateria.getjLabel1().setText("Editar Materia");
                agregarMateria.getFieldCodigo().setEditable(false);
            }
        } else if(e.getSource().equals(agregarMateria.getBtnCreate())){
            if (agregarMateria.getFieldCodigo().isEditable() == false){
                editMateria();
            } else{
                addMateria();
                clearFrame();
            } 
        } else agregarMateria.dispose();
    }
    
    public void lista(){
        List<Materia> materias = materia.read();
        modelo = (DefaultTableModel) panelMaterias.getTableMaterias().getModel();
        Object[] row = new Object[3];
        panelMaterias.getTableMaterias().setRowHeight(30);
        
        for (int i =0;i < materias.size();i++){
            row[0]= materias.get(i).getCodMateria();
            row[1] = materias.get(i).getNombreMateria();
            row[2] = materias.get(i).getProfesor().getDni();
            modelo.addRow(row);
        }
        
        panelMaterias.getTableMaterias().setModel(modelo);
    }
    
    public void clear() {
        for (int i = 0; i < panelMaterias.getTableMaterias().getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void addMateria(){
        if (agregarMateria.getFieldNombre().getText().isEmpty() || agregarMateria.getFieldCodigo().getText().isEmpty() || agregarMateria.getCbProfesor().getSelectedItem().equals("Seleccionar profesor")){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        
        String codigoMat = agregarMateria.getFieldCodigo().getText();
        
        if (materia.exist(Integer.parseInt(codigoMat))){
            JOptionPane.showMessageDialog(null, "El codigo de materia ingresado ya esta registrado");
            return;
        }
        
        if (agregarMateria.getCbProfesor().getSelectedItem().equals("Seleccionar Profesor")) {
            JOptionPane.showMessageDialog(null, "Seleccione un profesor");
            return;
        }
        
        String nombre = agregarMateria.getFieldNombre().getText();
        Object profesor = agregarMateria.getCbProfesor().getSelectedItem().toString();
        int DNIProf = splitearProfe((String) profesor);

        Materia mat = new Materia();
        mat.setCodMateria(Integer.parseInt(codigoMat));
        mat.setNombreMateria(nombre);
        mat.setProfesor(this.profesor.find(DNIProf));

        materia.create(mat);

        clear();
        lista();
        agregarMateria.dispose();
        clearFrame();
    }
    public void editMateria(){
        if (agregarMateria.getFieldNombre().getText().isEmpty() || agregarMateria.getFieldCodigo().getText().isEmpty() || agregarMateria.getCbProfesor().getSelectedItem().equals("Seleccionar profesor")){
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios");
            return;
        }
        
        String codigoMat = agregarMateria.getFieldCodigo().getText();
        String nombre = agregarMateria.getFieldNombre().getText();
        Object profesor = agregarMateria.getCbProfesor().getSelectedItem().toString();
        int DNIProf = splitearProfe((String) profesor);

        Materia mat = new Materia();
        mat.setCodMateria(Integer.parseInt(codigoMat));
        mat.setNombreMateria(nombre);
        mat.setProfesor(this.profesor.find(DNIProf));

        materia.update(mat);

        clear();
        lista();
        agregarMateria.dispose();
        clearFrame();
    }
    
    public int splitearProfe(String profeDniNombre) {
        String[] parts = profeDniNombre.split(" - ");
        String part1 = parts[0]; 

        return Integer.valueOf(part1);
    }
    
    public void delete(){
        Object cod = panelMaterias.getTableMaterias().getValueAt(panelMaterias.getTableMaterias().getSelectedRow(), 0);
     
        materia.delete((int) cod);
        clear();
        lista();
    }
    
    public void cargarCB() {
        List<Profesor> listaProfesores = profesorDAO.read();

        agregarMateria.getCbProfesor().removeAllItems();
        agregarMateria.getCbProfesor().addItem("Seleccionar Profesor");
        for (int i = 0; i < listaProfesores.size(); i++) {
            agregarMateria.getCbProfesor().addItem(listaProfesores.get(i).getDni() + " - " + listaProfesores.get(i).getNombre() + " " + listaProfesores.get(i).getApellido());

        }
    }
    
    public void clearFrame(){
        agregarMateria.getFieldCodigo().setText("");
        agregarMateria.getFieldNombre().setText("");
    }
}

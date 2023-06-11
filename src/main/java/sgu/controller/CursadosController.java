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
import sgu.view.AgregarCursado;
import sgu.view.Menu;
import sgu.view.PanelCursados;


public class CursadosController implements ActionListener{
    private PanelCursados panelCursados = new PanelCursados();
    private AgregarCursado agregarCursado = new AgregarCursado();
    private Cursado cursado = new Cursado();
    private Menu menu;
    private MenuController menuController;
    private DefaultTableModel modelo;
    private Materia materia = new Materia();
    private Alumno alumno = new Alumno();
    private Validaciones validacion = new Validaciones();
    
    public CursadosController(Menu menu, MenuController menuController){
        this.menu = menu;
        this.menuController = menuController;
        
        agregarCursado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menu.mainPanel.add(panelCursados, "panelCursados");
        this.menuController.getCardLayout().show(menu.mainPanel, "panelCursados");
        SwingUtilities.updateComponentTreeUI(menu);
        menu.repaint();
        lista();
         
        panelCursados.getBtnDel().addActionListener(this);
        panelCursados.getBtnAdd().addActionListener(this);
        panelCursados.getBtnEdit().addActionListener(this);
        agregarCursado.getBtnCreate().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelCursados.getBtnDel())){
            int selectedRow = panelCursados.getTableCursados().getSelectedRow(); 
            if (selectedRow== -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                delete();
            }
        } else if(e.getSource().equals(panelCursados.getBtnAdd())){
            cargarCB();
            this.agregarCursado.setVisible(true);
            
            agregarCursado.getBtnCreate().setText("Agregar");
            agregarCursado.getBtnCreate().setBackground(new Color(70,155,70));
            agregarCursado.getjLabel1().setText("Creación de Cursado");
        } else if(e.getSource().equals(panelCursados.getBtnEdit())){
            int selectedRow = panelCursados.getTableCursados().getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } else {
                cargarCB();
                Object aluDNI = panelCursados.getTableCursados().getValueAt(selectedRow, 0);
                Cursado cur = cursado.find((int) aluDNI);

                clearFrame();
                this.agregarCursado.setVisible(true);
                agregarCursado.getFieldNota().setText(String.valueOf(cur.getNota()));
                agregarCursado.getCbAlumnos().setSelectedItem(aluDNI);
                agregarCursado.getCbMateria().setSelectedItem(aluDNI);
                agregarCursado.getBtnCreate().setText("Editar");
                agregarCursado.getBtnCreate().setBackground(new Color(75,65,60));
                agregarCursado.getjLabel1().setText("Editar Cursado");
            }
        } else if(e.getSource().equals(agregarCursado.getBtnCreate())){
            if (agregarCursado.getjLabel1().equals("Editar Cursado")){
                editCursado();
            } else{
                addCursado();
                clearFrame();
            } 
        } else agregarCursado.dispose();
    }
    
     public void lista(){
        List<Cursado> cursados = cursado.read();
        if (cursados.size()<1) return;
        modelo = (DefaultTableModel) panelCursados.getTableCursados().getModel();
        Object[] row = new Object[3];
        panelCursados.getTableCursados().setRowHeight(30);
        Alumno alu = cursados.get(0).getAlumno();
        
        for (int i =0;i < cursados.size();i++){
            row[0]= cursados.get(i).getMateria().getCodMateria();
            row[1] = cursados.get(i).getAlumno().getDni();
            row[2] = cursados.get(i).getNota();
            modelo.addRow(row);
        }
        
        panelCursados.getTableCursados().setModel(modelo);
    }
    
      public void cargarCB() {

        List<Alumno> listaAlumnos = alumno.read();
        List<Materia> listaMaterias = materia.read();

        agregarCursado.getCbAlumnos().removeAllItems();
        agregarCursado.getCbAlumnos().addItem("Seleccionar Alumno");
        for (int i = 0; i < listaAlumnos.size(); i++) {
            agregarCursado.getCbAlumnos().addItem(String.valueOf(listaAlumnos.get(i).getDni()) + " - " + listaAlumnos.get(i).getNombre()
                    + " " + listaAlumnos.get(i).getApellido());
        }

        agregarCursado.getCbMateria().removeAllItems();
        agregarCursado.getCbMateria().addItem("Seleccionar Materia");

        for (int i = 0; i < listaMaterias.size(); i++) {
            agregarCursado.getCbMateria().addItem(String.valueOf(listaMaterias.get(i).getCodMateria() + " - " + listaMaterias.get(i).getNombreMateria()));
        }

    }
      
    public void addCursado(){
        if (agregarCursado.getFieldNota().equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
        }
        
        if (agregarCursado.getCbAlumnos().getSelectedItem().equals("Seleccionar Alumno")) {
            JOptionPane.showMessageDialog(null, "Seleccione un alumno");
            return;
        }
        
        if (agregarCursado.getCbMateria().getSelectedItem().equals("Seleccionar Materia")) {
            JOptionPane.showMessageDialog(null, "Seleccione una materia");
            return;
        }
        
        String nota = agregarCursado.getFieldNota().getText();
        Object alu = agregarCursado.getCbAlumnos().getSelectedItem().toString();
        Object mat = agregarCursado.getCbMateria().getSelectedItem().toString();
        int DNIalu = splitearString((String) alu);
        int codMat = splitearString((String) mat);
        
        if (!validacion.checkNota(Double.parseDouble(nota))){
            JOptionPane.showMessageDialog(null, "La nota debe ser un valor entre 1 y 10");
            return;
        }
        
        if (!validacion.checkNotaLetras(nota)){
            JOptionPane.showMessageDialog(null, "La nota no puede contener letras");
            return;
        }

        Cursado cur = new Cursado();
        cur.setAlumno(alumno.find(DNIalu));
        cur.setMateria(materia.find(codMat));
        cur.setNota(Double.parseDouble(nota));

        cursado.create(cur);

        clear();
        lista();
        agregarCursado.dispose();
        clearFrame();
    }  
    
    public void editCursado(){
        if (agregarCursado.getFieldNota().equals("")) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
        }
        
        if (agregarCursado.getCbAlumnos().getSelectedItem().equals("Seleccionar Alumno")) {
            JOptionPane.showMessageDialog(null, "Seleccione un alumno");
            return;
        }
        
        if (agregarCursado.getCbMateria().getSelectedItem().equals("Seleccionar Materia")) {
            JOptionPane.showMessageDialog(null, "Seleccione una materia");
            return;
        }
        
        String nota = agregarCursado.getFieldNota().getText();
        Object alu = agregarCursado.getCbAlumnos().getSelectedItem().toString();
        Object mat = agregarCursado.getCbMateria().getSelectedItem().toString();
        int DNIalu = splitearString((String) alu);
        int codMat = splitearString((String) mat);
        
        if (validacion.checkNota(Double.parseDouble(nota))){
            JOptionPane.showMessageDialog(null, "La nota debe ser un valor entre 1 y 10");
            return;
        }
        
        if (!validacion.checkNotaLetras(nota)){
            JOptionPane.showMessageDialog(null, "La nota no puede contener letras");
            return;
        }

        Cursado cur = new Cursado();
        cur.setAlumno(alumno.find(DNIalu));
        cur.setMateria(materia.find(codMat));
        cur.setNota(Double.parseDouble(nota));

        cursado.update(cur);

        clear();
        lista();
        agregarCursado.dispose();
        clearFrame();
    }
    
    public void delete(){
        Object cod = panelCursados.getTableCursados().getValueAt(panelCursados.getTableCursados().getSelectedRow(), 1);
        
        cursado.delete((int) cod);
        clear();
        lista();
    }
     
    public void clear() {
        for (int i = 0; i < panelCursados.getTableCursados().getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public int splitearString(String str) {
        String[] parts = str.split(" - ");
        String part1 = parts[0];

        return Integer.valueOf(part1);
    }
    
    public void clearFrame(){
        agregarCursado.getFieldNota().setText("");
    }
}

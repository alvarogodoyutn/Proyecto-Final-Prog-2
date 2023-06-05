/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sgu.model.Alumno;
import sgu.view.Menu;
import sgu.view.PanelAlumnos;


/**
 *
 * @author MI EQUIPO
 */
public class AlumnosController implements ActionListener {
    private Menu menu;
    private PanelAlumnos panelAlumnos;
    private DefaultTableModel modelo;
    private Alumno alumno = new Alumno();
    

    public AlumnosController(PanelAlumnos panelAlumnos, Menu menu) {
        this.panelAlumnos = panelAlumnos;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.panelAlumnos.btnDelete)) delete();
    }
    
    public void delete (){
        if (this.panelAlumnos.tableAlumnos.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }
    
    public void listarAlumnos(JTable table) {
        modelo = (DefaultTableModel) panelAlumnos.tableAlumnos.getModel();
        panelAlumnos.tableAlumnos.setRowHeight(30);
        List<Alumno> lista = alumno.readlumnos();
        Object[] fila = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDni();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getApellido();
            fila[3] = lista.get(i).getFechaNacimiento();
            fila[4] = lista.get(i).getDomicilio();
            fila[5] = lista.get(i).getTelefono();
            fila[6] = lista.get(i).getCodigoInscripcion();
            modelo.addRow(fila);
        }
        panelAlumnos.tableAlumnos.setModel(modelo);

    }
    
     public void clearTable() {
        for (int i = 0; i < panelAlumnos.tableAlumnos.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import sgu.view.Menu;
import sgu.view.PanelAlumnos;


public class MenuController implements  MouseListener, ActionListener {
    private Menu view = new Menu();
    private CardLayout cardLayout = (CardLayout) this.view.mainPanel.getLayout();
    
     //Paneles
    private PanelAlumnos panelAlumnos = new PanelAlumnos();
    
    //Controladores
    private AlumnosController alumnosController = new AlumnosController(panelAlumnos, view);

    public MenuController() {

        this.view.setVisible(true);

        this.view.btnAlu.addMouseListener(this);
        this.view.btnMat.addMouseListener(this);
        this.view.btnCur.addMouseListener(this);
        
        this.view.btnAlu.addActionListener(this);
        this.view.btnMat.addActionListener(this);
        this.view.btnCur.addActionListener(this);              
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.view.btnAlu)){
            panelAlumno();
            alumnosController.clearTable();
            alumnosController.listarAlumnos(this.panelAlumnos.tableAlumnos);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(this.view.btnAlu)) {
            this.view.btnAlu.setForeground(new Color(255, 144, 0));
            this.view.btnAlu.setIcon(new ImageIcon(getClass().getResource("/icons8-student-male-30.png")));
        } else if (e.getSource().equals(this.view.btnMat)) {
            this.view.btnMat.setForeground(new Color(255, 144, 0));
            this.view.btnMat.setIcon(new ImageIcon(getClass().getResource("/icons8-class-30 (1).png")));
        } else if (e.getSource().equals(this.view.btnCur)) {
            this.view.btnCur.setForeground(new Color(255, 144, 0));
            this.view.btnCur.setIcon(new ImageIcon(getClass().getResource("/icons8-classroom-30 (1).png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(this.view.btnAlu)) {
            this.view.btnAlu.setForeground(new Color(153, 153, 153));
            this.view.btnAlu.setIcon(new ImageIcon(getClass().getResource("/icons8-student-30.png")));
        } else if (e.getSource().equals(this.view.btnMat)) {
            this.view.btnMat.setForeground(new Color(153, 153, 153));
            this.view.btnMat.setIcon(new ImageIcon(getClass().getResource("/icons8-class-30.png")));
        } else if (e.getSource().equals(this.view.btnCur)) {
            this.view.btnCur.setForeground(new Color(153, 153, 153));
            this.view.btnCur.setIcon(new ImageIcon(getClass().getResource("/icons8-classroom-30.png")));
        }
    }
    
    public void panelAlumno(){
        this.view.mainPanel.add(panelAlumnos, "panelAlumnos");
        cardLayout.show(this.view.mainPanel, "panelAlumnos");
        SwingUtilities.updateComponentTreeUI(this.view);
        this.view.repaint();
        alumnosController.listarAlumnos(panelAlumnos.tableAlumnos);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
    
    
}

/*
 * Click nbfs://nbhost            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int getIconWidth() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int getIconHeight() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        }SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sgu.view;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.plaf.basic.BasicButtonUI;

public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {  
        initComponents();
        
        this.setResizable(false); 
        
         
         
    
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        navBar = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCur = new javax.swing.JButton();
        btnAlu = new javax.swing.JButton();
        btnMat = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerSize(0);

        navBar.setBackground(new java.awt.Color(30, 30, 30));

        jPanel5.setBackground(new java.awt.Color(30, 30, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-student-center-50.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SGU");
        jLabel8.setToolTipText("");
        jLabel8.setAlignmentX(0.5F);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnCur.setBackground(new java.awt.Color(30, 30, 30));
        btnCur.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnCur.setForeground(new java.awt.Color(153, 153, 153));
        btnCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-classroom-30.png"))); // NOI18N
        btnCur.setText("Cursados");
        btnCur.setBorder(null);
        btnCur.setBorderPainted(false);
        btnCur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCur.setFocusable(false);

        btnAlu.setBackground(new java.awt.Color(30, 30, 30));
        btnAlu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAlu.setForeground(new java.awt.Color(153, 153, 153));
        btnAlu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-student-30.png"))); // NOI18N
        btnAlu.setText("Alumnos");
        btnAlu.setBorder(null);
        btnAlu.setBorderPainted(false);
        btnAlu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlu.setFocusable(false);

        btnMat.setBackground(new java.awt.Color(30, 30, 30));
        btnMat.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnMat.setForeground(new java.awt.Color(153, 153, 153));
        btnMat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-class-30.png"))); // NOI18N
        btnMat.setText("Materias");
        btnMat.setBorder(null);
        btnMat.setBorderPainted(false);
        btnMat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMat.setFocusable(false);

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAlu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navBarLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btnAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCur, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(navBar);

        mainPanel.setBackground(new java.awt.Color(33, 33, 33));
        mainPanel.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        
        FlatMacDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAlu;
    public javax.swing.JButton btnCur;
    public javax.swing.JButton btnMat;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navBar;
    // End of variables declaration//GEN-END:variables
}
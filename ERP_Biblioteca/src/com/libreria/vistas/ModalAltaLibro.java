/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.libreria.vistas;

/**
 *
 * @author GZ TIENDA
 */
public class ModalAltaLibro extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModalAltaLibro.class.getName());

    /**
     * Creates new form ModalAltaLibro
     */
    public ModalAltaLibro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(560, 385);

        this.setBackground(new java.awt.Color(0, 0, 0, 0));

        // 1. Centrar siempre el modal respecto a la ventana principal
        this.setLocationRelativeTo(parent);

        // 2. Crear la capa de sombreado semitransparente (GlassPane) sobre la ventana padre
        if (parent instanceof javax.swing.JFrame frame) {
            javax.swing.JComponent capaSombreado = new javax.swing.JComponent() {
                @Override
                protected void paintComponent(java.awt.Graphics g) {
                    // Color azul noche oscuro con canal Alpha = 150 (semitransparente)
                    g.setColor(new java.awt.Color(15, 23, 42, 150));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            capaSombreado.setOpaque(false);
            frame.setGlassPane(capaSombreado);

            // Encender el sombreado al abrir el modal y apagarlo al cerrarlo
            this.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowOpened(java.awt.event.WindowEvent e) {
                    capaSombreado.setVisible(true);
                }

                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    capaSombreado.setVisible(false);
                }

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    capaSombreado.setVisible(false);
                }
            });

            // Dibujar el icono vectorial del circulo blanco con check verde para el boton Guardar
            btnGuardarLibroModal.setIcon(new javax.swing.Icon() {
                @Override
                public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    // Circulo blanco de fondo
                    g2.setColor(java.awt.Color.WHITE);
                    g2.fillOval(x, y, 16, 16);
                    // Tilde (check) del color del boton
                    g2.setColor(new java.awt.Color(16, 150, 105));
                    g2.setStroke(new java.awt.BasicStroke(2f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    g2.drawLine(x + 4, y + 8, x + 7, y + 11);
                    g2.drawLine(x + 7, y + 11, x + 12, y + 5);
                    g2.dispose();
                }

                @Override
                public int getIconWidth() {
                    return 16;
                }

                @Override
                public int getIconHeight() {
                    return 16;
                }
            });
            btnGuardarLibroModal.setIconTextGap(8); // Espacio entre el check y el texto GUARDAR LIBRO

        }
        // 1. Configurar el boton X real que está visible en pantalla (jButton2) y sus copias
        javax.swing.JButton[] botonesCerrar = {jButton2, jButton3, btnCierreCabecera};
        for (javax.swing.JButton btn : botonesCerrar) {
            btn.setText(""); // Quitamos el texto para que Nimbus jamás ponga "..."
            // Dibujamos la X blanca vectorial centrada al milímetro
            btn.setIcon(new javax.swing.Icon() {
                @Override
                public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(java.awt.Color.WHITE);
                    g2.setStroke(new java.awt.BasicStroke(2f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
                    g2.drawLine(x + 2, y + 2, x + 10, y + 10);
                    g2.drawLine(x + 10, y + 2, x + 2, y + 10);
                    g2.dispose();
                }

                @Override
                public int getIconWidth() {
                    return 12;
                }

                @Override
                public int getIconHeight() {
                    return 12;
                }
            });
            // Asegurar que al hacer clic cierre el modal
            btn.addActionListener(e -> this.dispose());
        }

        // 2. Agregar padding interno de 10px a los campos de texto normales (estilo Figma)
        javax.swing.border.Border bordeNormal = javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225), 1),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)
        );
        txtTitulo.setBorder(bordeNormal);
        txtAutor.setBorder(bordeNormal);
        txtPrecio.setBorder(bordeNormal);
        txtCantidad.setBorder(bordeNormal);

        // 3. Borde rojo con padding interno de 10px para el campo con error
        txtNroArticulo.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 68, 68), 1),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardarLibroModal = new javax.swing.JButton();
        btnCancelarModal = new javax.swing.JButton();
        cmbGenero = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNroArticulo = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlCabecera1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pnlCabecera = new javax.swing.JPanel();
        btnCierreCabecera = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 41, 59)));
        jPanel4.setVerifyInputWhenFocusTarget(false);
        jPanel4.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 41, 59)));
        jPanel6.setPreferredSize(new java.awt.Dimension(560, 385));
        jPanel6.setLayout(null);
        jPanel6.add(jSeparator1);
        jSeparator1.setBounds(0, 305, 560, 10);

        btnGuardarLibroModal.setBackground(new java.awt.Color(16, 150, 105));
        btnGuardarLibroModal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarLibroModal.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarLibroModal.setText("GUARDAR LIBRO");
        jPanel6.add(btnGuardarLibroModal);
        btnGuardarLibroModal.setBounds(360, 325, 175, 38);

        btnCancelarModal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarModal.setForeground(new java.awt.Color(51, 51, 51));
        btnCancelarModal.setText(" CANCELAR");
        btnCancelarModal.addActionListener(this::btnCancelarModalActionPerformed);
        jPanel6.add(btnCancelarModal);
        btnCancelarModal.setBounds(25, 320, 115, 38);

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar género...", "Novela", "Cuento", "Ciencia y Tecnología", "Ensayo e Historia" }));
        cmbGenero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cmbGenero.addActionListener(this::cmbGeneroActionPerformed);
        jPanel6.add(cmbGenero);
        cmbGenero.setBounds(25, 232, 240, 34);

        txtCantidad.setText("1");
        txtCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtCantidad);
        txtCantidad.setBounds(295, 232, 240, 34);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(295, 212, 100, 18);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("<html>Género <font color='red'>*</font></html>");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(25, 212, 100, 18);

        txtAutor.setForeground(new java.awt.Color(148, 163, 184));
        txtAutor.setText("Ingrese el autor...");
        txtAutor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAutor.addActionListener(this::txtAutorActionPerformed);
        jPanel6.add(txtAutor);
        txtAutor.setBounds(25, 155, 240, 34);

        txtPrecio.setText("$                                                    0,00");
        txtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtPrecio);
        txtPrecio.setBounds(295, 155, 240, 34);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("<html>Autor <font color='red'>*</font></html>");
        jPanel6.add(jLabel3);
        jLabel3.setBounds(25, 135, 100, 18);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Precio Unitario");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(295, 135, 120, 18);

        txtNroArticulo.setText("122.1.12");
        txtNroArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jPanel6.add(txtNroArticulo);
        txtNroArticulo.setBounds(295, 78, 240, 34);

        txtTitulo.setForeground(new java.awt.Color(148, 163, 184));
        txtTitulo.setText("Ingrese el título...");
        txtTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTitulo.addActionListener(this::txtTituloActionPerformed);
        jPanel6.add(txtTitulo);
        txtTitulo.setBounds(25, 78, 240, 34);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("<html>Título <font color='red'>*</font></html>");
        jPanel6.add(jLabel1);
        jLabel1.setBounds(25, 58, 100, 18);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("<html>Nro. Artículo <font color='red'>*</font></html>");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(295, 58, 120, 18);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("El Nro. Artículo es obligatorio");
        jPanel6.add(jLabel13);
        jLabel13.setBounds(295, 114, 240, 16);

        pnlCabecera1.setBackground(new java.awt.Color(44, 62, 80));
        pnlCabecera1.setForeground(new java.awt.Color(255, 255, 255));
        pnlCabecera1.setPreferredSize(new java.awt.Dimension(0, 40));
        pnlCabecera1.setLayout(null);

        jButton2.setBackground(new java.awt.Color(231, 76, 60));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("X");
        jButton2.setOpaque(true);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        pnlCabecera1.add(jButton2);
        jButton2.setBounds(520, 7, 30, 28);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Alta de Libro");
        pnlCabecera1.add(jLabel16);
        jLabel16.setBounds(50, 6, 70, 16);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/libreria/recursos/libro-modal-AltaLibro.png"))); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(20, 40));
        pnlCabecera1.add(jLabel17);
        jLabel17.setBounds(6, 0, 38, 32);

        jPanel6.add(pnlCabecera1);
        pnlCabecera1.setBounds(0, 0, 560, 42);

        jButton3.setBackground(new java.awt.Color(231, 76, 60));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("X");
        jButton3.setOpaque(true);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel6.add(jButton3);
        jButton3.setBounds(520, 7, 30, 28);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Alta de Libro");
        jPanel6.add(jLabel18);
        jLabel18.setBounds(50, 6, 70, 16);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/libreria/recursos/libro-modal-AltaLibro.png"))); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(20, 40));
        jPanel6.add(jLabel19);
        jLabel19.setBounds(6, 0, 38, 32);

        jPanel4.add(jPanel6);
        jPanel6.setBounds(0, 0, 580, 410);

        pnlCabecera.setBackground(new java.awt.Color(44, 62, 80));
        pnlCabecera.setForeground(new java.awt.Color(255, 255, 255));
        pnlCabecera.setPreferredSize(new java.awt.Dimension(0, 40));
        pnlCabecera.setLayout(null);

        btnCierreCabecera.setBackground(new java.awt.Color(231, 76, 60));
        btnCierreCabecera.setForeground(new java.awt.Color(255, 255, 255));
        btnCierreCabecera.setText("X");
        btnCierreCabecera.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCierreCabecera.setOpaque(true);
        btnCierreCabecera.addActionListener(this::btnCierreCabeceraActionPerformed);
        pnlCabecera.add(btnCierreCabecera);
        btnCierreCabecera.setBounds(520, 7, 30, 28);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Alta de Libro");
        pnlCabecera.add(jLabel14);
        jLabel14.setBounds(50, 6, 70, 16);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/libreria/recursos/libro-modal-AltaLibro.png"))); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(20, 40));
        pnlCabecera.add(jLabel15);
        jLabel15.setBounds(6, 0, 38, 32);

        jPanel4.add(pnlCabecera);
        pnlCabecera.setBounds(0, 0, 560, 42);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 560, 385);

        setBounds(0, 0, 560, 385);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        // TODO add your handling code here:
        this.dispose(); // Esto destruye el modal y te devuelve a la ventana principal
    }//GEN-LAST:event_btnCancelarModalActionPerformed

    private void cmbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGeneroActionPerformed

    private void txtAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAutorActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnCierreCabeceraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCierreCabeceraActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCierreCabeceraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ModalAltaLibro dialog = new ModalAltaLibro(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnCierreCabecera;
    private javax.swing.JButton btnGuardarLibroModal;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCabecera1;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNroArticulo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}

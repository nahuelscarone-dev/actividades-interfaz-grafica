package vista;

import controlador.ReservaController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Animal;
import modelo.EstadoSalud;

public class ReservaView extends javax.swing.JPanel {

    // La Vista NO lo crea: se lo entrega el main con setControlador().
    private ReservaController controlador;
    
    // Para agregar y borrar filas de la tabla.
    private DefaultTableModel modeloTabla;
    
        
    public ReservaView() {
        initComponents();
        
        modeloTabla = (DefaultTableModel) tblAnimales.getModel();
      
         // El combo de estados se llena desde el enum: así nunca se desincroniza.
        cmbEstado.removeAllItems();
            for (EstadoSalud e : EstadoSalud.values()) {
                cmbEstado.addItem(e.name());
        } 
    }

    // El main llama a esto después de crear el Controlador.
    public void setControlador(ReservaController controlador) {
        this.controlador = controlador;
    }
 
    // ← el Controlador llama acá con la lista que le dio el Modelo
    public void actualizarTabla(ArrayList<Animal> animales) {
        modeloTabla.setRowCount(0);   // vacía la tabla
        for (Animal a : animales) {
            modeloTabla.addRow(new Object[] {
                a.getClass().getSimpleName(),   // Tigre, Lobo, Loro...
                a.getNombre(),
                a.getEdad(),
                a.getEnergia(),
                a.getEstadoSalud()
            });
        }
    }
 
    // ← el Controlador llama acá con los números que calculó el Modelo
    public void actualizarResumen(int total, int cazadores, int cansados) {
        lblResumen.setText(total + " animales · " + cazadores + " cazadores · "
                + cansados + " cansados");
    }
 
    // ← el Controlador llama acá para avisar algo (barra de estado)
    public void mostrarMensaje(String texto) {
        lblMensaje.setText(texto);
    }
 
    // ← el Controlador llama acá cuando algo salió mal
    public void mostrarError(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Atención", JOptionPane.WARNING_MESSAGE);
    }
 
    // ← el Controlador llama acá después de agregar
    public void limpiarFormulario() {
        tfNombre.setText("");
        tfEdad.setText("");
        cmbEspecie.setSelectedIndex(0);
        tfNombre.requestFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPpal = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lblEspecie = new javax.swing.JLabel();
        cmbEspecie = new javax.swing.JComboBox<>();
        lblEdad = new javax.swing.JLabel();
        tfEdad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        pnlTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnimales = new javax.swing.JTable();
        btnAlimentar = new javax.swing.JButton();
        btnCazar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnCambiarEstado = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        lblResumen = new javax.swing.JLabel();

        pnlPpal.setForeground(new java.awt.Color(242, 242, 242));

        lblNombre.setText("Nombre:");

        tfNombre.setToolTipText("");
        tfNombre.setCaretColor(new java.awt.Color(204, 0, 0));

        lblEspecie.setText("Especie:");

        cmbEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una especie", "Tigre", "GatoMontes", "Zorro", "Lobo", "Aguila", "Loro" }));

        lblEdad.setText("Edad:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        pnlTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblAnimales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Especie", "Nombre", "Edad", "Energía", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAnimales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblAnimales);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        btnAlimentar.setText("Alimentar");
        btnAlimentar.addActionListener(this::btnAlimentarActionPerformed);

        btnCazar.setText("Cazar");
        btnCazar.addActionListener(this::btnCazarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        lblEstado.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SALUDABLE", "HERIDO", "ENFERMO", "EN_TRATAMIENTO" }));

        btnCambiarEstado.setText("Cambiar Estado");
        btnCambiarEstado.addActionListener(this::btnCambiarEstadoActionPerformed);

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMensaje.setText(" ");

        lblResumen.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblResumen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResumen.setText(" ");

        javax.swing.GroupLayout pnlPpalLayout = new javax.swing.GroupLayout(pnlPpal);
        pnlPpal.setLayout(pnlPpalLayout);
        pnlPpalLayout.setHorizontalGroup(
            pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPpalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPpalLayout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPpalLayout.createSequentialGroup()
                        .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPpalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnAlimentar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCazar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlPpalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addContainerGap())
        );
        pnlPpalLayout.setVerticalGroup(
            pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPpalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEspecie)
                    .addComponent(cmbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdad)
                    .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlimentar)
                    .addComponent(btnCazar)
                    .addComponent(btnEliminar)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstado))
                .addGap(18, 18, 18)
                .addGroup(pnlPpalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPpal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
         // ③ → la Vista recoge los campos y se los pasa al Controlador (como texto)
        controlador.agregarAnimal(
                tfNombre.getText(),
                (String) cmbEspecie.getSelectedItem(),
                tfEdad.getText());
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblAnimales.getSelectedRow();   // -1 si no hay selección
        if (fila >= 0) {
            int ok = JOptionPane.showConfirmDialog(this,
                    "¿Sacar a este animal de la reserva?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (ok != JOptionPane.YES_OPTION) { return; }
        }
        // ① → la Vista envía el índice seleccionado al Controlador
        controlador.eliminarAnimal(fila);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAlimentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlimentarActionPerformed
        // → la Vista envía el índice seleccionado al Controlador
        controlador.alimentar(tblAnimales.getSelectedRow());
    }//GEN-LAST:event_btnAlimentarActionPerformed

    private void btnCazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCazarActionPerformed
        // → la Vista envía el índice seleccionado al Controlador
        controlador.cazar(tblAnimales.getSelectedRow());
    }//GEN-LAST:event_btnCazarActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        // El combo guarda texto ("HERIDO"); valueOf lo convierte al valor del enum.
        // → la Vista envía índice y estado elegido al Controlador
        controlador.cambiarEstado(
                tblAnimales.getSelectedRow(),
                EstadoSalud.valueOf((String) cmbEstado.getSelectedItem()));
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAlimentar;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnCazar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbEspecie;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEspecie;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblResumen;
    private javax.swing.JPanel pnlPpal;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblAnimales;
    private javax.swing.JTextField tfEdad;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}

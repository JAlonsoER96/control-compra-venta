/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.ContolCategoria;

/**
 *
 * @author Alonso Romero
 */
public class FrmCategoria extends javax.swing.JInternalFrame {

    private final ContolCategoria CONTROL;
    private String accion;
    private String nombreAnte;

    /**
     * Creates new form FrmCategoria
     */
    public FrmCategoria() {
        super();
        initComponents();
        this.CONTROL = new ContolCategoria();
        this.listar("");
        this.accion = "guardar";
        lblNumRegistro.setVisible(false);
    }

    //Listar categorias
    private void listar(String text) {
        tablaListadoCat.setModel(this.CONTROL.listar(text));
        TableRowSorter orden = new TableRowSorter(tablaListadoCat.getModel());
        tablaListadoCat.setRowSorter(orden);
        lblTotalRegistros.setText("Mostrando: " + this.CONTROL.showAll() + " de un total de: " + this.CONTROL.count() + " registros");
    }

    private void limpiar() {
        this.txtNewCatName.setText("");
        this.txtDescription.setText("");
        this.accion = "guardar";
    }

    private void errorMesagge(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void successMessage(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNuevo = new javax.swing.JTabbedPane();
        panelListadoCat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCatNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListadoCat = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        panelManteCat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewCatName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNumRegistro = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Categorias");

        panelListadoCat.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nombre: ");

        txtCatNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCatNombre.setAutoscrolls(false);
        txtCatNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatNombreActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                btnBuscarStateChanged(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablaListadoCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListadoCat);

        lblTotalRegistros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActivar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnDesactivar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListadoCatLayout = new javax.swing.GroupLayout(panelListadoCat);
        panelListadoCat.setLayout(panelListadoCatLayout);
        panelListadoCatLayout.setHorizontalGroup(
            panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListadoCatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelListadoCatLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCatNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addGap(0, 132, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListadoCatLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnActivar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDesactivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelListadoCatLayout.setVerticalGroup(
            panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListadoCatLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCatNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListadoCatLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblTotalRegistros))
                    .addGroup(panelListadoCatLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActivar)
                            .addComponent(btnDesactivar))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelNuevo.addTab("Listado", panelListadoCat);

        panelManteCat.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Nombre(*): ");

        txtNewCatName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNewCatName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewCatNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Descripción");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("(*) Campos obligatorios");

        lblNumRegistro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelManteCatLayout = new javax.swing.GroupLayout(panelManteCat);
        panelManteCat.setLayout(panelManteCatLayout);
        panelManteCatLayout.setHorizontalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManteCatLayout.createSequentialGroup()
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(btnGuardar)
                        .addGap(62, 62, 62)
                        .addComponent(btnCancelar))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addComponent(txtNewCatName, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        panelManteCatLayout.setVerticalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManteCatLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNewCatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumRegistro))
                .addGap(60, 60, 60)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(73, 73, 73)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(215, Short.MAX_VALUE))
        );

        panelNuevo.addTab("Mantenimiento", panelManteCat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevo)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevo)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCatNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatNombreActionPerformed

    private void btnBuscarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnBuscarStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.listar(txtCatNombre.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtNewCatNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewCatNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewCatNameActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        panelNuevo.setEnabledAt(1, true);
        panelNuevo.setEnabledAt(0, false);
        panelNuevo.setSelectedIndex(1);
        this.accion = "guardar";
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiar();
        panelNuevo.setEnabledAt(1, false);
        panelNuevo.setEnabledAt(0, true);
        panelNuevo.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNewCatName.getText().length() == 0 || txtNewCatName.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "El campo nombre es obligatorio", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNewCatName.requestFocus();
            return;
        }
        if (txtDescription.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "La descripción es demasiado larga", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDescription.requestFocus();
            return;
        }
        String resp;
        if (this.accion.equals("editar")) {
            //Editar
            resp = this.CONTROL.update(Integer.parseInt(lblNumRegistro.getText()), txtNewCatName.getText(), this.nombreAnte, txtDescription.getText());
            if (resp.equals("OK")) {
                this.successMessage("Actualizado Correctamente");
                this.limpiar();
                this.listar("");
            } else {
                this.errorMesagge(resp);
            }
        } else {
            //Guardar
            resp = this.CONTROL.insert(txtNewCatName.getText(), txtDescription.getText());
            if (resp.equals("OK")) {
                this.successMessage("Registrado correctamente");
                this.limpiar();
                this.listar("");
            } else {
                this.errorMesagge(resp);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tablaListadoCat.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 1));
            this.nombreAnte = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 1));
            String descripcion = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 2));

            lblNumRegistro.setText(id);
            txtNewCatName.setText(nombre);
            txtDescription.setText(descripcion);

            panelNuevo.setEnabledAt(1, true);
            panelNuevo.setEnabledAt(0, false);
            panelNuevo.setSelectedIndex(1);
            this.accion = "editar";
            btnGuardar.setText("Editar");
        } else {
            this.errorMesagge("Seleccione un registro para editar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        if (tablaListadoCat.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 1));
            if (JOptionPane.showConfirmDialog(this, "¿Deseas desactivar la categoría: "+nombre+ "?: ","Desactivar", JOptionPane.YES_NO_OPTION)==0) {
                String resp = this.CONTROL.desactivate(Integer.parseInt(id));
                if (resp=="OK") {
                    this.successMessage("Registro desactivado");
                    this.listar("");
                }else{
                    this.errorMesagge(resp);
                }
            }
        }else{
            this.errorMesagge("Seleccione un registro para desactivar");
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (tablaListadoCat.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListadoCat.getValueAt(tablaListadoCat.getSelectedRow(), 1));
            if (JOptionPane.showConfirmDialog(this, "¿Deseas activar la categoría: "+nombre+ "?: ","Activar", JOptionPane.YES_NO_OPTION)==0) {
                String resp = this.CONTROL.activate(Integer.parseInt(id));
                if (resp=="OK") {
                    this.successMessage("Registro activado");
                    this.listar("");
                }else{
                    this.errorMesagge(resp);
                }
            }
        }else{
            this.errorMesagge("Seleccione un registro para activar");
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNumRegistro;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel panelListadoCat;
    private javax.swing.JPanel panelManteCat;
    private javax.swing.JTabbedPane panelNuevo;
    private javax.swing.JTable tablaListadoCat;
    private javax.swing.JTextField txtCatNombre;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtNewCatName;
    // End of variables declaration//GEN-END:variables
}

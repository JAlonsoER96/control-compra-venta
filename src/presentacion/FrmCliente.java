/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import negocio.ControlPersona;

/**
 *
 * @author Alonso Romero
 */
public class FrmCliente extends javax.swing.JInternalFrame {

    private final ControlPersona CONTROL;
    private String accion;
    private String nombreAnte;
    private int totalPorPagina =10;
    private int numPagina =1;
    private boolean primerCarga = true;
    private int totalRegistros;
    private boolean t;

    /**
     * Creates new form FrmCategoria
     */
    public FrmCliente() {
        initComponents();
        this.CONTROL = new ControlPersona();
        this.paginar();
        this.listar("",false);
        this.accion = "guardar";
        lblNumRegistro.setVisible(false);
    }
    private void paginar(){
        int totalPaginas;
        this.totalRegistros = this.CONTROL.showAll();
        this.totalPorPagina = Integer.parseInt((String)comboTotalPorPagina.getSelectedItem());
        totalPaginas = (int)(Math.ceil((double)this.totalRegistros/this.totalPorPagina));
        if (totalPaginas==0) {
            totalPaginas=1;
        }
        comboPagina.removeAllItems();
        for (int i = 0; i <totalPaginas; i++) {
            comboPagina.addItem(Integer.toString(i+1));
        }
        comboPagina.setSelectedIndex(0);
    }
    
    private void listar(String text, boolean paginar) {
        this.totalPorPagina = Integer.parseInt((String)comboTotalPorPagina.getSelectedItem());
        if ((String)comboPagina.getSelectedItem()!=null) {
            this.numPagina = Integer.parseInt((String)comboPagina.getSelectedItem());
        }
        if(paginar = true){
            
            tablaListProvee.setModel(this.CONTROL.listarTipo(text,this.totalPorPagina,this.numPagina,"Cliente"));
        }else{
            tablaListProvee.setModel(this.CONTROL.listarTipo(text,this.totalPorPagina,1,"Cliente"));
        }
        TableRowSorter orden = new TableRowSorter(tablaListProvee.getModel());
        tablaListProvee.setRowSorter(orden);
        lblTotalRegistros.setText("Mostrando: " + this.CONTROL.showAll() + " de un total de: " + this.CONTROL.count() + " registros");
    }
    private void limpiar() {
        this.txtNewArtName.setText("");
        this.txtMail.setText("");
        txtTelefono.setText("");
        txtDir.setText("");
        txtNumDoc.setText("");
        this.lblNumRegistro.setText("");
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
        tablaListProvee = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        comboPagina = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        comboTotalPorPagina = new javax.swing.JComboBox<>();
        panelManteCat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewArtName = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNumRegistro = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumDoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        comoTipoDoc = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");

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

        tablaListProvee.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tablaListProvee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListProvee);

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

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("# Pagína:");

        comboPagina.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPaginaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Total de registros:");

        comboTotalPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "30", "50", "100" }));
        comboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTotalPorPaginaActionPerformed(evt);
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
                        .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelListadoCatLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCatNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar))
                            .addGroup(panelListadoCatLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActivar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDesactivar)
                                    .addGroup(panelListadoCatLayout.createSequentialGroup()
                                        .addComponent(comboPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(comboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivar)
                    .addComponent(btnDesactivar))
                .addGap(28, 28, 28))
        );

        panelNuevo.addTab("Listado", panelListadoCat);

        panelManteCat.setBackground(new java.awt.Color(255, 255, 255));
        panelManteCat.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Nombre(*): ");

        txtNewArtName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtNewArtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewArtNameActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel5.setText("Email:");

        txtMail.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel8.setText("Tipo documento:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Num. Documento");

        txtNumDoc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Teléfono: ");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel12.setText("Dirección:");

        txtDir.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        comoTipoDoc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        comoTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RFC", "Cedúla", "Pasaporte" }));

        javax.swing.GroupLayout panelManteCatLayout = new javax.swing.GroupLayout(panelManteCat);
        panelManteCat.setLayout(panelManteCatLayout);
        panelManteCatLayout.setHorizontalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManteCatLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(34, 34, 34)
                                .addComponent(btnGuardar)
                                .addGap(90, 90, 90)
                                .addComponent(btnCancelar))
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addGap(336, 336, 336)
                                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))))))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManteCatLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManteCatLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(56, 56, 56)))
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewArtName, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comoTipoDoc, 0, 308, Short.MAX_VALUE)
                                .addComponent(txtTelefono)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMail, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(txtNumDoc)
                    .addComponent(txtDir))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        panelManteCatLayout.setVerticalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManteCatLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNewArtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comoTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(139, 139, 139)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(546, Short.MAX_VALUE))
        );

        panelNuevo.addTab("Mantenimiento", panelManteCat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 1494, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevo)
        );

        getAccessibleContext().setAccessibleName("Articulo");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCatNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatNombreActionPerformed

    private void btnBuscarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnBuscarStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.listar(txtCatNombre.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtNewArtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewArtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewArtNameActionPerformed

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
        if (txtNewArtName.getText().length() == 0 || txtNewArtName.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "El campo nombre es obligatorio", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNewArtName.requestFocus();
            return;
        }
        if (txtMail.getText().length()>50) {
            JOptionPane.showMessageDialog(this, "El campo email es demasiado largo", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtMail.requestFocus();
            return;
        }

        if (txtNumDoc.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "El número de documento es demasiado largo", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNumDoc.requestFocus();
            return;
        }
        if (txtDir.getText().length() > 70) {
            JOptionPane.showMessageDialog(this, "La dirección de demasiado larga", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDir.requestFocus();
            return;
        }
        if (txtTelefono.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "El teléfono debe contener no mas de 15 cararacteres", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDir.requestFocus();
            return;
        }
        String resp;
        if (this.accion.equals("editar")) {
            //Editar
            resp = this.CONTROL.update(Integer.parseInt(lblNumRegistro.getText()),"Cliente",txtNewArtName.getText(),nombreAnte,(String)comoTipoDoc.getSelectedItem(),txtNumDoc.getText(),txtDir.getText(),txtTelefono.getText(),txtMail.getText());
            if (resp.equals("OK")) {
                this.successMessage("Actuailzado Correctamente");
                this.limpiar();
                this.listar("",false);
            } else {
                this.errorMesagge(resp);
            }
        } else {
            //Guardar
            resp = this.CONTROL.insert("Cliente",txtNewArtName.getText(),(String)comoTipoDoc.getSelectedItem(),txtNumDoc.getText(),txtDir.getText(),txtTelefono.getText(),txtMail.getText());
            if (resp.equals("OK")) {
                this.successMessage("Registrado correctamente");
                this.limpiar();
                this.listar("",false);
            } else {
                this.errorMesagge(resp);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tablaListProvee.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 2));
            this.nombreAnte = nombre;
            String tipo_documento = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 3));
            String num_documento = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 4));
            String direccion = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 5));
            String telefono =String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 6));
            String email = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 7));            
            lblNumRegistro.setText(id);
            txtNewArtName.setText(nombre);
            txtMail.setText(email);
            comoTipoDoc.setSelectedItem(tipo_documento);
            txtNumDoc.setText(num_documento);
            txtTelefono.setText(telefono);
            txtDir.setText(direccion);
            txtMail.setText(email);
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
        if (tablaListProvee.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 2));
            if (JOptionPane.showConfirmDialog(this, "¿Deseas desactivar el cliente: "+nombre+ "?: ","Desactivar", JOptionPane.YES_NO_OPTION)==0) {
                String resp = this.CONTROL.desactivate(Integer.parseInt(id));
                if (resp=="OK") {
                    this.successMessage("Registro desactivado");
                    this.listar("",false);
                }else{
                    this.errorMesagge(resp);
                }
            }
        }else{
            this.errorMesagge("Seleccione un registro para desactivar");
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (tablaListProvee.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListProvee.getValueAt(tablaListProvee.getSelectedRow(), 2));
            if (JOptionPane.showConfirmDialog(this, "¿Deseas activar el cliente: "+nombre+ "?: ","Activar", JOptionPane.YES_NO_OPTION)==0) {
                String resp = this.CONTROL.activate(Integer.parseInt(id));
                if (resp=="OK") {
                    this.successMessage("Registro activado");
                    this.listar("",false);
                }else{
                    this.errorMesagge(resp);
                }
            }
        }else{
            this.errorMesagge("Seleccione un registro para activar");
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void comboPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaginaActionPerformed
        if (this.primerCarga == false) {
            this.listar("", true);
        }
    }//GEN-LAST:event_comboPaginaActionPerformed

    private void comboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        this.paginar();
    }//GEN-LAST:event_comboTotalPorPaginaActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> comboPagina;
    private javax.swing.JComboBox<String> comboTotalPorPagina;
    private javax.swing.JComboBox<String> comoTipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumRegistro;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel panelListadoCat;
    private javax.swing.JPanel panelManteCat;
    private javax.swing.JTabbedPane panelNuevo;
    private javax.swing.JTable tablaListProvee;
    private javax.swing.JTextField txtCatNombre;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNewArtName;
    private javax.swing.JTextField txtNumDoc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

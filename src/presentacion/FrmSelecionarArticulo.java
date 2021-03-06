/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entidades.Categoria;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.ControlArticulo;

/**
 *
 * @author Alonso Romero
 */
public class FrmSelecionarArticulo extends javax.swing.JDialog {

    FrmIngreso vista;
    private final ControlArticulo CONTROL;
    private String accion;
    private String nombreAnte;
    private String rutaOrigen;
    private String rutaDestino;
    private final String DIR = "src/files/articulos/";
    private String imagen = "";
    private String imagenAnt = "";
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primerCarga = true;
    private int totalRegistros;

    /**
     * Creates new form FrmSelecionarArticulo
     */
    public FrmSelecionarArticulo(java.awt.Frame parent, FrmIngreso frm, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.vista = frm ;
        this.CONTROL = new ControlArticulo();
        this.paginar();
        this.listar("", false);
        this.primerCarga = false;
        this.accion = "guardar";
        lblNumRegistro.setVisible(false);
        this.cargarCategorias();
        setTitle("Seleccionar artículo");
        this.setVisible(true);
    }

    //Listar categorias
    private void ocultarColumnas() {
        this.tablaListArt.getColumnModel().getColumn(1).setMaxWidth(0);
        this.tablaListArt.getColumnModel().getColumn(1).setMinWidth(0);
        this.tablaListArt.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        this.tablaListArt.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
    }

    private void paginar() {
        int totalPaginas;
        this.totalRegistros = this.CONTROL.showAll();
        this.totalPorPagina = Integer.parseInt((String) comboTotalPorPagina.getSelectedItem());
        totalPaginas = (int) (Math.ceil((double) this.totalRegistros / this.totalPorPagina));
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        comboPagina.removeAllItems();
        for (int i = 0; i < totalPaginas; i++) {
            comboPagina.addItem(Integer.toString(i + 1));
        }
        comboPagina.setSelectedIndex(0);
    }

    private void listar(String text, boolean paginar) {
        this.totalPorPagina = Integer.parseInt((String) comboTotalPorPagina.getSelectedItem());
        if ((String) comboPagina.getSelectedItem() != null) {
            this.numPagina = Integer.parseInt((String) comboPagina.getSelectedItem());
        }
        if (paginar = true) {

            tablaListArt.setModel(this.CONTROL.listar(text, this.totalPorPagina, this.numPagina));
        } else {
            tablaListArt.setModel(this.CONTROL.listar(text, this.totalPorPagina, 1));
        }

        TableRowSorter orden = new TableRowSorter(tablaListArt.getModel());
        tablaListArt.setRowSorter(orden);
        this.ocultarColumnas();
        lblTotalRegistros.setText("Mostrando: " + this.CONTROL.showAll() + " de un total de: " + this.CONTROL.count() + " registros");
    }

    private void cargarCategorias() {
        DefaultComboBoxModel items = CONTROL.select();
        this.comboCat.setModel(items);
    }

    public void cargarImagen() {
        File origen = new File(this.rutaOrigen);
        File destino = new File(this.rutaDestino);
        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiar() {
        this.txtNewArtName.setText("");
        this.txtDescription.setText("");
        this.txtCodigo.setText("");
        this.txtPrecioVenta.setText("");
        txtStock.setText("");
        imagen = "";
        this.lblNumRegistro.setText("");
        lblImagen.setIcon(null);
        this.rutaDestino = "";
        this.rutaOrigen = "";
        this.accion = "guardar";
        this.imagenAnt = "";
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
        tablaListArt = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        comboPagina = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        comboTotalPorPagina = new javax.swing.JComboBox<>();
        btnSeleccionar = new javax.swing.JButton();
        panelManteCat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewArtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNumRegistro = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboCat = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtStock = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        btnCargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

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

        tablaListArt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tablaListArt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListArt);

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

        btnSeleccionar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
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
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(comboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListadoCatLayout.createSequentialGroup()
                                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))))
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
                    .addComponent(btnEditar)
                    .addComponent(btnSeleccionar))
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
                .addContainerGap(76, Short.MAX_VALUE))
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

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Descripción");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
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

        jLabel5.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel5.setText("Codígo: ");

        comboCat.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel6.setText("Categoría(*): ");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel7.setText("Precio Venta(*)");

        txtPrecioVenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel8.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel8.setText("Stock(*):");

        txtStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtStock.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        jLabel9.setText("Imagen:");

        lblImagen.setBackground(new java.awt.Color(101, 189, 19));
        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCargar.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N
        btnCargar.setText("Cargar Imagen");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelManteCatLayout = new javax.swing.GroupLayout(panelManteCat);
        panelManteCat.setLayout(panelManteCatLayout);
        panelManteCatLayout.setHorizontalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManteCatLayout.createSequentialGroup()
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelManteCatLayout.createSequentialGroup()
                                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtNewArtName)
                                                    .addComponent(comboCat, 0, 308, Short.MAX_VALUE)
                                                    .addComponent(txtStock))
                                                .addGap(28, 28, 28)
                                                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel5)))
                                            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(52, 52, 52)
                                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCodigo)
                                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                            .addComponent(btnCargar)))
                                    .addComponent(jLabel4)))))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(744, 744, 744)
                        .addComponent(btnGuardar)
                        .addGap(62, 62, 62)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnCargar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(46, 46, 46)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(103, 103, 103))
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
            .addComponent(panelNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
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
        this.listar(txtCatNombre.getText(), false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        panelNuevo.setEnabledAt(1, true);
        panelNuevo.setEnabledAt(0, false);
        panelNuevo.setSelectedIndex(1);
        this.accion = "guardar";
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tablaListArt.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 0));
            int idCat = Integer.parseInt(String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 1)));
            String catAnt = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 2));
            String codigo = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 4));
            this.nombreAnte = nombre;
            String precioVenta = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 5));
            String stock = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 6));
            String descripcion = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 7));
            this.imagenAnt = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 8));

            lblNumRegistro.setText(id);
            Categoria seleccionado = new Categoria(idCat, catAnt);
            comboCat.setSelectedItem(seleccionado);
            txtCodigo.setText(codigo);
            txtNewArtName.setText(nombre);
            txtPrecioVenta.setText(precioVenta);
            txtStock.setText(stock);
            txtDescription.setText(descripcion);

            //CargarImagen
            ImageIcon im = new ImageIcon(this.DIR + this.imagenAnt);
            Icon icon = new ImageIcon(im.getImage().getScaledInstance(this.lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            this.lblImagen.setIcon(icon);
            this.lblImagen.repaint();

            panelNuevo.setEnabledAt(1, true);
            panelNuevo.setEnabledAt(0, false);
            panelNuevo.setSelectedIndex(1);
            this.accion = "editar";
            btnGuardar.setText("Editar");
        } else {
            this.errorMesagge("Seleccione un registro para editar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void comboPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaginaActionPerformed
        if (this.primerCarga == false) {
            this.listar("", true);
        }
    }//GEN-LAST:event_comboPaginaActionPerformed

    private void comboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        this.paginar();
    }//GEN-LAST:event_comboTotalPorPaginaActionPerformed

    private void txtNewArtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewArtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewArtNameActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNewArtName.getText().length() == 0 || txtNewArtName.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "El campo nombre es obligatorio", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNewArtName.requestFocus();
            return;
        }
        if (txtPrecioVenta.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio de venta", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNewArtName.requestFocus();
            return;
        }
        if (txtStock.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de stock", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNewArtName.requestFocus();
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
            String imagenActual = "";
            if (!this.imagenAnt.equals("")) {
                imagenActual = this.imagenAnt;
            } else {
                imagenActual = this.imagen;
            }
            Categoria seleccionado = (Categoria) comboCat.getSelectedItem();
            resp = this.CONTROL.update(Integer.parseInt(lblNumRegistro.getText()), seleccionado.getIdcategoria(), txtCodigo.getText(), txtNewArtName.getText(), this.nombreAnte, Double.parseDouble(txtPrecioVenta.getText()), Integer.parseInt(txtStock.getText()), txtDescription.getText(), imagenActual);
            if (resp.equals("OK")) {
                if (!this.imagen.equals("")) {
                    this.cargarImagen();
                }
                this.successMessage("Actuailzado Correctamente");
                this.limpiar();
                this.listar("", false);
            } else {
                this.errorMesagge(resp);
            }
        } else {
            //Guardar
            Categoria seleccionado = (Categoria) comboCat.getSelectedItem();
            resp = this.CONTROL.insert(seleccionado.getIdcategoria(), txtCodigo.getText(), txtNewArtName.getText(), Double.parseDouble(txtPrecioVenta.getText()), Integer.parseInt(txtStock.getText()), txtDescription.getText(), this.imagen);
            if (resp.equals("OK")) {
                if (!this.imagen.equals("")) {
                    this.cargarImagen();
                }
                this.successMessage("Registrado correctamente");
                this.limpiar();
                this.listar("", false);
            } else {
                this.errorMesagge(resp);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiar();
        panelNuevo.setEnabledAt(1, false);
        panelNuevo.setEnabledAt(0, true);
        panelNuevo.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        int estado = file.showOpenDialog(this);
        if (estado == JFileChooser.APPROVE_OPTION) {
            this.imagen = file.getSelectedFile().getName();
            this.rutaOrigen = file.getSelectedFile().getAbsolutePath();
            this.rutaDestino = DIR + this.imagen;
            ImageIcon im = new ImageIcon(this.rutaOrigen);
            Icon icon = new ImageIcon(im.getImage().getScaledInstance(this.lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            this.lblImagen.setIcon(icon);
            this.lblImagen.repaint();
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        if (tablaListArt.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 0));
            String codigo = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 4));
            String precio = String.valueOf(tablaListArt.getValueAt(tablaListArt.getSelectedRow(), 5));
            this.vista.agregarDet(id, codigo, nombre, precio);

        } else {
            errorMesagge("Debe seleccionar un artículo a los detalles");
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> comboCat;
    private javax.swing.JComboBox<String> comboPagina;
    private javax.swing.JComboBox<String> comboTotalPorPagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNumRegistro;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel panelListadoCat;
    private javax.swing.JPanel panelManteCat;
    private javax.swing.JTabbedPane panelNuevo;
    private javax.swing.JTable tablaListArt;
    private javax.swing.JTextField txtCatNombre;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtNewArtName;
    private javax.swing.JFormattedTextField txtPrecioVenta;
    private javax.swing.JFormattedTextField txtStock;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.ControlIngreso;
import negocio.ControlPersona;

/**
 *
 * @author Alonso Romero
 */
public class FrmIngreso extends javax.swing.JInternalFrame {

    private final ControlIngreso CONTROL;
    private String accion;
    private String nombreAnte;
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primerCarga = true;
    private int totalRegistros;
    private boolean t;
    public DefaultTableModel modeloDetalles;
    public JFrame contenedor;

    /**
     * Creates new form FrmCategoria
     */
    public FrmIngreso(JFrame frmp) {
        initComponents();
        this.contenedor = frmp;
        this.CONTROL = new ControlIngreso();
        this.paginar();
        this.listar("", false);
        this.accion = "guardar";
        lblNumRegistro.setVisible(false);
        this.genDet();
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

            tablaListIngreso.setModel(this.CONTROL.listar(text, this.totalPorPagina, this.numPagina));
        } else {
            tablaListIngreso.setModel(this.CONTROL.listar(text, this.totalPorPagina, 1));
        }
        TableRowSorter orden = new TableRowSorter(tablaListIngreso.getModel());
        this.ocultarColumnas();
        tablaListIngreso.setRowSorter(orden);
        lblTotalRegistros.setText("Mostrando: " + this.CONTROL.showAll() + " de un total de: " + this.CONTROL.count() + " registros");
    }

    private void genDet() {
        modeloDetalles = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return column == 3;
                }
                if (column == 4) {
                    return column == 4;
                }
                return column == 3;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (column == 5) {
                    Double cantd;
                    try {
                        cantd = Double.parseDouble((String) getValueAt(row, 3));
                    } catch (Exception e) {
                        cantd = 1.0;
                    }
                    Double preciod = Double.parseDouble((String) getValueAt(row, 4));
                    if (cantd != null && preciod != null) {
                        return String.format("%.2f", (cantd * preciod));
                    } else {
                        return 0;
                    }
                }
                return super.getValueAt(row, column);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column); //To change body of generated methods, choose Tools | Templates.
                calcularTotales();
                fireTableDataChanged();
            }

        };
        modeloDetalles.setColumnIdentifiers(new Object[]{"ID", "Codigo", "Articulo", "Cantidad", "Precio", "Subtotal"});
        tableDetalles.setModel(modeloDetalles);
    }

    public void agregarDet(String id, String codigo, String nombre, String precio) {
        String idTem;
        boolean existe = false;
        for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
            idTem = String.valueOf(modeloDetalles.getValueAt(i, 0));
            if (idTem.equals(id)) {
                existe = true;
            }
        }
        if (existe) {
            errorMesagge("El artículo ya ha sido agregado");
        } else {
            this.modeloDetalles.addRow(new Object[]{id, codigo, nombre, "1", precio, precio});
            this.calcularTotales();
        }
    }

    private void calcularTotales() {
        double total = 0;
        double sub;
        int items = modeloDetalles.getRowCount();
        if (items == 0) {
            total = 0;
        } else {
            for (int i = 0; i < items; i++) {
                total = total + Double.parseDouble(String.valueOf(modeloDetalles.getValueAt(i, 5)));
            }
        }
        sub = total / (1 + Double.parseDouble(txtImpuesto.getText()));
        txtTotal.setText(String.format("%.2f", total));
        txtSub.setText(String.format("%.2f", sub));
        txtTotImp.setText(String.format("%.2f", (total - sub)));
    }

    private void limpiar() {
        txtNomProv.setText("");
        txtIdProv.setText("");
        txtSerie.setText("");
        txtNumero.setText("");
        txtImpuesto.setText("0.16");
        txtTotal.setText("0.00");
        txtSub.setText("0.00");
        txtTotImp.setText("0.00");
        this.lblNumRegistro.setText("");
        this.accion = "guardar";
        this.genDet();
        btnGuardar.setVisible(true);
    }

    private void errorMesagge(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void successMessage(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ocultarColumnas() {
        //Ocultar ID persona
        this.tablaListIngreso.getColumnModel().getColumn(1).setMaxWidth(0);
        this.tablaListIngreso.getColumnModel().getColumn(1).setMinWidth(0);
        this.tablaListIngreso.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        this.tablaListIngreso.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        //Ocultar ID usuario
        this.tablaListIngreso.getColumnModel().getColumn(2).setMaxWidth(0);
        this.tablaListIngreso.getColumnModel().getColumn(2).setMinWidth(0);
        this.tablaListIngreso.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        this.tablaListIngreso.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
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
        tablaListIngreso = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        comboPagina = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        comboTotalPorPagina = new javax.swing.JComboBox<>();
        btnDetalles = new javax.swing.JButton();
        panelManteCat = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNumRegistro = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdProv = new javax.swing.JTextField();
        txtNomProv = new javax.swing.JTextField();
        btnSelProv = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtImpuesto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboCompro = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnVerArt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetalles = new javax.swing.JTable();
        txtSub = new javax.swing.JTextField();
        txtTotImp = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDelDet = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingresos Almacén");

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

        tablaListIngreso.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tablaListIngreso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListIngreso);

        lblTotalRegistros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnDesactivar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDesactivar.setText("Anular");
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

        btnDetalles.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnDetalles.setText("Ver detalle");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
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
                                .addComponent(btnNuevo))
                            .addGroup(panelListadoCatLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(comboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(btnDesactivar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                        .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDetalles, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                    .addComponent(btnDetalles))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addGroup(panelListadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(comboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDesactivar))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        panelNuevo.addTab("Listado", panelListadoCat);

        panelManteCat.setBackground(new java.awt.Color(255, 255, 255));
        panelManteCat.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 16)); // NOI18N

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

        jLabel6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Proveedor(*):");

        txtIdProv.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        txtNomProv.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        btnSelProv.setText("...");
        btnSelProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelProvActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Impuesto: ");

        txtImpuesto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtImpuesto.setText("0.16");
        txtImpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImpuestoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Tipo Comprobante(*): ");

        comboCompro.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        comboCompro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOLETA", "TICKET", "FACTURA" }));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Serie:");

        txtSerie.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Numero:");

        txtNumero.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Artículo:");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnVerArt.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnVerArt.setText("Ver");
        btnVerArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerArtActionPerformed(evt);
            }
        });

        tableDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableDetalles);

        txtSub.setEditable(false);
        txtSub.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSub.setForeground(new java.awt.Color(255, 0, 0));

        txtTotImp.setEditable(false);
        txtTotImp.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotImp.setForeground(new java.awt.Color(255, 0, 0));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Sub Total");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total impuesto");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Total");

        btnDelDet.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnDelDet.setText("Quitar");
        btnDelDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelDetActionPerformed(evt);
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
                        .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(34, 34, 34)
                        .addComponent(btnGuardar)
                        .addGap(90, 90, 90)
                        .addComponent(btnCancelar))
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addComponent(txtIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomProv))
                            .addComponent(txtCodigo)
                            .addComponent(comboCompro, 0, 328, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerArt)
                            .addGroup(panelManteCatLayout.createSequentialGroup()
                                .addComponent(btnSelProv)
                                .addGap(131, 131, 131)
                                .addComponent(jLabel1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addGap(594, 594, 594)
                        .addComponent(btnDelDet, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addGroup(panelManteCatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelManteCatLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(497, 497, 497))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManteCatLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSub, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(txtTotImp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        panelManteCatLayout.setVerticalGroup(
            panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelManteCatLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelProv)
                    .addComponent(jLabel1)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboCompro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerArt)
                    .addComponent(btnDelDet))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelManteCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(156, 156, 156))
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
        this.listar(txtCatNombre.getText(), false);
    }//GEN-LAST:event_btnBuscarActionPerformed

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
        if (txtIdProv.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor.", "Sistema", JOptionPane.WARNING_MESSAGE);
            btnSelProv.requestFocus();
            return;
        }
        if (txtSerie.getText().length() > 7) {
            JOptionPane.showMessageDialog(this, "El número de serie no es valido.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtSerie.requestFocus();
            return;
        }
        if (txtNumero.getText().length() == 0 || txtNumero.getText().length() > 10) {
            JOptionPane.showMessageDialog(this, "El número de comprobante no es valido.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNumero.requestFocus();
            return;
        }
        if (modeloDetalles.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Debé agregar al menos un artículo a los detalles.", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String resp;
        //Guardar
        resp = this.CONTROL.insert(Integer.parseInt(txtIdProv.getText()),(String)comboCompro.getSelectedItem(),txtSerie.getText(),txtNumero.getText(),Double.parseDouble(txtImpuesto.getText()),Double.parseDouble(txtTotal.getText()),modeloDetalles);
        if (resp.equals("OK")) {
            this.successMessage("Registrado correctamente");
            this.limpiar();
            this.listar("", false);
        } else {
            this.errorMesagge(resp);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        if (tablaListIngreso.getSelectedRowCount() == 1) {
            String id = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 0));
            String comprobante = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 5));
            String serie = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 6));
            String numero = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 7));
            if (JOptionPane.showConfirmDialog(this, "¿Deseas anular el ingreso: " + comprobante +" "+serie+"-"+numero+ "?: ", "Anular", JOptionPane.YES_NO_OPTION) == 0) {
                String resp = this.CONTROL.desactivate(Integer.parseInt(id));
                if (resp == "OK") {
                    this.successMessage("Registro anulado");
                    this.listar("", false);
                } else {
                    this.errorMesagge(resp);
                }
            }
        } else {
            this.errorMesagge("Seleccione un registro para desactivar");
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void comboPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaginaActionPerformed
        if (this.primerCarga == false) {
            this.listar("", true);
        }
    }//GEN-LAST:event_comboPaginaActionPerformed

    private void comboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        this.paginar();
    }//GEN-LAST:event_comboTotalPorPaginaActionPerformed

    private void btnSelProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelProvActionPerformed
        FrmSeleccionarProveedor frm = new FrmSeleccionarProveedor(contenedor, this, true);
        frm.toFront();
    }//GEN-LAST:event_btnSelProvActionPerformed

    private void btnVerArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerArtActionPerformed
        FrmSelecionarArticulo frm = new FrmSelecionarArticulo(contenedor, this, true);
        frm.toFront();
    }//GEN-LAST:event_btnVerArtActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (txtCodigo.getText().length() > 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                entidades.Articulo art;
                art = CONTROL.obtenerPorCod(txtCodigo.getText());
                if (art == null) {
                    errorMesagge("El codigo no existe");
                } else {
                    agregarDet(Integer.toString(art.getIdarticulo()), art.getCodigo(), art.getNombre(), Double.toString(art.getPrecio_venta()));
                }
            }
        } else {
            errorMesagge("La caja de texto para el código esta vacia");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnDelDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDetActionPerformed
        if (tableDetalles.getSelectedRowCount() == 1) {
            modeloDetalles.removeRow(tableDetalles.getSelectedRow());
            this.calcularTotales();
        } else {
            errorMesagge("Seleccione un detalle a eliminar");
        }
    }//GEN-LAST:event_btnDelDetActionPerformed

    private void txtImpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImpuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpuestoActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        if(tablaListIngreso.getSelectedRowCount()==1){
            String id = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 0));
            String idPro = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 3));
            String nombrePro = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 4));
            String tipoComprobante = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 5));
            String serie = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 6));
            String numero = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 7));
            String impuesto = String.valueOf(tablaListIngreso.getValueAt(tablaListIngreso.getSelectedRow(), 9));
            
            txtIdProv.setText(idPro);
            txtNomProv.setText(nombrePro);
            comboCompro.setSelectedItem(tipoComprobante);
            txtSerie.setText(serie);
            txtNumero.setText(numero);
            txtImpuesto.setText(impuesto);
            this.modeloDetalles = CONTROL.listarDetalle(Integer.parseInt(id));
            tableDetalles.setModel(modeloDetalles);
            this.calcularTotales();
            panelNuevo.setEnabledAt(1, true);
            panelNuevo.setEnabledAt(0, false);
            panelNuevo.setSelectedIndex(1);
            btnGuardar.setVisible(false);
        }else{
           this.errorMesagge("Seleccione un ingreso");
        }
    }//GEN-LAST:event_btnDetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDelDet;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSelProv;
    private javax.swing.JButton btnVerArt;
    private javax.swing.JComboBox<String> comboCompro;
    private javax.swing.JComboBox<String> comboPagina;
    private javax.swing.JComboBox<String> comboTotalPorPagina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel lblNumRegistro;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel panelListadoCat;
    private javax.swing.JPanel panelManteCat;
    private javax.swing.JTabbedPane panelNuevo;
    private javax.swing.JTable tablaListIngreso;
    private javax.swing.JTable tableDetalles;
    private javax.swing.JTextField txtCatNombre;
    private javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtIdProv;
    private javax.swing.JTextField txtImpuesto;
    public javax.swing.JTextField txtNomProv;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtSub;
    private javax.swing.JTextField txtTotImp;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

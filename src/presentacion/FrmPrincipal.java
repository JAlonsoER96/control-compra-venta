/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;
import java.awt.*;
/**
 *
 * @author Alonso Romero
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Control Compra - Venta");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principal =  new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }};
            escritorio = new javax.swing.JMenuBar();
            mnuAlmacen = new javax.swing.JMenu();
            mnuItemCategoria = new javax.swing.JMenuItem();
            mnuItemArticulo = new javax.swing.JMenuItem();
            mnuCompra = new javax.swing.JMenu();
            mnuItemProveedor = new javax.swing.JMenuItem();
            mnuItemIngreso = new javax.swing.JMenuItem();
            mnuVentas = new javax.swing.JMenu();
            mnuItemCliente = new javax.swing.JMenuItem();
            mnuItemVenta = new javax.swing.JMenuItem();
            mnuConsulta = new javax.swing.JMenu();
            mnuItemCI = new javax.swing.JMenuItem();
            mnuItemCV = new javax.swing.JMenuItem();
            mnuAcceso = new javax.swing.JMenu();
            mnuItemRol = new javax.swing.JMenuItem();
            mnuItemUsuario = new javax.swing.JMenuItem();
            mnuItemSalir = new javax.swing.JMenu();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(java.awt.Color.white);

            principal.setBackground(new java.awt.Color(255, 255, 255));

            mnuAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/almacen.png"))); // NOI18N
            mnuAlmacen.setText("Almacen");
            mnuAlmacen.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuAlmacenActionPerformed(evt);
                }
            });

            mnuItemCategoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
            mnuItemCategoria.setText("Categorias");
            mnuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemCategoriaActionPerformed(evt);
                }
            });
            mnuAlmacen.add(mnuItemCategoria);

            mnuItemArticulo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
            mnuItemArticulo.setText("Artículos");
            mnuItemArticulo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemArticuloActionPerformed(evt);
                }
            });
            mnuAlmacen.add(mnuItemArticulo);

            escritorio.add(mnuAlmacen);

            mnuCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/compras.png"))); // NOI18N
            mnuCompra.setText("Compras");

            mnuItemProveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
            mnuItemProveedor.setText("Proveedores");
            mnuItemProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemProveedorActionPerformed(evt);
                }
            });
            mnuCompra.add(mnuItemProveedor);

            mnuItemIngreso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
            mnuItemIngreso.setText("Ingresos");
            mnuCompra.add(mnuItemIngreso);

            escritorio.add(mnuCompra);

            mnuVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/ventas.png"))); // NOI18N
            mnuVentas.setText("Ventas");

            mnuItemCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
            mnuItemCliente.setText("Cliente");
            mnuVentas.add(mnuItemCliente);

            mnuItemVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
            mnuItemVenta.setText("Ventas");
            mnuItemVenta.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemVentaActionPerformed(evt);
                }
            });
            mnuVentas.add(mnuItemVenta);

            escritorio.add(mnuVentas);

            mnuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/consultas.png"))); // NOI18N
            mnuConsulta.setText("Consultas");

            mnuItemCI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
            mnuItemCI.setText("Consulta Ingresos");
            mnuConsulta.add(mnuItemCI);

            mnuItemCV.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
            mnuItemCV.setText("Consulta Ventas");
            mnuConsulta.add(mnuItemCV);

            escritorio.add(mnuConsulta);

            mnuAcceso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/acceso.png"))); // NOI18N
            mnuAcceso.setText("Accesos");

            mnuItemRol.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
            mnuItemRol.setText("Roles");
            mnuItemRol.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemRolActionPerformed(evt);
                }
            });
            mnuAcceso.add(mnuItemRol);

            mnuItemUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
            mnuItemUsuario.setText("Usuario");
            mnuAcceso.add(mnuItemUsuario);

            escritorio.add(mnuAcceso);

            mnuItemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/salir.png"))); // NOI18N
            mnuItemSalir.setText("Salir");
            mnuItemSalir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mnuItemSalirActionPerformed(evt);
                }
            });
            escritorio.add(mnuItemSalir);

            setJMenuBar(escritorio);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void mnuItemArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuItemArticuloActionPerformed

    private void mnuItemProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuItemProveedorActionPerformed

    private void mnuItemVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuItemVentaActionPerformed

    private void mnuItemRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuItemRolActionPerformed

    private void mnuAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlmacenActionPerformed

    }//GEN-LAST:event_mnuAlmacenActionPerformed

    private void mnuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuItemSalirActionPerformed

    private void mnuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoriaActionPerformed
        FrmCategoria frmc = new FrmCategoria();
        principal.add(frmc);
        frmc.setVisible(true);
    }//GEN-LAST:event_mnuItemCategoriaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar escritorio;
    private javax.swing.JMenu mnuAcceso;
    private javax.swing.JMenu mnuAlmacen;
    private javax.swing.JMenu mnuCompra;
    private javax.swing.JMenu mnuConsulta;
    private javax.swing.JMenuItem mnuItemArticulo;
    private javax.swing.JMenuItem mnuItemCI;
    private javax.swing.JMenuItem mnuItemCV;
    private javax.swing.JMenuItem mnuItemCategoria;
    private javax.swing.JMenuItem mnuItemCliente;
    private javax.swing.JMenuItem mnuItemIngreso;
    private javax.swing.JMenuItem mnuItemProveedor;
    private javax.swing.JMenuItem mnuItemRol;
    private javax.swing.JMenu mnuItemSalir;
    private javax.swing.JMenuItem mnuItemUsuario;
    private javax.swing.JMenuItem mnuItemVenta;
    private javax.swing.JMenu mnuVentas;
    private javax.swing.JDesktopPane principal;
    // End of variables declaration//GEN-END:variables

}
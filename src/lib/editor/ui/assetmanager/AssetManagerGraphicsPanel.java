/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author gaetan
 */
public class AssetManagerGraphicsPanel extends javax.swing.JPanel {

    /**
     * Creates new form AssetManagerGraphicsPanel
     */
    public AssetManagerGraphicsPanel() {
        initComponents();
    }

    public void setAsset(File file){
        setVisible(true);
        
        ImageIcon  image = new ImageIcon (file.getAbsolutePath());
        jLabel1.setText(null);
        jLabel1.setIcon(image);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane1.setViewportView(jLabel1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
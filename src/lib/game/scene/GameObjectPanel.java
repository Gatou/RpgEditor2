/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.scene;

import java.awt.event.ItemEvent;
import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.AbstractDatabasePanel;
import lib.editor.widget.inspector.InspectorPanel;

/**
 *
 * @author Gaetan
 */
public class GameObjectPanel extends InspectorPanel  {

    private static GameObjectPanel instance;
    
    private GameObjectPanel() {
        super("Game Object", "object.png");
        initComponents();
    }
    
    public static InspectorPanel instance() {
        if(instance == null){
            instance = new GameObjectPanel();
        }
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        visibleCheckBox = new javax.swing.JCheckBox();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout());

        visibleCheckBox.setText("Visible");
        visibleCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visibleCheckBoxItemStateChanged(evt);
            }
        });
        jPanel3.add(visibleCheckBox);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void visibleCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visibleCheckBoxItemStateChanged
        ((GameObject) data).visible = evt.getStateChange() == ItemEvent.SELECTED;
    }//GEN-LAST:event_visibleCheckBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox visibleCheckBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setup(AbstractDatabasePanel dataPanel, AbstractData data) {
        super.setup(dataPanel, data);
        
    }
    
    @Override
    public void refresh() {
        refreshing = true;
        
        GameObject gameObject = (GameObject) data;
        
        visibleCheckBox.setSelected(gameObject.visible);
        
        refreshing = false;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.component;

import java.awt.Dimension;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.AbstractDatabasePanel;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class TransformPanel extends InspectorPanel {

    private static TransformPanel instance;
    
    private TransformPanel() {
        super("Transform", "transform.png");
        initComponents();
        
        positionXSpinner.setModel(new SpinnerNumberModel(0, -999999999, 999999999, 1f));
        positionXSpinner.setMinimumSize(new Dimension(0, 0));
        positionXSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) positionXSpinner.getValue();
                comp.setX((float) value);
            }
        });
        positionYSpinner.setModel(new SpinnerNumberModel(0, -999999999, 999999999, 1f));
        positionYSpinner.setMinimumSize(new Dimension(0, 0));
        positionYSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) positionYSpinner.getValue();
                comp.setY((float) value);
            }
        });
        
        originXSpinner.setModel(new SpinnerNumberModel(0, -999999999, 999999999, 1f));
        originXSpinner.setMinimumSize(new Dimension(0, 0));
        originXSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) originXSpinner.getValue();
                comp.setOrigin((float) value, comp.getOriginY());
            }
        });
        originYSpinner.setModel(new SpinnerNumberModel(0, -999999999, 999999999, 1f));
        originYSpinner.setMinimumSize(new Dimension(0, 0));
        originYSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) originYSpinner.getValue();
                comp.setOrigin(comp.getOriginX(), (float) value);
            }
        });
        
        rotationSpinner.setModel(new SpinnerNumberModel(0, -999999999, 999999999, 1f));
        rotationSpinner.setMinimumSize(new Dimension(0, 0));
        rotationSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) rotationSpinner.getValue();
                comp.setRotation((float) value);
            }
        });
        
        scaleXSpinner.setModel(new SpinnerNumberModel(1, -999999999, 999999999, 0.1f));
        scaleXSpinner.setMinimumSize(new Dimension(0, 0));
        scaleXSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) scaleXSpinner.getValue();
                comp.setScaleX((float) value);
            }
        });
        scaleYSpinner.setModel(new SpinnerNumberModel(1, -999999999, 999999999, 0.1f));
        scaleYSpinner.setMinimumSize(new Dimension(0, 0));
        scaleYSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(refreshing){return;}
                TransformComponent comp = (TransformComponent) currentGameObject().getComponent("TransformComponent");
                double value = (Double) scaleYSpinner.getValue();
                comp.setScaleY((float) value);
            }
        });
    }

    public static TransformPanel instance(){
        if(instance == null){
            instance = new TransformPanel();
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
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        textPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        positionXSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.0");
            }
        };
        textPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        positionYSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.0");
            }
        };
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        textPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        originXSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.0");
            }
        };
        textPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        originYSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.0");
            }
        };
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        textPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        rotationSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.0");
            }
        };
        textPanel5 = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(32767, 4));
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        textPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        scaleXSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.000");
            }
        };
        textPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        scaleYSpinner = new javax.swing.JSpinner(){
            @Override
            protected NumberEditor createEditor(SpinnerModel model) {
                return new NumberEditor(this, "0.000");
            }
        };

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Position");
        jPanel3.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        textPanel1.setLayout(new javax.swing.BoxLayout(textPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText("X");
        jLabel4.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel4.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel1.add(jLabel4);

        positionXSpinner.setMaximumSize(null);
        positionXSpinner.setMinimumSize(null);
        textPanel1.add(positionXSpinner);

        jPanel4.add(textPanel1);

        textPanel3.setLayout(new javax.swing.BoxLayout(textPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Y");
        jLabel7.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel3.add(jLabel7);

        positionYSpinner.setMaximumSize(null);
        positionYSpinner.setMinimumSize(null);
        textPanel3.add(positionYSpinner);

        jPanel4.add(textPanel3);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel3);
        add(filler1);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Origin");
        jPanel9.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        textPanel2.setLayout(new javax.swing.BoxLayout(textPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setText("X");
        jLabel11.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel11.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel11.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel2.add(jLabel11);

        originXSpinner.setMaximumSize(null);
        originXSpinner.setMinimumSize(null);
        originXSpinner.setPreferredSize(null);
        textPanel2.add(originXSpinner);

        jPanel10.add(textPanel2);

        textPanel8.setLayout(new javax.swing.BoxLayout(textPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Y");
        jLabel12.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel12.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel12.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel8.add(jLabel12);

        originYSpinner.setMaximumSize(null);
        originYSpinner.setMinimumSize(null);
        originYSpinner.setPreferredSize(null);
        textPanel8.add(originYSpinner);

        jPanel10.add(textPanel8);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        add(jPanel9);
        add(filler2);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Rotation");
        jPanel5.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        textPanel4.setLayout(new javax.swing.BoxLayout(textPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jLabel13.setText(" ");
        jLabel13.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel13.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel13.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel4.add(jLabel13);

        rotationSpinner.setMaximumSize(null);
        rotationSpinner.setMinimumSize(null);
        rotationSpinner.setPreferredSize(null);
        textPanel4.add(rotationSpinner);

        jPanel6.add(textPanel4);

        textPanel5.setLayout(new javax.swing.BoxLayout(textPanel5, javax.swing.BoxLayout.LINE_AXIS));
        jPanel6.add(textPanel5);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        add(jPanel5);
        add(filler3);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Scale");
        jPanel7.add(jLabel8, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        textPanel6.setLayout(new javax.swing.BoxLayout(textPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setText("X");
        jLabel9.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel6.add(jLabel9);

        scaleXSpinner.setMaximumSize(null);
        scaleXSpinner.setMinimumSize(null);
        scaleXSpinner.setPreferredSize(null);
        textPanel6.add(scaleXSpinner);

        jPanel8.add(textPanel6);

        textPanel7.setLayout(new javax.swing.BoxLayout(textPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Y");
        jLabel10.setMaximumSize(new java.awt.Dimension(14, 14));
        jLabel10.setMinimumSize(new java.awt.Dimension(14, 14));
        jLabel10.setPreferredSize(new java.awt.Dimension(14, 14));
        textPanel7.add(jLabel10);

        scaleYSpinner.setMaximumSize(null);
        scaleYSpinner.setMinimumSize(null);
        scaleYSpinner.setPreferredSize(null);
        textPanel7.add(scaleYSpinner);

        jPanel8.add(textPanel7);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(jPanel7);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner originXSpinner;
    private javax.swing.JSpinner originYSpinner;
    private javax.swing.JSpinner positionXSpinner;
    private javax.swing.JSpinner positionYSpinner;
    private javax.swing.JSpinner rotationSpinner;
    private javax.swing.JSpinner scaleXSpinner;
    private javax.swing.JSpinner scaleYSpinner;
    private javax.swing.JPanel textPanel1;
    private javax.swing.JPanel textPanel2;
    private javax.swing.JPanel textPanel3;
    private javax.swing.JPanel textPanel4;
    private javax.swing.JPanel textPanel5;
    private javax.swing.JPanel textPanel6;
    private javax.swing.JPanel textPanel7;
    private javax.swing.JPanel textPanel8;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setup(AbstractDatabasePanel dataPanel, AbstractData data) {
        super.setup(dataPanel, data);
        
    }
    
    @Override
    public void refresh() {
        refreshing = true;
        
        TransformComponent comp = (TransformComponent) ((GameObject) data).getComponent("TransformComponent");
        
        positionXSpinner.setValue(comp.getX());
        positionYSpinner.setValue(comp.getY());
        
        originXSpinner.setValue(comp.getOriginX());
        originYSpinner.setValue(comp.getOriginY());
        
        rotationSpinner.setValue(comp.getRotation());
        
        scaleXSpinner.setValue(comp.getScaleX());
        scaleYSpinner.setValue(comp.getScaleY());
        
        refreshing = false;
    }
    
    public GameObject currentGameObject(){
        return (GameObject) data;
    }
    
}

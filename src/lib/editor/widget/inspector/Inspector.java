/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.AbstractDatabasePanel;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author gaetan
 */
public class Inspector {
    
    private static Inspector middleInspector;
    private static Inspector rightInspector;
    
    public static Inspector getMiddleInspector(){
        if(middleInspector == null){
            middleInspector = new Inspector(new JXTaskPaneContainer());
        }
        return middleInspector;
    }
    
    public static Inspector getRightInspector(){
        if(rightInspector == null){
            rightInspector = new Inspector(new JXTaskPaneContainer());
        }
        return rightInspector;
    }
    
    public JScrollPane scrollPane;
    public JXTaskPaneContainer container;
    //public AbstractDatabasePanel dataPanel;
    List<InspectorPanel> panels;
    //public Map<String, InspectorPanel> panels;
    //private int mode;
    
    private Inspector(JXTaskPaneContainer container){
        this.container = container;
        this.scrollPane = new JScrollPane(container);
        
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        
        container.setBorder(new EmptyBorder(6, 6, 6, 6));
        //container.setBackground(Color.red);
        //scrollPane.setViewportView(container);
        scrollPane.setPreferredSize(new Dimension(240, 0));
        //container.setMinimumSize(new Dimension(240, 240));
        //panels = new Hashtable<String, InspectorPanel>();
        //panels.put("property", new PropertyPanel(container));
        //panels.put("map", new MapSizePanel(container));
        
        hide();
    }
    
    
    public void hide(){
        container.removeAll();
        if(panels != null){
            panels.clear();
            panels = null;
        }
        
        //container.repaint();
        //mode = Inspector.Mode.Null;
        //container.removeAll();
        //panels.get("property").setVisible(false, null);
        //panels.get("map").setVisible(false, null);
    }
    
    public void setup(AbstractDatabasePanel dataPanel, AbstractData data) {
        hide();
        if(data != null){
            panels = data.createInspectorPanels();
            container.updateUI();
            for(InspectorPanel panel : panels){
                panel.setup(dataPanel, data);
                panel.refresh();
            }
        }
    }
    

    
}

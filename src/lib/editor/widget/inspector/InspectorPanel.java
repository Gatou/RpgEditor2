/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Color;
import javax.swing.JPanel;
import lib.editor.data.game.AbstractData;
import lib.editor.mgr.IconManager;
import lib.editor.ui.data.AbstractDatabasePanel;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author gaetan
 */
public abstract class InspectorPanel extends JPanel implements IInspectorPanel{
    
    public static final int LEFT_COLUMN_WIDTH = 70;
    
    public JXTaskPane collapsible;
    protected boolean refreshing;
    
    public AbstractDatabasePanel dataPanel;
    public AbstractData data;
    
    public InspectorPanel(String title, String iconFilename){

        
        collapsible = new JXTaskPane();
        //collapsible.setPreferredSize(new Dimension(160, 50));
        //collapsible.setMinimumSize(getPreferredSize());
        collapsible.setIcon(IconManager.instance().getSystemIcon(iconFilename, false));//new javax.swing.ImageIcon(getClass().getResource("/assets/icons/project_root.png")));
        collapsible.setScrollOnExpand(true);
        collapsible.setSpecial(true);
        collapsible.setTitle(title);
        collapsible.add(this);
        
        refreshing = false;
        
    }

    
    /*
    public void setVisible(boolean visible, AbstractData data){
        this.data = data;
        if(visib
    AbstractData data;
    
    public InspectorPanel(Inspector inspector, String title, String iconFilename){

        
        collapsible = new JXTaskPane();
        //collapsible.setPreferredSize(new Dimension(160, 50));
        //collapsible.setMinimumSize(getPreferredSize());
        collapsible.setIcon(Mgr.icon.getSystemIcon(iconFilename, false));//new javax.swing.ImageIcon(getClass().getResource("/assets/icons/project_root.png")));
        collapsible.setScrollOnExpand(true);
        collapsible.setSpecial(true);
        collapsible.setTitle(title);
        collapsible.add(this);
        
        inspector.container.add(collapsible);
        refreshing = false;
        
        
    }

    public void refresh(){
    }
    
    /*
    public void le){
            refresh();
        }
        collapsible.setVisible(visible);
        super.setVisible(visible);
    }*/

    public void setup(AbstractDatabasePanel dataPanel, AbstractData data) {
        this.dataPanel = dataPanel;
        this.data = data;
    }
     
    public void setInspector(Inspector inspector){
        inspector.container.add(collapsible);
    }
}

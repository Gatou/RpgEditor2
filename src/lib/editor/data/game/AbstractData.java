package lib.editor.data.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.inspector.InspectorPanel;
import lib.editor.widget.inspector.PropertyPanel;



public class AbstractData implements Serializable{
    
    private static final long serialVersionUID = 42L;
    
    public int id;
    public String name;
    
    public AbstractData(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public String getIdName(){
        if(id < 10){
            return "000" + id;
        }
        else if(id >= 10 && id < 100){
            return "00" + id;
        }
        else if(id >= 100 && id < 1000){
            return "0" + id;
        }
        else{
            return "" + id;
        }
    }
    
    public List<InspectorPanel> createInspectorPanels(){
        List<InspectorPanel> panels = new ArrayList<InspectorPanel>();
        panels.add(new PropertyPanel(Inspector.getRightInspector()));
        return panels;
    }
    
}

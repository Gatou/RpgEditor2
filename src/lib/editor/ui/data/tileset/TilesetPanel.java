/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.tileset;

import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.widget.inspector.Inspector;

/**
 *
 * @author gaetan
 */
public class TilesetPanel extends DatabasePanel{
    
    @Override
    public void setup(String dataName, Class dataClass){
        super.setup(dataName, dataClass);

    }
    
    @Override
    public void selected(){
        super.selected();
    }
    
    @Override
    public void dataChanged(AbstractData data){
        super.dataChanged(data);
        //Inspector.getRightInspector().setup(this, data);
        //System.out.println(data.name);
    }
}

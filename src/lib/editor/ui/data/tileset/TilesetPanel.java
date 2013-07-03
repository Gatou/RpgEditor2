/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.tileset;

import lib.editor.data.game.AbstractData;
import lib.editor.data.game.TilesetData;
import lib.editor.ui.data.DatabasePanel;

/**
 *
 * @author gaetan
 */
public class TilesetPanel extends DatabasePanel{
    
    public TilesetMiddlePanel middleTilesetPanel;
    
    public TilesetPanel() {
        middleTilesetPanel = new TilesetMiddlePanel();
        
        //Inspector.getMiddleInspector()
    }
    
    public void selected(){
        super.selected();
        
        middlePanel.removeAll();
        middlePanel.add(middleTilesetPanel);
    }
    
    @Override
    public void setup(String dataName, Class dataClass){
        super.setup(dataName, dataClass);

    }
    
    @Override
    public void dataChanged(AbstractData data){
        
        //System.out.println(data);
        super.dataChanged(data);
        //middlePanel.removeAll();
        //Inspector.getRightInspector().setup(this, data);
        //System.out.println(data.name);
        if(data instanceof TilesetData){
            //System.out.println(data);
            //middlePanel.add(middleTilesetPanel);
            middleTilesetPanel.setup(((TilesetData) data).tilesetFilenames);
            //System.out.println("Salut");
            //System.out.println(middleTilesetPanel.getComponent(1).getName());
        }
        else{
            middleTilesetPanel.setup(null);
        }
        //middlePanel.updateUI();
    }
}

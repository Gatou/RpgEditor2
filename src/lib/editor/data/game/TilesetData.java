/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.data.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaetan
 */
public class TilesetData extends AbstractData{

    public List<String> tilesetFilenames;
    
    
    public TilesetData(){
        this(-1, "");
    }
    
    public TilesetData(int id, String name) {
        super(id, name);
        tilesetFilenames = new ArrayList<String>();
        tilesetFilenames.add("Salut");
    }
    
    public void update(TilesetData oldData){
        this.tilesetFilenames = new ArrayList<String>(oldData.tilesetFilenames);
        
    }
    
}

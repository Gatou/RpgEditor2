/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.tileset;

import java.util.List;
import lib.editor.widget.list.item.ListItem;
import lib.editor.widget.list.list.DatabaseList;

/**
 *
 * @author gaetan
 */
public class TilesetList extends DatabaseList<String>{

    public TilesetList(){
    }
    
    public void setup(List<String> tilesetFilenames){
       super.setup(tilesetFilenames);
    }
    
    public ListItem makeItem(String tilesetFilename){
        ListItem item = new ListItem(tilesetFilename, null);
        return item;
    }
}

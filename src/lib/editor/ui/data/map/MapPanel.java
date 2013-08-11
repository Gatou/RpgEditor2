/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map;

import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;

/**
 *
 * @author gaetan
 */
public class MapPanel extends DatabasePanel{
    
    SceneTree sceneTree;
    
    public MapPanel() {
        MapEditorGraphicsView mapEditor = MapEditorGraphicsView.getInstance();
        middlePanel.add(mapEditor.getCanvas());
        
        sceneTree = new SceneTree();
    }
    
    
    //public void setup(String dataName, DataMa){
    //    super.setup(dataName, );
        //databaseTree.setup(dataName);
    //}
    
    @Override
    public void dataChanged(AbstractData data){
        super.dataChanged(data);
    }
}

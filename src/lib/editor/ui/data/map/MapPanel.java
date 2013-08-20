/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map;

import javax.swing.JScrollPane;
import lib.editor.data.game.AbstractData;
import lib.editor.data.game.MapData;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public class MapPanel extends DatabasePanel{
    
    SceneTree sceneTree;
    public boolean databaseLastFocused;
    
    public MapPanel() {
        MapEditorGraphicsView mapEditor = MapEditorGraphicsView.instance();
        middlePanel.add(mapEditor.getCanvas());
        
        JScrollPane scrollPane = new javax.swing.JScrollPane();
        sceneTree = new lib.editor.ui.data.map.SceneTree();
        scrollPane.setViewportView(sceneTree);
        leftPanel.add(scrollPane);
    }
    
    
    //public void setup(String dataName, DataMa){
    //    super.setup(dataName, );
        //databaseTree.setup(dataName);
    //}
    
    @Override
    public void dataChanged(AbstractData data){
        super.dataChanged(data);
        
        sceneTree.setup(this, ((MapData) data));
        MapEditorGraphicsView.instance().setup((MapData) data);
        databaseLastFocused = true;
    }
    
    @Override
    public AbstractData getCurrentNamableData() {
        if(databaseLastFocused){
            return getCurrentData();
        }
        else{
            return sceneTree.getCurrentData();
        }
    }
    
    @Override
    public TreeItem getCurrentItem() {
        if(databaseLastFocused){
            return databaseTree.getCurrentItem();
        }
        else{
            return sceneTree.getCurrentItem();
        }
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map;

import java.io.File;
import java.util.List;
import lib.editor.data.DataInfos;
import lib.editor.data.game.AbstractData;
import lib.editor.data.game.MapLayerData;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.item.TreeItemData;
import lib.editor.widget.tree.item.TreeItemDataPackage;
import lib.editor.widget.tree.tree.DatabaseTreeBase;

/**
 *
 * @author Gaetan
 */
public class SceneTree extends DatabaseTreeBase{

    List<MapLayerData> layers;
    
    public SceneTree() {
    }

    @Override
    public void createMenu() {
    }

    @Override
    public void createMenuShortcut() {
    }

    @Override
    public void checkEnabledMenuAction() {
    }
    
    public void setup(List<MapLayerData> layers){
        this.layers = layers;
        refresh();
    }
    
    @Override
    public void refresh(){
        clear();
        
        for(MapLayerData layer : layers){
            TreeItemData item = new TreeItemData(this, layer.name, null, layer, null);
            addTopLevelItem(item);
        }
    }
    
    @Override
    public AbstractData getGameData(TreeItem item){
        if(item == null){
            return null;
        }
        
        System.out.println(((TreeItemData) item).data);
        return ((TreeItemData) item).data;
    }
    
    @Override
    public void currentItemChanged(TreeItem newItem){
        //super.currentItemChanged(newItem);
        
        //dataPanel.dataChanged(getGameData(newItem));
    }
    
}

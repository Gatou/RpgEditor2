/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map;

import java.util.ArrayList;
import java.util.List;
import lib.editor.data.game.AbstractData;
import lib.editor.data.game.MapData;
import lib.editor.ui.data.map.action.AddMenuAction;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.editor.widget.mapeditor.tools.SelectTool;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.DatabaseTreeBase;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class SceneTree extends DatabaseTreeBase{

    public MapData mapData;
    public MapPanel mapPanel;
            
    public SceneTree() {
    }

    @Override
    public void createMenu() {
        menu.add(new AddMenuAction(this, "Add"));
        
        //SubMenuItem addMenu = new SubMenuItem("Add");
        //menu.add(addMenu);
    }

    public void setup(MapPanel mapPanel, MapData mapData){
        this.mapPanel = mapPanel;
        this.mapData = mapData;
        
        if(mapData == null){
            clear();
            return;
        }
        
        rootItem = new GameObjectTreeItem(this, mapData.sceneRoot);
        MapEditorGraphicsView.instance().setup(mapData);
        
        refresh();
    }
    
    @Override
    public void refresh(){
        clear();
        
        rootItem.removeAllChildren();
        
        addTopLevelItem(rootItem);
        
        
        refreshRec(((GameObjectTreeItem) rootItem).gameObject, (GameObjectTreeItem) rootItem);
        setItemExpanded(rootItem, true);
        
    }
        
    private void refreshRec(GameObject parent, GameObjectTreeItem parentItem){
        for(GameObject child : parent.children){
            
            GameObjectTreeItem item = new GameObjectTreeItem(this, child);
            
            parentItem.addChild(item, false);
            
            refreshRec(child, item);
            if(child.expanded){
                item.setExpanded(true);
            }
        }
    }
    
    @Override
    public AbstractData getGameData(TreeItem item){
        if(item == null || item == rootItem){
            return null;
        }
        
        //System.out.println(((TreeItemData) item).data);
        return ((GameObjectTreeItem) item).gameObject;
    }
    
    @Override
    public AbstractData getCurrentData(){
        TreeItem currentItem = getCurrentItem();
        if(currentItem == null || currentItem == rootItem){
            return null;
        }
        else{
            return getGameData(currentItem);
        }
    }
    
    @Override
    public void currentItemChanged(TreeItem newItem){
        if(newItem == null){
            return;
        }
        //super.currentItemChanged(newItem);
        
        //dataPanel.dataChanged(getGameData(newItem));
        Inspector.getRightInspector().setup(mapPanel, getGameData(newItem));
        mapPanel.databaseLastFocused = false;
        SelectTool.instance().select(((GameObjectTreeItem)newItem).gameObject, false);
    }

    public void itemAdded(GameObjectTreeItem parentItem, GameObjectTreeItem addedItem) {
        parentItem.setExpanded(true);
        setCurrentItem(addedItem);
        mapPanel.currentDataModified();
    }
    
    public int generateId(){
        int id = 1;
        List<Integer> allId = new ArrayList<Integer>();
        makeAllIdList(((GameObjectTreeItem)rootItem).gameObject, allId);
        
        while(allId.contains(id)){
            id += 1;
        }
        
        return id;
    }
    
    private void makeAllIdList(GameObject parent, List<Integer> allId){
        allId.add(parent.id);
        for(GameObject child : parent.children){
            makeAllIdList(child, allId);
        }
    }
    
    public void itemCollapsed(TreeItem item){
        super.itemCollapsed(item);
        
        ((GameObjectTreeItem) item).gameObject.expanded = false;
    }
    
    public void itemExpanded(TreeItem item){
        super.itemExpanded(item);
        
        if(!(item instanceof GameObjectTreeItem)){
            return;
        }
        
        ((GameObjectTreeItem) item).gameObject.expanded = true;

        for(int i=0; i<item.getChildCount(); i++){
            GameObjectTreeItem childItem = (GameObjectTreeItem) item.getChildAt(i);
            if(childItem.gameObject.expanded){
                childItem.setExpanded(true);
            }
        }
    }
    
    @Override
    public boolean canShowMenu(){
        return mapData != null;
    }
}

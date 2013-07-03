/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.util.ArrayList;
import java.util.List;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.data.game.MapData;
import lib.editor.mgr.TransferMgr;
import lib.editor.mgr.SaveMgr;
import lib.editor.widget.tree.item.TreeItemData;

/**
 *
 * @author gaetan
 */
public class MapTree extends DatabaseTree {
            
    
            
    public List<TreeItemData> deletedItems;

    public MapTree() {
        deletedItems = new ArrayList<TreeItemData>();
    }

    /*
    public void createMenu() {
        
        newMapItem = new MenuItem("New map", null, "Create a new map.", KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
        newMapItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                newMap(false);
            }
        });
        menu.add(newMapItem);
        
        menu.add(new Separator());
        
        
        copyItem = new MenuItem("Copy", Mgr.icon.getSystemIcon("copy.png", false), "Copy the selection and put it on the clipboard.", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.copy();
            }
        });
        menu.add(copyItem);
        
        pasteItem = new MenuItem("Paste", Mgr.icon.getSystemIcon("paste.png", false), "Insert clipboard contents.", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.paste();
            }
        });
        menu.add(pasteItem);
        
        deleteItem = new MenuItem("Delete", Mgr.icon.getSystemIcon("delete.png", false), "Delete the selection.", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.delete();
            }
        });
        menu.add(deleteItem);
    }
    
  
    
    public void createMenuShortcut() {
        //new map
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "NEW_MAP", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        newMap(false);
                    }
                });
        //copy
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "COPY", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        WidgetMgr.MAIN_WINDOW.copy();
                    }
                });

        //paste
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "PASTE", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        WidgetMgr.MAIN_WINDOW.paste();
                    }
                });
        //delete
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        WidgetMgr.MAIN_WINDOW.delete();
                    }
                });
    }

    public void checkEnabledMenuAction() {

        
        TreeItem item = getCurrentItem();
        boolean enabled = item != null;
        
        newMapItem.setEnabled(enabled);
        copyItem.setEnabled(enabled);
        pasteItem.setEnabled(TransferMgr.isEditorDataPastable(DataEditorMap.class));
        deleteItem.setEnabled(enabled);
        
        if(filter.isFiltering){
            //newMapItem.setEnabled(false);
            //pasteItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }      
        else if(item == rootItem){
            copyItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }
        

        WidgetMgr.MAIN_WINDOW.setActionEnabled("new map", newMapItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("cut", false);
        WidgetMgr.MAIN_WINDOW.setActionEnabled("copy", copyItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("paste", pasteItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("delete", deleteItem.isEnabled());
    }
    */
    //public DatabaseTreeItem getMapRoot(){
    //    return rootItem;
    //}
    
    /*
    public List<TreeItem> getAllItems(){
        List<TreeItem> result = super.getAllItems();
        result.remove(0);
        return result;
    }*/
    /*
    public void setup(String dataName){
        super.setup(dataName);
        
        
        
        checkEnabledMenuAction();
        
        setCurrentItem(rootItem);
        for(int i=0; i<2000; i++){
            if(i%5==0){
                setCurrentItem(root());
            }
            newMap(false);
        }
        
    }*/
    

    
    public void newMap(boolean pastedData){
        /*
        checkEnabledMenuAction();
        if(!newMapItem.isEnabled()){ return; }
        
        TreeItemData parentItem = (TreeItemData) getCurrentItem();
        
        int id = generateId();
        
        DataMap gameData = null;
        DataEditorMap editorData = null;
        
        if(pastedData){
            gameData = (DataMap) TransferMgr.pasteGameData();
            if(gameData == null){
                gameData = new DataMap(0, "" , 16, 16);
            }
            //editorData = (DataEditorMap) TransferMgr.pasteEditorData();
        }
        else{
            gameData = new DataMap(0, "" , 16, 16);
            
        }
        
        gameData.id = id;
        gameData.name = "Map" + gameData.getIdName();
        editorData = new DataEditorMap(gameData.id, gameData.name);
        //editorData.id = gameData.id;
        //editorData.name = gameData.name;
        
        TreeItemData item = new TreeItemData(this, gameData.name, null, gameData, editorData);
        
        parentItem.addChild(item, true);
        setItemExpanded(parentItem, true);
        setCurrentItem(item);
        
        ((PropertyPanel) WidgetMgr.INSPECTOR.panels.get("property")).focusNameTextField();
        
        checkEnabledMenuAction();
        
        SaveMgr.requestSaveEnabled();*/
    }
    
    
    public void copy(){
        checkEnabledMenuAction();
        if(!copyItem.isEnabled()){ return; }
        
        TransferMgr.copyEditorData(getCurrentData());
        TransferMgr.copyGameData(getCurrentData());
        
        checkEnabledMenuAction();
    }
    
    public void paste(){
        checkEnabledMenuAction();
        if(!pasteItem.isEnabled()){ return; }
        
        newMap(true);
                
        checkEnabledMenuAction();
    }
    
    public void delete(){
        checkEnabledMenuAction();
        if(!deleteItem.isEnabled()){ return; }
        
        TreeItem item = getCurrentItem();
        TreeItem parentItem = (TreeItem) item.getParent();
        int index = parentItem.getIndex(item);
            
        parentItem.removeChild(item);
        //removeItem(item);
        
        int count = parentItem.getChildCount();
        if(count == 0){
            setCurrentItem(parentItem);
        }
        else{
            if(index == count){
                setCurrentItem((TreeItem) parentItem.getChildAt(index-1));
            }
            else{
                setCurrentItem((TreeItem) parentItem.getChildAt(index));
            }
        }
        //if(parentItem.ch)
        //setCurrentItem(rootItem);
        
        checkEnabledMenuAction();
        
        SaveMgr.requestSaveEnabled();
    }
    
    //public List<TreeItem> getItems(){
        //List<TreeItem> result = super.getItems();
        //result.remove(0);
        //return result;
    //}
    /*
    public void save(){
        for(TreeItemData item : deletedItems){
            file = new File(ProjectMgr.getDataGamePath(), "Map" + item.getData().getIdName() + "." + AppMgr.getExtension("data file"));
            file.delete();
        }
        deletedItems.clear();
        
        List<TreeItem> items = getItems();
        items.remove(0); //Remove the tree root ("Project")
        
        for(TreeItem item : items){
            TreeItemData dataItem = (TreeItemData) item;
            
            if(dataItem.gameData != null){
                File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.gameData.getIdName() + "." + AppMgr.getExtension("data file"));
                DataMgr.dump(dataItem.gameData, file.getAbsolutePath());
                dataItem.gameData = null;
            }
        }
        
        
        DataEditorBase rootMapEditorData = (DataEditorBase) rootItem.editorData;
        
        File file = new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        DataMgr.dump(rootMapEditorData, file.getAbsolutePath());
        

    }*/
    
    /*
    public DataBase getGameData(TreeItem item){
        if(item == null || item == rootItem){
            return null;
        }
        
        TreeItemData dataItem = (TreeItemData) item;
        DataBase data = dataItem.gameData;
        
        if(data == null){
            File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.getData().getIdName() + "." + AppMgr.getExtension("data file"));
            data =  (DataBase) DataMgr.load(file.getAbsolutePath());
            if(data == null){
                data = new DataMap(0, "" , 16, 16);
                data.id = dataItem.getData().id;
                data.name = dataItem.getData().name;
                dataItem.gameData = data;
                SaveMgr.requestSaveEnabled();
            }
        }
        
        return data;
    }*/
    
    /*
    public Data getCurrentData(){
        TreeItem currentItem = getCurrentItem();
        if(currentItem instanceof TreeItemDataPackage){
            return ((TreeItemDataPackage) currentItem).getData();
        }
        else if(currentItem instanceof TreeItemData){
            return getGameData(currentItem);
        }
        return null; 
    }*/
    
    @Override
    public void currentItemChanged(TreeItem newItem){
        super.currentItemChanged(newItem);
        //WidgetMgr.INSPECTOR.setMapMode((MapData) getGameData(newItem));
        //WidgetMgr.MAP_EDITOR.refresh((MapData) getGameData(newItem));
    }
    
    public void currentMapEdited(){
        MapData data = (MapData) getCurrentData();
        TreeItemData item = (TreeItemData) getCurrentItem();
        item.data = data;
    }
    
}

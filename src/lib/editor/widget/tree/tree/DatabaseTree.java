/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import lib.editor.data.DataInfos;
import lib.editor.data.editor.DataEditorPackage;
import lib.editor.data.game.AbstractData;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.ProjectManager;
import lib.editor.mgr.SaveMgr;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.widget.menu.MenuItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.item.TreeItemData;
import lib.editor.widget.tree.item.TreeItemDataBase;
import lib.editor.widget.tree.item.TreeItemDataPackage;

/**
 *
 * @author gaetan
 */
public class DatabaseTree extends  DatabaseTreeBase{

    MenuItem newDataItem, newPackageItem, copyItem, pasteItem, deleteItem;
    public List<TreeItemDataBase> deletedItems;
    public List<TreeItemData> modifiedItems; 
    public DatabasePanel dataPanel;
    
    public DatabaseTree() {
        deletedItems = new ArrayList<TreeItemDataBase>();
        modifiedItems = new ArrayList<TreeItemData>();
    }
    
    public void setDataPanel(DatabasePanel dataPanel){
        this.dataPanel = dataPanel;
    }
    
    
    public void createMenu() {
        
        
        newDataItem = new MenuItem("New data", "new_data.png"){
            public boolean isEnabled(){
                return super.isEnabled() && !(getCurrentItem() instanceof TreeItemData);
            }
            
            public void execute() {
                if(!isEnabled()){ return; }
                newData(false);
            }
        };
        menu.add(newDataItem);
        
        newPackageItem = new MenuItem("New package", "new_package.png"){
            public boolean isEnabled(){
                return super.isEnabled() && !(getCurrentItem() instanceof TreeItemData);
            }
            
            public void execute() {
                if(!isEnabled()){ return; }
                newPackage(false);
            }
        };
        menu.add(newPackageItem);
        
        menu.add(new JPopupMenu.Separator());
        
        copyItem = new MenuItem("Copy", "copy.png", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "COPY", false, this){
            public boolean isEnabled(){
                return super.isEnabled() && getCurrentItem() != rootItem;
            }
            
            public void execute() {
                if(!isEnabled()){ return; }
                copy();
            }
        };
        menu.add(copyItem);
        
        pasteItem = new MenuItem("Paste", "paste.png", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "PASTE", false, this){

            public void paste() {
                if(!isEnabled()){ return; }
                paste();
            }
        };
        menu.add(pasteItem);
        
        deleteItem = new MenuItem("Delete", "delete.png", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE", false, this){
            
            public boolean isEnabled(){
                return super.isEnabled() && getCurrentItem() != rootItem;
            }
            
            public void execute() {
                if(!isEnabled()){ return; }
                delete();
            }
        };
        menu.add(deleteItem);

    }
    
  
    public void newData(boolean pastedData){
        //checkEnabledMenuAction();
        if(!newDataItem.isEnabled()){ return; }
        
        TreeItemDataPackage parentItem = (TreeItemDataPackage) getCurrentItem();
        
        int id = generateId();
        
        AbstractData data = null;
        try {
            data = (AbstractData) dataClass.newInstance();
            //DataEditorBase editorData = null;
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        if(pastedData){
            gameData = (DataMap) TransferMgr.pasteGameData();
            if(gameData == null){
                gameData = new DataMap(0, "" , 16, 16);
            }
            //editorData = (DataEditorMap) TransferMgr.pasteEditorData();
        }
        else{
            gameData = new DataMap(0, "" , 16, 16);
            
        }*/
        
        data.id = id;
        data.name = DataInfos.getSavePrefixName(dataName) + data.getIdName();
        AbstractData editorData = new AbstractData(data.id, data.name);
        
        TreeItemData item = new TreeItemData(this, data.name, "data.png", data, editorData);
        
        parentItem.addChild(item, true);
        setItemExpanded(parentItem, true);
        setCurrentItem(item);
        
        //((PropertyPanel) WidgetMgr.INSPECTOR.panels.get("property")).focusNameTextField();
        
        //checkEnabledMenuAction();
        
        //SaveMgr.requestSaveEnabled();
        dataPanel.currentDataModified();
    }
    
    public void newPackage(boolean pastedData){
        //checkEnabledMenuAction();
        if(!newDataItem.isEnabled()){ return; }
        
        TreeItemDataPackage parentItem = (TreeItemDataPackage) getCurrentItem();
        
        int id = -1;
        //int id = generateId();
        
        //DataMap gameData = null;
        DataEditorPackage packageData = null;
        
        packageData = new DataEditorPackage(id, "");
        
        /*
        if(pastedData){
            gameData = (DataMap) TransferMgr.pasteGameData();
            if(gameData == null){
                gameData = new DataMap(0, "" , 16, 16);
            }
            //editorData = (DataEditorMap) TransferMgr.pasteEditorData();
        }
        else{
            gameData = new DataMap(0, "" , 16, 16);
            
        }*/
        
        //packageData.id = id;
        packageData.name = "Package";
        //editorData = new DataEditorBase(gameData.id, gameData.name);
        
        TreeItemDataPackage item = new TreeItemDataPackage(this, packageData.name, "package.png", packageData);
        
        parentItem.addChild(item, true);
        setItemExpanded(parentItem, true);
        setCurrentItem(item);
        
        //((PropertyPanel) WidgetMgr.INSPECTOR.panels.get("property")).focusNameTextField();
        
        //checkEnabledMenuAction();
        
        //SaveMgr.requestSaveEnabled();
        dataPanel.currentDataModified();
    }
    
    public void copy(){
        
    }
    
    public void paste(){
        
    }
    
    public void delete(){
        //checkEnabledMenuAction();
        if(!deleteItem.isEnabled()){ return; }
        
        TreeItemDataBase item = (TreeItemDataBase) getCurrentItem();
        deletedItems.add(item);
        
        TreeItemDataBase parentItem = (TreeItemDataBase) item.getParent();
        int index = parentItem.getIndex(item);
            
        parentItem.removeChild(item);
        //removeItem(item);w
        
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
        
        //checkEnabledMenuAction();
        
        SaveMgr.requestSaveEnabled();
        //dataPanel.currentDataModified();
    }
    
    private void deleteRec(AbstractData data){
        if(data instanceof DataEditorPackage){
            for(AbstractData d : ((DataEditorPackage) data).children){
                deleteRec(d);
            }
        }
        else{
            String dataPrefix = DataInfos.getSavePrefixName(dataName);
            File file = new File(ProjectManager.getDataPath(dataName), dataPrefix + data.getIdName() + "." + AppMgr.getExtension("data file"));
            file.delete();
        }

    }
    
    public void save(){
        String dataPrefix = DataInfos.getSavePrefixName(dataName);
        
        for(TreeItemDataBase item : deletedItems){
            if(item instanceof TreeItemData){
                deleteRec(((TreeItemData)item).editorData);
            }
            else if(item instanceof TreeItemDataPackage){
                deleteRec(item.editorData);
            }
            //File file = new File(ProjectMgr.getDataPath(dataName), dataPrefix + item.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
            //file.delete();
        }
        deletedItems.clear();
        
        //List<TreeItem> items = getItems();
        //items.remove(0); //Remove the tree root ("Project")
        /*
        for(TreeItem item : items){
            if(!(item instanceof TreeItemData)){
                continue;
            }
            
            TreeItemData dataItem = (TreeItemData) item;
            System.out.println(dataItem.editorData.name + " " + dataItem.data);
            if(dataItem.data != null){
                File file = new File(ProjectMgr.getDataPath(dataName), dataPrefix + dataItem.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
                System.out.println(file.getName());
                DataMgr.dump(dataItem.data, file.getAbsolutePath());
                dataItem.data = null;
            }
        }
        */
        for(TreeItemData dataItem : modifiedItems){
            //System.out.println(dataItem.editorData.name + " " + dataItem.data);
            if(dataItem.data != null){
                File file = new File(ProjectManager.getDataPath(dataName), dataPrefix + dataItem.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
                //System.out.println(file.getName());
                DataMgr.dump(dataItem.data, file.getAbsolutePath());
                //dataItem.data = null;
            }
        }
        modifiedItems.clear();
        
        DataEditorPackage rootEditorData = (DataEditorPackage) ((TreeItemDataPackage)rootItem).editorData;
        
        File file = new File(ProjectManager.getDataPath(dataName), dataPrefix + "Infos" + "." + AppMgr.getExtension("data file"));
        DataMgr.dump(rootEditorData, file.getAbsolutePath());
        
        
    }
    

    public void itemCollapsed(TreeItem item){
        super.itemCollapsed(item);
        
        TreeItemDataPackage dataItem = (TreeItemDataPackage) item;
        DataEditorPackage data = (DataEditorPackage) dataItem.editorData;
        data.expanded = false;
    }
    
    public void itemExpanded(TreeItem item){
        super.itemExpanded(item);
        
        if(item instanceof TreeItemDataPackage){
            TreeItemDataPackage dataItem = (TreeItemDataPackage) item;
            DataEditorPackage data = (DataEditorPackage) dataItem.editorData;
            data.expanded = true;
        }

        for(int i=0; i<item.getChildCount(); i++){
            if(item.getChildAt(i) instanceof TreeItemDataPackage){
                TreeItemDataPackage childItem = (TreeItemDataPackage) item.getChildAt(i);
                if(((DataEditorPackage)childItem.editorData).expanded){
                    setItemExpanded(childItem, true);
                }
            }
        }
    }
}

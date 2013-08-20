/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreePath;
import lib.editor.data.DataInfos;
import lib.editor.data.editor.DataEditorPackage;
import lib.editor.data.game.AbstractData;
import lib.editor.data.game.MapData;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.ProjectManager;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.widget.tree.item.TreeItemData;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.item.TreeItemDataBase;
import lib.editor.widget.tree.item.TreeItemDataPackage;

/**
 *
 * @author gaetan
 */
public abstract class DatabaseTreeBase extends TreeMenu{
    
    public DatabasePanel dataPanel;
    public String dataName;
    public Class dataClass;
    public TreeItem rootItem;
    
    @Override
    public void collapsePath(TreePath path) {
        if(path.getLastPathComponent() == rootItem){
            return;
        }
        super.collapsePath(path);
    }
    
    public int generateId(){
        int id = 1;
        List<Integer> allId = new ArrayList<Integer>();
        makeAllIdList((DataEditorPackage) ((TreeItemDataPackage)rootItem).editorData, allId);
        
        /*
        List<TreeItem> items = getItems();
        for(TreeItem item : items){
            TreeItemDataBase dataItem = (TreeItemDataBase) item;
            allId.add(dataItem.data.id);
        }
        */
        while(allId.contains(id)){
            id += 1;
        }
        
        return id;
    }
    
    private void makeAllIdList(DataEditorPackage parentPackageData, List<Integer> allId){
        for(AbstractData data : parentPackageData.children){
            if(data instanceof DataEditorPackage){
                makeAllIdList((DataEditorPackage) data, allId);
            }
            else{
                allId.add(data.id);
            }
        }
    }
    
    public void setup(DatabasePanel dataPanel, String dataName, String rootIconFilename, Class dataClass){
        this.dataPanel = dataPanel;
        this.dataName = dataName;
        this.dataClass = dataClass;
             
        DataEditorPackage rootEditorData = null;
        
        String dataPrefix = DataInfos.getSavePrefixName(dataName);
        File file = new File(ProjectManager.getDataPath(dataName), dataPrefix + "Infos" + "." + AppMgr.getExtension("data file"));
        if(file.exists()){
            rootEditorData = (DataEditorPackage) DataMgr.load(file.getAbsolutePath());
            //DataEditorMap rootMapEditorData = new DataEditorMap(0, "");
            //List<DataEditorMap> mapEditorDatabase = new ArrayList<DataEditorMap>();
            //mapEditorDatabase.add();
            //file2.createNewFile();
            //dump(rootMapEditorData, file.getAbsolutePath());
        }
        
        if(rootEditorData == null){
            rootEditorData = new DataEditorPackage(-1, dataName);
        }
        
        rootItem = new TreeItemDataPackage(this, dataName, rootIconFilename, rootEditorData);
        
        refresh();   
    }
    
    public void refresh(){
        clear();
        
        rootItem.removeAllChildren();
        
        addTopLevelItem(rootItem);
        refreshRec((DataEditorPackage) ((TreeItemDataPackage)rootItem).editorData, (TreeItemDataPackage)rootItem);
        setItemExpanded(rootItem, true);
        
    }
        
    private void refreshRec(DataEditorPackage dataEditor, TreeItemDataPackage parentItem){
        for(AbstractData data : dataEditor.children){
            
            TreeItemDataBase item = null;
            
            if(data instanceof DataEditorPackage){
                item = new TreeItemDataPackage(this, data.name, "package.png", (DataEditorPackage) data);
            }
            else if(data instanceof AbstractData){
                item = new TreeItemData(this, data.name, "data.png", null, (AbstractData) data);
            }
            
            parentItem.addChild(item, false);
            
            if(data instanceof DataEditorPackage){
                refreshRec((DataEditorPackage) data, (TreeItemDataPackage) item);
                DataEditorPackage dataPackage = (DataEditorPackage) ((TreeItemDataPackage)item).editorData;

                if(dataPackage.expanded){
                    setItemExpanded(item, true);
                }
            }
        }
    }
    
    public AbstractData getGameData(TreeItem item){
        if(item == null || item == rootItem){
            return null;
        }
        
        if(item instanceof TreeItemDataPackage){
            TreeItemDataPackage dataItem = (TreeItemDataPackage) item;
            return dataItem.editorData;
        }
        
        TreeItemData dataItem = (TreeItemData) item;
        AbstractData data = dataItem.data;
        
        if(data == null){
            String dataPrefix = DataInfos.getSavePrefixName(dataName);
            File file = new File(ProjectManager.getDataPath(dataName), dataPrefix + dataItem.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
            //System.out.println(file.getAbsolutePath());
            //File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.data.getIdName() + "." + AppMgr.getExtension("data file"));
            data =  (AbstractData) DataMgr.load(file.getAbsolutePath());
            dataItem.data = data; //????????????????????????????????????????????????????????????????????
            //return data;
            /*
            File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.data.getIdName() + "." + AppMgr.getExtension("data file"));
            data =  (DataBase) DataMgr.load(file.getAbsolutePath());
            if(data == null){
                data = new DataMap(0, "" , 16, 16);
                data.id = dataItem.data.id;
                data.name = dataItem.data.name;
                dataItem.gameData = data;
                SaveMgr.requestSaveEnabled();
            }
            */
            //return null;
        }
        
        return data;
    }
    
    public AbstractData getCurrentData(){
        TreeItem currentItem = getCurrentItem();
        if(currentItem == null || currentItem == rootItem){
            return null;
        }
        else if(currentItem instanceof TreeItemDataPackage){
            return ((TreeItemDataPackage) currentItem).editorData;
        }
        else if(currentItem instanceof TreeItemData){
            return getGameData(currentItem);
        }
        return null; 
    }
    
    public AbstractData getCurrentEditorData(){
        TreeItem currentItem = getCurrentItem();
        if(currentItem == null){
            return null;
        }
        return ((TreeItemDataBase)currentItem).editorData;
    }

    
    @Override
    public void currentItemChanged(TreeItem newItem){        
        super.currentItemChanged(newItem);
        
        dataPanel.dataChanged(getGameData(newItem));
    }
}

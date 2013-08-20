/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.io.File;
import lib.editor.mgr.IconManager;
import lib.editor.mgr.ProjectManager;
import lib.editor.widget.list.item.ListItem;
import lib.editor.widget.list.list.AbstractList;

/**
 *
 * @author gaetan
 */
public class AssetManagerAssetFolderList extends AbstractList{

    AssetManagerAssetsTree assetTree;
    
    public AssetManagerAssetFolderList() {
        //addItem(new ListItem("onjours", null));
    }
    
    public void refresh(){
        clear();
        
        File assetsFolder = new File(ProjectManager.getAssetsPath());
        for (String name : assetsFolder.list()) {
            addItem(new ListItem(name, IconManager.instance().getSystemIcon("folder.png", false)));
        }
    }
    
    public void currentItemChanged(ListItem item, int row){
        AssetManagerWindow.getInstance().clearFilter();
        assetTree.refresh();
    }
    
    public void setAssetTree(AssetManagerAssetsTree assetTree){
        this.assetTree = assetTree;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import lib.editor.data.editor.DataEditorPackage;
import lib.editor.data.game.AbstractData;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class TreeItemDataPackage extends TreeItemDataBase{
    
    //public DataEditorPackage editorData;
    
    public TreeItemDataPackage(Tree tree, String text, String iconFilename, DataEditorPackage data) {
        super(tree, text, iconFilename, data);
        //this.editorData = editorData;
    }
    
    //public AbstractData getData(){
    //    return editorData;
    //}
    
    public void addChild(TreeItem item, boolean addToDatabase){
        if(addToDatabase){
            ((DataEditorPackage)editorData).children.add(((TreeItemDataBase)item).editorData);
            /*
            if(item instanceof TreeItemDataPackage){
                ((DataEditorPackage)editorData).children.add(((TreeItemDataPackage)item).editorData);
            }
            else if(item instanceof TreeItemData){
                ((DataEditorPackage)editorData).children.add(((TreeItemData)item).editorData);
            }*/
            /*
            AbstractData addedData = ((TreeItemDataBase)item).getData();
            if(addedData instanceof DataEditorPackage){
                ((DataEditorPackage)data).children.add(addedData);
            }
            else{
                ((DataEditorPackage)data).children.add(((TreeItemData)item).editorData);
                //AbstractData editorData = new AbstractData(addedData.id, addedData.name);
                //((TreeItemData)item).editorData = editorData;
                //((DataEditorPackage)data).children.add(editorData);
            }*/
        }
        super.addChild(item);
    }
    
    @Override
    public void removeChild(TreeItem item){
        System.out.println("Removed id: " + item.getText());
        ((DataEditorPackage)editorData).children.remove(((TreeItemDataBase)item).editorData);
        System.out.println(((DataEditorPackage)editorData).children.size());
        /*
        System.out.println(((TreeItemDataBase)item).editorData.id);
        for(AbstractData data : ((DataEditorPackage)editorData).children){
            System.out.println(data.id);
        }*/
        super.removeChild(item);
    }
    
}

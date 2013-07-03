/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import lib.editor.data.game.AbstractData;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class TreeItemData extends TreeItemDataBase{

    public AbstractData data;
    //public AbstractData gameData;
    
    public TreeItemData(Tree tree, String text, String iconFilename, AbstractData data, AbstractData editorData){
        super(tree, text, iconFilename, editorData);
        this.data = data;
    }
    
    //public AbstractData getData(){
    //    return gameData;
    //}
    
}

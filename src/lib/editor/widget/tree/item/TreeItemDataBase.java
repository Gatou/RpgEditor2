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
public abstract class TreeItemDataBase extends TreeItem{

    public AbstractData editorData;
    
    public TreeItemDataBase(Tree tree, String text, String iconFilename, AbstractData editorData) {
        super(tree, text, iconFilename);
        this.editorData = editorData;
    }
    
    //public AbstractData getData(){
    //    return data;
    //}
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map;

import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.Tree;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class GameObjectTreeItem extends TreeItem{
    
    public GameObject gameObject;
    
    public GameObjectTreeItem(Tree tree, GameObject gameObject) {
        super(tree, gameObject.name, gameObject.getIconFilename());
        this.gameObject = gameObject;
    }
    
    public void addChild(TreeItem item, boolean addToDatabase){
        if(addToDatabase){
            //System.out.println(gameObject.name + " " + ((GameObjectTreeItem)item).gameObject.name);
            gameObject.addChild(((GameObjectTreeItem)item).gameObject);
        }
        super.addChild(item);
    }
    
    @Override
    public void removeChild(TreeItem item){
        gameObject.removeChild(((GameObjectTreeItem)item).gameObject);
        super.removeChild(item);
    }
    
}
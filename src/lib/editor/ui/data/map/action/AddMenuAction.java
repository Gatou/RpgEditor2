/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map.action;

import lib.editor.ui.data.map.SceneTree;
import lib.editor.widget.menu.SubMenuItem;

/**
 *
 * @author Gaetan
 */
public class AddMenuAction extends SubMenuItem{

    public AddMenuAction(SceneTree sceneTree, String text) {
        super(text);
        
        add(new AddEmptyGameObjectAction(sceneTree));
    }
    
    
}

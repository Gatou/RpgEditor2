/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.component;

import lib.editor.data.game.AbstractData;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public abstract class GameComponent extends AbstractData{
    

    public GameObject gameObject;
    //public String name;
    //public boolean visible;
    
    public GameComponent(GameObject gameObject) {
        super(-1, "");
        this.gameObject = gameObject;
    }
    
    
    public String getIconFilename(){
        return "";
    }

    public InspectorPanel getPanel() {
        return null;
    }
    
    
}

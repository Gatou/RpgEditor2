/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data.map.action;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;
import lib.editor.ui.data.map.GameObjectTreeItem;
import lib.editor.ui.data.map.SceneTree;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.editor.widget.menu.MenuItem;
import lib.game.component.RendererComponent;
import lib.game.component.TransformComponent;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class AddEmptyGameObjectAction extends MenuItem{

    private SceneTree sceneTree;
    
    public AddEmptyGameObjectAction(SceneTree sceneTree) {
        super("Empty GameObject", "node.png");
        this.sceneTree = sceneTree;
    }

    @Override
    public boolean isEnabled(){
        return super.isEnabled();
    }

    //Texture texture;
    
    @Override
    public void execute() {
        if(!isEnabled()){ return; }
        
        /*
        Random rand = new Random();
        
        GameObject obj = MapEditorGraphicsView.instance().scene.getRoot();
        for(int i=0; i<20000; i++){
            //int id = sceneTree.generateId();
        
            GameObject gameObject = new GameObject(0, "gameObject" + 0);
        
            TransformComponent transform = (TransformComponent)gameObject.getComponent("TransformComponent");
            transform.setX(rand.nextInt(400));
            transform.setY(rand.nextInt(400));
            
            //texture = new Texture("assets/tab/actor.png");
            //RendererComponent comp = new RendererComponent(gameObject, "assets/tab/actor.png");
            //comp.setTexture(texture);
            //gameObject.addComponent(comp);
        
            obj.addChild(gameObject);
            //obj = gameObject;
            
       //GameObjectTreeItem gameObjectItem = new GameObjectTreeItem(sceneTree, gameObject);
        
        //GameObjectTreeItem currentItem = (GameObjectTreeItem) sceneTree.getCurrentItem();
        //currentItem.addChild(gameObjectItem, true);
        
        //sceneTree.itemAdded(currentItem, gameObjectItem);
        }
        */
        
        int id = sceneTree.generateId();

        GameObject gameObject = new GameObject(id, "gameObject" + id);

        //TransformComponent transform = (TransformComponent)gameObject.getComponent("TransformComponent");

        //texture = new Texture("assets/tab/actor.png");
        RendererComponent comp = new RendererComponent(gameObject, "assets/tab/actor.png");
        //comp.setTexture(texture);
        gameObject.addComponent(comp);
            
        GameObjectTreeItem gameObjectItem = new GameObjectTreeItem(sceneTree, gameObject);
        
        GameObjectTreeItem currentItem = (GameObjectTreeItem) sceneTree.getCurrentItem();
        currentItem.addChild(gameObjectItem, true);
        
        sceneTree.itemAdded(currentItem, gameObjectItem);
    }
    
}

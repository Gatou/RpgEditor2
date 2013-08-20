package lib.editor.widget.mapeditor.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import lib.editor.ui.data.map.SceneTree;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.game.component.RendererComponent;
import lib.game.component.TransformComponent;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class SelectTool {
    
    private static SelectTool instance;
    
    private ShapeRenderer shapeRenderer;
    private List<GameObject> selectedObjects;
    
    private SelectTool() {
        shapeRenderer = new ShapeRenderer();
        selectedObjects = new ArrayList<GameObject>();
    }
    
    public static SelectTool instance(){
        if(instance == null){
            instance = new SelectTool();
        }
        return instance;
    }
    
    public void select(GameObject gameObject, boolean add){
        if(!add){
            selectedObjects.clear();
        }
        selectedObjects.add(gameObject);
    }
    
    
    public void update(SpriteBatch spriteBatch){
        
        
        GameObject root = MapEditorGraphicsView.instance().scene.getRoot();
        //if(root != null){
        //    System.out.println(root.getBounds(true));
        //}
        
        for(GameObject gameObject : selectedObjects){
            Rectangle bounds = gameObject.getBounds(true);
            
            //TransformComponent transform = (TransformComponent) gameObject.getComponent("TransformComponent");
            
            shapeRenderer.begin(ShapeType.Rectangle);
            
            shapeRenderer.identity();
            //System.out.println(bounds.x);
            //shapeRenderer.translate(bounds.x, bounds.y, 0);
            //shapeRenderer.rotate(0, 0, 1, transform.getRotation(root));
            //shapeRenderer.setTransformMatrix(computeTransform(gameObject));
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            shapeRenderer.end();
 
            /*
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.translate(bounds.x, bounds.y, 0);
            shapeRenderer.rotate(0, 0, 1, transform.getRotation());
            shapeRenderer.line(0, 0, 10, 0);
            shapeRenderer.end();*/
            
            //System.out.println(transform.localToAscendantCoordinates(MapEditorGraphicsView.getInstance().root, new Vector2(0,0)));
        
            gameObject.onDrawGizmosSelected(spriteBatch, shapeRenderer);
        }
    }
    
    /*
    public Rectangle getBounds(GameObject gameObject){
        TransformComponent transform = (TransformComponent) gameObject.getComponent("TransformComponent");
        RendererComponent renderer = (RendererComponent) gameObject.getComponent("RendererComponent");
        GameObject root = MapEditorGraphicsView.getInstance().root;
        
        if(renderer != null){
            return new Rectangle(transform.getX(root), transform.getY(root), renderer.getRegion().getRegionWidth(), renderer.getRegion().getRegionHeight());
            //
        }
        return null;
    }
    
    
	public GameObject hit (float stageX, float stageY) {
            stageY = Gdx.graphics.getHeight() - stageY;
            GameObject root = MapEditorGraphicsView.getInstance().root;
            if(root == null){return null;}
            checkHit(root, stageX, stageY);
            
            return null;
	}
        
    public void checkHit(GameObject obj, float x, float y){
        Vector2 coord = new Vector2(x,y);
        TransformComponent transform = (TransformComponent)obj.getComponent("TransformComponent");
        
        transform.parentToLocalCoordinates(coord);
        //coord.y = Gdx.graphics.getHeight() - coord.y;
        System.out.println(obj.name + " " + coord);
        
        if(transform.hit(coord.x, coord.y) != null){
            System.out.println(transform.gameObject.name);
        }
        
        for(GameObject child : obj.children){
            checkHit(child, x, y);
        }
    }
    */
    
}

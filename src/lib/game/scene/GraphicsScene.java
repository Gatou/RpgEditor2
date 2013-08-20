/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import lib.game.component.TransformComponent;

/**
 *
 * @author Gaetan
 */
public class GraphicsScene {
    
    static private final Vector3 cameraCoords = new Vector3();
    
    private OrthographicCamera camera; 
    private GameObject root;
    private SpriteBatch spriteBatch;
    
    public GraphicsScene() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();
    }
    
    public void setup(GameObject root){
        this.root = root;
        
        if(root == null){
            return;
        }
        
        root.setScene(this);
    }
    
    public GameObject getRoot(){
        return root;
    }
    
    public OrthographicCamera getCamera () {
        return camera;
    }
        
    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }
    
    public void clear(){
        if(root == null) return;
        root.clear();
        root = null;
    }
    
    public void update(){
        if(root == null) return;
        
        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        //grid.update(camera);
      
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        
        root.draw(spriteBatch, 0, 0, 1f);
        
        //gameObject.onDrawGizmosSelected(spriteBatch, shapeRenderer);
        
        //TextureRegion region = new TextureRegion(texture);
        //spriteBatch.draw(region, 0, 0, texture.getWidth()/2, texture.getHeight()/2, texture.getWidth(), texture.getHeight(), 1, 1, 0);
        spriteBatch.end();
        
    }
    
    public void resize(float width, float height){
      camera.setToOrtho(false, width, height);
      //camera.position.set(camPos);
    }

        
    public Vector2 screenToSceneCoordinates(Vector2 screenCoords) {
        camera.unproject(cameraCoords.set(screenCoords.x, screenCoords.y, 0));
        //camera.unproject(cameraCoords.set(screenCoords.x, screenCoords.y, 0), 0, 0, camera.viewportWidth, camera.viewportHeight);
        screenCoords.x = cameraCoords.x;
        screenCoords.y = cameraCoords.y;
        return screenCoords;
    }

    
    public Vector2 stageToScreenCoordinates (Vector2 stageCoords) {
        camera.project(cameraCoords.set(stageCoords.x, stageCoords.y, 0));
        //camera.project(cameraCoords.set(stageCoords.x, stageCoords.y, 0), viewportX, viewportY, viewportWidth, viewportHeight);
        stageCoords.x = cameraCoords.x;
        stageCoords.y = camera.viewportHeight - cameraCoords.y;
        return stageCoords;
    }
     
    public GameObject hit (float stageX, float stageY) {
        stageY = Gdx.graphics.getHeight() - stageY;
        /*GameObject root = MapEditorGraphicsView.getInstance().root;
        if(root == null){return null;}
        checkHit(root, stageX, stageY);
        */
        return null;
    }
        
    public void checkHit(GameObject obj, float x, float y){
        /*
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
        }*/
    }
            
}

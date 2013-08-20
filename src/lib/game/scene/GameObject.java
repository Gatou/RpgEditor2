/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lib.editor.data.game.AbstractData;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.component.GameComponent;
import lib.game.component.RendererComponent;
import lib.game.component.TransformComponent;

/**
 *
 * @author Gaetan
 */
public class GameObject extends AbstractData{

    public boolean visible;
    public boolean expanded;
    
    transient private GraphicsScene scene;
    
    private List<String> componentsOrder;
    private Map<String, GameComponent> components;
    public List<GameObject> children;
    public GameObject parent;
    
    //private Rectangle bounds;
    private boolean boundsComputed;
    
    transient public Matrix3 worldTransform = new Matrix3();
    transient private Matrix4 batchTransform = new Matrix4();
    transient private Matrix4 oldBatchTransform = new Matrix4();
    
    private Rectangle hitBox;
    private Rectangle localBoundingBox;
    private Rectangle childrenBoundingBox;
    private boolean fixedHitBox;
    private boolean fixedBoundingBox;
    private boolean needComputeHitBox;
    public boolean needComputeChildrenBounds;
    
    public GameObject(int id, String name) {
        super(id, name);
        
        visible = true;
        expanded = false;
        
        componentsOrder = new ArrayList<String>();
        components = new HashMap<String, GameComponent>();
        children = new ArrayList<GameObject>();
        
        TransformComponent component = new TransformComponent(this);
        addComponent(component);
        
        boundsComputed = false;
        //bounds = new Rectangle();
        
        fixedHitBox = false;
        fixedBoundingBox = false;
        hitBox = new Rectangle();
        localBoundingBox = new Rectangle();
        childrenBoundingBox = new Rectangle();
        needComputeHitBox = true;
        needComputeChildrenBounds = true;
    }
    
    public void setScene(GraphicsScene scene){
        if(this.scene == scene) return;
        this.scene = scene;
        for(GameObject child : children){
            child.setScene(scene);
        }
    }
    
    private Rectangle getHitBox(){
        if(fixedHitBox){
            return hitBox;
        }
        
        if(!needComputeHitBox){
            return hitBox;
        }
        
        RendererComponent renderer = (RendererComponent) getComponent("RendererComponent");
        if(renderer != null){
            hitBox.set(0, 0, renderer.getRegion().getRegionWidth(), renderer.getRegion().getRegionHeight());
        }
        else{
            hitBox.set(0, 0, 10, 10);
        }
        
        needComputeHitBox = false;
        
        return hitBox;
    }
    
    
    public Rectangle getBounds(boolean includeChildren){
        
        if(includeChildren && fixedBoundingBox){
            return childrenBoundingBox;
        }
        
        if(includeChildren && !needComputeChildrenBounds){
            return childrenBoundingBox;
        }
        
        
        //Rectangle localBounds = new Rectangle();
        
        //if(children.isEmpty() || !includeChildren){
        getHitBox(); //compute hit box
        //System.out.println("----------------");

        TransformComponent transform = (TransformComponent) getComponent("TransformComponent");
        
        Vector2 vec = new Vector2(0,0);
        vec.set(hitBox.x, hitBox.y);
        transform.localToSceneCoordinates(vec);
        //System.out.println(vec);

        float xmin = vec.x;
        float ymin = vec.y;
        float xmax = vec.x;
        float ymax = vec.y;

        vec.set(hitBox.x+hitBox.width, hitBox.y);
        transform.localToSceneCoordinates(vec);
        //System.out.println(vec);
        xmin = Math.min(xmin, vec.x);
        ymin = Math.min(ymin, vec.y);
        xmax = Math.max(xmax, vec.x);
        ymax = Math.max(ymax, vec.y);

        vec.set(hitBox.x, hitBox.y+hitBox.height);
        transform.localToSceneCoordinates(vec);
        //System.out.println(vec);
        xmin = Math.min(xmin, vec.x);
        ymin = Math.min(ymin, vec.y);
        xmax = Math.max(xmax, vec.x);
        ymax = Math.max(ymax, vec.y);

        vec.set(hitBox.x+hitBox.width, hitBox.y+hitBox.height);
        transform.localToSceneCoordinates(vec);
        //System.out.println(vec);
        xmin = Math.min(xmin, vec.x);
        ymin = Math.min(ymin, vec.y);
        xmax = Math.max(xmax, vec.x);
        ymax = Math.max(ymax, vec.y);

        //System.out.println(xmin + " " + xmax + " " + ymin + " " + ymax);
        //float xmax = vec.x;
        //float ymax = vec.y;
        /*
        bounds.set(0, 0, 20, 20);
        x2 = x0+(x-x0)*cos(theta)+(y-y0)*sin(theta)
        y2 = y0-(x-x0)*sin(theta)+(y-y0)*cos(theta)
        (min_x,min_y), (min_x,max_y), (max_x,max_y), (max_x,min_y)*/
        localBoundingBox.set(xmin, ymin, xmax-xmin, ymax-ymin);
            //return localBounds;
        //}
        
        
        if(!includeChildren || children.isEmpty()){
            return localBoundingBox;
        }
        
        
        
        //TransformComponent firstChildTransform = (TransformComponent) children.get(0).getComponent("TransformComponent");
        Rectangle childBounds;// = children.get(0).getBounds(includeChildren);
        float xmin2 = localBoundingBox.x;
        float ymin2 = localBoundingBox.y;
        float xmax2 = localBoundingBox.x + localBoundingBox.width;
        float ymax2 = localBoundingBox.y + localBoundingBox.height;
        
        for(GameObject child : children){
            childBounds = child.getBounds(includeChildren);
            //localBounds.merge(childBounds);
            xmin2 = Math.min(xmin2, childBounds.x);
            ymin2 = Math.min(ymin2, childBounds.y);
            xmax2 = Math.max(xmax2, childBounds.x+childBounds.width);
            ymax2 = Math.max(ymax2, childBounds.y+childBounds.height);
        }
        
        needComputeChildrenBounds = false;
        childrenBoundingBox.set(xmin2, ymin2, xmax2-xmin2, ymax2-ymin2);
        
        //System.out.println(xmin2);
        return childrenBoundingBox;
    }
    
    /*
    private void computeBounds(){
        TransformComponent firstChildTransform = (TransformComponent) children.get(0).getComponent("TransformComponent");
        float xmin = firstChildTransform.getX(this);
        float ymin = firstChildTransform.getX(this);
        float xmax = firstChildTransform.getX(this);
        float ymax = firstChildTransform.getX(this);
        
        for(GameObject child : children){
            
        }
        boundsComputed = true;
    }*/
    
    public void setup() {
    }
    
    public void clear(){
        clearChildren();
    }
    
    public void clearChildren(){
        children.clear();
    }
    
    public void addComponent(GameComponent component){
        componentsOrder.add(component.getClass().getSimpleName());
        components.put(component.getClass().getSimpleName(), component);
    }
    
    public GameComponent getComponent(String className){
        return components.get(className);
    }
    
    public boolean hasComponent(String className){
        return components.get(className) != null;
    }
    
    public void addChild(GameObject gameObject){
        gameObject.detach();
        children.add(gameObject);
        gameObject.setScene(scene);
        gameObject.parent = this;
    }
    
    public void removeChild(GameObject gameObject){
        children.remove(gameObject);
        gameObject.parent = null;
    }
    
    public void detach(){
        if(parent != null){
            parent.removeChild(this);
        }
    }
    
    public void draw (SpriteBatch spriteBatch, float parentX, float parentY, float parentAlpha){
        
        
        RendererComponent renderer = (RendererComponent) getComponent("RendererComponent");
        TransformComponent transform = (TransformComponent) getComponent("TransformComponent");
        /*
        Vector2 vec = new Vector2(0, 0);
        scene.screenToSceneCoordinates(vec);
        System.out.println(transform.sceneToLocalCoordinates(vec));*/
        
        System.out.println(name + " " + transform);
        
        float x = transform.getX();
        float y = transform.getY();
        if(parent != null){
            TransformComponent parentTransform = (TransformComponent) parent.getComponent("TransformComponent");
            if(!parentTransform.transformRotationOrScale()){
                x += parentX;
                y += parentY;
            }
        }
        
        if(renderer != null){
            
            TextureRegion region = renderer.getRegion();
            region = renderer.getRegion();
            /*
            if(region == null){
                if(renderer.textureFilename != ""){
                    renderer.setTexture(renderer.textureFilename);
                    region = renderer.getRegion();
                }
            }*/
            
            if(region != null){
                if(transform.transformRotationOrScale()){
                    spriteBatch.draw(region, x, y, transform.getOriginX(), transform.getOriginY(), region.getRegionWidth(), region.getRegionHeight(), transform.getScaleX(), transform.getScaleY(), transform.getRotation());
                }
                else{
                    spriteBatch.draw(region, x, y, region.getRegionWidth(), region.getRegionHeight());
                }
            }
        }
        
        if(children.isEmpty()){
            return;
        }
        
        if(transform.transformRotationOrScale()){
            applyTransform(spriteBatch, computeTransform(parentX, parentY));
        }
        
        for(GameObject child : children){
            if(!child.visible){
                continue;
            }
            if(transform.transformRotationOrScale()){
                child.draw(spriteBatch, 0, 0, parentAlpha);
            }
            else{
                child.draw(spriteBatch, x, y, parentAlpha);
            }
        }
        
        if(transform.transformRotationOrScale()){
            resetTransform(spriteBatch);
        }

    }
    
    protected void applyTransform (SpriteBatch batch, Matrix4 transform) {
            oldBatchTransform.set(batch.getTransformMatrix());
            batch.setTransformMatrix(transform);
    }
    
    
    public Matrix4 computeTransform (float parentX, float parentY) {
        if(worldTransform == null){
            worldTransform = new Matrix3();
        }
        if(batchTransform == null){
            batchTransform = new Matrix4();
        }
        if(oldBatchTransform == null){
            oldBatchTransform = new Matrix4();
        }
        
        //Matrix3 temp = worldTransform;

        TransformComponent comp = (TransformComponent) getComponent("TransformComponent");
        float originX = comp.getOriginX();
        float originY = comp.getOriginY();
        float rotation = comp.getRotation();
        float scaleX = comp.getScaleX();
        float scaleY = comp.getScaleY();

        Matrix3 localTransform = new Matrix3();
        //System.out.println(localTransform);
        if (originX != 0 || originY != 0)
            localTransform.setToTranslation(originX, originY);
        else
            localTransform.idt();
        if (rotation != 0) localTransform.rotate(rotation);
        if (scaleX != 1 || scaleY != 1) localTransform.scale(scaleX, scaleY);
        if (originX != 0 || originY != 0) localTransform.translate(-originX, -originY);
        localTransform.trn(parentX+comp.getX(), parentY+comp.getY());

        // Find the first parent that transforms.
        GameObject parentGameObject = parent;
        
        while(parentGameObject != null) {
            TransformComponent parentComp = (TransformComponent) parentGameObject.getComponent("TransformComponent");
            if (parentComp.transformRotationOrScale()){
                break;
            }
            parentGameObject = parentGameObject.parent;
        }

        if (parentGameObject != null) {
                worldTransform.set(parentGameObject.worldTransform);
                worldTransform.mul(localTransform);
        } else {
                worldTransform.set(localTransform);
        }

        batchTransform.set(worldTransform);
        return batchTransform;
    }

    protected void resetTransform (SpriteBatch batch) {
        batch.setTransformMatrix(oldBatchTransform);
    }
    
    public String getIconFilename(){
        if(componentsOrder.size() == 1){
            return components.get(componentsOrder.get(0)).getIconFilename();
        }
        else{
            return components.get(componentsOrder.get(1)).getIconFilename();
        }
    }

    public List<InspectorPanel> createInspectorPanels(){
        List<InspectorPanel> panels = super.createInspectorPanels();
        
        InspectorPanel panel = GameObjectPanel.instance();
        panel.setInspector(Inspector.getRightInspector());
        panels.add(panel);
        
        for(String componentName : componentsOrder){
            GameComponent comp = components.get(componentName);
            
            panel = comp.getPanel();
            if(panel == null){
                continue;
            }
            panel.setInspector(Inspector.getRightInspector());
            panels.add(panel);
        }
        //panels.add(new MapScenePanel(Inspector.getRightInspector()));
        return panels;
    }
    
    public void onDrawGizmos(SpriteBatch batch, ShapeRenderer shapeRenderer){
        
    }
    
    public void onDrawGizmosSelected(SpriteBatch batch, ShapeRenderer shapeRenderer){
        
    }
    
    public GraphicsScene getScene(){
        return scene;
    }
}

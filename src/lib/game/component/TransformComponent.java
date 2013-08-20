/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.component;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class TransformComponent extends GameComponent{
    
    private float x, y;
    private float rotation;
    private float scaleX, scaleY;
    private float originX, originY;
    private boolean transformRotationOrScale;
    
    Matrix3 localTransform;
    private Vector2 computeVector;  
    
    transient boolean needComputeLocalTransform;
    
    public TransformComponent(GameObject gameObject) {
        super(gameObject);
        
        needComputeLocalTransform = true;
        
        x = 0;
        y = 0;
        rotation = 0;
        scaleX = 1;
        scaleY = 1;
        originX = 0;
        originY = 0;
        computeVector = new Vector2();
        computeLocalTransform();
    }
    
    public Matrix3 getLocalTransform(){
        if(needComputeLocalTransform){
            computeLocalTransform();
        }
        return localTransform;
    }
    
    private void computeLocalTransform(){
        if(localTransform == null){
            localTransform = new Matrix3();
        }
        
        if (originX != 0 || originY != 0)
            localTransform.setToTranslation(originX, originY);
        else
            localTransform.idt();
        if (rotation != 0) localTransform.rotate(rotation);
        if (scaleX != 1 || scaleY != 1) localTransform.scale(scaleX, scaleY);
        if (originX != 0 || originY != 0) localTransform.translate(-originX, -originY);
        localTransform.trn(x, y);
        needComputeLocalTransform = false;
    }
    
	/** Transforms the specified point in the actor's coordinates to be in the parent's coordinates. */
	public Vector2 localToParentCoordinates(Vector2 localCoords){
            localCoords.mul(getLocalTransform());
            return localCoords;
	}

	public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
            parentCoords.mul(getLocalTransform().inv());
            return parentCoords;
	}
        
        public Vector2 localToAscendantCoordinates(GameObject other, Vector2 localCoords) {
            GameObject obj = gameObject;
            while(obj.parent != null) {
                TransformComponent transform = (TransformComponent) obj.getComponent("TransformComponent");
                transform.localToParentCoordinates(localCoords);
                obj = obj.parent;
                if(obj == other) break;
            }
            return localCoords;
	}
    
	public Vector2 localToDescendantCoordinates (GameObject other, Vector2 localCoords) {
            GameObject parent = other.parent;
            //if(parent == null){ return localCoords; }
            if (parent == null) throw new IllegalArgumentException("Child is not a descendant: " + other);
            // First convert to the actor's parent coordinates.
            if (parent != gameObject) localToDescendantCoordinates(parent, localCoords);

            TransformComponent transform = (TransformComponent) other.getComponent("TransformComponent");
            // Then from each parent down to the descendant.
            transform.parentToLocalCoordinates(localCoords);

            return localCoords;
	}
        
	public Vector2 sceneToLocalCoordinates(Vector2 stageCoords) {
            if(gameObject.parent == null) return stageCoords;

            TransformComponent transform = (TransformComponent) gameObject.parent.getComponent("TransformComponent");
            transform.sceneToLocalCoordinates(stageCoords);
            parentToLocalCoordinates(stageCoords);
            return stageCoords;
	}

	public Vector2 localToSceneCoordinates (Vector2 localCoords) {
		return localToAscendantCoordinates(null, localCoords);
	}
        

    
        /*
	public GameObject hit (float x, float y) {
            RendererComponent renderer = (RendererComponent)gameObject.getComponent("RendererComponent");
            float width = 24;
            float height = 24;
            if(renderer != null){
                width = renderer.getRegion().getRegionWidth();
                height = renderer.getRegion().getRegionHeight();
            }
            //System.out.println(x);
            return x >= 0 && x < width && y >= 0 && y < height ? gameObject : null;
	}*/
        
    public float getX(){
        return x;
    }
    
    public float getGlobalX(){
        computeVector.set(0, 0);
        
        localToAscendantCoordinates(gameObject.getScene().getRoot(), computeVector);
            
        return computeVector.x;
    }
    
    public void setX(float x){
        this.x = x;
        localTransformChanged();
        //needComputeLocalTransform = true;
    }
    
    public float getY(){
        return y;
    }
    
    public float getGlobalY(){
        computeVector.set(0, 0);
        return localToAscendantCoordinates(gameObject.getScene().getRoot(), computeVector).y;
    }
    
    public void setY(float y){
        this.y = y;
        localTransformChanged();
        //needComputeLocalTransform = true;
    }
    
    public float getOriginX(){
        return originX;
    }
    
    public float getOriginY(){
        return originY;
    }
    
    public void setOrigin(float originX, float originY){
        this.originX = originX;
        this.originY = originY;
        localTransformChanged();
        // = true;
    }
    
    public float getRotation(){
        return rotation;
    }
    
    public float getRotation(GameObject other){
        float rot = getRotation();
        GameObject actor = gameObject.parent;
        while (actor.parent != null) {
            TransformComponent transform = (TransformComponent) actor.getComponent("TransformComponent");
            rot += transform.getRotation();
            actor = actor.parent;
            if(actor == other) break;
        }
        return rot;
    }
    

    
    public void setRotation(float rotation){
        this.rotation = rotation;
        localTransformChanged();
    }
    
    public float getScaleX(){
        return scaleX;
    }
    
    public float getScaleX(GameObject other){
        float sx = getScaleX();
        GameObject actor = gameObject.parent;
        while (actor.parent != null) {
            TransformComponent transform = (TransformComponent) actor.getComponent("TransformComponent");
            sx *= (transform.getScaleX());
            actor = actor.parent;
            if(actor == other) break;
        }
        return sx;
    }
    
    public void setScaleX(float scaleX){
        this.scaleX = scaleX;
        localTransformChanged();
    }
    
    public float getScaleY(){
        return scaleY;
    }
    
    public float getScaleY(GameObject other){
        float sy = getScaleY();
        GameObject actor = gameObject.parent;
        while (actor.parent != null) {
            TransformComponent transform = (TransformComponent) actor.getComponent("TransformComponent");
            sy *= (transform.getScaleY());
            actor = actor.parent;
            if(actor == other) break;
        }
        return sy;
    }
    
    public void setScaleY(float scaleY){
        this.scaleY = scaleY;
        localTransformChanged();
    }
    
    public boolean transformRotationOrScale(){
        return transformRotationOrScale;
    }
    
    public String getIconFilename(){
        return "transform.png";
    }
    
    public InspectorPanel getPanel() {
        return TransformPanel.instance();
    }
    
    private void localTransformChanged(){
        transformRotationOrScale = (rotation != 0) || (scaleX != 1) || (scaleY != 1);
        
        if(transformRotationOrScale){
            for(GameObject child : gameObject.children){
                //TransformComponent transfrom = (TransformComponent) child.getComponent("TransformComponent");
                if(!child.children.isEmpty()){
                    child.needComputeChildrenBounds = true;
                }
            }
        }
        
        GameObject obj = gameObject;
        while(obj != null){
            obj.needComputeChildrenBounds = true;
            obj = obj.parent;
        }
        
        needComputeLocalTransform = true;
    }
    
}

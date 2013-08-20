/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.IOException;
import java.io.ObjectInputStream;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.scene.GameObject;

/**
 *
 * @author Gaetan
 */
public class RendererComponent extends GameComponent{
    
    public static Texture TEX;
    
    public String textureFilename;
    transient private TextureRegion textureRegion;

    public RendererComponent(GameObject gameObject, String textureFilename) {
        super(gameObject);
        setTexture(textureFilename);
    }
    
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        System.out.println("salut");
    }

    
    public void setTexture(String textureFilename){
        this.textureFilename = textureFilename;
        
        if(TEX == null){
            TEX = new Texture(textureFilename);
        }
        
        textureRegion = new TextureRegion(TEX);
    }
    
    public TextureRegion getRegion(){
        return textureRegion;
    }
    
    public String getIconFilename(){
        return "renderer.png";
    }
    
    public InspectorPanel getPanel() {
        return RendererPanel.instance();
    }
}

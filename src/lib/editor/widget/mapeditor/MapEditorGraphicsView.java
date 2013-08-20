/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.mapeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import lib.editor.data.game.MapData;
import lib.editor.widget.graphics.GraphicsView;
import lib.editor.widget.mapeditor.tools.SelectTool;
import lib.game.scene.GameObject;
import lib.game.scene.GraphicsScene;

/**
 *
 * @author gaetan
 */
public class MapEditorGraphicsView extends GraphicsView{

    private static MapEditorGraphicsView instance;
    public GraphicsScene scene;
    
    //IsoGrid grid;
    final Vector3 camPos = new Vector3();

    public MapData data;
    
    
    private MapEditorGraphicsView() {
        super(new MapEditorApp(), false);
    }
    
    public static MapEditorGraphicsView instance(){
        if(instance == null){
            instance = new MapEditorGraphicsView();
        }
        return instance;
    }
    
    public void setup(MapData data){
        this.data = data;
        clear();
        
        if(data == null){
            return;
        }
        
        scene.setup(data.sceneRoot);
    }
    
    public void resize(int width, int height){
        scene.resize(width, height);
    }
    
    public void clear(){
        scene.setup(null);
        //scene.clear();
        //grid.clear();
    }

    
    /*
    public void resizeGrid(int width, int height){
        grid.width = width;
        grid.height = height;
    }*/
    
    public void update(){        
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);        
        scene.update();
        SelectTool.instance().update(scene.getSpriteBatch());
    }

    

}

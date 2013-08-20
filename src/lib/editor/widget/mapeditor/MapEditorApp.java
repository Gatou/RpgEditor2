/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.mapeditor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import lib.editor.mgr.WindowMgr;
import lib.editor.widget.mapeditor.tools.SelectTool;
import lib.game.scene.GameObject;
import lib.game.scene.GraphicsScene;

/**
 *
 * @author gaetan
 */
public class MapEditorApp  implements ApplicationListener, InputProcessor {


    public static final Plane XY_PLANE = new Plane(new Vector3(0, 0, 1), 0);
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    
    public void create() {
        WindowMgr.init();
        
        
        Gdx.input.setInputProcessor(this);
        Gdx.graphics.getGL20().glClearColor(0.4f, 0.4f, 0.4f, 1);
        
        MapEditorGraphicsView.instance().scene = new GraphicsScene();
    }

    @Override
    public void resize(int width, int height) {
      Gdx.gl.glViewport(0, 0, width, height);
      MapEditorGraphicsView.instance().resize(width, height);
      WindowMgr.resize(width, height);
      //spriteBatch.getProjectionMatrix().setToOrtho(0, width, height, 0, 0, 1);

      //effect.setPosition(width / 2, height / 2);
    }

    @Override
    public void render() {
        MapEditorGraphicsView.instance().update();
        WindowMgr.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        OrthographicCamera camera = MapEditorGraphicsView.instance().scene.getCamera();
        
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){

                Ray pickRay = camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
                Intersector.intersectRayPlane(pickRay, XY_PLANE, curr);

                if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
                        pickRay = camera.getPickRay(last.x, last.y);
                        Intersector.intersectRayPlane(pickRay, XY_PLANE, delta);			
                        delta.sub(curr);
                        camera.position.add(delta.x, delta.y, delta.z);

                }
                last.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                MapEditorGraphicsView.instance().camPos.set(camera.position);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //SelectTool.instance().hit(screenX, screenY);
        return false;
    }


    
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
}

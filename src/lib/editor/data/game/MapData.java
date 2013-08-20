package lib.editor.data.game;

import java.util.List;
import lib.editor.widget.inspector.InspectorPanel;
import lib.game.scene.GameObject;



public class MapData extends AbstractData{

    //public int width;
    //public int height;
    //public Point2i tileSize;
    //public int maximumHeight;
    //public short[][] tilemap;
    //public short[][] heightmap;

    public GameObject sceneRoot;
    
    public MapData(){
        this(-1, "" , 16, 16);
    }
    
    public MapData(int id, String name, int width, int height) {
        super(id, name);
        sceneRoot = new GameObject(-1, "scene");
        //this.width = width;
        //this.height = height;
        //tilemap = new short[width][height];
        //heightmap = new short[width][height];
        //maximumHeight = 0;
    }

    /*
    public List<InspectorPanel> createInspectorPanels(){
        List<InspectorPanel> panels = super.createInspectorPanels();
        //panels.add(new MapScenePanel(Inspector.getRightInspector()));
        return panels;
    }*/

}

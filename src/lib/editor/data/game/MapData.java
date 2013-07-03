package lib.editor.data.game;



public class MapData extends AbstractData{

    public int width;
    public int height;
    //public Point2i tileSize;
    //public int maximumHeight;
    public short[][] tilemap;
    public short[][] heightmap;

    public MapData(){
        this(-1, "" , 16, 16);
    }
    
    public MapData(int id, String name, int width, int height) {
        super(id, name);
        this.width = width;
        this.height = height;
        tilemap = new short[width][height];
        heightmap = new short[width][height];
        //maximumHeight = 0;
    }
    /*
    public boolean isWall(int x, int y) {
            return getDataTile(x,y).isWall;
    }

    public DataTile getDataTile(int x, int y) {
            return Data.tiles.get(tilemap[x][y]);
    }

    public DataTile getDataHeight(int x, int y) {
            return Data.tiles.get(heightmap[x][y]);
    }*/

}

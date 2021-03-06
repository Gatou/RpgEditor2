/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.Image;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

/**
 *
 * @author gaetan
 */
public class IconManager {
    
    private static IconManager instance;
    public final String SYSTEM_PATH = "/assets/icons/";
    public final String TAB_PATH = "/assets/tab/";
    public final String COMPONENT_PATH = "/assets/component/";
    
    //private Map<String, String> icons;
    private Map<String, ImageIcon> cache;
    
    private IconManager() {
        cache = new Hashtable<String, ImageIcon>();
        //addIconExtension("png", "png.png");
    }
    
    public static IconManager instance(){
        if(instance == null){
            instance = new IconManager();
        }
        return instance;
    }
    
    public ImageIcon getComponentIcon(String filename, boolean disabledVersion) { 
        return getIcon(COMPONENT_PATH, filename, disabledVersion);
    }
    
    public ImageIcon getTabIcon(String filename, boolean disabledVersion) { 
        return getIcon(TAB_PATH, filename, disabledVersion);
    }
    
    public ImageIcon getSystemIcon(String filename, boolean disabledVersion) { 
        return getIcon(SYSTEM_PATH, filename, disabledVersion);
    }
    
    private ImageIcon getIcon(String path, String filename, boolean disabledVersion) {
        if(filename == null){
            return null;
        }
        
        String trueFilename = filename;
        if(disabledVersion){
            trueFilename = filename + "-disabled___";
        }
        
        addIcon(path, filename, trueFilename, disabledVersion);
        
        return cache.get(trueFilename);
    }
    
    private void addIcon(String path, String filename, String trueFilename, boolean disabledVersion){
        try{
        ImageIcon icon = null;
        
            if(!cache.containsKey(trueFilename)){

                if(disabledVersion){
                    ImageIcon baseicon = new ImageIcon(getClass().getResource(path + filename));
                    Image normalImage = baseicon.getImage();

                    Image grayImage = GrayFilter.createDisabledImage(normalImage);
                    icon = new ImageIcon(grayImage);
                }
                else{
                    icon = new ImageIcon(getClass().getResource(path + filename));
                }
                cache.put(trueFilename, icon);
            }
        }
        catch(NullPointerException e){
            //Icon filename doesn't exist
        }
    }
    
    /*
    public void addIconExtension(String ext, String iconFilename){
        icons.put(ext, iconFilename);
    }
    
    public ImageIcon getIconByExtension(String ext){
        return getIcon(icons.get(ext));
    }*/
    
}

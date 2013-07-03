/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.data.editor;

import java.util.ArrayList;
import java.util.List;
import lib.editor.data.game.AbstractData;

/**
 *
 * @author gaetan
 */
public class DataEditorPackage extends AbstractData{
    
    public boolean expanded;
    public List<AbstractData> children;
    
    public DataEditorPackage(int id, String name){
        super(id, name);
        expanded = false;
        children = new ArrayList<AbstractData>();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.list.list;

import java.util.List;
import lib.editor.widget.list.item.ListItem;

/**
 *
 * @author gaetan
 */
public class DatabaseList<T> extends AbstractList{
    
    List<T> datas;
    
    public void setup(List<T> datas){
        this.datas = datas;
        refresh();
    }
    
    public void refresh(){
        clear();
        
        if(datas == null){
            return;
        }
        
        for(T data : datas){
            addItem(makeItem(data));
        }
    }
    
    public ListItem makeItem(T data){
        ListItem item = new ListItem("Undefined item", null);
        return item;
    }
    
    
}

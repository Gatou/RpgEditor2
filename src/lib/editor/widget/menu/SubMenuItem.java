/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;

/**
 *
 * @author Gaetan
 */
public class SubMenuItem extends JMenu{

    public SubMenuItem(String text) {
        super(text);
    }
    
    public void checkActionsEnabled(){
        for(Component component : getComponents()){
            if(component instanceof MenuItem){
                ((MenuItem) component).checkEnabled();
            }
            else if(component instanceof SubMenuItem){
                ((SubMenuItem) component).checkActionsEnabled();
            }
        }
    }
    
}
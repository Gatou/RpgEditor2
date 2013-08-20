/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import java.awt.Component;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Gaetan
 */
public class Menu extends JPopupMenu{

    public Menu() {
        setDefaultLightWeightPopupEnabled(false);
        setLightWeightPopupEnabled(false);
        addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                checkActionsEnabled();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
        
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



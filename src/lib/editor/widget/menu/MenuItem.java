/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author gaetan
 */
public class MenuItem extends JMenuItem{
    
    
    
    public MenuItem(String text, ImageIcon icon, KeyStroke shortcut){
        super(text, icon);
        setAccelerator(shortcut);
        init();
    }
    
    public MenuItem(){
        init();
    }
    
    private void init(){
    }
    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import lib.editor.mgr.IconManager;
import lib.editor.mgr.ProjectManager;
import lib.editor.util.Shortcut;

/**
 *
 * @author gaetan
 */
public class MenuItem extends JMenuItem{
    
    private Shortcut shortcut;
    
    public MenuItem(){
    }
    
    public MenuItem(String text, String iconFilename){
        this(text, iconFilename, null, null, false, null);
    }
    
    public MenuItem(String text, String iconFilename, KeyStroke shortcut, String shortcutName, boolean shortcutAutorepeat, JComponent source){
        super(text, IconManager.instance().getSystemIcon(iconFilename, false));
        
        
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                execute();
            }
        });
          
        if(shortcut != null){
            setAccelerator(shortcut);
            this.shortcut = new Shortcut(source, shortcut, shortcutName, shortcutAutorepeat, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        execute();
                    }
            });
        }

    }
    
    public void checkEnabled() {
        setEnabled(isEnabled());
    }
    
    public boolean isEnabled(){
        return ProjectManager.instance().isProjectOpen();
    }
    
    public void execute(){
        
    }
    
}

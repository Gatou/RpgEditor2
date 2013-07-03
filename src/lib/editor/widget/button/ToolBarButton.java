/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.button;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author gaetan
 */
public class ToolBarButton extends JButton{
    
    
    public ToolBarButton(){
        
        setMinimumSize(new Dimension(28, 28));
        setMaximumSize(new Dimension(28, 28));
        setPreferredSize(new Dimension(28, 28));
        setOpaque(false);
    }
    
}

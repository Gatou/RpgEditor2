package lib.editor.widget.textfield;


import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class IconTextField extends JTextField{
 
    protected Icon icon;//=new ImageIcon("images\\omag\\user.png");

    
    public IconTextField(){
        icon = new ImageIcon("images\\omag\\user.png");
        
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        int y = getHeight()/2 - icon.getIconHeight()/2;
        
        icon.paintIcon(null, g, 5, y);
    }
 
    public Insets getInsets() {
        Insets i = super.getInsets();
        i.left += icon.getIconWidth() + 2;
        return i;
    }
    
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    
    public Icon getIcon(){
        return icon;
    }
    
}
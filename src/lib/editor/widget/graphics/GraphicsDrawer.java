/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author gaetan
 */
public class GraphicsDrawer {
    
    public static final Color defaultInternalColor1 = new Color(20,20,20);
    public static final Color defaultInternalColor2 = new Color(10,10,10);
    public static final Color defaultBorderColor1 = new Color(40,40,40);
    public static final Color defaultBorderColor2 = new Color(30,30,30);
    
    public static void drawComponentBackground(Graphics g, Component comp){
        GradientPaint internalGradient = new GradientPaint(0, 0,
                defaultInternalColor1, 0, comp.getHeight(),
                defaultInternalColor2);
        GradientPaint borderGradient = new GradientPaint(0, 0,
                defaultBorderColor1, 0, comp.getHeight(),
                defaultBorderColor2);
        drawRoundedRect(g, 0, 0, comp.getWidth(), comp.getHeight(), internalGradient, borderGradient);
    }

    public static void drawFocusedComponentBackground(Graphics g, Component comp){
        GradientPaint internalGradient = new GradientPaint(0, 0,
                new Color(50,50,50), 0, comp.getHeight(),
                new Color(40,40,40));
        GradientPaint borderGradient = new GradientPaint(0, 0,
                new Color(200,200,200), 0, comp.getHeight(),
                new Color(100,100,100));
        drawRoundedRect(g, 0, 0, comp.getWidth(), comp.getHeight(), internalGradient, borderGradient);
    }
    
    public static void drawRoundedRect(Graphics g, int x, int y, int width, int height){
        GradientPaint internalGradient = new GradientPaint(0, 0,
                defaultInternalColor1, 0, height,
                defaultInternalColor2);
        GradientPaint borderGradient = new GradientPaint(0, 0,
                defaultBorderColor1, 0, height,
                defaultBorderColor2);
        drawRoundedRect(g, x, y, width, height, internalGradient, borderGradient);
    }
    
    public static void drawRoundedRect(Graphics g, int x, int y, int width, int height, GradientPaint internalGradient, GradientPaint borderGradient){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(borderGradient);
        g2d.fillRoundRect(x, y, width, height, 15, 15);
        

        g2d.setPaint(internalGradient);
        g2d.fillRoundRect(x+4, y+4, width-8, height-8, 15, 15);
    }

    
    
}

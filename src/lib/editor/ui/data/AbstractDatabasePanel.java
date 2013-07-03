/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.data;

import lib.editor.mgr.SaveMgr;

/**
 *
 * @author gaetan
 */
public abstract class AbstractDatabasePanel extends javax.swing.JPanel {
    
    public void selected(){
    }
    
    public void currentDataModified(){
        SaveMgr.requestSaveEnabled();
    }
    
}

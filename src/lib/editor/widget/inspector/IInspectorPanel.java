/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import lib.editor.data.game.AbstractData;
import lib.editor.ui.data.AbstractDatabasePanel;

/**
 *
 * @author Gaetan
 */
public interface IInspectorPanel {
    
    void setup(AbstractDatabasePanel dataPanel, AbstractData data);
    public void refresh();
    
}

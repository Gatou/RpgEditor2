package lib.editor.ui;

import lib.editor.ui.assetmanager.AssetManagerWindow;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import lib.editor.data.DataInfos;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.IconManager;
import lib.editor.mgr.ProjectManager;
import lib.editor.mgr.SaveMgr;
import lib.editor.ui.data.AbstractDatabasePanel;
import lib.editor.ui.data.DatabasePanel;
import lib.editor.util.Cst;
import lib.editor.util.SwingUtil;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetan
 */
public class MainWindow extends javax.swing.JFrame {
    
    private static MainWindow instance;
    public Map<String, DatabasePanel> dataPanels;
    /**
     * Creates new form NewJFrame
     */
            
    private MainWindow() {
        setLocationRelativeTo( null );
    }
    
    public static MainWindow getInstance(){
        if(instance == null){
            instance = new MainWindow();
        }
        return instance;
    }
    
    public void init(){
        initComponents();
        
        //mapEditor = new MapEditorGraphicsView();
        //WidgetMgr.MAP_EDITOR = mapEditor;
        //jPanel3.add(mapEditor.getCanvas());
        //mapEditor.init();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        setTitle(AppMgr.getNameVersion());
        //WidgetMgr.MAIN_WINDOW = this;
        
        setProjectStateEnabled(false);
        AppMgr.init();
        
        //WidgetMgr.MAP_TREE.setup();
        
        
        //Inspector.init();
        //propertyCollapsiblePane.add();
        //jXTaskPane1.add(jTable1);
        AbstractButton[] saveButtons = new AbstractButton[]{fileSave, toolBarSave};
        SaveMgr.init(saveButtons);


        
    }
    
    
    public void saveSettings(Properties prop){
        String prefix = "MainWindow_";
        SwingUtil.saveWindowBasics(prop, prefix, this);
        prop.put(prefix + "maximized", String.valueOf(this.getExtendedState() & JFrame.MAXIMIZED_BOTH));
    }
    
    public void loadSettings(Properties prop){
        String prefix = "MainWindow_";
        SwingUtil.loadWindowBasics(prop, prefix, this);
        if(Integer.parseInt(prop.getProperty(prefix + "maximized")) != 0){
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
    }
    
    public void setProjectStateEnabled(boolean enabled){
        //Enabled tool bar, except new and open project
        for(int i=2; i<mainToolBar.getComponentCount(); i++){
            if(!(mainToolBar.getComponent(i) instanceof JToolBar.Separator)){
                mainToolBar.getComponent(i).setEnabled(enabled);
            }
        }
        
        //Enabled file menu, except new, open project and exit
        for(int i=2; i<fileMenu.getMenuComponentCount()-1; i++){
            if(!(fileMenu.getMenuComponent(i) instanceof JPopupMenu.Separator)){
                fileMenu.getMenuComponent(i).setEnabled(enabled);
            }
        }
        
        //Enabled all the other menu in the mainMenuBar
        for(int i=1; i<mainMenuBar.getComponentCount(); i++){
            JMenu menu = (JMenu) mainMenuBar.getComponent(i);
            for(Component comp : menu.getMenuComponents()){
                if(!(comp instanceof JPopupMenu.Separator)){
                    comp.setEnabled(enabled);
                }
            }
        }
        
    }

    private void openProject(){
        String filterText = AppMgr.NAME + " (*." + AppMgr.getExtension("project file") + ")";
        File result = SwingUtil.getFileChoice(this, "", new FileNameExtensionFilter(filterText, AppMgr.getExtension("project file")), "Open project");
        if(result != null){
            String projectPath = result.getParent();
            if(projectPath != null){
                ProjectManager.openProject(projectPath);
            }
        }
    }
    
    private void exit(){
        AppMgr.saveSettings();
        ProjectManager.closeProject();
        dispose();
    }
    
    public void undo(){
        System.out.println("undo");
    }
    
    public void redo(){
        System.out.println("redo");
    }
    
    public void setActionEnabled(String type, boolean enabled){
        if(type.equals("undo")){
            toolBarUndo.setEnabled(enabled);
            editUndo.setEnabled(enabled);
        }
        else if(type.equals("redo")){
            toolBarRedo.setEnabled(enabled);
            editRedo.setEnabled(enabled);
        }
    }
    
    public void openWindow(String windowName){
        if(windowName.equals("resourceManager")){
            //System.out.println(AssetManagerWindow.getAssetFilename(this, "Tiles/Graphics/Battlers/Dummy_Character.png", Cst.VALID_SOUND_FORMAT));
            //(new AssetManagerWindow(this, true, "", Cst.ALL_ASSET_FORMAT)).setVisible(true);
            AssetManagerWindow window = AssetManagerWindow.getInstance();
            window.setup(true, "", Cst.ALL_ASSET_FORMAT);
            window.setVisible(true);
        }
    }
    
    public void refresh(){
        dataPanels = new HashMap<String, DatabasePanel>();
        mainTabbedPane.removeAll();
    
        Gson gson = new Gson();
        List<DataInfos> dataInfosList = null;
        try {
            //System.out.println(ProjectManager.getProjectPath());
            String path = new File(ProjectManager.getProjectPath(), "Data/data.properties").getAbsolutePath();
            
            Type listOfTestObject = new TypeToken<List<DataInfos>>(){}.getType();
            dataInfosList = gson.fromJson(new FileReader(path), listOfTestObject);
            } catch (JsonSyntaxException e) {
                    e.printStackTrace();
            } catch (JsonIOException e) {
                    e.printStackTrace();
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            }
        
        for(DataInfos dataInfos : dataInfosList){
            JPanel panelkaka = new JPanel();
            panelkaka.setLayout(new javax.swing.BoxLayout(panelkaka, javax.swing.BoxLayout.LINE_AXIS));
        
            ImageIcon icon = IconManager.instance().getTabIcon(dataInfos.icon_filename, false);
            mainTabbedPane.addTab(dataInfos.name, icon, panelkaka, "Does nothing");
            
            Class<?> dataClass = null;
            try {
                dataClass = Class.forName(dataInfos.data_class);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Class<?> panelClass = null;
            try {
                panelClass = Class.forName(dataInfos.panel_class);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object object = null;
            try {
                object = panelClass.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DatabasePanel panel = (DatabasePanel) object;
            panel.setup(dataInfos.name, dataInfos.icon_filename, dataClass);
            panelkaka.add(panel);
            dataPanels.put(dataInfos.name, panel);
        }
        
        
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                JPanel panel = (JPanel) sourceTabbedPane.getComponentAt(index);
                //if(panel != null){
                    
                //}
                if(panel != null){
                    AbstractDatabasePanel dataPanel = (AbstractDatabasePanel) panel.getComponent(0);
                    dataPanel.selected();
                }
                //System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            }
        };
        mainTabbedPane.addChangeListener(changeListener);
        ((AbstractDatabasePanel)((JPanel)mainTabbedPane.getComponentAt(0)).getComponent(0)).selected();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainToolBar = new javax.swing.JToolBar();
        toolBarNew = new lib.editor.widget.button.ToolBarButton();
        toolBarOpen = new lib.editor.widget.button.ToolBarButton();
        toolBarSave = new lib.editor.widget.button.ToolBarButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        toolBarUndo = new lib.editor.widget.button.ToolBarButton();
        toolBarRedo = new lib.editor.widget.button.ToolBarButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        toolBarButton1 = new lib.editor.widget.button.ToolBarButton();
        mainPanel = new javax.swing.JPanel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileNew = new lib.editor.widget.menu.MenuItem();
        fileOpen = new lib.editor.widget.menu.MenuItem();
        fileClose = new lib.editor.widget.menu.MenuItem();
        fileSave = new lib.editor.widget.menu.MenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        fileExit = new lib.editor.widget.menu.MenuItem();
        editMenu = new javax.swing.JMenu();
        editUndo = new lib.editor.widget.menu.MenuItem();
        editRedo = new lib.editor.widget.menu.MenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItem2 = new lib.editor.widget.menu.MenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuItem1 = new lib.editor.widget.menu.MenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        toolBarNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        toolBarNew.setToolTipText("Create a new project.");
        toolBarNew.setFocusable(false);
        toolBarNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarNewActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarNew);

        toolBarOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        toolBarOpen.setToolTipText("Open an existing project.");
        toolBarOpen.setFocusable(false);
        toolBarOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarOpenActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarOpen);

        toolBarSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        toolBarSave.setToolTipText("Save the current project.");
        toolBarSave.setFocusable(false);
        toolBarSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(toolBarSave);
        mainToolBar.add(jSeparator1);

        toolBarUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        toolBarUndo.setToolTipText("Undo the last action.");
        toolBarUndo.setFocusable(false);
        toolBarUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarUndoActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarUndo);

        toolBarRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        toolBarRedo.setToolTipText("Redo the last undo action.");
        toolBarRedo.setFocusable(false);
        toolBarRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarRedoActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarRedo);
        mainToolBar.add(jSeparator3);

        toolBarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/resource.png"))); // NOI18N
        toolBarButton1.setToolTipText("Open the assets manager.");
        toolBarButton1.setFocusable(false);
        toolBarButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarButton1ActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarButton1);

        getContentPane().add(mainToolBar, java.awt.BorderLayout.PAGE_START);

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));
        jPanel12.add(jPanel13);

        mainTabbedPane.addTab("tab1", jPanel12);

        mainPanel.add(mainTabbedPane);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        fileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        fileNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        fileNew.setText("New Project...");
        fileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNewActionPerformed(evt);
            }
        });
        fileMenu.add(fileNew);

        fileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        fileOpen.setText("Open Project...");
        fileOpen.setPreferredSize(null);
        fileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOpenActionPerformed(evt);
            }
        });
        fileMenu.add(fileOpen);

        fileClose.setText("Close Project");
        fileClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileCloseActionPerformed(evt);
            }
        });
        fileMenu.add(fileClose);

        fileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        fileSave.setText("Save Project");
        fileMenu.add(fileSave);
        fileMenu.add(jSeparator4);

        fileExit.setText("Exit");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileExit);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        editUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        editUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        editUndo.setText("Undo");
        editUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUndoActionPerformed(evt);
            }
        });
        editMenu.add(editUndo);

        editRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        editRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        editRedo.setText("Redo");
        editRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRedoActionPerformed(evt);
            }
        });
        editMenu.add(editRedo);

        mainMenuBar.add(editMenu);

        jMenu2.setText("Tools");

        menuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        menuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/resource.png"))); // NOI18N
        menuItem2.setText("Assets Manager");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(menuItem2);

        mainMenuBar.add(jMenu2);

        jMenu1.setText("Game");

        menuItem1.setText("Open Game Folder");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem1);

        mainMenuBar.add(jMenu1);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       exit();
    }//GEN-LAST:event_formWindowClosing

    private void toolBarNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarNewActionPerformed
        new ProjectWindow(this, true).setVisible(true);
    }//GEN-LAST:event_toolBarNewActionPerformed

    private void toolBarOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarOpenActionPerformed
        openProject();
    }//GEN-LAST:event_toolBarOpenActionPerformed

    private void toolBarUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarUndoActionPerformed
        undo();
    }//GEN-LAST:event_toolBarUndoActionPerformed

    private void toolBarRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarRedoActionPerformed
        redo();
    }//GEN-LAST:event_toolBarRedoActionPerformed

    private void fileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNewActionPerformed
        new ProjectWindow(this, true).setVisible(true);
    }//GEN-LAST:event_fileNewActionPerformed

    private void fileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOpenActionPerformed
        openProject();
    }//GEN-LAST:event_fileOpenActionPerformed

    private void fileCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileCloseActionPerformed
        ProjectManager.closeProject();
    }//GEN-LAST:event_fileCloseActionPerformed

    private void fileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitActionPerformed
        exit();
    }//GEN-LAST:event_fileExitActionPerformed

    private void editUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUndoActionPerformed
        undo();
    }//GEN-LAST:event_editUndoActionPerformed

    private void editRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRedoActionPerformed
        redo();
    }//GEN-LAST:event_editRedoActionPerformed

  
    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        try {
            Desktop.getDesktop().open(new File(ProjectManager.getProjectPath()));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void toolBarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarButton1ActionPerformed
        openWindow("resourceManager");
    }//GEN-LAST:event_toolBarButton1ActionPerformed

    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        openWindow("resourceManager");
    }//GEN-LAST:event_menuItem2ActionPerformed



    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private lib.editor.widget.menu.MenuItem editRedo;
    private lib.editor.widget.menu.MenuItem editUndo;
    private lib.editor.widget.menu.MenuItem fileClose;
    private lib.editor.widget.menu.MenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private lib.editor.widget.menu.MenuItem fileNew;
    private lib.editor.widget.menu.MenuItem fileOpen;
    private lib.editor.widget.menu.MenuItem fileSave;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JToolBar mainToolBar;
    private lib.editor.widget.menu.MenuItem menuItem1;
    private lib.editor.widget.menu.MenuItem menuItem2;
    private lib.editor.widget.button.ToolBarButton toolBarButton1;
    private lib.editor.widget.button.ToolBarButton toolBarNew;
    private lib.editor.widget.button.ToolBarButton toolBarOpen;
    private lib.editor.widget.button.ToolBarButton toolBarRedo;
    private lib.editor.widget.button.ToolBarButton toolBarSave;
    private lib.editor.widget.button.ToolBarButton toolBarUndo;
    // End of variables declaration//GEN-END:variables

}

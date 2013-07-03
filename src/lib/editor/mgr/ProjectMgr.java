/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lib.editor.ui.MainWindow;
import lib.editor.util.ProjectError;

/**
 *
 * @author gaetan
 */
public class ProjectMgr {
    
    private static Properties properties;
    
    public static final String[] assetsFolders = {"Scripts", "Animations", "Sounds", "Characters", "Tiles",
        "System", "Pictures"};
    private static List<String> allPath = new ArrayList<String>();
    private static String projectPath, assetsPath, settingsPath, dataPath;
    //private static String ;
    
    public static String getProjectPath(){
        return projectPath;
    }
    
    public static String getAssetsPath(){
        return assetsPath;
    }
    
    public static String getSettingsPath(){
        return settingsPath;
    }
    
    public static String getDataPath(){
        return dataPath;
    }
    
    //public static String getDataEditorPath(){
    //    return dataEditorPath;
    //}
    
    public static String getDataPath(String dataName){
        File file = new File(getDataPath(), dataName);
        if(!file.exists()){
            file.mkdir();
        }
        return file.getAbsolutePath();
    }
    
    public static void createNewProject(String path){
        //Create usefull path (project, assets, etc...)
        createPath(path);
        //Create project folders
        (new File(projectPath)).mkdir();
        
        (new File(assetsPath)).mkdir();
        for(String folderName : assetsFolders){
            (new File(assetsPath, folderName)).mkdir();
        }
        
        (new File(settingsPath)).mkdir();
        (new File(dataPath)).mkdir();
        //(new File(dataGamePath)).mkdir();
        //(new File(dataEditorPath)).mkdir();
        //Create the project file
        File file = new File(projectPath, "project." + AppMgr.getExtension("project file"));
        try {
            file.createNewFile();
              FileWriter fstream = new FileWriter(file);
              BufferedWriter out = new BufferedWriter(fstream);
              out.write(AppMgr.getNameVersion());
              out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProjectMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        openProject(projectPath);
        /*
        #---
        file = open(join(ProjectMgr.project_path(), "." + AppMgr.extension("project file")), "w") #create .proj file
        file.write(AppMgr.name_version())
        file.close()
        #--
        ProjectMgr.open_project(ProjectMgr.project_path())*/
                }
    
    public static void openProject(String path){
        closeProject();
        createPath(path);
        checkProjectValid();
        
        if(projectPath != null){
            MainWindow.getInstance().setTitle((new File(projectPath)).getName() + " - " + AppMgr.getNameVersion());
            DataMgr.init();
            MainWindow.getInstance().setProjectStateEnabled(true);
            loadSettings();
            MainWindow.getInstance().refresh();
            MainWindow.getInstance().setVisible(true);
            
         
            
        }
        
        SaveMgr.clear();
    }
    
    public static void closeProject(){
        if(projectPath != null){
            saveSettings();
            MainWindow.getInstance().setTitle(AppMgr.getNameVersion());
        }
        MainWindow.getInstance().setProjectStateEnabled(false);
        assetsPath = null;
        settingsPath = null;
        projectPath = null;
        dataPath = null;
        properties = null;
    }
    
    private static void createPath(String path){
        allPath.clear();
        projectPath = (new File(path)).getAbsolutePath();
        assetsPath = (new File(projectPath, "Assets")).getAbsolutePath();
        settingsPath = (new File(projectPath, "ProjectSettings")).getAbsolutePath();
        dataPath = (new File(projectPath, "Data")).getAbsolutePath();
        
        allPath.add(projectPath);
        allPath.add(assetsPath);
        allPath.add(settingsPath);
        allPath.add(dataPath);
    }
    
    private static void checkProjectValid(){
        List<ProjectError> errors = new ArrayList<ProjectError>();
        File file;
        
        for(String path : allPath){
            file = new File(path);
            if(!file.exists()){
                errors.add(new ProjectError(ProjectError.FOLDER_MISSING, path));
            }
        }
        /*
        String errorMessage = "";
        
        if(!(new File(projectPath).exists())){
            errorMessage = "This project is invalid.\nCan't find project folder (" + projectPath + ")";
        }
        else if(!(new File(assetsPath).exists())){
            errorMessage = "This project is invalid.\nCan't find assets folder (" + assetsPath + ")";
        }
        else if(!(new File(dataGamePath).exists())){
            errorMessage = "This project is invalid.\nCan't find assets folder (" + dataGamePath + ")";
        }
        else if(!(new File(dataEditorPath).exists())){
            errorMessage = "This project is invalid.\nCan't find assets folder (" + dataEditorPath + ")";
        }
        //else if(!(new File(settingsPath).exists())){
        //    errorMessage = "This project is invalid.\nCan't find settings folder (" + settingsPath + ")";
        //}
        */
        
        if(errors.size() == 0){ //Valid project

        }
        else{ //Invalid project
            closeProject();
            MainWindow.getInstance().setVisible(true);
            JOptionPane.showMessageDialog(MainWindow.getInstance(), "Can't find folder (" + errors.get(0).path + ")", "Invalid project", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(MainWindow.getInstance(), errorMessage, "Invalid project", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public static File getPropertiesFile(){        
        File settingsFolder = new File(ProjectMgr.getSettingsPath());
        if(!settingsFolder.exists()){
            settingsFolder.mkdir();
        }
        
        File iniFile = new File(ProjectMgr.getSettingsPath(), "settings." + AppMgr.getExtension("settings file"));
        if(!iniFile.exists()){
            try {iniFile.createNewFile();} catch (IOException ex) {Logger.getLogger(ProjectMgr.class.getName()).log(Level.SEVERE, null, ex);}
        }
        return iniFile;
    }
    
    public static Properties getProperties(){
        if(properties == null){
            createProperties();
        }
        return properties;
    }
    
    public static void createProperties(){
        File iniFile = getPropertiesFile();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(iniFile));
        } catch (IOException ex) {
            System.err.println("ProjectMgr -> createProperties() -> failed load properties");
        }
    }
    
    public static void loadSettings(){
        Properties prop = getProperties();
        MainWindow.getInstance().loadSettings(prop);
    }
    
    public static void saveSettings(){
        Properties prop = getProperties();
        File iniFile = getPropertiesFile();
        
        MainWindow.getInstance().saveSettings(prop);
        
        try {
            prop.store(new FileOutputStream(iniFile), AppMgr.getNameVersion() + " project settings");
        } catch (IOException ex) {
            System.err.println("ProjectMgr -> saveSettings() -> failed to save settings");
        }
    }
    
    public static boolean isProjectOpen(){
        return projectPath != null;
    }
}



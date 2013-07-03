/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.data;

/**
 *
 * @author gaetan
 */
public class DataInfos {

    public String name;
    public String icon_filename;
    
    public String panel_class;
    public String data_class;
    
    
    public static String getSavePrefixName(String dataName){
        String result = dataName;
        if(dataName.charAt(dataName.length()-1) == 's'){
            result = dataName.substring(0, dataName.length()-1);
        }
        return result;
    }
    
}

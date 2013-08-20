/*
 * Probeme pour afficher l'inspector d'une map deja selectionner (l'inspector de la map a changer vu qu'on a
 * cliquer sur un node de la scene). Il faudrait pouvoir declecnher l'inspector quand on clique sur un item deja
 * selectionner. mais attention a ne pas rafrachier 2 fois l'inspector a cause de ça
 * 
 * Trouver un autre moyen pour sauvegarder les gameObject car actuellement c pas cool
 * + ça n'instancie pas les variable transient quand deserialization
 */
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import lib.editor.mgr.AppMgr;
import lib.editor.ui.MainWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
  public static void main (String[] args) {
      
    //for(String arg : args){
    //    System.out.println(arg);
    //}
    
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
      if ("Nimbus".equals(info.getName())) {
        try {
          UIManager.setLookAndFeel(info.getClassName());
        } catch (Throwable ignored) {
        }
        break;
      }
    }
    EventQueue.invokeLater(new Runnable() {
      public void run () {
          MainWindow win = MainWindow.getInstance();
          win.init();
          //win.setVisible(true);
          AppMgr.loadSettings();
          win.setVisible(true);
      }
    });
  }
}

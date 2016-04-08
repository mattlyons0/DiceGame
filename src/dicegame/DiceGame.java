package dicegame;

import javax.swing.UIManager;

/**
 * Main class, creates the GUI
 * @author Matt Lyons, David Lukacs, Daniel Kercheski, David McClure
 */
public class DiceGame {
    public static void main(String[] args){
            
            //Use operating system look and feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if (UIManager.getSystemLookAndFeelClassName().equals("javax.swing.plaf.metal.MetalLookAndFeel")) { //Fix detection with linux
                if (System.getProperty("os.name").contains("Linux")) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        GUI gui = new GUI();
	}
}

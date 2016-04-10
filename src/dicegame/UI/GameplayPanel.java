package dicegame.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Matt Lyons
 */
public class GameplayPanel extends JPanel implements ActionListener{
    
    private GUI gui;
    
    /**
     * Create a StartMenuPanel to be used in the GUI
     * @param gui GUI object which is used to display this panel
     */
    public GameplayPanel(GUI gui){
        super();
        
        this.gui=gui;
        
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
    }
    
}

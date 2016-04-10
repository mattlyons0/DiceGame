package dicegame.UI;

import dicegame.GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author David Lukacs
 */
public class PlayerCreationPanel extends JPanel implements ActionListener{
    
    private GUI gui;
    
    public PlayerCreationPanel(GUI gui){
        super();
        
        this.gui=gui;
        
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        //Add JLabel
        
        //Add JTextField (use getText() to get entered text)
        //You can update the players label by using setText("new text") on the label object
        
        //Add Create Button (remember to add action listener)
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
    }
    
}

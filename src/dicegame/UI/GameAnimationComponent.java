package dicegame.UI;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

/**
 * Handles Animation of Gameplay.
 * @author Matt Lyons
 */
public class GameAnimationComponent extends JComponent {
    public GameAnimationComponent(){
        super();
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setLayout(new FlowLayout());
        
        add(new JLabel("A visualization will go here in the next sprint."));
    }
}

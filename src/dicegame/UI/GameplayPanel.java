package dicegame.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 * Contains the UI for the Gameplay view.
 *
 * @author Matt Lyons
 */
public class GameplayPanel extends JPanel {

    private GUI gui;

    public GameAnimationComponent animationComp;
    public GameScoreboardComponent scoreboardComp;
    private GameControlsComponent controlsComp;

    /**
     * Create a StartMenuPanel to be used in the GUI
     *
     * @param gui GUI object which is used to display this panel
     */
    public GameplayPanel(GUI gui) {
        super();

        this.gui = gui;
        gui.maximize();

        setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 1;
        cons.weighty = 70;
        cons.fill = GridBagConstraints.BOTH; //Stretch components to fill space
        cons.insets = new Insets(5, 5, 5, 5); //Add some padding to components

        //Animation Part
        animationComp = new GameAnimationComponent(this.gui);
        add(animationComp, cons);

        //Scoreboard Part
        cons.gridy++;
        cons.weighty=1;
        scoreboardComp = new GameScoreboardComponent(this.gui);
        add(scoreboardComp, cons);

        //Game Controls
        cons.gridy++;
        cons.weighty = 6;
        controlsComp = new GameControlsComponent(this.gui);
        add(controlsComp, cons);
    }

}

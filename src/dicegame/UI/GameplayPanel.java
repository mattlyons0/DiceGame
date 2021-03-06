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
    public final int TOTAL_HOLES;

    public GameAnimationComponent animationComp;
    public GameScoreboardComponent scoreboardComp;
    private GameControlsComponent controlsComp;

    /**
     * Create a StartMenuPanel to be used in the GUI
     *
     * @param totalHoles the number of holes for which this game will be played
     * up to
     * @param gui GUI object which is used to display this panel
     */
    public GameplayPanel(int totalHoles, GUI gui) {
        super();

        this.gui = gui;
        gui.maximize();

        TOTAL_HOLES = totalHoles;

        setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 10;
        cons.weighty = 70;
        cons.gridheight = 1;
        cons.gridwidth = 1;
        cons.fill = GridBagConstraints.BOTH; //Stretch components to fill space

        //Animation Part
        animationComp = new GameAnimationComponent(this.gui);
        add(animationComp, cons);

        //Scoreboard Part
        cons.gridy++;
        cons.weighty = 30;
        cons.insets = new Insets(5, 5, 5, 5); //Add some padding to components
        scoreboardComp = new GameScoreboardComponent(this.gui);
        add(scoreboardComp, cons);

        //Game Controls
        cons.gridy++;
        cons.weighty = 30;
        controlsComp = new GameControlsComponent(this.gui, animationComp);
        add(controlsComp, cons);
    }

}

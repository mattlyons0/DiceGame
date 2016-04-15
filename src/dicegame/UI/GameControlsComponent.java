package dicegame.UI;

import dicegame.Game;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the controls for the gameplay (ex the dice to roll) and handles
 * their buttonpresses
 *
 * @author Matt Lyons
 */
class GameControlsComponent extends JComponent implements ActionListener {

    private JLabel playerTurnLabel;
    private JPanel dicePanel;

    private Game gameLogic;
    private GameScoreboardComponent scoreboard;
    
    private int[] rollValues;
    /**
     * Create a new GameControlsComponent and all its components.
     */
    public GameControlsComponent(GUI gui) {
        super();
        
        this.gameLogic = gui.gameLogic;
        this.scoreboard = gui.gameplayPanel.scoreboardComp;

        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.weightx = 1;
        cons.weighty = 1;
        cons.insets = new Insets(5, 0, 0, 0);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.NORTH;

        playerTurnLabel = new JLabel("Player 1's Turn");
        add(playerTurnLabel, cons);

        cons.gridy++;
        cons.anchor = GridBagConstraints.CENTER;
        dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        updateDice();
        add(dicePanel, cons);

    }

    /**
     * Update Dice UI from Game Logic //TODO implement this correctly
     */
    public void updateDice() {
        diceRollMultiplier();
    }

    private void diceRollMultiplier() { //Display multiplier UI
        JButton multiplierRoll = new JButton("Roll Dice Multiplier");
        multiplierRoll.setActionCommand("MultiplierRoll");
        multiplierRoll.addActionListener(this);

        dicePanel.removeAll();
        dicePanel.add(multiplierRoll);

        dicePanel.revalidate();
    }

    private void diceRoll(int numDice) { //Display multi-dice roll UI
        dicePanel.removeAll();

        for (int number = 0; number < numDice; number++) {
            JButton die = new JButton("Roll");
            die.setActionCommand("RollDie " + number);
            die.addActionListener(this);
            dicePanel.add(die);
        }

        dicePanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("MultiplierRoll")) {
            int multiplier = gameLogic.roll();
            rollValues = gameLogic.hitTheBall(0,0); //Preroll values and display them as they are clicked
            
            diceRoll(multiplier); //Update GUI with multiplier
        } else if (event.getActionCommand().startsWith("RollDie")) {
            int diceNum = Integer.parseInt(event.getActionCommand().split(" ")[1]);
            
            JButton rollButton = (JButton) event.getSource();
            rollButton.setEnabled(false);
            rollButton.setText(rollValues[diceNum]+"");
            rollButton.removeActionListener(this);
            
            scoreboard.hitBall(rollValues[diceNum]);
        }
    }
}

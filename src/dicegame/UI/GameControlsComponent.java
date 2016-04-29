package dicegame.UI;

import dicegame.Game;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the controls for the gameplay (ex the dice to roll) and handles
 * their button presses
 *
 * @author Matt Lyons
 */
class GameControlsComponent extends JComponent implements ActionListener {

    private JLabel playerTurnLabel;
    private JPanel dicePanel;

    private Game gameLogic;
    private GUI gui;
    private GameScoreboardComponent scoreboard;

    private int[] rollValues;

    /**
     * Create a new GameControlsComponent and all its components.
     */
    public GameControlsComponent(GUI gui) {
        super();

        this.gui = gui;
        this.gameLogic = gui.gameLogic;

        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.weightx = 1;
        cons.weighty = 1;
        cons.insets = new Insets(5, 0, 0, 0);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.NORTH;

        playerTurnLabel = new JLabel();
        updateTurn();
        add(playerTurnLabel, cons);

        cons.gridy++;
        cons.anchor = GridBagConstraints.CENTER;
        dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        diceRollMultiplier();
        add(dicePanel, cons);

    }

    private void diceRollMultiplier() { //Display multiplier UI
        JButton multiplierRoll = new JButton("Roll Dice Multiplier");
        multiplierRoll.setPreferredSize(new Dimension(180, 76));
        multiplierRoll.setActionCommand("MultiplierRoll");
        multiplierRoll.addActionListener(this);

        dicePanel.removeAll();
        dicePanel.add(multiplierRoll);

        dicePanel.revalidate();
    }

    private void diceRoll(int numDice) { //Display multi-dice roll UI
        dicePanel.removeAll();

        for (int number = 0; number < numDice; number++) {
            if (number == 0 || rollValues[number] != 0) {
                JButton die = new JButton("Roll");
                die.setPreferredSize(new Dimension(76, 76));
                die.setActionCommand("RollDie " + number);
                die.addActionListener(this);
                dicePanel.add(die);
            } else{
                rollValues[number]=-1;
            }
        }

        dicePanel.revalidate();
    }

    private void updateTurn() {
        gameLogic.currentPlayer();
        System.out.println("Current Player: "+gameLogic.getCurrentPlayer());

        int playerTurnIndex = gameLogic.getCurrentPlayer();
        String player = gameLogic.getPlayer()[playerTurnIndex];
        playerTurnLabel.setText(player + "'s Turn");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("MultiplierRoll")) {
            int multiplier = gameLogic.roll();
            rollValues = gameLogic.hitTheBall(multiplier,gameLogic.getCurrentPlayer(),gameLogic.getHoleIndex()); //Preroll values and display them as they are clicked

            diceRoll(multiplier); //Update GUI with multiplier
        } else if (event.getActionCommand().startsWith("RollDie")) {
            int diceNum = Integer.parseInt(event.getActionCommand().split(" ")[1]);

            //Show Dice Roll
            JButton rollButton = (JButton) event.getSource();
            rollButton.setEnabled(false);
            rollButton.setText("");
            rollButton.setIcon(new ImageIcon(getClass().getResource("/dicegame/Images/Dice" + rollValues[diceNum]
                    + ".png")));
            rollButton.removeActionListener(this);
            if (scoreboard == null) {
                this.scoreboard = this.gui.gameplayPanel.scoreboardComp;
            }
            scoreboard.hitBall(rollValues[diceNum]);
            gui.gameplayPanel.animationComp.repaint();

            rollValues[diceNum] = -1; //Dice has been rolled and roll has been used

            boolean allRolled = true;
            for (int index = 0; index < rollValues.length; index++) {
                if (rollValues[index] != -1) {
                    allRolled = false;
                }
            }

            if (allRolled) {
                String buttonText = "Next Turn";
                if (gameLogic.getDistanceFromHole() == 0 && gameLogic.getHoleIndex() == gui.gameplayPanel.TOTAL_HOLES) {
                    buttonText = "End Game";
                } else if (gameLogic.getDistanceFromHole() == 0) {
                    buttonText = "Next Hole";
                }
                JButton nextTurn = new JButton(buttonText);
                nextTurn.setActionCommand("NextTurn");
                nextTurn.addActionListener(this);
                dicePanel.add(nextTurn);
            }
        } else if (event.getActionCommand().equals("NextTurn")) {
            System.out.println("DistanceFromHole: "+gameLogic.getDistanceFromHole()+" getCurrentPlayerDistance(0): "+gameLogic.getCurrentPlayerDistance(0));
            if (((JButton) event.getSource()).getText().equals("End Game")) {
                gui.statsView();
                return;
            } else if (((JButton) event.getSource()).getText().equals("Next Hole")) {
                gameLogic.nextHole();
                gui.gameplayPanel.animationComp.recalculateHole();
                gui.gameplayPanel.scoreboardComp.newHole();
            }
            updateTurn();
            diceRollMultiplier();
        }
    }
}

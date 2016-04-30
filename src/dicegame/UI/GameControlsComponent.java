package dicegame.UI;

import dicegame.Game;
import java.awt.Color;
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
    private GameAnimationComponent animationComp;

    private int[] rollValues;
    private int playerTurnIndex; //Index of the current player

    /**
     * Create a new GameControlsComponent and all its components.
     *
     * @param gui the parent GUI object
     * @param animationComponent the animationComponent which has been created
     * for this game
     */
    public GameControlsComponent(GUI gui, GameAnimationComponent animationComponent) {
        super();

        this.gui = gui;
        this.gameLogic = gui.gameLogic;
        this.animationComp = animationComponent;

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
            } else {
                rollValues[number] = -1;
            }
        }

        dicePanel.revalidate();
    }

    private void updateTurn() {
        //Update Current Player Label
        playerTurnIndex = gameLogic.getCurrentPlayer();
        String player = gameLogic.getPlayer()[playerTurnIndex];
        playerTurnLabel.setText(player + "'s Turn");

        //Color Current Player Label
        Color textColor = animationComp.getPlayerColor(playerTurnIndex);
        Color backgroundColor = animationComp.getPlayerColorBackground(playerTurnIndex);
        playerTurnLabel.setForeground(textColor);
        playerTurnLabel.setBackground(backgroundColor);

        //Update the animation to select the current player's ball
        animationComp.update(playerTurnIndex);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (scoreboard == null) {
            this.scoreboard = this.gui.gameplayPanel.scoreboardComp;
        }

        if (event.getActionCommand().equals("MultiplierRoll")) {
            gameLogic.addStroke(playerTurnIndex, gameLogic.getHoleIndex());
            int multiplier = gameLogic.roll();
            rollValues = gameLogic.hitTheBall(multiplier, gameLogic.getCurrentPlayer(), gameLogic.getHoleIndex()); //Preroll values and display them as they are clicked

            diceRoll(multiplier); //Update GUI with multiplier
            scoreboard.updateTable();
        } else if (event.getActionCommand().startsWith("RollDie")) {
            int diceNum = Integer.parseInt(event.getActionCommand().split(" ")[1]);

            //Show Dice Roll
            JButton rollButton = (JButton) event.getSource();
            rollButton.setEnabled(false);
            rollButton.setText("");
            rollButton.setIcon(new ImageIcon(getClass().getResource("/dicegame/Images/Dice" + rollValues[diceNum]
                    + ".png")));
            rollButton.removeActionListener(this);

            scoreboard.hitBall(rollValues[diceNum], playerTurnIndex);
            animationComp.update(playerTurnIndex);

            rollValues[diceNum] = -1; //Dice has been rolled and roll has been used

            boolean allRolled = true;
            for (int index = 0; index < rollValues.length; index++) {
                if (rollValues[index] != -1) {
                    allRolled = false;
                }
            }

            if (allRolled) {
                String buttonText = "Next Turn";
                boolean allPlayersReachedHole = true;
                for (int playerIndex = 0; playerIndex < gameLogic.getNumberOfPlayers(); playerIndex++) {
                    if (gameLogic.getCurrentPlayerDistance(playerIndex, gameLogic.getHoleIndex()) != 0) {
                        allPlayersReachedHole = false;
                        break;
                    }
                }
                JButton nextTurnSpacer = new JButton("Next Turn");
                nextTurnSpacer.setEnabled(false);

                if (allPlayersReachedHole && gameLogic.getHoleIndex() == gui.gameplayPanel.TOTAL_HOLES - 1) {
                    dicePanel.add(nextTurnSpacer);
                    buttonText = "End Game";
                } else if (allPlayersReachedHole) {
                    dicePanel.add(nextTurnSpacer);
                    buttonText = "Next Hole";
                }
                JButton nextTurn = new JButton(buttonText);
                nextTurn.setActionCommand("NextTurn");
                nextTurn.addActionListener(this);
                dicePanel.add(nextTurn);
            }
        } else if (event.getActionCommand().equals("NextTurn")) {
            if (((JButton) event.getSource()).getText().equals("End Game")) {
                gameLogic.nextHole();
                gui.statsView();
                return;
            } else if (((JButton) event.getSource()).getText().equals("Next Hole")) {
                gameLogic.nextHole();
                animationComp.recalculateHole();
                gui.gameplayPanel.scoreboardComp.newHole();
            }

            updateTurn();
            diceRollMultiplier();
        }
    }
}

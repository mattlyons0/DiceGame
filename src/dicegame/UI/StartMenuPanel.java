package dicegame.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Contains all the UI in the Start Menu and methods to modify the data
 * displayed
 *
 * @author Matt Lyons
 */
public class StartMenuPanel extends JPanel implements ActionListener,KeyListener {

    private GUI gui;
    private int holeCount;

    private JLabel playersLabel;
    private JButton startButton;
    private JButton createPlayerButton;
    private JButton statisticsButton;
    private JButton quitButton;
    private JTextField holesCount;

    /**
     * Create a StartMenuPanel to be used in the GUI
     *
     * @param gui GUI object which is used to display this panel
     */
    public StartMenuPanel(GUI gui) {
        super(); //Calls JPanel constructor

        this.gui = gui;

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        validateStart();

        cons.gridx = 0;
        cons.gridy = 0; //Use Grid cell 0,0
        cons.weightx = 100;
        cons.weighty = 1;
        cons.anchor=GridBagConstraints.EAST;
        cons.insets = new Insets(5, 5, 5, 5); //Add 5px all around of padding
        add(startButton, cons);
        
        JPanel comp = new JPanel();
        comp.setLayout(new FlowLayout());
        holesCount = new JTextField(2);
        holesCount.setText("3");
        holesCount.addKeyListener(this);
        keyReleased(new KeyEvent(holesCount,0,0,0,0,'0'));
        comp.add(holesCount);
        comp.add(new JLabel("Holes"));
        cons.gridx++;
        cons.anchor=GridBagConstraints.WEST;
        add(comp,cons);
        

        createPlayerButton = new JButton("Create New Player");
        createPlayerButton.addActionListener(this);
        cons.gridy++;
        cons.anchor=GridBagConstraints.CENTER;
        cons.gridx=0;
        cons.gridwidth=3;
        add(createPlayerButton, cons);

        cons.gridy++;
        statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(this);
        add(statisticsButton, cons);

        cons.gridy++;
        quitButton = new JButton("Quit Game");
        quitButton.addActionListener(this);
        add(quitButton, cons);

        playersLabel = new JLabel("No Players Created");
        cons.gridy++;
        cons.anchor = GridBagConstraints.SOUTH;
        cons.weighty = 100;
        add(playersLabel, cons);

    }

    /**
     * Add a player to the UI in the startMenu
     *
     * @param playerName String of players name
     */
    public void addPlayer(String playerName) {
        if (playersLabel.getText().equals("No Players Created")) {
            playersLabel.setText("Players: " + playerName);
        } else {
            playersLabel.setText(playersLabel.getText() + ", " + playerName);
        }
        gui.gameLogic.createPlayer(playerName);
        validateStart();
    }
    
    /**
     * Validate if the start button should be enabled
     */
    protected void validateStart(){
        if(gui.gameLogic.getNumberOfPlayers() > 0 && holeCount > 0){
            startButton.setEnabled(true);
            startButton.setToolTipText(null);
        } else if(holeCount <= 0){
            startButton.setEnabled(false);
            startButton.setToolTipText("There must be at least 1 hole to play.");
        } else if(gui.gameLogic.getNumberOfPlayers() <= 0){
            startButton.setEnabled(false);
            startButton.setToolTipText("Create at least one player before you can play!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            gui.startGame(holeCount);
        } else if (event.getSource() == createPlayerButton) {
            gui.createPlayer();
        } else if (event.getSource() == statisticsButton) {
            gui.statsView();
        } else if (event.getSource() == quitButton) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(holesCount)){
            JTextField field = (JTextField) e.getSource();
            try{
                int num = Integer.parseInt(field.getText());
                holesCount.setForeground(UIManager.getColor("Panel.foreground"));
                holesCount.setToolTipText(null);
                holeCount = num;
            } catch(NumberFormatException exception){
                holesCount.setForeground(Color.red);
                holesCount.setToolTipText("Holes must be a number!");
                holeCount = -1;
            }
            validateStart();
        }
    }
}

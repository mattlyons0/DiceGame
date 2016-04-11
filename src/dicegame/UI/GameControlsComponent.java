package dicegame.UI;

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
 * Displays the controls for the gameplay (ex the dice to roll) and handles their buttonpresses
 * @author Matt Lyons
 */
class GameControlsComponent extends JComponent implements ActionListener{
    
    JLabel playerTurnLabel;
    JPanel dicePanel;
    
    public GameControlsComponent(){
        super();
        
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.weightx=1; cons.weighty=1; cons.insets=new Insets(5,0,0,0);
        cons.gridx=0; cons.gridy=0; cons.anchor=GridBagConstraints.NORTH;
        
        playerTurnLabel = new JLabel("Player 1's Turn");
        add(playerTurnLabel,cons);
        
        cons.gridy++; cons.anchor=GridBagConstraints.CENTER;
        dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        updateDice();
        add(dicePanel,cons);
        
    }

    public void updateDice(){
        diceRollMultiplier();
    }
    private void diceRollMultiplier(){
        JButton multiplierRoll = new JButton("Roll Dice Multiplier");
        multiplierRoll.setActionCommand("MultiplierRoll");
        multiplierRoll.addActionListener(this);
        
        dicePanel.removeAll();
        dicePanel.add(multiplierRoll);
        
        dicePanel.revalidate();
    }
    private void diceRoll(int numDice){
        dicePanel.removeAll();
        
        for(int number=0;number<numDice;number++){
            JButton die = new JButton("Roll");
            die.setActionCommand("RollDie "+number);
            die.addActionListener(this);
            dicePanel.add(die);
        }
        
        dicePanel.revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("MultiplierRoll")){
            Random rand = new Random();
            diceRoll(rand.nextInt(6)+1);
        } else if(event.getActionCommand().startsWith("RollDie")){
            int number = Integer.parseInt(event.getActionCommand().split(" ")[1]);
            System.out.println("Got Dice Roll "+number+" command.");
        }
    }
}

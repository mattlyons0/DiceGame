package dicegame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Handle the Creation of the UI here
 * @author Matt Lyons, David Lukacs, Daniel Kercheski, David McClure
 */
public class GUI extends JFrame implements ActionListener{
    
    private JButton startButton;
    private JButton createPlayerButton;
    private JButton quitButton; 
    private JLabel playersLabel;
    
    public GUI(){
        super("Dice Golf");
        initLayout();
        initGui();
    }
    
    public void initLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        cons.gridx=0; cons.gridy=0; //Use Grid cell 0,0
        cons.weightx = 100; cons.weighty = 1;
        cons.insets = new Insets(5,5,5,5); //Add 5px all around of padding
        add(startButton,cons);
        
        createPlayerButton = new JButton("Create New Player");
        createPlayerButton.addActionListener(this);
        cons.gridy++;
        add(createPlayerButton,cons);
        
        cons.gridy++;
        quitButton = new JButton("Quit Game"); 
        quitButton.addActionListener(this);
        add(quitButton,cons);
        
        playersLabel = new JLabel("No Players Created");
        cons.gridy++; cons.anchor = GridBagConstraints.SOUTH;
        cons.weighty = 100;
        add(playersLabel,cons);
    }
    
    public void initGui(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == startButton){
            //Start Game
        } else if(event.getSource() == createPlayerButton){
            //Create Player
        } else if(event.getSource() == quitButton){
            System.exit(0);
        }
    }
}

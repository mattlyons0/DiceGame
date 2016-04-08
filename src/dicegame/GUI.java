package dicegame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handle the Creation of the UI here
 * @author Matt Lyons, David Lukacs, Daniel Kercheski, David McClure
 */
public class GUI extends JFrame implements ActionListener{
    
    //Panels
    private JPanel startMenuPanel;
    private JPanel createPlayerPanel;
    
    //Components
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
        startMenuPanel = new JPanel();
        
        startMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        cons.gridx=0; cons.gridy=0; //Use Grid cell 0,0
        cons.weightx = 100; cons.weighty = 1;
        cons.insets = new Insets(5,5,5,5); //Add 5px all around of padding
        startMenuPanel.add(startButton,cons);
        
        createPlayerButton = new JButton("Create New Player");
        createPlayerButton.addActionListener(this);
        cons.gridy++;
        startMenuPanel.add(createPlayerButton,cons);
        
        cons.gridy++;
        quitButton = new JButton("Quit Game"); 
        quitButton.addActionListener(this);
        startMenuPanel.add(quitButton,cons);
        
        playersLabel = new JLabel("No Players Created");
        cons.gridy++; cons.anchor = GridBagConstraints.SOUTH;
        cons.weighty = 100;
        startMenuPanel.add(playersLabel,cons);
        
        add(startMenuPanel);
    }
    
    public void initGui(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300,500);
        setVisible(true);
    }
    
    public void createPlayer(){
        remove(startMenuPanel);
        repaint();
        
        createPlayerPanel = new JPanel();
        
        createPlayerPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        
        //Add JLabel
        
        //Add JTextField (use getText() to get entered text)
        //You can update the players label by using setText("new text") on the label object
        
        //Add Create Button (remember to add action listener)
        
        add(createPlayerPanel);
        //You might have to repaint
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == startButton){
            //Start Game
        } else if(event.getSource() == createPlayerButton){
            createPlayer();
        } else if(event.getSource() == quitButton){
            System.exit(0);
        }
    }
}

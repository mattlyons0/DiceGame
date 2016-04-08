package dicegame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Handle the Creation of the UI here
 * @author Matt Lyons, David Lukacs, Daniel Kercheski, David McClure
 */
public class GUI extends JFrame{
    
    private JButton startButton;
    
    public GUI(){
        super("Dice Golf");
        initLayout();
        initGui();
    }
    
    public void initLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx=0; cons.gridy=0; //Use Grid cell 0,0
        
        startButton = new JButton("Start Game");
        add(startButton,cons);
    }
    
    public void initGui(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300,500);
        setVisible(true);
    }
}

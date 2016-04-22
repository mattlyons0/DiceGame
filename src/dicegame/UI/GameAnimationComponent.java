package dicegame.UI;

import dicegame.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

/**
 * Handles Animation of Gameplay.
 * Hole is at 1100,350 in image.
 * @author Matt Lyons
 */
public class GameAnimationComponent extends JComponent {

    private GUI gui;
    private Game gameLogic;
    
    private BufferedImage animationBuffer;
    
    private Dimension holeLocation;
    
    /**
     * Creates a new Game Animation Component
     */
    public GameAnimationComponent(GUI gui) {
        super();
        
        this.gui = gui;
        this.gameLogic = gui.gameLogic;
        
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setLayout(new FlowLayout());

        Dimension maxSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Create buffer for animation and make sure it can scale to as large as the window will possibly be (screen size)
        animationBuffer=new BufferedImage(maxSize.width,maxSize.height,BufferedImage.TYPE_INT_ARGB);
        
        //Determine hole location
        Random rand = new Random();
        int heightThreshold = 200;
        int heightOffset = 275;
        int widthLimit = 1200;
        //Max values for course from gameLogic
        double maxDistance = 100;
        int distance = gameLogic.getHoleLength();
        
        int y = rand.nextInt(heightThreshold);
        double x = distance / maxDistance * 0.5;
        holeLocation = new Dimension((int)(x*widthLimit+640),y+heightOffset);
    }
    
    @Override
    public void paint(Graphics graphics) {
        Graphics animationGraphics = animationBuffer.getGraphics();
        
        BufferedImage courseImage = null;
        BufferedImage holeImage = null;
        
        try{
            courseImage = ImageIO.read(getClass().getResource("/dicegame/Images/Course.png"));
            holeImage = ImageIO.read(getClass().getResource("/dicegame/Images/Hole.png"));
        } catch(IOException exception){
            System.err.println("Error loading animation images.");
        }
        
        Rectangle visibleArea = this.getVisibleRect();
        double scaleFactorX = visibleArea.width / 1280.0;
        double scaleFactorY = visibleArea.height / 720.0;
        
        if(courseImage != null){
            //Display course image and scale it to the visible size
            animationGraphics.drawImage(courseImage.getScaledInstance(visibleArea.width,visibleArea.height, Image.SCALE_SMOOTH), 0, 0, null);
        }
        if(holeImage != null){
            //Display hole in randomized location based on how far away the hole is
            animationGraphics.drawImage(holeImage,(int)(holeLocation.width * scaleFactorX),(int)(holeLocation.height*scaleFactorY),null);
        }
        
        
        
        graphics.drawImage(animationBuffer, 0, 0, this);
    }
}

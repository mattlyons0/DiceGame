package dicegame.UI;

import dicegame.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;

/**
 * Handles Animation of Gameplay.
 *
 * @author Matt Lyons
 */
public class GameAnimationComponent extends JComponent {

    private GUI gui;
    private Game gameLogic;
    private GameScoreboardComponent scoreboardComp;

    private BufferedImage animationBuffer;
    private BufferedImage courseImage;
    private BufferedImage holeImage;

    private Dimension holeLocation;
    private Color[] playerColors;
    
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
        playerColors = new Color[gameLogic.getPlayer().length];
        for(int playerIndex=0;playerIndex<gameLogic.getPlayer().length;playerIndex++){
            Random rand = new Random();
            playerColors[playerIndex]=new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
        }
        
        //Create buffer for animation and make sure it can scale to as large as the window will possibly be (screen size)
        animationBuffer = new BufferedImage(maxSize.width, maxSize.height, BufferedImage.TYPE_INT_ARGB);

        courseImage = null;
        holeImage = null;

        try {
            courseImage = ImageIO.read(getClass().getResource("/dicegame/Images/Course.png"));
            holeImage = ImageIO.read(getClass().getResource("/dicegame/Images/Hole.png"));
        } catch (IOException exception) {
            System.err.println("Error loading animation images.\n" + exception);
            JOptionPane.showMessageDialog(null, "Error loading Game Animation Images", 
                    "Error loading Animation Image Resources!\n" + exception, JOptionPane.ERROR_MESSAGE);
        }
        recalculateHole();
    }
    
    public void recalculateHole(){
        //Determine hole location
        Random rand = new Random();
        int heightThreshold = 200;
        int heightOffset = 275;
        int widthLimit = 1150 - (30 * 2); //30*2for starting tee spot (it is halved below)
        //Max values for course from gameLogic
        double maxDistance = 100;
        int distance = gameLogic.getHoleLength();
        int y = rand.nextInt(heightThreshold);
        double x = distance / maxDistance * 0.5;
        holeLocation = new Dimension((int) (x * widthLimit + 640), y + heightOffset);
        
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        if (scoreboardComp == null) {
            scoreboardComp = gui.gameplayPanel.scoreboardComp;
        }

        Graphics2D animationGraphics = (Graphics2D) animationBuffer.getGraphics();

        Rectangle visibleArea = this.getVisibleRect();
        double scaleFactorX = visibleArea.width / 1280.0;
        double scaleFactorY = visibleArea.height / 720.0;

        if (courseImage != null) {
            //Display course image and scale it to the visible size
            animationGraphics.drawImage(courseImage.getScaledInstance(visibleArea.width, visibleArea.height, Image.SCALE_SMOOTH), 0, 0, null);
        }
        if (holeImage != null) {
            //Display hole in randomized location based on how far away the hole is
            animationGraphics.drawImage(holeImage, (int) (((holeLocation.width) + 30) * scaleFactorX), (int) (holeLocation.height * scaleFactorY), null);
        }

        int ballLoc = gameLogic.getHoleLength() - scoreboardComp.getBallDistanceLeft();
        double ballPercent = (double) ballLoc / gameLogic.getHoleLength();
        int ballPixels = (int) (((ballPercent * ((holeLocation.width)) + 30) * scaleFactorX));

        animationGraphics.setColor(playerColors[gameLogic.getCurrentPlayer()]);

        Ellipse2D.Double ball = new Ellipse2D.Double(ballPixels, (int) (holeLocation.height * scaleFactorY), 10, 10);
        animationGraphics.fill(ball);

        //Draw Hole Number
        animationGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 24);
        animationGraphics.setColor(Color.white);
        animationGraphics.setFont(font);
        animationGraphics.drawString("Hole " + (gameLogic.getHoleIndex()), 25, (int) (60 * scaleFactorY));

        graphics.drawImage(animationBuffer, 0, 0, this);
    }
    
    public Color getPlayerColor(int playerIndex){
        return playerColors[playerIndex];
    }
}

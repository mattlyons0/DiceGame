package dicegame.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
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

    private BufferedImage animationBuffer;
    
    /**
     * Creates a new Game Animation Component
     */
    public GameAnimationComponent() {
        super();
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setLayout(new FlowLayout());

        animationBuffer=new BufferedImage(1280,720,BufferedImage.TYPE_INT_ARGB);
    }
    
    @Override
    public void paint(Graphics graphics) {
        System.out.println("called");
        Graphics animationGraphics = animationBuffer.getGraphics();
        //drawImage(ImageIO.read(f).getScaledInstance(tileSize, tileSize, Image.SCALE_FAST), 0, 0, null);
        
        super.paint(graphics); //Repaints to white
        graphics.drawImage(animationBuffer, 0, 0, this);
    }
}

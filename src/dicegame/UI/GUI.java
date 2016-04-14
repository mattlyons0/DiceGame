package dicegame.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Create JFrame window and add panels to it based on the current view.
 *
 * @author Matt Lyons, David Lukacs, Daniel Kercheski, David McClure
 */
public class GUI extends JFrame {

    //Panels (Public so other panels can call their methods)
    public StartMenuPanel startMenuPanel;
    public PlayerCreationPanel playerCreationPanel;
    public GameplayPanel gameplayPanel;

    //Store Current Panel so it can be easilly removed
    private JPanel currentPanel;

    /**
     * Create a JFrame with the start menu view.
     */
    public GUI() {
        super("Dice Golf");

        currentPanel = null;

        initLayout();
        initGui();
    }

    private void initLayout() {
        startMenuPanel = new StartMenuPanel(this);
        switchPanel(startMenuPanel);
    }

    private void initGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit Java Application when the window is closed

        setSize(300, 500);
        setVisible(true);
    }

    /**
     * Switch to the createPlayer view.
     */
    public void createPlayer() {
        playerCreationPanel = new PlayerCreationPanel(this);
        switchPanel(playerCreationPanel);
    }

    /**
     * Switch to the gameplay view.
     */
    public void startGame() {
        gameplayPanel = new GameplayPanel(this);
        switchPanel(gameplayPanel);
    }

    /**
     * Switch to the main menu.
     */
    public void switchStart() {
        switchPanel(startMenuPanel);
    }

    /**
     * Remove the current panel (if it exists), and add the new panel
     *
     * @param newPanel JPanel to be added to JFrame
     */
    protected void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = newPanel;

        add(newPanel);
        repaint();
        revalidate();
    }

    /**
     * Maximize this JFrame
     */
    public void maximize() {
//        DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
//        setLocation(0,20);
//        setSize(display.getWidth(),display.getHeight());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    void showStatistics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

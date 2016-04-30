package dicegame.UI;

import dicegame.Game;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Create JFrame window and add panels to it based on the current view.
 *
 * @author Matt Lyons, David Lukacs
 */
public class GUI extends JFrame {

    //Panels (Public so other panels can call their methods)
    public StartMenuPanel startMenuPanel;
    public PlayerCreationPanel playerCreationPanel;
    public GameplayPanel gameplayPanel;
    public StatsPanel statsPanel;

    //Store Current Panel so it can be easilly removed
    private JPanel currentPanel;

    //Logic which runs the game and stores the Stats
    public Game gameLogic;

    /**
     * Create a JFrame with the start menu view.
     */
    public GUI() {
        super("Dice Golf"); //Window Title

        gameLogic = new Game(true);

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
     *
     * @param holeCount number of holes to be played
     */
    public void startGame(int holeCount) {
        //Prepare Course
        gameLogic.setNumberOfHoles(holeCount);
        gameLogic.createCourse();
        gameLogic.createGameStats();

        //Prepare and show UI
        gameplayPanel = new GameplayPanel(holeCount, this);
        switchPanel(gameplayPanel);
    }

    /**
     * Switch to the main menu.
     */
    public void showStartMenu() {
        switchPanel(startMenuPanel);
    }

    /**
     * Switch to the statistics view.
     */
    public void statsView() {
        statsPanel = new StatsPanel(this);
        switchPanel(statsPanel);
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
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

}

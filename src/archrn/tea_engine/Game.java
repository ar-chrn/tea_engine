package archrn.tea_engine;

import archrn.tea_engine.input.Input;

/**
 * <p>{@code Game} represents the game.</p>
 * <p> It connects highest-level parts of the game together.</p>
 * @author Artem
 */
public class Game
{

    private SceneManager sceneManager;
    private Screen screen;

    /**
     * <p>Initializes the game.</p>
     * <p>Connects high-level parts of the game.</p>
     */
    public Game()
    {
        this.sceneManager = SceneManager.shared;
        this.screen = Screen.shared;
        this.screen.addKeyListener(Input.getSharedAsInput());
        this.screen.addMouseListener(Input.getSharedAsInput());
        this.screen.addMouseMotionListener(Input.getSharedAsInput());
    }

    /**
     * <p>Returns this {@code Game}'s {@code SceneManager}.</p>
     * <p>Currently it's the same as {@code SceneManager.shared}. Maybe
     * shouldn't be used.</p>
     * @return This {@code Game}'s {@code SceneManager}.
     */
    public SceneManager getSceneManager()
    {
        return this.sceneManager;
    }

    /**
     * <p>Starts the game.</p>
     */
    public void start()
    {
        this.sceneManager.loadNextScene();
    }

}

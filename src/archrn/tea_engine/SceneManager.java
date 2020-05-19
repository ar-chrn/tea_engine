package archrn.tea_engine;

import java.util.ArrayList;
import java.util.Objects;

// TODO: Implement serialization.

/**
 * <p>{@code SceneManager} manages {@code Scene}s in the game.</p>
 * @author Artem
 */
public final class SceneManager
{

    public static final SceneManager shared = new SceneManager();

    private ArrayList<Scene> scenes;
    private int currentSceneIndex;
    private Time time;

    /**
     * <p>Initializes a {@code SceneManager}.</p>
     */
    private SceneManager()
    {
        this.currentSceneIndex = -1;
        this.scenes = new ArrayList<Scene>();
        this.time = Time.shared;
    }

    /**
     * <p>Adds the given {@code scene} to the game.</p>
     * @param scene The {@code Scene} to add.
     * @throws NullPointerException if {@code scene} is {@code null}.
     */
    public void addScene(Scene scene)
    {
        Objects.requireNonNull(scene);
        this.scenes.add(scene);
    }

    /**
     * <p>Returns the currently running {@code Scene}.</p>
     * @return The currently running {@code Scene} if there is a running
     * {@code Scene}.
     * @throws IndexOutOfBoundsException if no scene is loaded yet.
     */
    public Scene getCurrentScene()
    {
        this.checkSceneIndex(currentSceneIndex);
        return this.scenes.get(currentSceneIndex);
    }

    /**
     * <p>Loads the next by index {@code Scene}.</p>
     * @throws IndexOutOfBoundsException if the last scene is currently
     * loaded or if there're no scenes registered.
     */
    public void loadNextScene()
    {
        this.loadScene(this.currentSceneIndex + 1);
    }

    /**
     * <p>Loads the {@code Scene} with the given {@code index}.</p>
     * @param index The index of the {@code Scene} to load.
     * @throws IndexOutOfBoundsException if a {@code Scene} with this index
     * isn't registered.
     */
    public void loadScene(int index)
    {
        if (index < 0
            || index >= this.scenes.size())
        {
            throw new IndexOutOfBoundsException();
        }

        this.time.pause();
        this.currentSceneIndex = index;
        this.scenes.get(index).load();
        this.time.start();
    }

    /**
     * <p>Throws an exception if a {@code Scene} with the given {@code index}
     * doesn't exist.</p>
     * @param index The index of the {@code Scene} to check.
     * @throws IndexOutOfBoundsException if a {@code Scene} with the given
     * {@code index} doesn't exist.
     */
    private void checkSceneIndex(int index)
    {
        if (index < 0
            || index >= this.scenes.size())
        {
            throw new IndexOutOfBoundsException();
        }
    }

}

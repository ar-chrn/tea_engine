package archrn.tea_engine;

import archrn.tea_engine.camera.Camera;
import archrn.tea_engine.camera.CameraGameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * <p>{@code Scene} represents a scene in a game world.</p>
 * <p>{@code Scene} contains {@code GameObject}s. It represents a separate
 * space in the game world. For example, a level.</p>
 * @author Artem
 */
public class Scene
{

    /**
     * <p>{@code true} if it's the currently displayed {@code Scene}, {@code
     * false} if not.</p>
     */
    private boolean loaded;

    /**
     * <p>{@code ArrayList} with all the {@code GameObject}s contained in
     * the {@code Scene}.</p>
     */
    private ArrayList<GameObject> gameObjects;
    private final ArrayList<GameObject> gameObjectsToRemove;

    /**
     * <p>The current {@code Camera} for this {@code Scene}.</p>
     */
    private Camera camera;

    /**
     * Initializes an empty {@code Scene}.
     */
    public Scene()
    {
        this.loaded = false;
        this.gameObjects = new ArrayList<>();
        this.gameObjectsToRemove = new ArrayList<>();
        this.createCamera();
    }

    /**
     * <p>Returns {@code true} if the {@code Scene} is currently loaded,
     * {@code false} if not.</p>
     * <p>If the {@code Scene} is loaded, it means it exists in the game
     * world at the moment, receives update(), etc. Only one {@code Scene}
     * can be loaded at one moment.</p>
     * @return {@code true} if the {@code Scene} is loaded, {@code false} if
     * not.
     */
    public final boolean isLoaded()
    {
        return this.loaded;
    }

    /**
     * <p>Returns the {@code Scene}'s main {@code Camera}.</p>
     * <p> If a {@code Scene} doesn't have a {@code Camera}, it can't be
     * displayed.</p>
     * @return The {@code Scene}'s main {@code Camera}.
     */
    public final Camera getCamera()
    {
        return this.camera;
    }

    /**
     * <p>Sets the {@code Scene}'s main {@code Camera}.</p>
     * @param camera The new main {@code Camera}.
     * @throws NullPointerException if {@code camera} is {@code null}.
     */
    public final void setCamera(Camera camera)
    {
        Objects.requireNonNull(camera);
        this.camera = camera;
    }

    /**
     * <p>Adds the given {@code gameObject} to the {@code Scene}.</p>
     * <p>All {@code GameObject}s are container in {@code Scene}s.</p>
     * @param gameObject The {@code GameObject} to add to the {@code Scene}.
     * @return {@code gameObject}.
     * @throws NullPointerException if {@code gameObject} is {@code null}.
     */
    public final <T extends GameObject> T addGameObject(T gameObject)
    {
        Objects.requireNonNull(gameObject);
        this.gameObjects.add(gameObject);
        gameObject.setScene(this);
        gameObject.onAddedToScene();
        if (this.loaded)
        {
            gameObject.start();
        }
        return gameObject;
    }

    /**
     * <p>Loads the {@code Scene}.</p>
     */
    public final void load()
    {
        Camera.setMain(this.camera);
        this.loaded = true;
        this.start();
    }

    /**
     * <p>Unloads the {@code Scene}.</p>
     */
    public final void unload()
    {
        this.loaded = false;
    }

    /**
     * <p>Returns all {@code Component}s of {@code type} on all {@code
     * GameObject}s in this {@code Scene}.</p>
     * @param type The type of the {@code Component}s to find.
     * @param <T>
     * @return A {@code Collection} with all {@code Component}s of {@code
     * type} on this {@code Scene}.
     */
    public final <T extends Component> List<T> getAllComponentsOfType(
            Class<T> type)
    {
        List<T> components = new ArrayList<T>();
        this.forEachGameObject(gameObject ->
                components.addAll(gameObject.getAllComponentsOfType(type)));
        return components;
    }

    /**
     * <p>Runs the given {@code action} on all {@code Component}s of the
     * given {@code type} on all the {@code GameObject}s in this {@code
     * Scene}.</p>
     * @param type The type of the {@code Component}s to apply {@code action}
     *            to.
     * @param <T>
     * @param action The {@code Consumer} to run at each {@code Component} of
     * {@code type}.
     */
    public final <T extends Component> void forEachComponentOfType(
            Class<T> type, Consumer<T> action)
    {
        this.getAllComponentsOfType(type).forEach(action);
    }

    /**
     * <p>Runs {@code start()} on all {@code GameObject}s contained in this
     * {@code Scene}.</p>
     */
    final void start()
    {
        if (!loaded)
        {
            return;
        }
        this.forEachActiveGameObject(GameObject::start);
    }

    /**
     * <p>Runs {@code update()} on all {@code GameObject}s contained in this
     * {@code Scene}.</p>
     */
    final void update()
    {
        if (!loaded)
        {
            return;
        }
        this.forEachActiveGameObject(GameObject::update);
    }

    /**
     * <p>Runs {@code lateUpdate()} on all {@code GameObject}s contained in
     * this {@code Scene}.</p>
     */
    final void lateUpdate()
    {
        this.forEachActiveGameObject(GameObject::lateUpdate);

        this.gameObjects.removeAll(this.gameObjectsToRemove);
        this.gameObjectsToRemove.clear();
    }

    /**
     * <p>Removes the given {@code GameObject} from the {@code Scene} at the
     * end of the frame.</p>
     * @param gameObject The {@code GameObject} to remove.
     */
    final void removeGameObject(GameObject gameObject)
    {
        this.gameObjectsToRemove.add(gameObject);
        //this.gameObjects.remove(gameObject);
    }

    /**
     * <p>Runs the given {@code Consumer} on all {@code GameObject}s.</p>
     * @param action The {@code Consumer} to run on all {@code GameObject}s.
     * @throws NullPointerException if {@code action} is {@code null}.
     */
    private void forEachGameObject(Consumer<GameObject> action)
    {
        Objects.requireNonNull(action);
        for (int i = 0; i < this.gameObjects.size(); ++i)
        {
            action.accept(this.gameObjects.get(i));
        }
    }

    /**
     * <p>Runs the given {@code Consumer} on all active {@code GameObject}s.</p>
     * @param action The {@code Consumer} to run.
     * @throws NullPointerException if {@code action} is {@code null}.
     */
    private void forEachActiveGameObject(Consumer<GameObject> action)
    {
        Objects.requireNonNull(action);
        this.forEachGameObject(gameObject ->
        {
           if (gameObject.isActive())
           {
               action.accept(gameObject);
           }
        });
    }

    /**
     * <p>Creates a default {@code Camera} for the {@code Scene}.</p>
     * <p>Any {@code Scene} should have a {@code Camera}, so it's created on
     * initialization.</p>
     */
    private void createCamera()
    {
        CameraGameObject cameraGameObject = new CameraGameObject();
        cameraGameObject.setScene(this);
        this.camera = cameraGameObject.getCamera();
        this.addGameObject(cameraGameObject);
    }

}

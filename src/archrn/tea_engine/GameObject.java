package archrn.tea_engine;

import archrn.tea_engine.physics.Collider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>{@code GameObject} represents an object in the game world.</p>
 * @author Artem
 */
public class GameObject implements Serializable
{

    private final ArrayList<Component> components;
    private final ArrayList<Component> componentsToRemove;
    private Scene scene;
    private Transform transform;
    private boolean active;

    /**
     * <p>Initializes an empty {@code GameObject}.</p>
     */
    public GameObject()
    {
        this.components = new ArrayList<Component>();
        this.componentsToRemove = new ArrayList<Component>();
        this.scene = null;
        this.transform = new Transform();
        this.active = true;
        this.addComponent(transform);
    }

    /**
     * <p>Returns the {@code Scene}, which contains this {@code GameObject}.</p>
     * @return The {@code GameObject}'s {@code Scene}.
     */
    public final Scene getScene()
    {
        return this.scene;
    }

    // TODO: Can a GameObject be reattached to another Scene?
    /**
     * <p>Set's the {@code GameObject}'s {@code Scene}.</p>
     * @param scene The new {@code Scene} to attach this {@code GameObject} to.
     * @throws NullPointerException if {@code scene} is {@code null}.
     */
    public final void setScene(Scene scene)
    {
        Objects.requireNonNull(scene);
        this.scene = scene;
    }

    /**
     * <p>Returns the {@code GameObject}'s {@code Transform} component.</p>
     * @return The {@code GameObject}'s {@code Transform}.
     */
    public final Transform getTransform()
    {
        return this.transform;
    }

    /**
     * <p>Returns {@code true}, if this {@code GameObject} is active now.</p>
     * <p>If {@code GameObject} isn't active, it won't receive {@code
     * update()} and other game cycle events.</p>
     * @return {@code true}, if {@code GameObject} is active.
     */
    public final boolean isActive()
    {
        return this.active;
    }

    /**
     * <p>Sets this {@code GameObject}'s active state.</p>
     * @param active New active state for this {@code GameObject}.
     * @see GameObject#isActive()
     */
    public final void setActive(boolean active)
    {
        this.active = active;
    }

    /**
     * <p>Adds the given {@code Component} to the {@code GameObject}.</p>
     * @param component The {@code Component} to add to the {@code GameObject}.
     * @return {@code component}.
     * @throws NullPointerException if {@code component} is {@code null}.
     */
    public final <T extends Component> T addComponent(T component)
    {
        Objects.requireNonNull(component);
        component.setGameObject(this);
        this.components.add(component);
        return component;
    }

    /**
     * <p>Finds the first {@code Component} of the given {@code type},
     * attached to this {@code GameObject}.</p>
     * @param type The {@code type} of the {@code Component} to find.
     * @param <T> The {@code type} of the {@code Component} to find.
     * @return The first {@code Component} on this {@code GameObject} of the
     * given {@code type}.
     */
    public final <T extends Component> T getComponentOfType(Class<T> type)
    {
        return type.cast(this.findFirstComponentToPass(type::isInstance));
    }

    /**
     * <p>Finds all {@code Component}s of the given {@code type}, attached
     * to this {@code GameObject}.</p>
     * @param type The {@code type} of the {@code Component}s to find.
     * @param <T> The {@code type} of the {@code Component}s to find.
     * @return An {@code ArrayList} containing all the {@code Component}s on
     * this {@code GameObject} of the given {@code type}.
     */
    public final <T extends Component> ArrayList<T> getAllComponentsOfType(
            Class<T> type)
    {
        ArrayList<T> foundComponents = new ArrayList<T>();
        this.forEachComponent(new Consumer<Component>()
        {
            @Override
            public void accept(Component component)
            {
                if (type.isInstance(component))
                {
                    foundComponents.add(type.cast(component));
                }
            }
        });
        return foundComponents;
    }

    /**
     * <p>Destroys the {@code GameObject} and all of it's
     * {@code Component}s.</p>
     * <p>The {@code GameObject} and it's {@code Component}s will actually
     * be destroyed only at the end of the frame. To stop {@code GameObject}
     * and it's {@code Component}s immediately, also use {@code
     * setActive(false)}.</p>
     */
    public final void destroy()
    {
        this.onDestroy();
        this.forEachComponent(Component::destroy);
        this.scene.removeGameObject(this);
    }

    protected void onAddedToScene()
    {
        // To be overriden.
    }

    protected void onDestroy()
    {
        // To be overriden.
    }

    /**
     * <p>Runs {@code start()} on all {@code Components}.</p>
     */
    void start()
    {
        this.forEachActiveComponent(Component::start);
    }

    /**
     * <p>Runs {@code update()} on all {@code Components}.</p>
     */
    void update()
    {
        this.forEachActiveComponent(Component::update);
    }

    /**
     * <p>Runs {@code lateUpdate()} on all {@code Components} and removes all
     * the {@code Components} to be removed this frame.</p>
     */
    void lateUpdate()
    {
        this.forEachActiveComponent(Component::lateUpdate);

        this.components.removeAll(componentsToRemove);
        componentsToRemove.clear();
    }

    public void onCollisionEnter(Collider collider)
    {
        Objects.requireNonNull(collider);
        for (int i = 0; i < this.components.size(); ++i)
        {
            this.components.get(i).onCollisionEnter(collider);
        }
    }

    public void onCollisionExit(Collider collider)
    {
        Objects.requireNonNull(collider);
        for (int i = 0; i < this.components.size(); ++i)
        {
            this.components.get(i).onCollisionExit(collider);
        }
    }

    /**
     * <p>Removes (destroys) the given {@code Component} from the {@code
     * GameObject}.</p>
     * @param component The {@code Component} to remove (destroy) from the
     * {@code GameObject}.
     * @throws NullPointerException if {@code component} is {@code null}.
     */
    final void removeComponent(Component component)
    {
        Objects.requireNonNull(component);
        if (component.getGameObject() == this)
        {
            this.componentsToRemove.add(component);
        }
        else
        {
            throw new IllegalArgumentException("Tried to remove a Component " +
                                    "that isn't attached to this GameObject.");
        }
    }

    /**
     * <p>Runs the given {@code Consumer} on all {@code Component}s.</p>
     * @param action The {@code Consumer} to run on all {@code Component}s.
     * @throws NullPointerException if {@code action} is {@code null}.
     */
    private void forEachComponent(Consumer<Component> action)
    {
        Objects.requireNonNull(action);
        for (int i = 0; i < this.components.size(); ++i)
        {
            action.accept(this.components.get(i));
        }
    }

    /**
     * <p>Runs the given {@code Consumer} on all active {@code Component}s.</p>
     * @param action The {@code Consumer} to run.
     * @throws NullPointerException if {@code action} is {@code null}.
     */
    private void forEachActiveComponent(Consumer<Component> action)
    {
        Objects.requireNonNull(action);
        this.forEachComponent(component ->
        {
            if (component.isActive())
            {
                action.accept(component);
            }
        });
    }

    /**
     * <p>Finds the first {@code Component} to pass the given {@code
     * Predicate} test.</p>
     * @param tester The {@code Predicate} to check on all {@code Component}s.
     * @throws NullPointerException if {@code tester} is {@code null}.
     */
    private Component findFirstComponentToPass(Predicate<Component> tester)
    {
        Objects.requireNonNull(tester);
        for (int i = 0; i < this.components.size(); ++i)
        {
            if (tester.test(this.components.get(i)))
            {
                return this.components.get(i);
            }
        }
        return null;
    }

}

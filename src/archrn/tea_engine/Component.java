package archrn.tea_engine;

import archrn.tea_engine.physics.Collider;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Represents a component, which defines the behavior of a {@code
 * GameObject}.</p>
 * @author Artem
 */
public abstract class Component implements Serializable
{

    private GameObject gameObject;
    private Transform transform;
    private boolean active;

    public Component()
    {
        this.active = true;
    }

    /**
     * <p>Returns the {@code GameObject}, to which this {@code Component} is
     * attached.</p>
     * @return The {@code Component}'s {@code GameObject}.
     */
    public final GameObject getGameObject()
    {
        return this.gameObject;
    }

    /**
     * <p>Returns the {@code Transform} of the {@code Component}'s {@code
     * GameObject}.</p>
     * <p>Each {@code GameObject} has a {@code Transform}, which
     * describes it's position in the game world.</p>
     * @return The {@code Transform} of the {@code Component}'s {@code
     * GameObject}.
     */
    public final Transform getTransform()
    {
        return this.transform;
    }

    /**
     * <p>Returns {@code true}, if this {@code Component} is active now.</p>
     * <p>If {@code Component} isn't active, it won't receive {@code
     * update()} and other game cycle events.</p>
     * @return {@code true}, if the {@code Component} is active.
     */
    public final boolean isActive()
    {
        return this.active;
    }

    /**
     * <p>Sets this {@code Component}'s active state.</p>
     * @param active New active state for the {@code Component}.
     * @see Component#isActive()
     */
    public final void setActive(boolean active)
    {
        this.active = active;
    }

    /**
     * <p>Destroys the component and disattaches it from it's {@code
     * GameObject}.</p>
     * <p>The {@code Component} will actually be destroyed only at the end of
     * the frame. To stop {@code Component} immediately, also use {@code
     * setActive(false)}.</p>
     */
    public final void destroy()
    {
        this.onDestroy();
        this.gameObject.removeComponent(this);
    }

    /**
     * <p>Called once when the {@code Scene} starts or when the {@code
     * GameObject} appears on the {@code Scene}.</p>
     */
    protected void start()
    {
        // To be overridden.
    }

    /**
     * <p>Called every frame.</p>
     */
    protected void update()
    {
        // To be overridden.
    }

    /**
     * <p>Called every frame.</p>
     */
    protected void lateUpdate()
    {
        // To be overridden.
    }

    /**
     * <p>Called before the {@code Component} is destroyed.</p>
     */
    protected void onDestroy()
    {
        // To be overridden.
    }

    protected void onCollisionEnter(Collider other)
    {
        // To be overridden.
    }

    protected void onCollisionExit(Collider other)
    {
        // To be overridden.
    }

    /**
     * <p>Attaches the component to the given {code GameObject}.</p>
     * @param gameObject The {@code GameObject} to attach this component to.
     * @throws RuntimeException if the {@code Component}'s {@code GameObject}
     * is already set.
     * @throws NullPointerException if {@code gameObject} is {@code null}.
     */
    final void setGameObject(GameObject gameObject)
    {
        // Check that gameObject wasn't initialized yet.
        if (this.gameObject != null)
        {
            throw new RuntimeException("Tried to reinitilize the Component's " +
                                       "gameObject.");
        }
        Objects.requireNonNull(gameObject);

        this.gameObject = gameObject;
        this.transform = gameObject.getTransform();
    }

}
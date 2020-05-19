package archrn.tea_engine.physics;

import archrn.tea_engine.Component;
import archrn.tea_engine.SceneManager;
import archrn.tea_engine.Time;
import archrn.tea_engine.geometry.Vector2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Collider
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public abstract class Collider extends Component
{

    private Map<Collider, Boolean> collisions;
    private Vector2 bias;
    private boolean passThrough;

    Collider()
    {
        bias = Vector2.zero();
        passThrough = false;
    }

    @Override
    protected void start()
    {
        this.collisions = new HashMap<>();
    }

    @Override
    protected void update()
    {
        if (this.collisions == null)
        {
            return;
        }

        Collection<Collider> colliders = SceneManager.shared.getCurrentScene()
                .getAllComponentsOfType(Collider.class);

        // Update all collisions.
        for (Collider collider : colliders)
        {
            if (collider == this)
            {
                continue;
            }

            boolean collides = this.collidesWith(collider);
            if (this.collisions.containsKey(collider))
            {
                // If collision changed.
                if (collides != this.collisions.get(collider))
                {
                    if (collides)
                    {
                        this.getGameObject().onCollisionEnter(collider);
                        collider.getGameObject().onCollisionEnter(this);
                    }
                    else
                    {
                        this.getGameObject().onCollisionExit(collider);
                        collider.getGameObject().onCollisionExit(this);
                    }
                    this.collisions.put(collider, collides);
                    collider.collisions.put(this, collides);
                }
            }
            else
            {
                if (collides)
                {
                    this.getGameObject().onCollisionEnter(collider);
                }
                this.collisions.put(collider, collides);
            }
        }
    }

    public abstract boolean collidesWith(Collider other);

    public void setPassThrough(boolean passThrough)
    {
        this.passThrough = passThrough;
    }

    public void move(Vector2 direction)
    {
        Objects.requireNonNull(direction);

        if (direction.magnitude() == 0)
        {
            return;
        }

        if (this.passThrough)
        {
            getTransform().setPosition(Vector2.sum(
                    getTransform().getPosition(), direction));
            return;
        }

        Collection<Collider> colliders = SceneManager.shared
                .getCurrentScene().getAllComponentsOfType(Collider.class);
        float minMove = 1;
        Collider closest = null;
        for (Collider collider : colliders)
        {
            if (!collider.passThrough)
            {
                float movement = move(direction, collider);
                if (movement < minMove)
                {
                    minMove = movement;
                    closest = collider;
                    if (minMove <= 0)
                    {
                        return;
                    }
                }
            }
        }
        if (closest != null)
        {
            minMove -= 0.001;
            this.getGameObject().onCollisionEnter(closest);
            closest.getGameObject().onCollisionEnter(this);
        }
        getTransform().setPosition(Vector2.sum(
                getTransform().getPosition(),
                Vector2.multiplied(direction, minMove)));
    }

    public Vector2 moveTowards(Vector2 position, float speed)
    {
        Objects.requireNonNull(position);

        Vector2 originalDirection = Vector2.difference(
                position, getTransform().getPosition());
        Vector2 direction = Vector2.multiplied(
                originalDirection.normalized(),
                Time.shared.getDeltaTime() * speed);
        direction = Vector2.min(originalDirection, direction);
        move(direction);
        return direction;
    }

    protected Vector2 getBias()
    {
        return this.bias;
    }

    protected void setBias(Vector2 bias)
    {
        this.bias = bias;
    }

    protected Vector2 getBiasedPosition()
    {
        return Vector2.sum(this.getTransform().getPosition(), this.bias);
    }

    protected abstract float move(Vector2 direction, Collider other);

    protected abstract float left();

    protected abstract float right();

    protected abstract float top();

    protected abstract float bot();

}

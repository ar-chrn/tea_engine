package archrn.tea_engine;

import archrn.tea_engine.geometry.Vector2;

import java.util.Objects;

/**
 * <p>{@code Transform} represents the state and location of a {@code
 * GameObject} in the {@code Scene}: it's position, rotation, scale. </p>
 * @author Artem
 */
public final class Transform extends Component
{

    /**
     * <p>The position of the {@code GameObject} relative to it's parent.</p>
     */
    private Vector2 position;

    /**
     * <p>The scale of the {@code GameObject} relative to it's parent.</p>
     */
    private Vector2 scale;

    /**
     * <p>The rotation of the {@code GameObject} relative to it's parent.</p>
     */
    private float rotation;

    /**
     * <p>The parent of the {@code GameObject}.</p>
     * <p>{@code GameObject}s can be attached to each other in a hierarchy.
     * In this case children {@code GameObject}'s {@code Transform}s change
     * together with the parent's {@code Transform}s. </p>
     */
    private Transform parent;

    /**
     * <p>Initializes {@code Transform}.</p>
     */
    public Transform()
    {
        this.position = new Vector2();
        this.scale = Vector2.one();
        this.rotation = 0;
        this.parent = null;
    }

    /**
     * <p>Returns the position of the {@code GameObject} in the game world
     * relative to it's parent.</p>
     * @return The {@code GameObject}'s position relative to parent.
     */
    public final Vector2 getPosition()
    {
        return this.position;
    }

    /**
     * <p>Returns the absolute position of the {@code GameObject} in the game
     * world. (Not relative to parent).</p>
     * @return The {@code GameObject}'s absolute position.
     */
    public final Vector2 getPositionAbsolute()
    {
        if (this.parent != null)
        {
            Vector2 parentPositionAbsolute = this.getParent()
                                                 .getPositionAbsolute();
            return Vector2.sum(this.getPosition(),
                               parentPositionAbsolute);
        }
        else
        {
            return this.getPosition();
        }
    }

    /**
     * <p>Sets the position of the {@code GameObject} in the game world relative
     * to it's parent.</p>
     * @param position The new position of the {@code GameObject} relative to
     *                parent.
     * @throws NullPointerException, if {@code position} is {@code null}.
     */
    public final void setPosition(Vector2 position)
    {
        Objects.requireNonNull(position);
        this.position = position;
    }

    /**
     * <p>Sets the absolute position of the {@code GameObject} in the game
     * world. (Not relative to parent).</p>
     * @param positionAbsolute The new absolute position of the {@code
     * GameObject} in the game world.
     * @throws NullPointerException if {@code positionAbsolute} is {@code null}.
     */
    public final void setPositionAbsolute(Vector2 positionAbsolute)
    {
        Objects.requireNonNull(positionAbsolute);
        if (this.parent != null)
        {
            Vector2 parentPositionAbsolute = this.getParent()
                                                 .getPositionAbsolute();
            this.setPosition(Vector2.difference(positionAbsolute,
                                                parentPositionAbsolute));
        }
        else
        {
            this.setPosition(positionAbsolute);
        }
    }

    /**
     * <p>Returns the scale of the {@code GameObject} in the game world
     * relative to it's parent.</p>
     * @return The {@code GameObject}'s scale relative to parent.
     */
    public final Vector2 getScale()
    {
        return this.scale;
    }

    /**
     * <p>Returns the absolute scale of the {@code GameObject} in the game
     * world. (Not relative to parent).</p>
     * @return The {@code GameObject}'s absolute scale.
     */
    public final Vector2 getScaleAbsolute()
    {
        if (this.parent != null)
        {
            Vector2 scale = this.getScale();
            Vector2 parentScaleAbsolute = this.getParent().getScaleAbsolute();
            return new Vector2(scale.x * parentScaleAbsolute.x,
                               scale.y * parentScaleAbsolute.y);
        }
        else
        {
            return this.getScale();
        }
    }

    /**
     * <p>Sets the scale of the {@code GameObject} in the game world
     * relative to it's parent.</p>
     * @param scale The new scale of the {@code GameObject} relative to parent.
     * @throws NullPointerException if {@code scale} is {@code null}.
     */
    public final void setScale(Vector2 scale)
    {
        Objects.requireNonNull(scale);
        this.scale = scale;
    }

    /**
     * <p>Sets the absolute scale of the {@code GameObject} in the game
     * world. (Not relative to it's parent).</p>
     * @param scaleAbsolute The new absolute scale of the {@code GameObject}.
     * @throws NullPointerException if {@code scaleAbsolute} is {@code null}.
     */
    public final void setScaleAbsolute(Vector2 scaleAbsolute)
    {
        Objects.requireNonNull(scaleAbsolute);
        if (this.parent != null)
        {
            Vector2 parentScaleAbsolute = this.getParent().getScaleAbsolute();
            this.setScale(new Vector2(
                    scaleAbsolute.x / parentScaleAbsolute.x,
                    scaleAbsolute.y / parentScaleAbsolute.y));
        }
        else
        {
            this.setScale(scaleAbsolute);
        }
    }

    /**
     * <p>Returns the rotation of the {@code GameObject} in the game world
     * relative to it's parent.</p>
     * @return The {@code GameObject}'s rotation relative to parent.
     */
    public final float getRotation()
    {
        return this.rotation;
    }

    /**
     * <p>Returns the absolute rotation of the {@code GameObject} in the
     * game world. (Not relative to parent).</p>
     * @return The {@code GameObject}'s absolute rotation.
     */
    public final float getRotationAbsolute()
    {
        if (this.parent != null)
        {
            return this.getRotation() + this.getParent().getRotationAbsolute();
        }
        else
        {
            return this.getRotation();
        }
    }

    /**
     * <p>Sets the rotation of the {@code GameObject} in the game world
     * relative to it's parent.</p>
     * @param rotation The new rotation of the {@code GameObject} realtive to
     *                parent.
     */
    public final void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    /**
     * <p>Sets the absolute rotation of the {@code GameObject} in the game
     * world. (Not relative to parent).</p>
     * @param rotationAbsolute The new absolute rotation of the {@code
     * GameObject}.
     */
    public final void setRotationAbsolute(float rotationAbsolute)
    {
        if (this.parent != null)
        {
            float parentRotationAbsolute = this.getParent()
                                               .getRotationAbsolute();
            this.setRotation(rotationAbsolute - parentRotationAbsolute);
        }
        else
        {
            this.setRotation(rotationAbsolute);
        }
    }

    /**
     * <p>Returns the parent, to which this {@code GameObject} is attached.</p>
     * @return The {@code GameObject}'s parent.
     */
    public final Transform getParent()
    {
        return this.parent;
    }

    /**
     * <p>Sets the parent, to which this {@code GameObject} is attached.</p>
     * @param parent The new {@code GameObject}'s parent.
     */
    public final void setParent(Transform parent)
    {
        // Remember old absolute transform values.
        Vector2 positionAbsolute = this.getPositionAbsolute();
        Vector2 scaleAbsolute = this.getScaleAbsolute();
        float rotationAbsolute = this.getRotationAbsolute();

        this.parent = parent;

        // Set absolute transform values after attaching to another parent.
        this.setPositionAbsolute(positionAbsolute);
        this.setScaleAbsolute(scaleAbsolute);
        this.setRotationAbsolute(rotationAbsolute);
    }

}

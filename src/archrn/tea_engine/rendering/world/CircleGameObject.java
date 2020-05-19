package archrn.tea_engine.rendering.world;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.geometry.Vector2;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code CircleGameObject} represents a basic {@code GameObject} with a
 * {@code CircleRenderer} component on it.</p>
 */
public class CircleGameObject extends GameObject
{

    private final CircleRenderer renderer;

    /**
     * <p>Initializes the {@code CircleGameObject} at the given {@code
     * position} of the given {@code size} with the given {@code color}.</p>
     * @param position The position of the {@code CircleGameObject}.
     * @param size The size of the {@code CirclGameObject}.
     * @param color The color of the {@code CircleRenderer}.
     * @throws NullPointerException if {@code position} or {@code size} or
     * {@code color} is {@code null}.
     */
    public CircleGameObject(Vector2 position, Vector2 size, Color color)
    {
        Objects.requireNonNull(position);
        Objects.requireNonNull(size);
        Objects.requireNonNull(color);

        this.getTransform().setPosition(position);
        this.getTransform().setScale(size);
        this.renderer = this.addComponent(new CircleRenderer(color));
    }

    /**
     * <p>Returns the {@code CircleGameObject}'s {@code CircleRenderer}.</p>
     * @return The {@code CircleRenderer}.
     */
    public CircleRenderer getRenderer()
    {
        return this.renderer;
    }

}

package archrn.tea_engine.rendering.world;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.geometry.Vector2;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code RectangleGameObject} represents a basic {@code GameObject} with
 * a {@code RectangleRenderer} component on it.</p>
 */
public class RectangleGameObject extends GameObject
{

    private final RectangleRenderer renderer;

    /**
     * <p>Initializes the {@code RectangleGameObject}.</p>
     * @param position The position of the {@code RectangleGameObject}.
     * @param size The size of the {@code RectangleGameObject}.
     * @param color The color of the {@code RectangleRenderer}.
     * @throws NullPointerException if {@code position} or {@code size} or
     * {@code color} is {@code null}.
     */
    public RectangleGameObject(Vector2 position, Vector2 size, Color color)
    {
        Objects.requireNonNull(position);
        Objects.requireNonNull(size);
        Objects.requireNonNull(color);

        this.getTransform().setPosition(position);
        this.getTransform().setScale(size);
        this.renderer = this.addComponent(new RectangleRenderer(color));
    }

    /**
     * <p>Return the {@code RectangleGameObject}'s {@code
     * RectangleRenderer}.</p>
     * @return The {@code RectangleRenderer}.
     */
    public RectangleRenderer getRenderer()
    {
        return this.renderer;
    }

}

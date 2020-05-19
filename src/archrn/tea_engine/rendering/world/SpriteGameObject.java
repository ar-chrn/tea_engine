package archrn.tea_engine.rendering.world;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code SpriteGameObject} represents a basic {@code GameObject} with a
 * {@code SpriteRenderer} component on it.</p>
 */
public class SpriteGameObject extends GameObject
{

    private final SpriteRenderer renderer;

    /**
     * <p>Initializes the {@code SpriteRenderer}.</p>
     * @param position The position of the {@code SpriteGameObject}.
     * @param scale The scale of the {@code SpriteGameObject}.
     * @param color The color of the {@code SpriteRenderer}.
     * @param sprite The sprite for the {@code SpriteRenderer}.
     * @throws NullPointerException if {@code position} or {@code scale} or
     * {@code color} or {@code sprite} is {@code null}.
     */
    public SpriteGameObject(Vector2 position, Vector2 scale,
                            Color color, Sprite sprite)
    {
        Objects.requireNonNull(position);
        Objects.requireNonNull(scale);
        Objects.requireNonNull(color);
        Objects.requireNonNull(sprite);

        this.getTransform().setPosition(position);
        this.getTransform().setScale(scale);
        this.renderer = this.addComponent(new SpriteRenderer(sprite, color));
    }

    /**
     * <p>Returns the {@code SpriteGameObject}'s {@code SpriteRenderer}.</p>
     * @return The {@code SpriteRenderer}.
     */
    public SpriteRenderer getRenderer()
    {
        return this.renderer;
    }

}

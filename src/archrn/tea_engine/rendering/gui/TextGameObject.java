package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.geometry.Vector2Int;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code TextGameObject} represents a basic GUI {@code GameObject} with a
 * {@code TextRenderer} component on it.</p>
 */
public class TextGameObject extends GameObject
{

    private final static Color defaultColor = Color.black;
    private final static Vector2Int defaultSize = new Vector2Int(100, 100);

    private TextRenderer renderer;

    /**
     * <p>Initializes the {@code TextGameObject}.</p>
     * @param screenPosition The position of the {@code TextGameObject}.
     * @param text The text for the {@code TextRenderer}.
     * @throws NullPointerException if {@code position} or {@code text} is null.
     */
    public TextGameObject(Vector2Int screenPosition, String text)
    {
        this(screenPosition, text, TextGameObject.defaultColor);
    }

    public TextGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                          String text)
    {
        this(screenPosition, screenSize, text, TextGameObject.defaultColor);
    }

    /**
     * <p>Initializes the {@code TextGameObject}.</p>
     * @param screenPosition The position of the {@code TextGameObject} in
     *                       screen units.
     * @param text The text for the {@code TextRenderer}.
     * @param color The color for the {@code TextRenderer}.
     * @throws NullPointerException if {@code screenPosition} or {@code text}
     * or {@code color} is {@code null}.
     */
    public TextGameObject(Vector2Int screenPosition, String text, Color color)
    {
        this(screenPosition, TextGameObject.defaultSize, text, color);
    }

    public TextGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                          String text, Color color)
    {
        this(screenPosition, screenSize, text, color, false);
    }

    public TextGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                          String text, Color color, boolean center)
    {
        this(screenPosition, screenSize, text, color, center,
             false);
    }

    public TextGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                          String text, Color color, boolean center,
                          boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPosition);
        Objects.requireNonNull(screenSize);
        Objects.requireNonNull(text);
        Objects.requireNonNull(color);

        this.getTransform().setPosition(screenPosition.toVector2());
        this.getTransform().setScale(screenSize.toVector2());
        this.renderer = this.addComponent(
                new TextRenderer(text, center, positionFromScreenCenter));
        this.renderer.setColor(color);
    }

    /**
     * <p>Returns the {@code TextGameObject}'s {@code TextRenderer}.</p>
     * @return The {@code TextRenderer}.
     */
    public TextRenderer getRenderer()
    {
        return this.renderer;
    }

}

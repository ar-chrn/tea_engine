package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.geometry.Vector2Int;

import java.awt.*;
import java.util.Objects;

/**
 *
 */
public class PanelGameObject extends GameObject
{
    public final static Color DEFAULT_COLOR = Color.lightGray;
    private final static int DEFAULT_BORDER_RADIUS = 10;

    private PanelRenderer renderer;

    public PanelGameObject(Vector2Int screenPosition, Vector2Int screenSize)
    {
        this(screenPosition, screenSize,
             PanelGameObject.DEFAULT_COLOR,
             PanelGameObject.DEFAULT_BORDER_RADIUS);
    }

    public PanelGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                           Color color)
    {
        this(screenPosition, screenSize, color,
             PanelGameObject.DEFAULT_BORDER_RADIUS);
    }

    public PanelGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                           Color color, int radius)
    {
        this(screenPosition, screenSize, color, radius,
             false);
    }

    public PanelGameObject(Vector2Int screenPosition, Vector2Int screenSize,
                           Color color, int radius,
                           boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPosition);
        Objects.requireNonNull(screenSize);
        Objects.requireNonNull(color);

        this.getTransform().setPosition(screenPosition.toVector2());
        this.getTransform().setScale(screenSize.toVector2());
        this.renderer = this.addComponent(new PanelRenderer(
                color, radius, positionFromScreenCenter));
    }

    public PanelRenderer getRenderer()
    {
        return this.renderer;
    }

}

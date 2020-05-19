package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.math.MathF;
import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Objects;

/**
 * <p></p>
 * @author Artem
 */
public class PanelRenderer extends Renderer
{

    private int radius;
    private boolean positionFromScreenCenter;

    public PanelRenderer()
    {
        this(0);
    }

    public PanelRenderer(Color color)
    {
        this(color, 0, false);
    }

    public PanelRenderer(int radius)
    {
        super();
        this.setRadius(radius);
        this.positionFromScreenCenter = false;
    }

    public PanelRenderer(Color color, int radius)
    {
        this(color, radius, false);
    }

    public PanelRenderer(Color color, int radius,
                         boolean positionFromScreenCenter)
    {
        super(color);
        this.setRadius(radius);
        this.positionFromScreenCenter = positionFromScreenCenter;
    }

    public int getRadius()
    {
        return this.radius;
    }

    public void setRadius(int radius)
    {
        MathF.requireNonNegative(radius);
        this.radius = radius;
    }

    @Override
    protected void draw(RendererGraphics graphics)
    {
        Objects.requireNonNull(graphics);
        graphics.setColor(this.getColor());
        graphics.fillRectScreenTopLeft(
                this.getTransform().getPositionAbsolute().toVector2Int(),
                this.getTransform().getScaleAbsolute().toVector2Int(),
                this.radius, this.positionFromScreenCenter);
    }

}

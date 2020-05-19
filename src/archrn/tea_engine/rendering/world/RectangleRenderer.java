package archrn.tea_engine.rendering.world;

import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Objects;

/**
 * <p>Renders a rectangle.</p>
 * <p>{@code Transform.scale} used as size.</p>
 * @author Artem
 */
public class RectangleRenderer extends Renderer
{

    /**
     * <p>Initializes the {@code RectangleRenderer}.</p>
     */
    public RectangleRenderer()
    {
        super();
    }

    /**
     * <p>Initializes the {@code RectangleRenderer} with the given {@code
     * color}.</p>
     * @param color The {@code Color} for the {@code RectangleRenderer}.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public RectangleRenderer(Color color)
    {
        super(color);
    }

    /**
     * <p>Renders a rectangle with the given {@code graphics}.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     * @throws NullPointerException if {@code graphics} is {@code null}.
     */
    @Override
    protected void draw(RendererGraphics graphics)
    {
        Objects.requireNonNull(graphics);
        graphics.setColor(this.getColor());
        graphics.fillRect(this.getTransform());
    }

}

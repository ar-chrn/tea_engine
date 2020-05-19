package archrn.tea_engine.rendering.world;

import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code CircleRenderer} renders a circle.</p>
 * <p>{@code Transform.scale} used as size.</p>
 * @author Artem
 */
public class CircleRenderer extends Renderer
{

    /**
     * <p>Initializes the {@code CircleRenderer}.</p>
     */
    public CircleRenderer()
    {
        super();
    }

    /**
     * <p>Initializes the {@code CircleRendere} with the given {@code
     * color}.</p>
     * @param color The {@code Color} for the {@code CircleRenderer}.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public CircleRenderer(Color color)
    {
        super(color);
    }

    /**
     * <p>Renders a circle with the given {@code graphics.}</p>
     * @param graphics The {@code RendererGraphics} to render with.
     * @throws NullPointerException if {@code graphics} is {@code null}.
     */
    @Override
    protected void draw(RendererGraphics graphics)
    {
        Objects.requireNonNull(graphics);
        graphics.setColor(this.getColor());
        graphics.fillOval(this.getTransform());
    }

}

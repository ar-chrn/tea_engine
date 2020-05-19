package archrn.tea_engine.rendering;

import archrn.tea_engine.Component;

import java.awt.*;
import java.util.Objects;

/**
 * <p>{@code Renderer} component renders the {@code GameObject}'s image.</p>
 * @author Artem
 */
public abstract class Renderer extends Component
{

    private Color color;
    private boolean visible;
    private float layer;

    /**
     * <p>Initializes the {@code Renderer} with default color.</p>
     */
    public Renderer()
    {
        this(Color.white);
    }

    /**
     * <p>Initializes the {@code Renderer} with the given {@code color}.</p>
     * @param color The {@code Color} for the {@code Renderer}.
     */
    public Renderer(Color color)
    {
        Objects.requireNonNull(color);
        this.color = color;
        this.visible = true;
        this.layer = 0;
    }

    /**
     * <p>Returns the current {@code Color} of the {@code Renderer}.</p>
     * @return The current {@code Color}.
     */
    public final Color getColor()
    {
        return this.color;
    }

    /**
     * <p>Sets the {@code Color} of the {@code Renderer}.</p>
     * @param color The new {@code Color} of the {@code Renderer}.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public final void setColor(Color color)
    {
        Objects.requireNonNull(color);
        this.color = color;
    }

    /**
     * <p>Returns {@code true} if this {@code Renderer} is displayed now.</p>
     * @return {@code true}, if this {@code Renderer} is displayed now.
     */
    public final boolean isVisible()
    {
        return this.visible;
    }

    /**
     * <p>Sets this {@code Renderer}'s visibility.</p>
     * @param visible {@code Renderer}'s visibility.
     */
    public final void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    /**
     * <p>Renders the {@code GameObject}'s image.</p>
     * <p>Checks visibility and does other preparations. If everything is
     * good, calls {@code draw(RendererGraphics)}.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     */
    public final void render(RendererGraphics graphics)
    {
        if (this.visible)
        {
            this.draw(graphics);
        }
    }

    public float getLayer()
    {
        return layer;
    }

    public void setLayer(float layer)
    {
        this.layer = layer;
    }

    /**
     * <p>Renders the {@code GameObject} after visibility is already checked
     * and other preparations are already made.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     */
    protected abstract void draw(RendererGraphics graphics);

}

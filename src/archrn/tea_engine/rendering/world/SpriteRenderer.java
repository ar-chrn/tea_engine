package archrn.tea_engine.rendering.world;

import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Objects;

/**
 * <p>Renders a sprite.</p>
 * <p>Sprite's own size is multiplied by {@code Transform.scale}.</p>
 * @author Artem
 */
public class SpriteRenderer extends Renderer
{

    // Can be null.
    private Sprite sprite;
    private boolean tiled;

    /**
     * <p>Initializes the {@code SpriteRenderer} with no {@code Sprite}.</p>
     */
    public SpriteRenderer()
    {
        this((Sprite)null);
    }

    /**
     * <p>Initializes the {@code Sprite renderer} with the given {@code
     * color} and no {@code Sprite}.</p>
     * @param color The {@code Color} for the {@code SpriteRenderer}.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public SpriteRenderer(Color color)
    {
        this(null, color);
    }

    /**
     * <p>Initializes the {@code SpriteRenderer} with the given {@code
     * Sprite}.</p>
     * @param sprite The {@code Sprite} rendered by this {@code SpriteRenderer}.
     */
    public SpriteRenderer(Sprite sprite)
    {
        super();
        this.sprite = sprite;
    }

    /**
     * <p>Initializes the {@code SpriteRenderer} with the given {@code sprite}
     * and {@code color}.</p>
     * @param sprite The {@code Sprite} rendered by this {@code SpriteRenderer}.
     * @param color The {@code Color} for the {@code SpriteRenderer}.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public SpriteRenderer(Sprite sprite, Color color)
    {
        super(color);
        this.sprite = sprite;
        this.tiled = false;
    }

    /**
     * <p>Returns the current {@code SpriteRenderer}'s sprite.</p>
     * @return The sprite.
     */
    public Sprite getSprite()
    {
        return this.sprite;
    }

    /**
     * <p>Sets a new {@code Sprite} for the {@code SpriteRenderer}.</p>
     * <p>{@code sprite} can be null.</p>
     * @param sprite The new {@code Sprite} for the {@code SpriteRenderer}.
     */
    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public boolean isTiled()
    {
        return this.tiled;
    }

    public void setTiled(boolean tiled)
    {
        this.tiled = tiled;
    }

    /**
     * <p>Renders the sprite with the given {@code graphics}.</p>
     * <p>If {@code sprite} is null, doesn't render anything.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     * @throws NullPointerException if {@code graphics} is {@code null}.
     */
    @Override
    protected void draw(RendererGraphics graphics)
    {
        if (this.sprite == null)
        {
            return;
        }
        if (this.sprite.getImage() == null)
        {
            return;
        }
        Objects.requireNonNull(graphics);
        graphics.setColor(this.getColor());
        graphics.drawSprite(this.getTransform(), this.sprite, this.tiled);
    }

}

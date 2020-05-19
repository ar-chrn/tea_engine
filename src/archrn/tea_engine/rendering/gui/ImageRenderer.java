package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.util.Objects;

/**
 * ImageRenderer
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class ImageRenderer extends Renderer
{

    private final Sprite sprite;
    private boolean positionFromScreenCenter;

    public ImageRenderer(Sprite sprite)
    {
        this(sprite, false);
    }

    public ImageRenderer(Sprite sprite, boolean positionFromScreenCenter)
    {
        super();
        this.sprite = sprite;
        this.positionFromScreenCenter = positionFromScreenCenter;
    }

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
        graphics.drawSpriteScreen(
                this.getTransform().getPositionAbsolute().toVector2Int(),
                this.getTransform().getScaleAbsolute().toVector2Int(),
                this.sprite, this.positionFromScreenCenter);
    }

}

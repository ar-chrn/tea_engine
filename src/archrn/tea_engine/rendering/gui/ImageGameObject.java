package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2Int;

/**
 * ImageGameObject
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class ImageGameObject extends GameObject
{

    private ImageRenderer renderer;

    public ImageGameObject(Vector2Int screenPosition, Vector2Int screenScale,
                           Sprite sprite)
    {
        this(screenPosition, screenScale, sprite, false);
    }

    public ImageGameObject(Vector2Int screenPosition, Vector2Int screenScale,
                           Sprite sprite, boolean positionFromScreenCenter)
    {
        this.getTransform().setPosition(screenPosition.toVector2());
        this.getTransform().setScale(screenScale.toVector2());
        this.renderer = this.addComponent(new ImageRenderer(
                sprite, positionFromScreenCenter));
    }

    public ImageRenderer getRenderer()
    {
        return renderer;
    }

}

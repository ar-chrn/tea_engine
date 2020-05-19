package archrn.tea_engine.assets;

import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.math.MathF;

import java.awt.image.BufferedImage;

// TODO: Check path for null.

/**
 * <p>{@code Sprite} represents an Image in the game world in world
 * coordinates.</p>
 * @author Artem
 */
public class Sprite
{

    /**
     * <p>Amount of pixels of the {@code Sprite} in 1 world unit.</p>
     * <p>For example, if {@code pixelsPerUnit} equals 64, then a 32x48
     * image would take 0.5x0.75 world space.</p>
     */
    private int pixelsPerUnit;

    private String path;
    private BufferedImage image;

    /**
     * <p>The size of the {@code Sprite} in world units.</p>
     * <p>It is the size of the image divided by {@code pixelsPerUnit}.</p>
     */
    private Vector2 size;

    public Sprite()
    {
        this.pixelsPerUnit = 64;
    }

    /**
     * <p>Initializes the {@code Sprite} with the given image {@code path}.</p>
     * @param path The path of the image represented by this {@code Sprite}.
     */
    public Sprite(String path)
    {
        this.pixelsPerUnit = 64;
        this.setPath(path);
    }

    /**
     * <p>Returns the path of the image represented by this {@code Sprite}.</p>
     * @return The path of the image represented by this {@code Sprite}.
     */
    public String getPath()
    {
        return this.path;
    }

    /**
     * <p>Sets the path of the image represented by this {@code Sprite}.</p>
     * @param path The new path of the image.
     */
    public void setPath(String path)
    {
        this.path = path;
        this.image = AssetManager.shared.getImage(this.path);
        this.updateSize();
    }

    /**
     * <p>Returns amount of image pixels in 1 world unit.</p>
     * @return The amount of image pixels in 1 world unit.
     */
    public int getPixelsPerUnit()
    {
        return this.pixelsPerUnit;
    }

    /**
     * <p>Sets the amount of image pixels in 1 world unit.</p>
     * @param pixelsPerUnit The new amount of image pixels in 1 world unit.
     * @throws IllegalArgumentException if {@code pixelsPerUnit} <= 0.
     */
    public void setPixelsPerUnit(int pixelsPerUnit)
    {
        MathF.requireLargerThanZero(pixelsPerUnit);
        this.pixelsPerUnit = pixelsPerUnit;
        this.updateSize();
    }

    /**
     * <p>Returns the image represented by this {@code Sprite}.</p>
     * <p>Can be null.</p>
     * @return The image represented by this {@code Sprite}.
     */
    public BufferedImage getImage()
    {
        return this.image;
    }

    /**
     * <p>Returns the size of the {@code Sprite} in world units.</p>
     * @return The size of the {@code Sprite} in world units.
     */
    public Vector2 getSize()
    {
        return this.size;
    }

    /**
     * <p>Recalculates the size of the {@code Sprite}.</p>
     * <p>Called after changing {@code path} or {@code pixelsPerUnit}.</p>
     */
    private void updateSize()
    {
        if (this.image != null)
        {
            this.size = new Vector2((float)this.image.getWidth(),
                                    (float)this.image.getHeight());
            this.size.divide(this.pixelsPerUnit);
        }
        else
        {
            this.size = Vector2.zero();
        }
    }

}

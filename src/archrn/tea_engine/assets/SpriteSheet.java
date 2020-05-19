package archrn.tea_engine.assets;

import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.math.MathF;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

// TODO: Check for nulls.

/**
 * <p>{@code SpriteSheet} represents an image that contains several {@code
 * Sprite}s.</p>
 */
public class SpriteSheet
{

    private int pixelsPerUnit;
    private String path;
    private BufferedImage image;
    private ArrayList<Sprite> sprites;

    /**
     * <p>Initializes the {@code SpriteSheet} with the given {@code path}.</p>
     * @param path The path of the {@code SpriteSheet}'s image.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    public SpriteSheet(String path)
    {
        Objects.requireNonNull(path);

        this.pixelsPerUnit = 64;
        this.sprites = new ArrayList<Sprite>();
        this.setPath(path);
    }

    /**
     * <p>Sets the path of the {@code SpriteSheet}'s image.</p>
     * @param path The new path of the image.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    public void setPath(String path)
    {
        Objects.requireNonNull(path);
        this.path = path;
        this.image = AssetManager.shared.getImage(this.path);
    }

    /**
     * <p>Sets the pixels per unit for {@code SpriteSheet}'s {@code
     * Sprite}s.</p>
     * @param pixelsPerUnit The new amount of pixels per world unit.
     * @throws IllegalArgumentException if {@code pixelsPerUnit <= 0}.
     */
    public void setPixelsPerUnit(int pixelsPerUnit)
    {
        MathF.requireLargerThanZero(pixelsPerUnit);
        this.pixelsPerUnit = pixelsPerUnit;
    }

    /**
     * <p>Returns an {@code ArrayList} with all the {@code Sprite}s in the
     * {@code SpriteSheet}.</p>
     * @return The {@code Sprite}s in this {@code SpriteSheet}.
     */
    public ArrayList<Sprite> getSprites()
    {
        return this.sprites;
    }

    /**
     * <p>Splits the {@code SpriteSheet}'s {@code Image} into several {@code
     * Sprite}s based on the given {@code cellSize}.</p>
     * <p>If {@code SpriteSheet}'s image is {@code null}, clears {@code
     * Sprite}s.</p>
     * @param cellSize The size of a cell to split the {@code SpriteSheet}'s
     *                 image.
     * @throws NullPointerException if {@code cellSize} is {@code null}.
     */
    public void splitByCellSize(Vector2Int cellSize)
    {
        Objects.requireNonNull(cellSize);
        if (this.image == null)
        {
            return;
        }
        this.sprites.clear();
        for (int x = 0; x <= this.image.getWidth() - cellSize.x; x += cellSize.x)
        {
            for (int y = 0; y <= this.image.getHeight() - cellSize.y; y += cellSize.y)
            {
                BufferedImage newImage = this.image.getSubimage(
                        x, y, cellSize.x, cellSize.y);
                String newImagePath = this.path + "/" + this.sprites.size();
                AssetManager.shared.addImage(newImagePath, newImage);
                Sprite newSprite = new Sprite(newImagePath);
                newSprite.setPixelsPerUnit(this.pixelsPerUnit);
                this.sprites.add(newSprite);
            }
        }
    }

}

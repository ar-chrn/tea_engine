package archrn.tea_engine.rendering;

import archrn.tea_engine.Screen;
import archrn.tea_engine.Transform;
import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>{@code RendererGraphics} is an analog for {@code Graphics2D} for {@code
 * Renderer} components.</p>
 * <p>{@code RendererGraphics} is created by {@code Camera} and passed to
 * renderers. It is a wrapper around {@code Graphics2D}. It has less
 * functionality, but is easier to use in a game.</p>
 * @author Artem
 */
public class RendererGraphics
{

    private final Graphics2D graphics2D;
    private WorldScreenConverter converter;
    private FontMetrics fontMetrics;

    /**
     * <p>Initializes {@code RendererGraphics} with the given {@code Graphics2D}
     * and {@code IWorldScreenConverter}.</p>
     * <p>{@code Graphics2D} is used to actually draw graphics. {@code
     * IWorldsScreenConverter} is used to convert world coordinates to screen
     * coordinates.</p>
     * @param graphics2D The {@code Graphics2D} to draw.
     * @param converter The {@code IWorldScreenConverter} to convert world
     *                  coordinates to screen coordinates.
     * @throws NullPointerException if {@code graphics2D} or {@code
     * converter} is {@code null}.
     */
    public RendererGraphics(Graphics2D graphics2D,
                            WorldScreenConverter converter)
    {
        Objects.requireNonNull(graphics2D);
        Objects.requireNonNull(converter);

        this.graphics2D = graphics2D;
        this.converter = converter;

        this.graphics2D.setColor(Color.white);
        this.fontMetrics = this.graphics2D.getFontMetrics();
    }

    /**
     * <p>Sets the current color of the {@code RendererGraphics}.</p>
     * <p>Color fills a shape (like with {@code CircleRenderer}) or is added to
     * a {@code Sprite}.</p>
     * @param color The new {@code Color} to set.
     * @throws NullPointerException if {@code color} is {@code null}.
     */
    public void setColor(Color color)
    {
        Objects.requireNonNull(color);
        this.graphics2D.setColor(color);
    }

    /**
     * <p>Sets the current font of the {@code RendererGraphics}.</p>
     * @param font The new {@code Font} to set.
     * @throws NullPointerException if {@code font} is null.
     */
    public void setFont(Font font)
    {
        Objects.requireNonNull(font);
        this.graphics2D.setFont(font);
        this.fontMetrics = this.graphics2D.getFontMetrics();
    }

    /**
     * <p>Draws an oval at the given {@code Vector2 worldPosition} with the
     * size {@code Vector2 worldSize}.</p>
     * @param worldPosition The position of the center of the oval in the
     *                      world coordinates.
     * @param worldSize The size of the oval in the world coordinates.
     * @throws NullPointerException if {@code worldPosition} or {@code
     * worldSize} is {@code null}.
     * @see RendererGraphics#fillOval(Transform)
     */
    public void fillOval(Vector2 worldPosition, Vector2 worldSize)
    {
        Objects.requireNonNull(worldPosition);
        Objects.requireNonNull(worldSize);

        Vector2Int screenPosition = this.converter
                .worldToScreenPositionTopLeft(worldPosition, worldSize);
        Vector2Int screenScale = this.converter.worldToScreenScale(worldSize);

        this.graphics2D.fillOval(screenPosition.x, screenPosition.y,
                                 screenScale.x, screenScale.y);
    }

    /**
     * <p>Draws an oval at {@code transform.position} of size {@code
     * transform.scale}.</p>
     * @see RendererGraphics#fillOval(Vector2, Vector2)
     * @param transform The {@code Transform} of the oval.
     * @throws NullPointerException if {@code transform} is {@code null}.
     */
    public void fillOval(Transform transform)
    {
        Objects.requireNonNull(transform);
        this.fillOval(transform.getPositionAbsolute(),
                      transform.getScaleAbsolute());
    }

    /**
     * <p>Draws a rectangle at the given {@code Vector2 worldPosition} of
     * size {@code Vector2 worldSize}.</p>
     * @param worldPosition The position of the center of the rectangle in
     *                      the world coordinates.
     * @param worldSize The size of the rectangle in the world coordinates.
     * @throws NullPointerException if {@code worldPosition} or {@code
     * worldSize} is null.
     * @see RendererGraphics#fillRect(Transform)
     */
    public void fillRect(Vector2 worldPosition,
                         Vector2 worldSize)
    {
        Objects.requireNonNull(worldPosition);
        Objects.requireNonNull(worldSize);

        Vector2Int screenPosition = this.converter
                .worldToScreenPositionTopLeft(worldPosition, worldSize);
        Vector2Int screenScale = this.converter.worldToScreenScale(worldSize);

        this.graphics2D.fillRect(screenPosition.x, screenPosition.y,
                                 screenScale.x, screenScale.y);
    }

    /**
     * <p>Draws a rectangle at {@code transform.position} of size {@code
     * transform.scale}.</p>
     * @param transform The {@code Transform} of the rectangle.
     * @throws NullPointerException if {@code transform} is {@code null}.
     * @see RendererGraphics#fillRect(Vector2, Vector2)
     */
    public void fillRect(Transform transform)
    {
        Objects.requireNonNull(transform);
        this.fillRect(transform.getPositionAbsolute(),
                      transform.getScaleAbsolute());
    }

    /**
     * <p>Draws a {@code Sprite} at the given {@code Vector2 worldPosition}
     * at scale {@code Vector2 worldScale}.</p>
     * <p>The size of the sprite on the screen is the size of the {@code
     * Sprite} multipled by the {@code worldScale}.</p>
     * @param worldPosition The position of the center of the sprite in the
     *                      world
     *                     coordinates.
     * @param worldScale The scale of the sprite in the world coordinates.
     * @param sprite The {@code Sprite} to display.
     * @throws NullPointerException if {@code worldPosition} or {@code
     * worldScale} or {@code sprite} or {@code sprite.getImage()} is null.
     * @see RendererGraphics#drawSprite(Transform, Sprite)
     */
    public void drawSprite(Vector2 worldPosition,
                           Vector2 worldScale,
                           Sprite sprite)
    {
        drawSprite(worldPosition, worldScale, sprite, false);
    }

    public void drawSprite(Vector2 worldPosition, Vector2 worldScale,
                           Sprite sprite, boolean tiled)
    {
        Objects.requireNonNull(worldPosition);
        Objects.requireNonNull(worldScale);
        Objects.requireNonNull(sprite);

        BufferedImage image = sprite.getImage();
        Vector2 spriteSize = sprite.getSize();
        Objects.requireNonNull(image);

        Vector2 worldSize = worldScale.duplicate();
        int xSign = (int)Math.signum(worldScale.x);
        int ySign = (int)Math.signum(worldScale.y);
        worldSize.x *= spriteSize.x;
        worldSize.y *= spriteSize.y;
        Vector2Int screenPosition = this.converter.worldToScreenPositionTopLeft(
                worldPosition, worldSize);
        worldSize.x *= xSign;
        worldSize.y *= ySign;
        Vector2 totalScreenSize =
                this.converter.worldToScreenScaleVector2(worldSize);
        Vector2 screenSize;
        if (tiled)
        {
            worldSize = spriteSize.duplicate();
            screenSize = this.converter.worldToScreenScaleVector2(worldSize);
        }
        else
        {
            screenSize = totalScreenSize;
        }

        totalScreenSize.add(Vector2.multiplied(screenSize, 0.2f));

        for (float x = 0; x + screenSize.x <= totalScreenSize.x; x += screenSize.x)
        {
            for (float y = 0; y + screenSize.y <= totalScreenSize.y;
                    y += screenSize.y)
            {
                this.graphics2D.drawImage(
                        image,
                        Math.round(screenPosition.x + x * xSign),
                        Math.round(screenPosition.y + y * ySign),
                        Math.round(screenSize.x * xSign),
                        Math.round(screenSize.y * ySign),
                        Screen.shared);
            }
        }
    }

    /**
     * <p>Draws a {@code Sprite} at {@code transform.position} at scale
     * {@code transform.scale}</p>
     * @param transform The {@code Transform} of the {@code Sprite}.
     * @param sprite The {@code Sprite} to display.
     * @throws NullPointerException if {@code transform} or {@code sprite} or
     * {@code sprite.getImage()} is null.
     * @see RendererGraphics#drawSprite(Vector2, Vector2, Sprite)
     */
    public void drawSprite(Transform transform, Sprite sprite)
    {
        this.drawSprite(transform, sprite, false);
    }

    public void drawSprite(Transform transform, Sprite sprite, boolean tiled)
    {
        Objects.requireNonNull(transform);
        this.drawSprite(transform.getPositionAbsolute(),
                        transform.getScaleAbsolute(),
                        sprite, tiled);
    }

    /**
     * <p>Displays an oval in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionCenter The position of the oval's center in
     *                             screen units.
     * @param screenSize The size of the oval in screen units.
     * @throws NullPointerException if {@code screenPositionCenter} or {@code
     * screenSize} is null.
     */
    public void fillOvalScreen(Vector2Int screenPositionCenter,
                               Vector2Int screenSize)
    {
        Objects.requireNonNull(screenPositionCenter);
        Objects.requireNonNull(screenSize);

        Vector2Int screenPositionTopLeft =
                this.converter.positionTopLeftScreen(screenPositionCenter,
                                                     screenSize);
        this.fillOvalScreenTopLeft(screenPositionTopLeft, screenSize);
    }

    /**
     * <p>Displays an oval in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionTopLeft The position of the oval's top left corner
     *                              in screen units.
     * @param screenSize The size of the oval in screen units.
     * @throws NullPointerException if {@code screenPositionTopLeft} or
     * {@code screenSize} is null.
     */
    public void fillOvalScreenTopLeft(Vector2Int screenPositionTopLeft,
                                      Vector2Int screenSize)
    {
        Objects.requireNonNull(screenPositionTopLeft);
        Objects.requireNonNull(screenSize);

        this.graphics2D.fillOval(screenPositionTopLeft.x,
                                 screenPositionTopLeft.y,
                                 screenSize.x,
                                 screenSize.y);
    }

    /**
     * <p>Displays a rectangle in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionCenter The position of the rectangle's center in
     *                             screen units.
     * @param screenSize The size of the rectangle in screen units.
     * @throws NullPointerException if {@code screenPositionCenter} or {@code
     * screenSize} is null.
     */
    public void fillRectScreen(Vector2Int screenPositionCenter,
                               Vector2Int screenSize)
    {
        this.fillRectScreen(screenPositionCenter, screenSize, 0);
    }

    public void fillRectScreen(Vector2Int screenPositionCenter,
                               Vector2Int screenSize,
                               int borderRadius)
    {
        Objects.requireNonNull(screenPositionCenter);
        Objects.requireNonNull(screenSize);

        Vector2Int screenPositionTopLeft =
                this.converter.positionTopLeftScreen(screenPositionCenter,
                                                     screenSize);
        this.fillRectScreenTopLeft(screenPositionTopLeft, screenSize,
                                   borderRadius);
    }

    /**
     * <p>Displays a rectangle in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionTopLeft The position of the rectangle's top left
     *                              corner in screen units.
     * @param screenSize The size of the rectangle in screen units.
     * @throws NullPointerException if {@code screenPositionTopLeft} or
     * {@code screenSize} is null.
     */
    public void fillRectScreenTopLeft(Vector2Int screenPositionTopLeft,
                                      Vector2Int screenSize)
    {
        this.fillRectScreenTopLeft(screenPositionTopLeft,
                                   screenSize, 0);
    }

    public void fillRectScreenTopLeft(Vector2Int screenPositionTopLeft,
                                      Vector2Int screenSize,
                                      int borderRadius)
    {
        this.fillRectScreenTopLeft(screenPositionTopLeft, screenSize,
                                   borderRadius, false);
    }

    public void fillRectScreenTopLeft(Vector2Int screenPositionTopLeft,
                                      Vector2Int screenSize,
                                      int borderRadius,
                                      boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPositionTopLeft);
        Objects.requireNonNull(screenSize);

        if (positionFromScreenCenter)
        {
            screenPositionTopLeft.add(Vector2Int.quotient(
                    Screen.shared.getScreenSize().toVector2Int(), 2));
        }

        this.graphics2D.fillRoundRect(screenPositionTopLeft.x,
                                      screenPositionTopLeft.y,
                                      screenSize.x, screenSize.y,
                                      borderRadius, borderRadius);
    }

    public void drawSpriteScreen(Vector2Int screenPositionCenter,
                                 Vector2Int screenSize,
                                 Sprite sprite)
    {
        this.drawSpriteScreen(screenPositionCenter, screenSize, sprite,
                              false);
    }

    /**
     * <p>Displays a sprite in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionCenter The position of the sprite's center in
     *                             screen units.
     * @param screenSize The size of the sprite in screen units. It's size,
     *                   not scale. I.e. the exact size of the image on the
     *                   screen, not the scale of the image, which would
     *                   be multiplied by image's own size.
     * @param sprite The {@code Sprite} to display.
     * @throws NullPointerException if {@code screenPositionCenter} or {@code
     * screenSize} or {@code sprite} or {@code sprite.getImage()} is null.
     */
    public void drawSpriteScreen(Vector2Int screenPositionCenter,
                                 Vector2Int screenSize, Sprite sprite,
                                 boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPositionCenter);
        Objects.requireNonNull(screenSize);

        if (positionFromScreenCenter)
        {
            screenPositionCenter.add(Vector2Int.quotient(
                    Screen.shared.getScreenSize().toVector2Int(), 2));
        }

        Vector2Int screenPositionTopLeft =
                this.converter.positionTopLeftScreen(screenPositionCenter,
                                                     screenSize);
        this.drawSpriteScreenTopLeft(screenPositionTopLeft, screenSize, sprite);
    }

    /**
     * <p>Displays a sprite in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPositionTopLeft The position of the sprite's top left
     *                              corner in screen units.
     * @param screenSize The size of the sprite in screen units. It's size,
     *                   not scale. I.e. the exact size of the image on the
     *                   screen, not the scale of the image, which would be
     *                   multiplied by image's own size.
     * @param sprite The {@code Sprite} to display.
     * @throws NullPointerException if {@code screenPositionTopLeft} or
     * {@code screenSize} or {@code sprite} or {@code sprite.getImage()} is
     * null.
     */
    public void drawSpriteScreenTopLeft(Vector2Int screenPositionTopLeft,
                                        Vector2Int screenSize,
                                        Sprite sprite)
    {
        Objects.requireNonNull(screenPositionTopLeft);
        Objects.requireNonNull(screenSize);
        Objects.requireNonNull(sprite);

        BufferedImage image = sprite.getImage();
        Objects.requireNonNull(image);

        this.graphics2D.drawImage(image,
                                  screenPositionTopLeft.x,
                                  screenPositionTopLeft.y,
                                  screenSize.x, screenSize.y,
                                  Screen.shared);
    }

    /**
     * <p>Displays a text in screen coordinates.</p>
     * <p>Should be used for GUI.</p>
     * @param screenPosition  The position of the string's top left corner in
     *                       screen units.
     * @param string The {@code String} to display.
     * @throws NullPointerException if {@code string} or {@code screenPosition}
     * is null.
     */
    public void drawStringTopLeft(Vector2Int screenPosition, String string)
    {
        Objects.requireNonNull(string);
        Objects.requireNonNull(screenPosition);
        this.graphics2D.drawString(string, screenPosition.x, screenPosition.y);
    }

    public void drawStringTopLeft(Vector2Int screenPosition,
                                  Vector2Int screenSize,
                                  String string)
    {
        drawStringTopLeft(screenPosition, screenSize, string,
                          false);
    }

    public void drawStringTopLeft(Vector2Int screenPosition,
                                  Vector2Int screenSize,
                                  String string,
                                  boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPosition);
        Objects.requireNonNull(screenSize);
        Objects.requireNonNull(string);

        if (positionFromScreenCenter)
        {
            screenPosition.add(Vector2Int.quotient(
                    Screen.shared.getScreenSize().toVector2Int(), 2));
        }

        int lineHeight = this.fontMetrics.getHeight();
        int currentHeight = screenPosition.y + lineHeight;

        ArrayList<String> lines = fitStringInBox(string, screenSize);
        for (String line : lines)
        {
            this.graphics2D.drawString(line, screenPosition.x, currentHeight);
            currentHeight += lineHeight;
        }
    }

    public void drawStringCenter(Vector2Int screenPositionTopLeft,
                                 Vector2Int screenSize,
                                 String string)
    {
        drawStringCenter(screenPositionTopLeft, screenSize, string,
                         false);
    }

    public void drawStringCenter(Vector2Int screenPositionTopLeft,
                                 Vector2Int screenSize,
                                 String string,
                                 boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(screenPositionTopLeft);
        Objects.requireNonNull(screenSize);
        Objects.requireNonNull(string);

        if (positionFromScreenCenter)
        {
            screenPositionTopLeft.add(Vector2Int.quotient(
                    Screen.shared.getScreenSize().toVector2Int(), 2));
        }

        int lineHeight = this.fontMetrics.getHeight();
        ArrayList<String> lines = fitStringInBox(string, screenSize);

        int yPosition = screenPositionTopLeft.y
                + (screenSize.y - lineHeight * lines.size()) / 2
                + lineHeight;
        for (String line: lines)
        {
            int lineWidth = this.fontMetrics.stringWidth(line);
            int xPosition = screenPositionTopLeft.x
                    + (screenSize.x - lineWidth) / 2;
            this.graphics2D.drawString(line, xPosition, yPosition);
            yPosition += lineHeight;
        }
    }

    private ArrayList<String> fitStringInBox(String string, Vector2Int boxSize)
    {
        Objects.requireNonNull(string);
        Objects.requireNonNull(boxSize);

        int lineHeight = this.fontMetrics.getHeight();
        Vector2Int currentPosition = Vector2Int.zero();
        ArrayList<String> lines = new ArrayList<String>();
        String currentLine = "";
        currentPosition.y = lineHeight;
        boolean fitsHeight = currentPosition.y <= boxSize.y;

        // Look at each paragraph.
        // To keep the string's line breaks.
        String[] paragraphs = string.split("\n");
        for (int pi = 0; pi < paragraphs.length && fitsHeight; ++pi)
        {
            // Look at each word in the paragraph.
            String[] words = paragraphs[pi].split(" ");
            for (int wi = 0; wi < words.length && fitsHeight; ++wi)
            {
                int wordWidth = this.fontMetrics.stringWidth(words[wi]);

                // If the word doesn't fit in this line, break line.
                if (currentPosition.x + wordWidth > boxSize.x)
                {
                    lines.add(currentLine);
                    currentLine = "";

                    currentPosition.x = 0;
                    currentPosition.y += lineHeight;

                    fitsHeight = currentPosition.y <= boxSize.y;
                }

                // If the current line fits in the box height, add the word to
                // the currentLine.
                if (fitsHeight)
                {
                    // Add space after the word and recalculate wordWidth.
                    words[wi] += " ";
                    currentLine += words[wi];
                    wordWidth = this.fontMetrics.stringWidth(words[wi]);
                    currentPosition.x += wordWidth;
                }
            }
            // Break line at the end of the paragraph.
            lines.add(currentLine);
            currentLine = "";
            currentPosition.x = 0;
            currentPosition.y += lineHeight;
            fitsHeight = currentPosition.y <= boxSize.y;
        }

        return lines;
    }

}

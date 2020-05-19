package archrn.tea_engine.rendering.gui;

import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Objects;

/**
 * <p></p>
 * @author Artem
 */
public class TextRenderer extends Renderer
{

    private final static Font defaultFont = new Font("Serif",
                                                     Font.PLAIN,
                                                     18);

    // Can be null.
    private String text;
    // Can't be null.
    private Font font;
    private boolean center;
    private boolean positionFromScreenCenter;

    /**
     * <p>Initializes a text renderer with no text displayed and the default
     * font.</p>
     */
    public TextRenderer()
    {
        this(null);
    }

    /**
     * <p>Initializes a text renderer with the given {@code text} and the
     * default font.</p>
     * @param text The text to display. Can be null, then nothing is displayed.
     */
    public TextRenderer(String text)
    {
        this(text, TextRenderer.defaultFont, false);
    }

    public TextRenderer(String text, boolean center)
    {
        this(text, TextRenderer.defaultFont, center);
    }

    public TextRenderer(String text, boolean center,
                        boolean positionFromScreenCenter)
    {
        this(text, TextRenderer.defaultFont, center, positionFromScreenCenter);
    }

    public TextRenderer(String text, Font font, boolean center)
    {
        this(text, font, center, false);
    }

    /**
     * <p>Initializes a text renderer with the given {@code text} and {@code
     * font}.</p>
     * @param text The text to display. Can be null, then nothing is displayed.
     * @param font The font to display with.
     */
    public TextRenderer(String text, Font font, boolean center,
                        boolean positionFromScreenCenter)
    {
        Objects.requireNonNull(font);
        this.text = text;
        this.font = font;
        this.center = center;
        this.positionFromScreenCenter = positionFromScreenCenter;
    }

    /**
     * <p>Returns the text displayed by this {@code TextRenderer}.</p>
     * @return The text displayed by this {@code TextRenderer}.
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * <p>Sets the text displayed by this {@code TextRenderer}.</p>
     * @param text The new text displayed by this {@code TextRenderer}.
     */
    public void setText(String text)
    {
        this.text = text;
    }

    /**
     * <p>Returns this {@code TextRenderer}'s font.</p>
     * @return This {@code TextRenderer}'s font.
     */
    public Font getFont()
    {
        return this.font;
    }

    /**
     * <p>Sets this {@code TextRenderer}'s font.</p>
     * @param font New font for this {@code TextRenderer}.
     * @throws NullPointerException if {@code font} is {@code null}.
     */
    public void setFont(Font font)
    {
        Objects.requireNonNull(font);
        this.font = font;
    }

    public boolean isCenter()
    {
        return this.center;
    }

    public void setCenter(boolean center)
    {
        this.center = center;
    }

    /**
     * <p>Changes this {@code TextRenderer}'s font to default.</p>
     */
    public void clearFont()
    {
        this.font = TextRenderer.defaultFont;
    }

    /**
     * <p>Renders the text in screen coordinates with the given {@code
     * graphics.}</p>
     * <p>If {@code text} is null, doesn't render anything.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     * @throws NullPointerException if {@code graphics} is {@code null}.
     */
    @Override
    protected void draw(RendererGraphics graphics)
    {
        if (this.text == null)
        {
            return;
        }
        Objects.requireNonNull(graphics);
        graphics.setColor(this.getColor());
        graphics.setFont(this.font);
        // TextRenderer's position should be in screen units.
        if (this.center)
        {
            graphics.drawStringCenter(
                    this.getTransform().getPositionAbsolute().toVector2Int(),
                    this.getTransform().getScaleAbsolute().toVector2Int(),
                    this.text, positionFromScreenCenter);
        }
        else
        {
            graphics.drawStringTopLeft(
                    this.getTransform().getPositionAbsolute().toVector2Int(),
                    this.getTransform().getScaleAbsolute().toVector2Int(),
                    this.text, positionFromScreenCenter);
        }
    }

}

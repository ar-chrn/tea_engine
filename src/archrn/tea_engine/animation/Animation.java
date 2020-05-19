package archrn.tea_engine.animation;

import archrn.tea_engine.assets.Sprite;
import archrn.tea_engine.math.MathF;

import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>{@code Animation} represents a sequence of {@code Sprite}s.</p>
 * @author Artem
 */
public class Animation
{

    private final static int defaultFramesPerSecond = 10;

    private ArrayList<Sprite> sprites;
    private int framesPerSecond;

    /**
     * <p>Initializes the {@code Animation} with the default fps (10).</p>
     */
    public Animation()
    {
        this.sprites = new ArrayList<Sprite>();
        this.framesPerSecond = Animation.defaultFramesPerSecond;
    }

    /**
     * <p>Returns the amount of {@code Sprite}s in the {@code Animation}.</p>
     * @return The amount of {@code Sprite}s in the {@code Animation}.
     */
    public int getSpritesAmount()
    {
        return this.sprites.size();
    }

    /**
     * <p>Returns the {@code Sprite} at the given {@code index} in this
     * {@code Animation}.</p>
     * @param index The index of the {@code Sprite}.
     * @return The {@code Sprite} with the given {@code index}.
     * @throws IndexOutOfBoundsException if a {@code Sprite} with the given
     * {@code index} doesn't exist.
     */
    public Sprite getSprite(int index)
    {
        return this.sprites.get(index);
    }

    /**
     * <p>Adds a single {@code Sprite} to the {@code Animation}.</p>
     * @param sprite The {@code Sprite} to add to the animation.
     * @throws NullPointerException if {@code sprite} is {@code null}.
     */
    public void addSprite(Sprite sprite)
    {
        Objects.requireNonNull(sprite);
        this.sprites.add(sprite);
    }

    /**
     * <p>Adds the given {@code Sprite}s to the {@code Animation}.</p>
     * @param sprites The {@code Sprite}s to add..
     * @throws NullPointerException if {@code sprites} is {@code null}.
     */
    public void addSprites(ArrayList<Sprite> sprites)
    {
        Objects.requireNonNull(sprites);
        for (Sprite sprite : sprites)
        {
            this.addSprite(sprite);
        }
    }

    /**
     * <p>Returns this {@code Animation}'s frames per second.</p>
     * @return Frames per second.
     */
    public int getFramesPerSecond()
    {
        return this.framesPerSecond;
    }

    /**
     * <p>Returns the length of one frame of the animation in milliseconds.</p>
     * <p>1 second / {@code framesPerSecond}.</p>
     * @return The length of one frame of the animation in milliseconds.
     */
    public int getFrameLengthMilliseconds()
    {
        return 1000 / this.framesPerSecond;
    }

    /**
     * <p>Stes the frames per second for this {@code Animation}.</p>
     * @param framesPerSecond The new frames per second for animation.
     * @throws NullPointerException if {@code framesPerSecond <= 0}.
     */
    public void setFramesPerSecond(int framesPerSecond)
    {
        MathF.requireLargerThanZero(framesPerSecond);
        this.framesPerSecond = framesPerSecond;
    }

}

package archrn.tea_engine.animation;

import archrn.tea_engine.Component;
import archrn.tea_engine.GameObject;
import archrn.tea_engine.rendering.world.SpriteRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * <p></p>
 * @author Artem
 */
public class Animator extends Component
{

    private Animation animation;
    private Animation nextAnimation;
    private SpriteRenderer spriteRenderer;
    private int currentSpriteIndex;
    private Timer timer;

    /**
     * <p>Initializes the {@code Animator} with the given {@code animation}.</p>
     * @param animation The {@code Animation} for the {@code Animator}.
     * @throws NullPointerException if {@code animation} is {@code null}.
     */
    public Animator(Animation animation)
    {
        Objects.requireNonNull(animation);
        this.animation = animation;
        this.currentSpriteIndex = 0;
    }

    /**
     * <p>Returns this {@code Animator}'s {@code Animation}.</p>
     * @return The {@code Animator}'s {@code Animation}.
     */
    public Animation getAnimation()
    {
        return this.animation;
    }

    public void setAnimation(Animation animation)
    {
        if (animation == this.animation)
        {
            return;
        }
        this.animation = animation;
        this.start();
    }

    public void setNextAnimation(Animation animation)
    {
        this.nextAnimation = animation;
    }

    /**
     * <p>Initializes the {@code SpriteRenderer} with the initial {@code Sprite}
     * and starts playing the {@code Animation}. </p>
     */
    @Override
    protected void start()
    {
        this.initializeSpriteRenderer();
        if (this.timer != null)
        {
            this.timer.stop();
        }
        this.timer = new Timer(this.animation.getFrameLengthMilliseconds(),
                     (ActionEvent e) -> Animator.this.updateAnimation());
        this.timer.start();
    }

    /**
     * <p>Creates or finds a {@code SpriteRenderer} component on this {@code
     * GameObject} and sets the first {@code Sprite}.</p>
     */
    private void initializeSpriteRenderer()
    {
        // Find or create SpriteRenderer.
        GameObject gameObject = this.getGameObject();
        this.spriteRenderer = gameObject.getComponentOfType(SpriteRenderer.class);
        if (this.spriteRenderer == null)
        {
            this.spriteRenderer = gameObject.addComponent(new SpriteRenderer());
        }

        // Set first sprite.
        if (this.animation.getSpritesAmount() > 0)
        {
            this.spriteRenderer.setSprite(this.animation.getSprite(0));
        }
    }

    /**
     * <p>Changes the {@code SpriteRenderer}'s currently displayed {@code
     * Sprite} to the next {@code Sprite} in the {@code Animation} sequence.</p>
     */
    private void updateAnimation()
    {
        int spritesAmount = this.animation.getSpritesAmount();
        if (spritesAmount == 0)
        {
            return;
        }

//        this.currentSpriteIndex = (this.currentSpriteIndex + 1) % spritesAmount;
        this.currentSpriteIndex += 1;
        if (this.currentSpriteIndex >= spritesAmount)
        {
            this.currentSpriteIndex = 0;
            if (this.nextAnimation != null)
            {
                this.animation = nextAnimation;
                this.nextAnimation = null;
                this.setAnimation(this.animation);
                return;
            }
        }
        this.spriteRenderer.setSprite(
                this.animation.getSprite(this.currentSpriteIndex));
    }

}

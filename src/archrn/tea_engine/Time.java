package archrn.tea_engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Implement FPS.
// TODO: Need to use Timer here? It would be easier to just make an infinite
//  cycle. It would be easier in terms of dynamic FPS and other stuff.

/**
 * <p>{@code Time} calls every-frame actions in the game and calculates
 * frame rate and delta time.</p>
 * @author Artem
 */
public final class Time implements ActionListener
{

    public static final Time shared = new Time();

    /**
     * <p>Delay in milliseconds before and between Timer ticks.</p>
     */
    private int delay = 25;

    private Timer timer;
    private long frameStartTime;
    private float deltaTime;

    /**
     * <p>Initializes {@code Time}.</p>
     */
    private Time()
    {
        this.timer = new Timer(this.delay, this);
    }

    /**
     * <p>Does all the every-frame actions.</p>
     * @param e {@code ActionEvent} with information about the event.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.updateFrameRate();
        SceneManager.shared.getCurrentScene().update();
        SceneManager.shared.getCurrentScene().lateUpdate();
        Screen.shared.repaint();
    }

    public void setFrameRate(int framesPerSecond)
    {
        this.delay = 1000 / framesPerSecond;
        this.timer.setDelay(this.delay);
    }

    /**
     * <p>Returns the time when this frame has started.</p>
     * @return The time when this frame has started.
     */
    public long getFrameStartTime()
    {
        return this.frameStartTime;
    }

    /**
     * <p>Returns the time since the start of the previous frame until the
     * start of this frame.</p>
     * @return The time since the start of the previous frame until the start
     * of this frame.
     */
    public float getDeltaTime()
    {
        return this.deltaTime;
    }

    /**
     * <p>Starts {@code Time}.</p>
     * <p>Starts calling all the every-frame actions.</p>
     */
    void start()
    {
        this.frameStartTime = System.currentTimeMillis();
        this.timer.start();
    }

    /**
     * <p>Pauses {@code Time}.</p>
     * <p>Stops calling every-frame actions.</p>
     */
    void pause()
    {
        this.timer.stop();
    }

    /**
     * <p>Updates {@code deltaTime} and frame rate.</p>
     * <p>Called at the start of each frame.</p>
     */
    private void updateFrameRate()
    {
        long currentTime = System.currentTimeMillis();
        this.deltaTime = (currentTime - this.frameStartTime) / 1000f;
        this.frameStartTime = currentTime;
    }

}

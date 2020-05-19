package archrn.tea_engine.input;

import archrn.tea_engine.camera.Camera;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

// TODO: Does KeyEvent.getKeyCode() work with MouseEvent.getButton()?
// TODO: Clicks and types.

/**
 * <p>{@code Input} collects and provides information about keyboard and
 * mouse input.</p>
 * @author Artem
 */
public class Input implements InputProvider, KeyListener, MouseListener,
        MouseMotionListener
{

    private static final Input sharedAsInput = new Input();
    public static final InputProvider shared = sharedAsInput;

    private HashMap<Integer, Boolean> keyStates;
    private Vector2Int mousePosition;

    /**
     * <p>Initializes {@code Input}.</p>
     */
    private Input()
    {
        this.keyStates = new HashMap<Integer, Boolean>();
        this.mousePosition = new Vector2Int();
    }

    public static Input getSharedAsInput()
    {
        return sharedAsInput;
    }

    /**
     * <p>Doesn't do anything currently.</p>
     * <p>keyTyped() is only called when a character (a letter) is typed on
     * the keyboard. It doesn't work with arrow keys etc.</p>
     * @param e {@code KeyEvent} with the typed key.
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * <p>Registers that the key is pressed.</p>
     * @param e {@code KeyEvent} with the pressed key.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        this.keyStates.put(e.getKeyCode(), true);
    }

    /**
     * <p>Registers that the key is released.</p>
     * @param e {@code KeyEvent} with the released key.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        this.keyStates.put(e.getKeyCode(), false);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    /**
     * <p>Registers that the mouse button is pressed.</p>
     * @param e {@code KeyEvent} with the pressed button.
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.keyStates.put(e.getButton(), true);
    }

    /**
     * <p>Registers that the mouse button is released.</p>
     * @param e {@code KeyEvent} with the released button.
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.keyStates.put(e.getButton(), false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * <p>Updates the mouse position.</p>
     * @param e {@code KeyEvent} with the mouse position.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        this.updateMousePosition(e);
    }

    /**
     * <p>Updates the mouse position.</p>
     * @param e {@code KeyEvent} with the mouse position.
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.updateMousePosition(e);
    }

    /**
     * <p>Checks if the {@code key} is pressed.</p>
     * @param key The key to check. Can be key code from {@code
     * java.awt.event.KeyEvent} or mouse code from {@code
     * java.awt.event.MouseEvent} or key shortcut from {@code Input}.
     * @return {@code true} if the {@code key} is currently pressed, {@code
     * false} else.
     */
    @Override
    public boolean getKey(int key)
    {
        return this.keyStates.getOrDefault(key, false);
    }

    /**
     * <p>Returns the current mouse position in screen units.</p>
     * @return Current position of the mouse in screen units.
     */
    @Override
    public Vector2Int getMousePosition()
    {
        return this.mousePosition;
    }

    /**
     * <p>Returns the current mouse position in world units</p>
     * @return Current position of the mouse in world units.
     */
    @Override
    public Vector2 getMousePositionWorld()
    {
        return Camera.getMain().screenToWorldPosition(this.mousePosition);
    }

    /**
     * <p>Updates mouse position from the given {@code MouseEvent}.</p>
     * @param e The {@code MouseEvent} with the mouse position.
     */
    private void updateMousePosition(MouseEvent e)
    {
        this.mousePosition.x = e.getX();
        this.mousePosition.y = e.getY();
    }

}

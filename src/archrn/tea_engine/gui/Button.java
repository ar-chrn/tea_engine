package archrn.tea_engine.gui;

import archrn.tea_engine.Component;
import archrn.tea_engine.Screen;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.input.Input;

import java.util.ArrayList;
import java.util.Objects;

// TODO: Rework after implementing clicks in Input.

/**
 *
 */
public class Button extends Component
{

    private ArrayList<Runnable> onClick;
    private boolean mouseLeftLastFrame;
    private boolean positionFromScreenCenter;

    public Button()
    {
        this.onClick = new ArrayList<Runnable>();
        this.mouseLeftLastFrame = false;
    }

    public Button(Runnable onClick)
    {
        this(onClick, false);
    }

    public Button(Runnable onClick, boolean positionFromScreenCenter)
    {
        this.onClick = new ArrayList<Runnable>();
        this.addOnClick(onClick);
        this.mouseLeftLastFrame = false;
        this.positionFromScreenCenter = positionFromScreenCenter;
    }

    public void addOnClick(Runnable onClick)
    {
        Objects.requireNonNull(onClick);
        this.onClick.add(onClick);
    }

    public void removeOnClick(Runnable onClick)
    {
        Objects.requireNonNull(onClick);
        this.onClick.remove(onClick);
    }

    @Override
    protected void update()
    {
        boolean mouseLeft = Input.shared.getKey(Input.MOUSE_LEFT);
        if (mouseLeft && !this.mouseLeftLastFrame)
        {
            if (this.isMouseAtButton())
            {
                for (Runnable run : onClick)
                {
                    run.run();
                }
            }
        }
        this.mouseLeftLastFrame = mouseLeft;
    }

    private boolean isMouseAtButton()
    {
        Vector2Int mousePosition = Input.shared.getMousePosition();
        Vector2 position = this.getTransform().getPositionAbsolute();

        if (positionFromScreenCenter)
        {
            position = Vector2.sum(position, Vector2.quotient(
                    Screen.shared.getScreenSize(), 2));
        }

        Vector2 scale = this.getTransform().getScaleAbsolute();

        return mousePosition.x >= position.x
            && mousePosition.y >= position.y
            && mousePosition.x <= position.x + scale.x
            && mousePosition.y <= position.y + scale.y;
    }

}

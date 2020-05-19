package archrn.tea_engine.gui;

import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.rendering.gui.PanelGameObject;
import archrn.tea_engine.rendering.gui.TextGameObject;

import java.awt.*;
import java.util.Objects;

/**
 *
 */
public class ButtonGameObject extends PanelGameObject
{

    private final static String DEFAULT_TEXT = "Button";
    private final static int DEFAULT_BORDER_RADIUS = 10;

    private TextGameObject textGameObject;
    private Button button;

    private Vector2Int screenPositionTopLeft;
    private Vector2Int screenSize;
    private String text;
    private boolean positionFromScreenCenter;

    public ButtonGameObject(Vector2Int screenPositionTopLeft,
                            Vector2Int screenSize,
                            Runnable onClick)
    {
        this(screenPositionTopLeft, screenSize,
             ButtonGameObject.DEFAULT_TEXT, DEFAULT_COLOR,
             onClick);
    }

    public ButtonGameObject(Vector2Int screenPositionTopLeft,
                            Vector2Int screenSize, String text,
                            Runnable onClick)
    {
        this(screenPositionTopLeft, screenSize,
             text, DEFAULT_COLOR,
             onClick);
    }

    public ButtonGameObject(Vector2Int screenPositionTopLeft,
                            Vector2Int screenSize, Color color,
                            Runnable onClick)
    {
        this(screenPositionTopLeft, screenSize,
             ButtonGameObject.DEFAULT_TEXT, color,
             onClick);
    }

    public ButtonGameObject(Vector2Int screenPositionTopLeft,
                            Vector2Int screenSize, String text, Color color,
                            Runnable onClick)
    {
        this(screenPositionTopLeft, screenSize, text, color,
             false, onClick);
    }

    public ButtonGameObject(Vector2Int screenPositionTopLeft,
                            Vector2Int screenSize, String text, Color color,
                            boolean positionFromScreenCenter, Runnable onClick)
    {
        super(screenPositionTopLeft, screenSize, color,
              ButtonGameObject.DEFAULT_BORDER_RADIUS, positionFromScreenCenter);
        Objects.requireNonNull(text);
        Objects.requireNonNull(onClick);

        this.screenPositionTopLeft = screenPositionTopLeft;
        this.screenSize = screenSize;
        this.text = text;
        this.positionFromScreenCenter = positionFromScreenCenter;
        this.button = this.addComponent(new Button(
                onClick, positionFromScreenCenter));
    }

    public TextGameObject getText()
    {
        return this.textGameObject;
    }

    public Button getButton()
    {
        return this.button;
    }

    @Override
    protected void onAddedToScene()
    {
        this.textGameObject = this.getScene().addGameObject(
                new TextGameObject(this.screenPositionTopLeft, this.screenSize,
                                   this.text, Color.black, true,
                                   this.positionFromScreenCenter));
        this.textGameObject.getTransform().setParent(this.getTransform());
        this.textGameObject.getRenderer().setCenter(true);
    }

    @Override
    protected void onDestroy()
    {
        this.textGameObject.destroy();
    }

}

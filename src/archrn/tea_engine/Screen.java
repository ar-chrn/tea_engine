package archrn.tea_engine;

import archrn.tea_engine.camera.Camera;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * <p>{@code Screen} represents the window of the running game.</p>
 * @author Artem
 */
public final class Screen extends JPanel
{
    public static final Screen shared = new Screen();

    private Dimension size;
    private JFrame frame;

    /**
     * <p>Initializes an empty {@code Screen}.</p>
     */
    private Screen()
    {
        this.size = new Dimension(1200, 800);
        this.createFrame();
        this.setFocusable(true);
        this.requestFocus();
    }

    /**
     * <p>Returns the size of the window represented by this {@code Screen}.</p>
     * @return The size of the {@code Screen} in screen units.
     */
    public Vector2 getScreenSize()
    {
        return new Vector2(this.size.width, this.size.height);
    }

    /**
     * <p>Draws a frame on the {@code Screen}.</p>
     * @param graphics The {@code Graphics2D} to draw with.
     */
    @Override
    public void paint(Graphics graphics)
    {
        if (Camera.getMain() == null)
        {
            return;
        }

        Camera.getMain().render((Graphics2D)graphics);
        graphics.dispose();
    }

    /**
     * <p>Creates the {@code JFrame} for this {@code Screen}.</p>
     */
    private void createFrame()
    {
        this.frame = new JFrame();

        Container contentPane = this.frame.getContentPane();
        contentPane.setPreferredSize(this.size);
        contentPane.add(this);

        this.frame.setBackground(Color.black);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.pack();

        contentPane.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                size = contentPane.getSize();
                if (Camera.getMain() != null)
                {
                    Camera.getMain().updateWorldToScreenCoefficient();
                }
            }
        });
    }

    public void setMinimumSize(Vector2Int minimumSize)
    {
        this.frame.setMinimumSize(new Dimension(minimumSize.x, minimumSize.y));
    }

    public void setSize(Vector2Int size)
    {
        this.frame.setSize(new Dimension(size.x, size.y));
    }

}

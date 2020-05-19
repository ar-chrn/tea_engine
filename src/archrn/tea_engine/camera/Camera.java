package archrn.tea_engine.camera;

import archrn.tea_engine.Component;
import archrn.tea_engine.Scene;
import archrn.tea_engine.Screen;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;
import archrn.tea_engine.rendering.WorldScreenConverter;
import archrn.tea_engine.rendering.Renderer;
import archrn.tea_engine.rendering.RendererGraphics;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * <p>{@code Camera} component renders what's around it to the {@code Screen}.
 * </p>
 * @author Artem
 */
public final class Camera extends Component implements WorldScreenConverter
{

    /**
     * <p>Main {@code Camera} is the {@code Camera} that's currently
     * displayed.</p>
     */
    private static Camera main;

    /**
     * <p>The size of the {@code Camera}'s vision area in world units.</p>
     */
    private Vector2 viewSize;
    private Vector2 halfViewSize;

    /**
     * <p>The coefficient to covert world units to screen units.</p>
     */
    private float worldToScreenCoefficient;
    private Vector2 worldToScreenBias;
    private Color backgroundColor;

    /**
     * <p>Initializes the {@code Camera}.</p>
     */
    public Camera()
    {
        this.backgroundColor = Color.white;
        this.worldToScreenBias = Vector2.zero();
        this.setViewSize(new Vector2(12, 8));
    }

    /**
     * <p>Returns the main {@code Camera}.</p>
     * <p>The main {@code Camera} is the {@code Camera} that's currently
     * displayed.</p>
     * @return The main {@code Camera} that's currently displayed.
     */
    public static Camera getMain()
    {
        return Camera.main;
    }

    /**
     * <p>Sets the new main {@code Camera} that will be displayed on the
     * {@code Screen}.</p>
     * @param camera The new main {@code Camera}.
     * @throws NullPointerException if {@code camera} is {@code null}.
     */
    public static void setMain(Camera camera)
    {
        Objects.requireNonNull(camera);
        Camera.main = camera;
        camera.updateWorldToScreenCoefficient();
    }

    /**
     * <p>Returns the {@code Camera}'s vision area in world units.</p>
     * @return The {@code Camera}'s vision area in world units.
     */
    public Vector2 getViewSize()
    {
        return this.viewSize;
    }

    /**
     * <p>Sets a new vision area in world units.</p>
     * @param viewSize The new vision area in world units.
     * @throws NullPointerException if {@code viewSize} is {@code null}.
     */
    public void setViewSize(Vector2 viewSize)
    {
        Objects.requireNonNull(viewSize);
        this.viewSize = viewSize;
        this.halfViewSize = Vector2.quotient(this.viewSize, 2);
        this.calculateWorldToScreenCoefficient();
    }

    /**
     * <p>Returns the background {@code Color} of this {@code Camera}.</p>
     * @return The background {@code Color} displayed on the {@code Screen}.
     */
    public Color getBackgroundColor()
    {
        return this.backgroundColor;
    }

    /**
     * <p></p>
     * @param color
     * @throws
     */
    public void setBackgroundColor(Color color)
    {
        Objects.requireNonNull(color);
        this.backgroundColor = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2Int worldToScreenPosition(Vector2 worldPosition)
    {
        Vector2 screenPosition = Vector2.difference(
                worldPosition, this.getTransform().getPositionAbsolute());
        screenPosition.add(this.halfViewSize);
        screenPosition.y = this.viewSize.y - screenPosition.y;
        return Vector2Int.sum(this.worldToScreenScale(screenPosition),
                              this.worldToScreenBias.toVector2Int());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2Int worldToScreenPositionTopLeft(Vector2 worldPosition,
                                                   Vector2 worldSize)
    {
        Vector2 worldPositionTopLeft = this.positionTopLeftWorld(worldPosition,
                                                                 worldSize);
        return this.worldToScreenPosition(worldPositionTopLeft);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2Int worldToScreenScale(Vector2 worldScale)
    {
        Vector2 screenScale = Vector2.multiplied(worldScale,
                                                 this.worldToScreenCoefficient);
        return screenScale.toVector2Int();
    }

    @Override
    public Vector2 worldToScreenScaleVector2(Vector2 worldScale)
    {
        return Vector2.multiplied(worldScale, this.worldToScreenCoefficient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 screenToWorldPosition(Vector2Int screenPosition)
    {
        screenPosition = Vector2Int.difference(
                screenPosition, this.worldToScreenBias.toVector2Int());
        Vector2 worldPosition = this.screenToWorldScale(screenPosition);
        worldPosition.y = this.viewSize.y - worldPosition.y;
        worldPosition.subtract(this.halfViewSize);
        return Vector2.sum(worldPosition,
                           this.getTransform().getPositionAbsolute());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 screenToWorldScale(Vector2Int screenScale)
    {
        return Vector2.quotient(screenScale.toVector2(),
                                this.worldToScreenCoefficient);
    }

    /**
     * <p>Renders all the {@code GameObject}s in the field of view of the
     * {@code Camera}.</p>
     * @param graphics2D The {@code Graphics2D} to render with.
     */
    public void render(Graphics2D graphics2D)
    {
        RendererGraphics rendererGraphics = new RendererGraphics(graphics2D,
                                                                 this);
        this.renderBackground(rendererGraphics);

        Scene scene = this.getGameObject().getScene();
        List<Renderer> renderers = scene.getAllComponentsOfType(Renderer.class);
        renderers.sort(Comparator.comparingDouble(Renderer::getLayer));
        renderers.forEach(renderer -> renderer.render(rendererGraphics));
    }

    public void updateWorldToScreenCoefficient()
    {
        calculateWorldToScreenCoefficient();
    }

    /**
     * <p>Calculates the coefficient to convert world units to screen units.</p>
     */
    private void calculateWorldToScreenCoefficient()
    {
        Vector2 screenSize = Screen.shared.getScreenSize();
        float xCoefficient = screenSize.x / this.viewSize.x;
        float yCoefficient = screenSize.y / this.viewSize.y;
        this.worldToScreenCoefficient = Math.min(xCoefficient, yCoefficient);

        this.worldToScreenBias = Vector2.quotient(
                Vector2.difference(
                        screenSize,
                        Vector2.multiplied(this.viewSize,
                                           this.worldToScreenCoefficient)),
                2);
    }

    /**
     * <p>Renders the background color of the {@code Camera}.</p>
     * @param graphics The {@code RendererGraphics} to render with.
     */
    private void renderBackground(RendererGraphics graphics)
    {
        graphics.setColor(this.backgroundColor);
        graphics.fillRectScreenTopLeft(
                Vector2Int.zero(),
                Screen.shared.getScreenSize().toVector2Int());
    }

}

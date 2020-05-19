package archrn.tea_engine.rendering;

import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.geometry.Vector2Int;

import java.util.Objects;

/**
 * <p>Converts world coordinates to screen coordinates and visa versa.</p>
 * <p>Should be attached to a {@code Camera} and calculated based on {@code
 * Camera}'s position and view field.</p>
 * @author Artem.
 */
public interface WorldScreenConverter
{

    /**
     * <p>Converts the given {@code Vector2} world point to the corresponding
     * {@code Vector2Int} screen point.</p>
     * @param worldPosition The point in world units.
     * @return The point in screen unitss.
     */
    public Vector2Int worldToScreenPosition(Vector2 worldPosition);

    /**
     * <p>Returns the top left corner in screen coordinates of an object with
     * the center at {@code worldPosition} and the size {@code worldSize}.</p>
     * @param worldPosition The center point of an object in world units.
     * @param worldSize The size of an object in world units.
     * @return The top left corner of the object with the given {@code
     * worldPosition} and {@code worldSize}.
     */
    public Vector2Int worldToScreenPositionTopLeft(Vector2 worldPosition,
                                                   Vector2 worldSize);

    /**
     * <p>Converts the given {@code Vector2} world scale to the
     * corresponding {@code Vector2Int} screen scale.</p>
     * @param worldScale The scale in world units.
     * @return The scale in screen units.
     */
    public Vector2Int worldToScreenScale(Vector2 worldScale);

    public Vector2 worldToScreenScaleVector2(Vector2 worldScale);

    /**
     * <p>Converts the given {@code Vector2Int} screen point to the
     * corresponding {@code Vector2} world point.</p>
     * @param screenPosition The point in screen units.
     * @return The point in world units.
     */
    public Vector2 screenToWorldPosition(Vector2Int screenPosition);

    /**
     * <p>Converts the given {@code Vector2Int} screen scale to the
     * corresponding {@code Vector2} world scale.</p>
     * @param screenScale The scale in screen units.
     * @return The scale in world units.
     */
    public Vector2 screenToWorldScale(Vector2Int screenScale);

    /**
     * <p>Returns the top left position of a rectangle in world coordinates
     * with the given center {@code position} and {@code size}.</p>
     * <p>Only for world coordinates.</p>
     * @param worldPosition The center position of the rectangle.
     * @param size The size of the rectangle.
     * @return Top left position of a rectangle at {@code position} of {@code
     * size} in world coordinates.
     * @throws NullPointerException if {@code position} or {@code size} is
     * {@code null}.
     */
    public default Vector2 positionTopLeftWorld(Vector2 worldPosition,
                                                Vector2 size)
    {
        Objects.requireNonNull(worldPosition);
        Objects.requireNonNull(size);
        Vector2 topLeft = worldPosition.duplicate();
        topLeft.x -= size.x / 2;
        topLeft.y += size.y / 2;
        return topLeft;
    }

    /**
     * <p>Returns the top left position of a rectangle in screen coordinates
     * with the given center {@code position} and {@code size}.</p>
     * <p>Only for screen coordinates.</p>
     * @param screenPosition The center position of the rectangle.
     * @param size The size of the rectangle.
     * @return Top left position of a rectangle at {@code position} of {@code
     * size} in screen coordinates.
     * @throws NullPointerException if {@code position} or {@code size} is
     * {@code null}.
     */
    public default Vector2Int positionTopLeftScreen(Vector2Int screenPosition,
                                                    Vector2Int size)
    {
        Objects.requireNonNull(screenPosition);
        Objects.requireNonNull(size);
        return Vector2Int.difference(screenPosition,
                                     Vector2Int.quotient(size, 2));
    }

}

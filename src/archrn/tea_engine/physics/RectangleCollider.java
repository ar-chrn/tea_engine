package archrn.tea_engine.physics;

import archrn.tea_engine.geometry.Geometry;
import archrn.tea_engine.geometry.Vector2;

/**
 * RectangleCollider
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class RectangleCollider extends Collider
{

    private Vector2 size;

    public RectangleCollider()
    {
        this(Vector2.zero());
    }

    public RectangleCollider(Vector2 size)
    {
        this.size = size;
    }

    public RectangleCollider(Vector2 size, Vector2 bias)
    {
        this.size = size;
        this.setBias(bias);
    }

    @Override
    public boolean collidesWith(Collider other)
    {
        if (other instanceof RectangleCollider)
        {
            return this.collidesWith((RectangleCollider)other);
        }
        else if (other instanceof CircleCollider)
        {
            return other.collidesWith(this);
        }
        return false;
    }

    public Vector2 getSize()
    {
        return size;
    }

    public void setSize(Vector2 size)
    {
        this.size = size;
    }

    @Override
    protected float move(Vector2 direction, Collider other)
    {
        Vector2 position = this.getBiasedPosition();
        //        Vector2 endPosition = Vector2.sum(position, direction);
        Vector2 halfSize = Vector2.quotient(size, 2);

        // Minkowski sum
        float left = other.left() - halfSize.x;
        float top = other.top() + halfSize.y;
        float right = other.right() + halfSize.x;
        float bot = other.bot() - halfSize.y;
        float leftToRight = right - left;
        float botToTop = top - bot;

        float minMove = 1;
        // Left
        minMove = Math.min(minMove, Geometry.segmentsIntersectionByDirection(
                position.x, position.y, left, bot,
                direction.x, direction.y, 0, botToTop));
        // Right
        minMove = Math.min(minMove, Geometry.segmentsIntersectionByDirection(
                position.x, position.y, right, bot,
                direction.x, direction.y, 0, botToTop));
        // Top
        minMove = Math.min(minMove, Geometry.segmentsIntersectionByDirection(
                position.x, position.y, left, top,
                direction.x, direction.y, leftToRight, 0));
        // Bot
        minMove = Math.min(minMove, Geometry.segmentsIntersectionByDirection(
                position.x, position.y, left, bot,
                direction.x, direction.y, leftToRight, 0));

        return minMove;
    }

    @Override
    protected float left()
    {
        return this.getBiasedPosition().x - this.size.x / 2;
    }

    @Override
    protected float right()
    {
        return this.getBiasedPosition().x + this.size.x / 2;
    }

    @Override
    protected float top()
    {
        return this.getBiasedPosition().y + this.size.y / 2;
    }

    @Override
    protected float bot()
    {
        return this.getBiasedPosition().y - this.size.y / 2;
    }

    boolean collidesWith(RectangleCollider other)
    {
        return (this.left() <= other.right() &&
                this.right() >= other.left() &&
                this.top() <= other.bot() &&
                this.bot() >= other.top());
    }

}

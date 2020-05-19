package archrn.tea_engine.physics;

import archrn.tea_engine.geometry.Geometry;
import archrn.tea_engine.geometry.Vector2;
import archrn.tea_engine.math.MathF;

/**
 * CircleCollider
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class CircleCollider extends Collider
{

    private float radius;

    public CircleCollider()
    {
        this(0);
    }

    public CircleCollider(float radius)
    {
        this.radius = radius;
    }

    public CircleCollider(float radius, Vector2 bias)
    {
        this.radius = radius;
        this.setBias(bias);
    }

    @Override
    public boolean collidesWith(Collider other) {
        if (other instanceof RectangleCollider)
        {
            return this.collidesWith((RectangleCollider)other);
        }
        else if (other instanceof CircleCollider)
        {
            return this.collidesWith((CircleCollider)other);
        }
        return false;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    @Override
    protected float move(Vector2 direction, Collider other)
    {
        if (other instanceof RectangleCollider)
        {
            return other.move(direction.inverted(), this);
        }
        else if (other instanceof CircleCollider)
        {
            return this.move(direction, (CircleCollider)other);
        }
        return 1;
    }

    @Override
    protected float left()
    {
        return this.getBiasedPosition().x - this.radius;
    }

    @Override
    protected float right()
    {
        return this.getBiasedPosition().x + this.radius;
    }

    @Override
    protected float top()
    {
        return this.getBiasedPosition().y + this.radius;
    }

    @Override
    protected float bot()
    {
        return this.getBiasedPosition().y - this.radius;
    }

    boolean collidesWith(RectangleCollider rect)
    {
        Vector2 circlePos = this.getBiasedPosition();
        Vector2 closest = new Vector2();

        closest.x = MathF.clamp(circlePos.x, rect.left(), rect.right());
        closest.y = MathF.clamp(circlePos.y, rect.bot(), rect.top());

        return circlePos.distance(closest) < this.radius;
    }

    boolean collidesWith(CircleCollider other)
    {
        float distance = this.getBiasedPosition().distance(
                other.getBiasedPosition());
        return distance <= this.radius + other.radius;
    }

    float move(Vector2 direction, CircleCollider other)
    {
        return Geometry.segmentCircleIntersectionByDirection(
                this.getBiasedPosition(), direction,
                other.getBiasedPosition(), radius + other.radius);
    }

}

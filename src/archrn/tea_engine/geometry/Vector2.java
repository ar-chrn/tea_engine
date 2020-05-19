package archrn.tea_engine.geometry;

import archrn.tea_engine.math.MathF;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>{@code Vector2} represents a point or a vector on a 2D plane. </p>
 * @see Vector2Int
 * @author Artem
 */
public final class Vector2 implements Serializable
{

    /**
     * <p>X component of the {@code Vector2}.</p>
     */
    public float x;

    /**
     * <p>Y component of the {@code Vector2}.</p>
     */
    public float y;

    /**
     * <p>Initializes a zero-vector.</p>
     * @see Vector2#one()
     * @see Vector2#Vector2(float, float)
     */
    public Vector2()
    {
        this(0, 0);
    }

    /**
     * <p>Initializes the vector with the given coordinates.</p>
     * @param x X-coordinate of the vector.
     * @param y Y-coordinate of the vector.
     * @see Vector2#Vector2()
     */
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>Copies the given {@code Vector2}.</p>
     * @param other The {@code Vector2} to copy.
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public Vector2(Vector2 other)
    {
        Objects.requireNonNull(other);
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * <p>Creates a zero-{@code Vector2} with coordinates (0; 0).</p>
     * @return A {@code Vector2} with coordinates (0; 0).
     * @see Vector2#one()
     * @see Vector2#Vector2()
     */
    public static Vector2 zero()
    {
        return new Vector2(0, 0);
    }

    /**
     * <p>Creates a one-{@code Vector2} with coordinates (1; 1).</p>
     * @return A {@code Vector2} with coordinates (1; 1).
     * @see Vector2#zero()
     * @see Vector2#Vector2()
     */
    public static Vector2 one()
    {
        return new Vector2(1, 1);
    }

    /**
     * <p>Calculates the sum of two {@code Vector2}s.</p>
     * @param a First {@code Vector2}.
     * @param b Second {@code Vector2}.
     * @return A new {@code Vector2}, which is a sum of two given {@code
     * Vector2}s.
     * @see Vector2#add(Vector2)
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
     */
    public static Vector2 sum(Vector2 a, Vector2 b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    /**
     * <p>Calculate the difference of two {@code Vector2}s.</p>
     * <p>{@code Vector2.diff(a, b)} = a - b</p>
     * @param a First {@code Vector2}.
     * @param b Second {@code Vector2}.
     * @return A new {@code Vector2}, which is a difference of two given {code
     * Vector2}s.
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
     */
    public static Vector2 difference(Vector2 a, Vector2 b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    /**
     * <p>Returns the result of the multiplication of {@code vector} by
     * {@code multiplier}.</p>
     * <p>Coordinates of {@code Vector2} are multiplied by {@code multipler}.
     * Doesn't mutate the {@code vector}.</p>
     * @param vector The {@code Vector2} to muptiply.
     * @param multiplier The multipler.
     * @return A new {@code Vector2}, which is a composition of {@code
     * vector} and {@code multiplier}.
     * @throws NullPointerException if {@code vector} is {@code null}.
     */
    public static Vector2 multiplied(Vector2 vector, float multiplier)
    {
        Objects.requireNonNull(vector);
        return new Vector2(vector.x * multiplier,
                           vector.y * multiplier);
    }

    /**
     * <p>Returns the result of division of {@code vector} by {@code
     * divider}.</p>
     * @param vector The {@code Vector2} to divide.
     * @param divider The divider.
     * @return A new {@code Vector2}, which is a quotient of {@code vector} by
     * {@code divider}.
     * @throws NullPointerException if {@code vector} is {@code null}.
     * @throws IllegalArgumentException if {@code divider} is {@code 0}.
     */
    public static Vector2 quotient(Vector2 vector, float divider)
    {
        Objects.requireNonNull(vector);
        MathF.requireNonZero(divider);
        return new Vector2(vector.x / divider, vector.y / divider);
    }

    /**
     * <p>Calculates the distance between the points of two {@code
     * Vector2}s.</p>
     * @param a First {@code Vector2} point.
     * @param b Second {@code Vector2} point.
     * @return The distance between points {@code a} and {@code b}.
     * @see Vector2#distance(Vector2)
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
     */
    public static float distance(Vector2 a, Vector2 b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        double xDiffSquare = Math.pow(a.x - b.x, 2);
        double yDiffSquare = Math.pow(a.y - b.y, 2);
        return (float) Math.sqrt(xDiffSquare + yDiffSquare);
    }

    public static Vector2 max(Vector2 a, Vector2 b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return a.magnitude() >= b.magnitude() ? a : b;
    }

    public static Vector2 min(Vector2 a, Vector2 b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return a.magnitude() < b.magnitude() ? a : b;
    }

    /**
     * <p>Represents the {@code Vector2} as a {@code String} in format
     * {@code "(x; y)"}.</p>
     * @return The coordinates of the {@code Vector2} as a String.
     */
    @Override
    public String toString()
    {
        return "(" + this.x + "; " + this.y + ")";
    }

    // TODO: clone()?
    /**
     * <p>Returns a copy of the {@code Vector2}.</p>
     * @return A copy of {@code Vector2}.
     */
    public Vector2 duplicate()
    {
        return new Vector2(this);
    }

    /**
     * <p>Casts the {@code Vector2} to {@code Vector2Int}.</p>
     * @return A new {@code Vector2Int} with the same coordinates.
     */
    public Vector2Int toVector2Int()
    {
        return new Vector2Int(Math.round(this.x),
                              Math.round(this.y));
        //return new Vector2Int((int)this.x, (int)this.y);
    }

    /**
     * <p>Returns an inverted {@code Vector2}.</p>
     * @return A new {@code Vector2} with inverted coordinates.
     */
    public Vector2 inverted()
    {
        return new Vector2(-this.x, -this.y);
    }

    /**
     * <p>Returns a normalized {@code Vector2}.</p>
     * @return A new {@code Vector2} with normalized coordinates.
     */
    public Vector2 normalized()
    {
        if (MathF.isZero(this.magnitude()))
        {
            return this;
        }
        else
        {
            return Vector2.quotient(this, this.magnitude());
        }
    }

    /**
     * <p>Adds another {@code Vector2} to this {@code Vector2}.</p>
     * @param other The {@code Vector2} to add.
     * @throws NullPointerException if {@code other} is {@code null}.
     * @see Vector2#sum(Vector2, Vector2)
     */
    public void add(Vector2 other)
    {
        Objects.requireNonNull(other);
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * <p>Substracts {@code other} from the {@code Vector2}.</p>
     * @param other The substractor.
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public void subtract(Vector2 other)
    {
        Objects.requireNonNull(other);
        this.add(other.inverted());
    }

    /**
     * <p>Multiples coordinates of the {@code Vector2} by {@code
     * multiplier}.</p>
     * @param multiplier The multiplier.
     * @see Vector2#multiplied(Vector2, float)
     */
    public void multiply(float multiplier)

    {
        this.x *= multiplier;
        this.y *= multiplier;
    }

    /**
     * <p>Devides coordinates of the {@code Vector2} by {@code devider}.</p>
     * @param divider The divider.
     * @throws IllegalArgumentException if {@code divider} is {@code 0}.
     * @see Vector2#quotient(Vector2, float)
     */
    public void divide(float divider)
    {
        MathF.requireNonZero(divider, "Divider can't be 0.");
        this.x /= divider;
        this.y /= divider;
    }

    /**
     * <p>Calculates the distance towards the point of another
     * {@code Vector2}.</p>
     * @param other The {@code Vector2} point to calculate distance towards.
     * @return The distance towards the point of the other {@code Vector2}.
     * @see Vector2#distance(Vector2, Vector2)
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public float distance(Vector2 other)
    {
        Objects.requireNonNull(other);
        return Vector2.distance(this, other);
    }

    /**
     * <p>Calculates the length of the {@code Vector2}.</p>
     * @return The length of the {@code Vector2}.
     */
    public float magnitude()
    {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

}

package archrn.tea_engine.geometry;

import archrn.tea_engine.math.MathF;

import java.util.Objects;

/**
 * <p>{@code Vector2Int} represents an integer point or vector on a 2D
 * plane.</p>
 * @author Artem
 * @see Vector2
 */
public class Vector2Int
{

    /**
     * <p>X component of the {@code Vector2Int}.</p>
     */
    public int x;

    /**
     * <p>Y component of the {@code Vector2Int}.</p>
     */
    public int y;

    /**
     * <p>Initializes a zero-vector.</p>
     * @see Vector2Int(int, int)
     * @see Vector2Int#one()
     */
    public Vector2Int()
    {
        this(0, 0);
    }

    /**
     * <p>Initializes a {@code Vector2Int} with the given coordinates.</p>
     * @param x X-coordinate of the {@code Vector2Int}.
     * @param y Y-coordinate of the {@code Vector2Int}.
     * @see Vector2Int#Vector2Int()
     */
    public Vector2Int(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>Copies the given {@code Vector2Int}.</p>
     * @param other The {@code Vector2Int} to copy.
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public Vector2Int(Vector2Int other)
    {
        Objects.requireNonNull(other);
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * <p>Initializes a zero-{@code Vector2Int} with coordinates (0; 0).</p>
     * @return A new {@code Vector2Int} with coordinates (0; 0).
     * @see Vector2Int#one()
     * @see Vector2Int#Vector2Int()
     */
    public static Vector2Int zero()
    {
        return new Vector2Int(0, 0);
    }

    /**
     * <p>Initializes a one-{@code Vector2Int} with coordinates (1; 1).</p>
     * @return A new {@code Vector2Int} with coordinates (1; 1).
     * @see Vector2Int#zero()
     * @see Vector2Int#Vector2Int()
     */
    public static Vector2Int one()
    {
        return new Vector2Int(1, 1);
    }

    /**
     * <p>Calculates the sum of two given {@code Vector2Int}s.</p>
     * @param a First {@code Vector2Int}.
     * @param b Second {@code Vector2Int}.
     * @return The sum of {@code a} and {@code b}.
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
     */
    public static Vector2Int sum(Vector2Int a, Vector2Int b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return new Vector2Int(a.x + b.x, a.y + b.y);
    }

    /**
     * <p>Calculates the difference between two given {@code Vector2Int}s.</p>
     * @param a First {@code Vector2Int}.
     * @param b Second {@code Vector2Int}.
     * @return The difference between {@code a} and {@code b}.
     * @throws NullPointerException if {@code a} or {@code b} is {@code null}.
     */
    public static Vector2Int difference(Vector2Int a, Vector2Int b)
    {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return new Vector2Int(a.x - b.x, a.y - b.y);
    }

    /**
     * <p>Returns the result of multiplication of {@code vector} by {@code
     * multipler}.</p>
     * @param vector The {@code Vector2Int} to multiply.
     * @param multiplier The multiplier.
     * @return A new {@code Vector2Int}, which is the composition of {@code
     * vector} and {@code multiplier}.
     * @throws NullPointerException if {@code vector} is {@code null}.
     */
    public static Vector2Int multiplied(Vector2Int vector, int multiplier)
    {
        Objects.requireNonNull(vector);
        return new Vector2Int(vector.x * multiplier,
                              vector.y * multiplier);
    }

    /**
     * <p>Returns the quotient of {@code vector} by {@code divider}.</p>
     * @param vector The {@code Vector2Int} to divide.
     * @param divider The divider.
     * @return A new {@code Vector2Int}, which is the quotient of {@code vector}
     * and {@code divider}.
     * @throws NullPointerException if {@code vector} is {@code null}.
     * @throws IllegalArgumentException if {@code divider} is {@code 0}.
     */
    public static Vector2Int quotient(Vector2Int vector, int divider)
    {
        Objects.requireNonNull(vector);
        MathF.requireNonZero(divider);
        return new Vector2Int(vector.x / divider,
                              vector.y / divider);
    }

    /**
     * <p>Represents the {@code Vector2Int} as a {@code String} in a format
     * {@code "(x; y)"}.</p>
     * @return The coordinates of the {@code Vector2Int} as a {@code String}.
     */
    @Override
    public String toString()
    {
        return "(" + this.x + "; " + this.y + ")";
    }

    /**
     * <p>Returns a copy of the {@code Vector2Int}.</p>
     * @return a new {@code Vector2Int} with the same coordinates.
     */
    public Vector2Int duplicate()
    {
        return new Vector2Int(this.x, this.y);
    }

    /**
     * <p>Casts the {@code Vector2Int} to {@code Vector2}.</p>
     * @return A new {@code Vector2} with the same coordinates.
     */
    public Vector2 toVector2()
    {
        return new Vector2(this.x, this.y);
    }

    /**
     * <p>Returns an inverted {@code Vector2Int}.</p>
     * @return A new {@code Vector2Int} with inverted coordinates.
     */
    public Vector2Int inverted()
    {
        return new Vector2Int(-this.x, -this.y);
    }

    /**
     * <p>Adds another {@code Vector2Int} to this {@code Vector2Int}.</p>
     * @param other The {@code Vector2Int} to add.
     * @throws NullPointerException if {@code other} is {@code null}.
     * @see Vector2Int#sum(Vector2Int, Vector2Int)
     */
    public void add(Vector2Int other)
    {
        Objects.requireNonNull(other);
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * <p>Substracts another {@code Vector2Int} from this {@code
     * Vector2Int}.</p>
     * @param other The {@code Vector2Int} to substract.
     * @throws NullPointerException if {@code other} is {@code null}.
     * @see Vector2Int#difference(Vector2Int, Vector2Int)
     */
    public void substract(Vector2Int other)
    {
        Objects.requireNonNull(other);
        this.x -= other.x;
        this.y -= other.y;

    }

    /**
     * <p>Multiplies this {@code Vector2Int} by {@code multiplier}.</p>
     * @param multiplier The multiplier.
     * @see Vector2Int#multiplied(Vector2Int, int)
     */
    public void multiply(int multiplier)
    {
        this.x *= multiplier;
        this.y *= multiplier;
    }

    /**
     * <p>Divides this {@code Vector2Int} by {@code divider}.</p>
     * @param divider The divider.
     * @throws IllegalArgumentException if {@code divider} is {@code null}.
     * @see Vector2Int#quotient(Vector2Int, int)
     */
    public void divide(int divider)
    {
        MathF.requireNonZero(divider);
        this.x /= divider;
        this.y /= divider;
    }

}

package archrn.tea_engine.math;

/**
 * <p></p>
 * @author Artem
 */
public class MathF
{

    public static boolean isZero(int value)
    {
        return value == 0;
    }

    public static boolean isZero(float value)
    {
        return (Float.compare(value, 0f) == 0);
    }

    public static void requireNonZero(int value)
    {
        if (value == 0)
        {
            throw new IllegalArgumentException("Value is zero.");
        }
    }

    public static void requireNonZero(int value, String message)
    {
        if (value == 0)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireNonZero(float value)
    {
        if (Float.compare(value, 0f) == 0)
        {
            throw new IllegalArgumentException("Value is zero.");
        }
    }

    public static void requireNonZero(float value, String message)
    {
        if (Float.compare(value, 0f) == 0)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireLargerThanZero(int value)
    {
        if (value <= 0)
        {
            throw new IllegalArgumentException("Value is less than or equals " +
                                               "zero.");
        }
    }

    public static void requireNonNegative(int value)
    {
        if (value < 0)
        {
            throw new IllegalArgumentException("Value is negative.");
        }
    }

    public static float clamp(float value, float min, float max)
    {
        return Math.max(min, Math.min(max, value));
    }

    public static float sqrt(float a)
    {
        return (float)Math.sqrt(a);
    }

}

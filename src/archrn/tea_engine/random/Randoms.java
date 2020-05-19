package archrn.tea_engine.random;

import archrn.tea_engine.geometry.Vector2;

import java.util.List;
import java.util.Random;

/**
 * Randoms
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class Randoms
{

    private static Random random = new Random();

    public static float randomFloat(float min, float max)
    {
        return random.nextFloat() * (max - min) + min;
    }

    public static Vector2 randomVector2(Vector2 minPos, Vector2 maxPos)
    {
        return new Vector2(randomFloat(minPos.x, maxPos.x),
                           randomFloat(minPos.y, maxPos.y));
    }

    public static Vector2 randomVector2InArea(Vector2 center, Vector2 size)
    {
        Vector2 halfSize = Vector2.quotient(size, 2);
        return randomVector2(Vector2.difference(center, halfSize),
                             Vector2.sum(center, halfSize));
    }

    public static boolean chance(float chance)
    {
        return random.nextFloat() < chance;
    }

    public static <T> T randomElement(List<T> list)
    {
        return list.get(random.nextInt(list.size()));
    }

}

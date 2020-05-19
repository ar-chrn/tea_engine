package archrn.tea_engine.geometry;

import archrn.tea_engine.math.MathF;

/**
 * Geometry
 *
 * @author archrn
 * @version 0
 * @since 0
 */
public class Geometry
{

    public static Vector2 segmentsIntersection(Vector2 a, Vector2 b,
                                               Vector2 c, Vector2 d)
    {
        return segmentsIntersection(a.x, a.y, b.x, b.y,
                                    c.x, c.y, d.x, d.y);
    }

    public static Vector2 segmentsIntersection(float ax, float ay,
                                               float bx, float by,
                                               float cx, float cy,
                                               float dx, float dy)
    {
        float s1_x = bx - ax;
        float s1_y = by - ay;
        float s2_x = dx - cx;
        float s2_y = dy - cy;

        float divider = -s2_x * s1_y + s1_x * s2_y;
        float s = (-s1_y * (ax - cx) + s1_x * (ay - cy)) / divider;
        float t = ( s2_x * (ay - cy) - s2_y * (ax - cx)) / divider;

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
        {
            return new Vector2(ax + (t * s1_x),
                               ay + (t * s1_y));
        }
        return null;
    }

    public static float segmentsIntersectionByDirection(float ax, float ay,
                                                        float cx, float cy,
                                                        float asx, float asy,
                                                        float csx, float csy)
    {
        float divider = -csx * asy + asx * csy;
        float s = (-asy * (ax - cx) + asx * (ay - cy)) / divider;
        float t = ( csx * (ay - cy) - csy * (ax - cx)) / divider;

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
        {
            return t;
        }
        return Float.MAX_VALUE;
    }

    public static float segmentCircleIntersectionByDirection(
            Vector2 a, Vector2 d, Vector2 o, float r)
    {
        // Vector from sphere center to segment start.
        Vector2 f = Vector2.difference(a, o);

        // Solving equation.
        float A = dotProduct(d, d);
        float B = 2 * dotProduct(f, d);
        float C = dotProduct(f, f) - r*r;
        float discriminant = B*B - 4*A*C;
        if (discriminant < 0)
        {
            return 1;
        }
        else
        {
            discriminant = MathF.sqrt(discriminant);
            float t1 = (-B - discriminant) / (2*A);
            float t2 = (-B + discriminant) / (2*A);
            if( t1 >= 0 && t1 <= 1 )
            {
                return t1;
            }
            if( t2 >= 0 && t2 <= 1 )
            {
                return t2;
            }
            return 1;
        }
    }

    public static float dotProduct(Vector2 a, Vector2 b)
    {
        return dotProduct(a.x, a.y, b.x, b.y);
    }

    public static float dotProduct(float ax, float ay, float bx, float by)
    {
        return ax * bx + ay * by;
    }

}

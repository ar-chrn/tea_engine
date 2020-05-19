package archrn.tea_engine.input;

import archrn.tea_engine.geometry.Vector2;

/**
 * <p>{@code InputAxis} represents an input values that depends on two keys.</p>
 * <p>One of the keys makes {@code InputAxis} value negative, other makes it
 * positive. For example, horizontal axis is represented by left-right arrow
 * keys or by A-D keys.</p>
 */
public final class InputAxis
{

    /**
     * <p>Horizontal {@code InputAxis} represents the horizontal direction
     * input.</p>
     */
    public static final InputAxis horizontal =
            new InputAxis(Input.KEY_LEFT, Input.KEY_RIGHT,
                          Input.KEY_A, Input.KEY_D);

    /**
     * <p>Vertical {@code InputAxis} represents the vertical direction
     * input.</p>
     */
    public static final InputAxis vertical =
            new InputAxis(Input.KEY_DOWN, Input.KEY_UP,
                          Input.KEY_S, Input.KEY_W);

    private int negativeKey;
    private int positiveKey;
    private int negativeAlternativeKey;
    private int positiveAlternativeKey;

    /**
     * <p>Initializes the {@code InputAxis} with the given keys.</p>
     * @param negativeKey The key that decreases the {@code InputAxis} value.
     * @param positiveKey The key that increases the {@code InputAxis} value.
     */
    public InputAxis(int negativeKey,
                     int positiveKey)
    {
        this(negativeKey, positiveKey,
             -1,-1);
    }

    /**
     * <p>Initializes the {@code InputAxis} with the given keys and
     * additional keys.</p>
     * @param negativeKey The key that decreases the {@code InputAxis} value.
     * @param positiveKey The key that decreases the {@code InputAxis} value.
     * @param negativeAlternativeKey The additional key that decreases the
     * {@code InputAxis} value.
     * @param positiveAlternativeKey The additional key that decreases the
     * {@code InputAxis} value.
     */
    public InputAxis(int negativeKey,
                     int positiveKey,
                     int negativeAlternativeKey,
                     int positiveAlternativeKey)
    {
        this.negativeKey = negativeKey;
        this.positiveKey = positiveKey;
        this.negativeAlternativeKey = negativeAlternativeKey;
        this.positiveAlternativeKey = positiveAlternativeKey;
    }

    /**
     * <p>Returns the current input direction {@code Vector2} taken from
     * {@code InputAxis.horizontal} and {@code InputAxis.vertical}.</p>
     * @return The current input direction {@code Vector2}.
     */
    public static Vector2 getDirection()
    {
        Vector2 direction = new Vector2(InputAxis.horizontal.getValue(),
                                        InputAxis.vertical.getValue());
        return direction.normalized();
    }

    /**
     * <p>Returns the value of the {@code InputAxis}.</p>
     * @return The current value of the {@code InputAxis}.
     */
    public float getValue()
    {
        int value = 0;
        if (Input.shared.getKey(this.negativeKey) ||
            Input.shared.getKey(this.negativeAlternativeKey))
        {
            value -= 1;
        }
        if (Input.shared.getKey(this.positiveKey) ||
            Input.shared.getKey(this.positiveAlternativeKey))
        {
            value += 1;
        }
        return value;
    }

}

package archrn.tea_engine.camera;

import archrn.tea_engine.Component;
import archrn.tea_engine.Transform;

/**
 * <p>{@code CameraFollow} component is attached to a {@code Camera} if the
 * {@code Camera} should follow a certain {@code GameObject}.</p>
 * <p>For example, {@code CameraFollow} can be used to create a {@code
 * Camera} that would always follow the player.</p>
 * @author Artem
 */
public final class CameraFollow extends Component
{

    /**
     * <p>The {@code Transform} of the {@code GameObject}, which this
     * {@code CameraFollow} should follow.</p>
     * <p>{@code CameraFollow}'s position always equals</p>
     */
    // CameraFollow's target can be null, so it's not checked for null in any
    // methods.
    private Transform target;

    /**
     * <p>Initializes {@code CameraFollow} with no target.</p>
     */
    public CameraFollow()
    {
        this.target = null;
    }

    /**
     * <p>Initializes {@code CameraFollow} with the given {@code target}.</p>
     * @param target The target to follow for {@code CameraFollow}. Can be null.
     */
    public CameraFollow(Transform target)
    {
        this.target = target;
    }

    /**
     * <p>Returns the current {@code CameraFollow}'s target.</p>
     * @return The target, which this {@code CameraFollow} follows. Can be null.
     */
    public Transform getTarget()
    {
        return this.target;
    }

    /**
     * <p>Sets the target, which {@code CameraFollow} follows.</p>
     * <p>Can be set to null. If null, {@code CameraFollow} will stay where
     * it is.</p>
     * @param target The new target to follow.
     */
    public void setTarget(Transform target)
    {
        this.target = target;
    }

    /**
     * <p>Removes the target to follow.</p>
     * <p>{@code CameraFollow} stops moving.</p>
     */
    public void removeTarget()
    {
        this.target = null;
    }

    /**
     * <p>Sets {@code CameraFollow}'s position to target's position.</p>
     * <p>If there's no target, doesn't move.</p>
     */
    @Override
    protected void lateUpdate()
    {
        if (target == null)
        {
            return;
        }
        // Maybe should use .setPosition() instead.
        // But in this case it can be later changed from the target.
        this.getTransform().getPosition().x = this.target.getPosition().x;
        this.getTransform().getPosition().y = this.target.getPosition().y;
    }

}

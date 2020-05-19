package archrn.tea_engine.camera;

import archrn.tea_engine.GameObject;
import archrn.tea_engine.Transform;

import java.util.Objects;

/**
 * <p>{@code CameraGameObject} is a {@code GameObject} with the {@code
 * Camera} component attached.</p>
 * @author Artem
 */
public class CameraGameObject extends GameObject
{

    private Camera camera;
    private CameraFollow cameraFollow;

    /**
     * <p>Initializes {@code CameraGameObject}.</p>
     * <p>Creates {@code Camera} component.</p>
     */
    public CameraGameObject()
    {
        this.camera = new Camera();
        this.addComponent(camera);
        this.cameraFollow = null;
    }

    /**
     * <p>Returns this {@code CameraGameObject}'s {@code Camera} component.</p>
     * @return {@code CameraGameObject}'s {@code Camera} component.
     */
    public Camera getCamera()
    {
        return this.camera;
    }

    // TODO: Check if there already is a CameraFollow component on this object.
    /**
     * <p>Adds {@code CameraFollow} component to this {@code GameObject} and
     * sets the given {@code target}.</p>
     * <p>If there already exists a {@code CameraFollow}, sets it's target
     * to the given {@code target}.</p>
     * @param target The target to set for the {@code CameraFollow}. Can be
     *               null.
     */
    public void addCameraFollow(Transform target)
    {
        // CameraFollow's target can be null, so it's not checked.

        if (this.checkIfCameraFollowAttached())
        {
            Objects.requireNonNull(this.cameraFollow);
            this.cameraFollow.setTarget(target);
        }
        else
        {
            this.cameraFollow = new CameraFollow(target);
            this.addComponent(this.cameraFollow);
        }
    }

    /**
     * <p>Checks if this {@code GameObject} has a {@code CameraFollow}
     * component attached.</p>
     * <p>If yes and if it's not stored in {@code private cameraFollow}
     * variable, remembers it in {@code private cameraFollow}.</p>
     * @return {@code true} if this {@code GameObject} has a {@code
     * CameraFollow} component attached, {@code false} if not.
     */
    private boolean checkIfCameraFollowAttached()
    {
        if (this.cameraFollow == null)
        {
            this.cameraFollow = this.getComponentOfType(CameraFollow.class);
        }
        return this.cameraFollow != null;
    }

}

package archrn.tea_engine.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

// TODO: Check for nulls.

/**
 * <p>{@code AssetManager} loads and stores assets.</p>
 * <p>It loads each asset only once instead of loading every time when it's
 * needed.</p>
 * @author Artem
 */
public final class AssetManager
{

    public static final AssetManager shared = new AssetManager();

    private HashMap<String, BufferedImage> loadedImages;

    /**
     * <p>Initializes the {@code AssetManager}.</p>
     */
    private AssetManager()
    {
        this.loadedImages = new HashMap<String, BufferedImage>();
    }

    /**
     * <p>Returns the {@code Image} from the given {@code path}.</p>
     * <p>The {@code Image} is loaded once, then already existing object is
     * returned.</p>
     * @param path The path of the {@code Image}.
     * @return The {@code Image} from the given {@code path}.
     */
    public BufferedImage getImage(String path)
    {
        if (!this.loadedImages.containsKey(path))
        {
            this.loadImage(path);
        }
        return this.loadedImages.getOrDefault(path, null);
    }

    /**
     * <p>Adds the given {@code image} to the {@code AssetManager}.</p>
     * @param path The path of the {@code image}.
     * @param image The {@code BufferedImage} to store.
     */
    public void addImage(String path, BufferedImage image)
    {
        this.loadedImages.put(path, image);
    }

    /**
     * <p>Loads the {@code BufferedImage} at the given path.</p>
     * @param path The path to load the {@code BufferedImage}.
     */
    private void loadImage(String path)
    {
        BufferedImage image = null;
        if (path != null)
        {
            try {
                image = ImageIO.read(getClass().getResource(path));
            } catch (Exception e) {
                image = null;
            }
        }
        this.addImage(path, image);
    }

}

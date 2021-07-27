import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    protected File source;

    // Set source image
    public void setSource(String path) throws Exception {
        File file = new File(path);
        this.source = file;
        BufferedImage image = ImageIO.read(file);

        if(image == null) throw new Exception("[Error]: File is not an image.");
    }
    // Remove source image
    public void removeSource() {
        this.source = null;
    }

    // Steganograph the files 
    public abstract void encode(TargetFile targetFile);

    // Desteganograph the files
    public abstract void decode(String path);

    // Returns the max number of bytes the source can store
    public abstract long getMaxSpaceAvailable();
}

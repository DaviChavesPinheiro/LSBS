import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    BufferedImage image;
    List<InputStream> files;

    public ImageStegnography(String path) throws IOException, Exception {
        this.setImage(path);
    }

    public void setImage(String path) throws IOException, Exception {
        File file = new File(path);

        image = ImageIO.read(file);

        if(image == null) throw new Exception("[Error]: File is not an image.");
    }

    public void removeImage() {
        this.image = null;
    }

    public void addFile(String path) throws Exception, FileNotFoundException {
        files.add(new FileInputStream(path));
    }

    public void removeFile() {
        // Implementar
    }

    public abstract void encode();

    public abstract void decode(String path); 
}

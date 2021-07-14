import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    BufferedImage image;
    List<File> files = new ArrayList<File>();

    public ImageStegnography(String path) throws Exception {
        this.setImage(path);
    }

    public void setImage(String path) throws Exception {
        File file = new File(path);

        image = ImageIO.read(file);

        if(image == null) throw new Exception("[Error]: File is not an image.");
    }

    public void removeImage() {
        this.image = null;
    }

    public void addFile(String path) throws Exception, FileNotFoundException {
        files.add(new File(path));
    }

    public void removeFile() {
        // Implementar
    }

    public abstract void encode();

    public abstract void decode(String path);

    public byte[] getBytesToEncode() throws Exception, FileNotFoundException {
        File file = files.get(0);

        byte[] allBytes = new byte[(int)file.length()];

        InputStream inputStream = new FileInputStream(file.getAbsolutePath());

        inputStream.read(allBytes);
        
        return allBytes;
    }
}

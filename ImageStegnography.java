import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    File source;
    List<File> files = new ArrayList<File>();

    public ImageStegnography() {}

    public ImageStegnography(String path) throws Exception {
        this.setSource(path);
    }

    public void setSource(String path) throws Exception {
        File file = new File(path);
        this.source = file;
        BufferedImage image = ImageIO.read(file);

        if(image == null) throw new Exception("[Error]: File is not an image.");
    }

    public void removeSource() {
        this.source = null;
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

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

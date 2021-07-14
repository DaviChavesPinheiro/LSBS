import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
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

    public File addFile(String path) throws Exception {
        File file = new File(path);
        files.add(file);
        return file;
    }

    public void removeFile(File file) {
        files.remove(file);
    }

    public abstract void encode();

    public abstract void decode(String path);

    public byte[] getFileBytes() throws Exception {
        File file = files.get(0);

        byte[] allBytes = new byte[(int)file.length()];

        InputStream inputStream = new FileInputStream(file.getAbsolutePath());

        inputStream.read(allBytes);
        
        return allBytes;
    }

    // Retorna a soma do tamanho de todos os arquivos
    public long getCurrentFilesSize() {
        long sum = 0;
        for (File file : files) {
            sum += file.length();
        }
        return sum;
    }

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

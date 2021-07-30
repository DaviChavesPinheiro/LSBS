package src.backend;
import javax.imageio.ImageIO;

import java.io.File;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    protected File source;

    // Seta a imagem fonte
    public void setSource(String path) throws Exception {
        File file = new File(path);
        this.source = file;
        BufferedImage image = ImageIO.read(file);

        if(image == null) throw new Exception("[Error]: File is not an image.");
    }
    // Remove a imagem fonte
    public void removeSource() {
        this.source = null;
    }

    // Esteganografa os arquivos 
    public abstract void encode(TargetFile targetFile);

    // Desesteganografa os arquivos
    public abstract void decode(String path);

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

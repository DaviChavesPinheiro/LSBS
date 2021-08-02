package src.model;
import javax.imageio.ImageIO;

import java.io.File;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography_M {
    protected File source;

    // Seta a imagem fonte
    public void setSource(String path) throws Exception {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        if(image == null) throw new Exception("[Error]: File is not an image.");
        this.source = file;
    }
    // Seta a imagem fonte
    public void setSource(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        if(image == null) throw new Exception("[Error]: File is not an image.");
        this.source = file;
    }

    // Remove a imagem fonte
    public void removeSource() {
        this.source = null;
    }

    // Esteganografa os arquivos 
    public abstract void encode(TargetFile_M targetFile);

    // Desesteganografa os arquivos
    public abstract void decode(String path);

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography {
    File source;
    File zipFile;
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
        if(files.indexOf(file) != -1) throw new Exception("[Error]: Duplicate file");
        files.add(file);
        zipFiles();
        return file;
    }

    public void removeFile(File file) {
        files.remove(file);
        zipFiles();
    }

    public void zipFiles() {
        try {
            zipFile = new File("tmp.zip");
            ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(zipFile));

            for (File file : files) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                stream.putNextEntry(zipEntry);
                byte[] allBytes = new byte[(int)file.length()];

                InputStream inputStream = new FileInputStream(file.getAbsolutePath());
                inputStream.read(allBytes);
                stream.write(allBytes, 0, allBytes.length);

                stream.closeEntry();
            }
            stream.close();

        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }

    public abstract void encode();

    public abstract void decode(String path);

    public byte[] getFileBytes() throws Exception {
        byte[] allBytes = new byte[(int)zipFile.length()];

        InputStream inputStream = new FileInputStream(zipFile.getAbsolutePath());

        inputStream.read(allBytes);
        
        return allBytes;
    }

    // Retorna o tamanho dos arquivos compactados
    public long getCurrentZipSize() {
        return zipFile == null ? 0 : zipFile.length();
    }

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

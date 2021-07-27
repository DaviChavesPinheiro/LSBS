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
    protected File source, zipFile;
    private List<File> files = new ArrayList<File>();

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

    // Adiciona um arquivo a lista de arquivos que serão inseridos dentro da imagem fonte
    public File addFile(String path) throws Exception {
        File file = new File(path);
        if(files.indexOf(file) != -1) throw new Exception("[Error]: Duplicate file");
        files.add(file);
        return file;
    }

    // Remove um arquivo da lista de arquivos
    public void removeFile(File file) {
        files.remove(file);
        zipFiles();
    }

    // Zippa todos os arquivos da lista de arquivos
    public void zipFiles() {
        try {
            zipFile = File.createTempFile("LSBS-", "-files");
            zipFile.deleteOnExit();

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

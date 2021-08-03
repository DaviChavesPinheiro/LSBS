package src.model;
import javax.imageio.ImageIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.image.BufferedImage;

public abstract class ImageStegnography_M {
    protected File source, endodedFile, decodedFile;

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
        this.endodedFile = file;
    }

    public File getSource() {
        return source;
    }

    public File getEncoded() {
        return endodedFile;
    }

    public File getDecoded() {
        return decodedFile;
    }

    public File[] getDecodedFiles() {
        ArrayList<File> filesList = new ArrayList<>();
        byte[] buffer = new byte[2048];
        try {
            Path tempDir = Files.createTempDirectory("LSBS-");
            tempDir.toFile().deleteOnExit();
            InputStream zip = new FileInputStream(decodedFile.getAbsolutePath());
            ZipInputStream stream = new ZipInputStream(zip);
            ZipEntry entry;
            while((entry = stream.getNextEntry())!=null)
            {
                File outputFile = new File(tempDir.toAbsolutePath().toString(), entry.getName());
                outputFile.deleteOnExit();
                filesList.add(outputFile);
                FileOutputStream output = null;
                try
                {
                    output = new FileOutputStream(outputFile.getAbsolutePath());
                    int len = 0;
                    while ((len = stream.read(buffer)) > 0)
                    {
                        output.write(buffer, 0, len);
                    }
                }
                finally
                {
                    if(output!=null) output.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        File[] filesArray = new File[filesList.size()];
        return filesList.toArray(filesArray);
    }

    // Remove a imagem fonte
    public void removeSource() {
        this.source = null;
    }

    // Esteganografa os arquivos 
    public abstract void encode(TargetFile_M targetFile);

    // Desesteganografa os arquivos
    public abstract void decode();

    // Retorna o número de bytes máximo que o source pode armazenar
    public abstract long getMaxSpaceAvailable();
}

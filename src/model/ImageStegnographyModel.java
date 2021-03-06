package src.model;
import javax.imageio.ImageIO;

import src.utils.EventManeger;
import src.utils.EventTypes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.image.BufferedImage;

public abstract class ImageStegnographyModel {
    protected File source, endodedFile, decodedFile;
    public TargetFileModel targetFile;
    public EventManeger events;

    public ImageStegnographyModel() {
        this.events = new EventManeger();
        this.targetFile = new TargetFileModel(events);
    }

    // Set source image
    public void setSource(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        if(image == null) throw new Exception("[Error]: File is not an image.");
        this.source = file;
        // Create a temp file
        File tempFile = File.createTempFile("LSBS-", "-endodedFile.png");
        Files.copy(source.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        // Set temp file as encodedFile
        setEncoded(tempFile);
    }

    // Remove source image
    public void removeSource() {
        this.source = null;
        removeEncoded();
    }

    public File getSource() {
        return source;
    }

    public void setEncoded(File file) {
        this.endodedFile = file;
        events.notify(EventTypes.LSB_ENCODED_SET, this);
    }

    public void removeEncoded() {
        endodedFile.delete();
        this.endodedFile = null;
        events.notify(EventTypes.LSB_ENCODED_REMOVED, this);
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

    // Steganograph the files 
    public abstract void encode();

    // Desteganograph the files
    public abstract void decode();

    // Returns the max number of bytes the source can store
    public abstract long getMaxSpaceAvailable();
}

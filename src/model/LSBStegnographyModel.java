package src.model;
import javax.imageio.ImageIO;

import src.utils.BytesOperations;
import src.utils.EventTypes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
public class LSBStegnographyModel extends ImageStegnographyModel {
    private static LSBStegnographyModel instance = null;
    public static LSBStegnographyModel getInstance() {
        if(instance == null) {
            instance = new LSBStegnographyModel();
        }
        return instance;
    }
    
    @Override
    public void encode() {
        try {
            BufferedImage image = ImageIO.read(this.source);
            byte[] targetFileBytes = targetFile.getFileBytes();
            byte[] bytes = BytesOperations.concatArrays(new byte[4], targetFileBytes);

            bytes[0] = BytesOperations.getByte(targetFileBytes.length, 3);
            bytes[1] = BytesOperations.getByte(targetFileBytes.length, 2);
            bytes[2] = BytesOperations.getByte(targetFileBytes.length, 1);
            bytes[3] = BytesOperations.getByte(targetFileBytes.length, 0);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = BytesOperations.getBit(byt, 7 - (bn % 8));

                color = BytesOperations.setBit(color, bit, (2 - (bn % 3)) * 8);

                image.setRGB(imageX, imageY, color);
            }

            if(endodedFile != null) endodedFile.delete();

            File file = File.createTempFile("LSBS-", "-endodedFile.png");
            ImageIO.write(image, "png", file);
            setEncoded(file);
            endodedFile.deleteOnExit();

        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }

    @Override
    public void decode() {
        try {
            BufferedImage lsbImage = ImageIO.read(endodedFile);

            byte bytes[] = new byte[4];

            for (int bn = 0; bn < 4 * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn] = (byte)BytesOperations.setBit(bytes[bytn], BytesOperations.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            int hideFileSize = BytesOperations.bytesToInt(bytes);

            bytes = new byte[hideFileSize];

            for (int bn = 4 * 8; bn < 4 * 8 + (hideFileSize * 8); bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn - 4] = (byte)BytesOperations.setBit(bytes[bytn - 4], BytesOperations.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            if(decodedFile != null) decodedFile.delete();

            decodedFile = File.createTempFile("LSBS-", "-decodedFile.png");
            decodedFile.deleteOnExit();

            OutputStream stream = new FileOutputStream(decodedFile);
            stream.write(bytes);
            stream.close();

            events.notify(EventTypes.LSB_DECODE, this);
        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        
    }

    // Compute the max available storage
    @Override
    public long getMaxSpaceAvailable() {
        try {
            BufferedImage image = ImageIO.read(this.source);
            long pixelsAmount = image.getWidth() * image.getHeight();
            double bytesPerPixelProportion = 3d/8d;
            return (long)(pixelsAmount * bytesPerPixelProportion);
        } catch (Exception e) {
            return 0;
        }
    }
}

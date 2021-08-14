package src.model;
import javax.imageio.ImageIO;

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

    public LSBStegnographyModel() {}

    @Override
    public void encode(TargetFileModel targetFile) {
        try {
            BufferedImage image = ImageIO.read(this.source);
            byte[] targetFileBytes = targetFile.getFileBytes();
            byte[] bytes = UtilsModel.concatArrays(new byte[4], targetFileBytes);

            bytes[0] = UtilsModel.getByte(targetFileBytes.length, 3);
            bytes[1] = UtilsModel.getByte(targetFileBytes.length, 2);
            bytes[2] = UtilsModel.getByte(targetFileBytes.length, 1);
            bytes[3] = UtilsModel.getByte(targetFileBytes.length, 0);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = UtilsModel.getBit(byt, 7 - (bn % 8));

                color = UtilsModel.setBit(color, bit, (2 - (bn % 3)) * 8);

                image.setRGB(imageX, imageY, color);
            }

            if(endodedFile != null) endodedFile.delete();

            endodedFile = File.createTempFile("LSBS-", "-endodedFile.png");
            endodedFile.deleteOnExit();

            ImageIO.write(image, "png", endodedFile);
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

                bytes[bytn] = (byte)UtilsModel.setBit(bytes[bytn], UtilsModel.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            int hideFileSize = UtilsModel.bytesToInt(bytes);

            bytes = new byte[hideFileSize];

            for (int bn = 4 * 8; bn < 4 * 8 + (hideFileSize * 8); bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn - 4] = (byte)UtilsModel.setBit(bytes[bytn - 4], UtilsModel.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            if(decodedFile != null) decodedFile.delete();

            decodedFile = File.createTempFile("LSBS-", "-decodedFile");
            decodedFile.deleteOnExit();

            OutputStream stream = new FileOutputStream(decodedFile);
            stream.write(bytes);
            stream.close();

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

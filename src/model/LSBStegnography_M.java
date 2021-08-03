package src.model;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
public class LSBStegnography_M extends ImageStegnography_M {
    private static LSBStegnography_M instance = null;
    public static LSBStegnography_M getInstance() {
        if(instance == null) {
            instance = new LSBStegnography_M();
        }
        return instance;
    }

    public LSBStegnography_M() {}

    @Override
    public void encode(TargetFile_M targetFile) {
        try {
            BufferedImage image = ImageIO.read(this.source);
            byte[] targetFileBytes = targetFile.getFileBytes();
            byte[] bytes = Utils_M.concatArrays(new byte[4], targetFileBytes);

            bytes[0] = Utils_M.getByte(targetFileBytes.length, 3);
            bytes[1] = Utils_M.getByte(targetFileBytes.length, 2);
            bytes[2] = Utils_M.getByte(targetFileBytes.length, 1);
            bytes[3] = Utils_M.getByte(targetFileBytes.length, 0);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = Utils_M.getBit(byt, 7 - (bn % 8));

                color = Utils_M.setBit(color, bit, (2 - (bn % 3)) * 8);

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

                bytes[bytn] = (byte)Utils_M.setBit(bytes[bytn], Utils_M.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            int hideFileSize = Utils_M.bytesToInt(bytes);

            bytes = new byte[hideFileSize];

            for (int bn = 4 * 8; bn < 4 * 8 + (hideFileSize * 8); bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn - 4] = (byte)Utils_M.setBit(bytes[bytn - 4], Utils_M.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
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

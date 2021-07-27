import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
public class LSBStegnography extends ImageStegnography {

    public LSBStegnography() {}

    public LSBStegnography(String path) throws Exception {
        this.setSource(path);
    }

    @Override
    public void encode(TargetFile targetFile) {
        try {
            BufferedImage image = ImageIO.read(this.source);
            byte[] targetFileBytes = targetFile.getFileBytes();
            byte[] bytes = Utils.concatArrays(new byte[4], targetFileBytes);

            bytes[0] = Utils.getByte(targetFileBytes.length, 3);
            bytes[1] = Utils.getByte(targetFileBytes.length, 2);
            bytes[2] = Utils.getByte(targetFileBytes.length, 1);
            bytes[3] = Utils.getByte(targetFileBytes.length, 0);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = Utils.getBit(byt, 7 - (bn % 8));

                color = Utils.setBit(color, bit, (2 - (bn % 3)) * 8);

                image.setRGB(imageX, imageY, color);
            }

            ImageIO.write(image, "png", new File("./out.png"));
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }

    @Override
    public void decode(String path) {
        try {
            File file = new File(path);

            BufferedImage lsbImage = ImageIO.read(file);

            byte bytes[] = new byte[4];

            for (int bn = 0; bn < 4 * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn] = (byte)Utils.setBit(bytes[bytn], Utils.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            int hideFileSize = Utils.bytesToInt(bytes);

            bytes = new byte[hideFileSize];

            for (int bn = 4 * 8; bn < 4 * 8 + (hideFileSize * 8); bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn - 4] = (byte)Utils.setBit(bytes[bytn - 4], Utils.getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            File hiddenFile = new File("secret.zip");

            OutputStream stream = new FileOutputStream(hiddenFile);
            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        
    }

    // Calcula o espaco máximo disponível
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

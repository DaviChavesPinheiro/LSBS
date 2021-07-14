import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;

public class LSBStegnography extends ImageStegnography {

    public LSBStegnography() {}

    public LSBStegnography(String path) throws Exception {
        super(path);
    }

    public byte[] concatWithArrays(byte[] array1, byte[] array2) {
        byte[] bytes = new byte[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            bytes[i] = array1[i];
        }

        for (int i = array1.length; i < array1.length + array2.length; i++) {
            bytes[i] = array2[i - array1.length];
        }

        return bytes;
    }

    public byte getByte(int num, int bn) {
        return (byte)((num & (0xFF << (bn * 8))) >> (bn * 8));
    }

    public int setByte(int num, byte byt, int n) {
        num = num & (~(0xFF << (n * 8)));
        return (num | (((int)byt & 0xFF) << (n * 8)));
    }

    public int getHalfByte(int num, int bn) {
        return ((num & (0xF << (bn * 4))) >> (bn * 4)) & 0xF;
    }

    public int getBit(int num, int n) {
        return ((num & (0b1 << n)) >> n) & 0b1;
    }

    public int setBit(int num, int bit, int n) {
        num = num & (~(0b1 << n));
        return (num | ((bit & 0b1) << n));
    }

    public int bytesToInt(byte[] bytes) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            num = setByte(num, bytes[i], 3 - i);
        }
        return num;
    }

    @Override
    public File addFile(String path) throws Exception {
        File file = new File(path);

        if(getCurrentFilesSize() + file.length() > getMaxSpaceAvailable()) throw new Exception("[Error]: Cabou o espaco brother");
        return super.addFile(path);
    }

    @Override
    public void encode() {
        try {
            BufferedImage image = ImageIO.read(this.source);
            byte[] fileBytes = getBytesToEncode();
            byte[] bytes = this.concatWithArrays(new byte[4], fileBytes);


            int bytesToEncodeSize = fileBytes.length;
            bytes[0] = getByte(bytesToEncodeSize, 3);
            bytes[1] = getByte(bytesToEncodeSize, 2);
            bytes[2] = getByte(bytesToEncodeSize, 1);
            bytes[3] = getByte(bytesToEncodeSize, 0);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = getBit(byt, 7 - (bn % 8));

                color = setBit(color, bit, (2 - (bn % 3)) * 8);

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

                bytes[bytn] = (byte)setBit(bytes[bytn], getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            int hideFileSize = bytesToInt(bytes);

            bytes = new byte[hideFileSize];

            for (int bn = 4 * 8; bn < 4 * 8 + (hideFileSize * 8); bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % lsbImage.getWidth();
                int imageY = Math.floorDiv(pn, lsbImage.getHeight());
                int color = lsbImage.getRGB(imageX, imageY);

                int bytn = bn >> 3;

                bytes[bytn - 4] = (byte)setBit(bytes[bytn - 4], getBit(color, (2 - (bn % 3)) * 8), (7 - (bn % 8)));
            }

            File hiddenFile = new File("secret.png");

            OutputStream stream = new FileOutputStream(hiddenFile);
            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        
    }

    @Override
    public long getMaxSpaceAvailable() {
        if(this.source == null) return 0;
        try {
            BufferedImage image = ImageIO.read(this.source);
            long pixelsAmount = image.getWidth() * image.getHeight();
            double bytesPerPixelProportion = 3d/8d;
            return (long)(pixelsAmount * bytesPerPixelProportion);
        } catch (Exception e) {
            return -1;
        }
    }
    
}

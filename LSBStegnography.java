import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;

public class LSBStegnography extends ImageStegnography {

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

    @Override
    public void encode() {
        try {
            byte[] fileBytes = getBytesToEncode();
            byte[] bytes = this.concatWithArrays(new byte[4], fileBytes);

            System.out.println("fileBytes: " + fileBytes.length);
            System.out.println("bytes: " + bytes.length);

            int bytesToEncodeSize = fileBytes.length;
            System.out.println("bytesToEncodeSize: " + Integer.toBinaryString(bytesToEncodeSize));
            bytes[0] = getByte(bytesToEncodeSize, 3);
            bytes[1] = getByte(bytesToEncodeSize, 2);
            bytes[2] = getByte(bytesToEncodeSize, 1);
            bytes[3] = getByte(bytesToEncodeSize, 0);

            System.out.println("bytes[0]: " + bytes[0]);
            System.out.println("bytes[1]: " + bytes[1]);
            System.out.println("bytes[2]: " + bytes[2]);
            System.out.println("bytes[3]: " + bytes[3]);

            for (int bn = 0; bn < bytes.length * 8; bn++) {
            // for (int bn = 0; bn < 4 * 8; bn++) {
                int pn = Math.floorDiv(bn, 3);
                int imageX = pn % image.getWidth();
                int imageY = Math.floorDiv(pn, image.getHeight());
                int color = image.getRGB(imageX, imageY);

                int byt = bytes[bn >> 3];
                int bit = getBit(byt, 7 - (bn % 8));

                color = setBit(color, bit, (2 - (bn % 3)) * 8);

                image.setRGB(imageX, imageY, color);
            }

            // for (int i = 0; i < 11; i++) {
            //     int imageX = i % image.getWidth();
            //     int imageY = Math.floorDiv(i, image.getHeight());

            //     int color = image.getRGB(imageX, imageY);

            //     System.out.println(Integer.toBinaryString(color));
            // }

            ImageIO.write(image, "png", new File("./out.png"));
        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    int extractIntFromColors(int[] colors) {
        int out = 0;
        if(colors.length != 8) return -1;

        for (int i = 0; i < 8; i++) {
            int b3 = (colors[i] >> 24) & 0b00000001;
            int b2 = (colors[i] >> 16) & 0b00000001;
            int b1 = (colors[i] >> 8) & 0b00000001;
            int b0 = (colors[i] >> 0) & 0b00000001;

            out = out | (b3 << (31 - (4 * i) - 0));
            out = out | (b2 << (31 - (4 * i) - 1));
            out = out | (b1 << (31 - (4 * i) - 2));
            out = out | (b0 << (31 - (4 * i) - 3));
        }

        return out;
    }

    byte extractByteFromColors(int[] colors) {
        int out = 0;
        if(colors.length != 2) return -1;

        int b7 = (colors[0] >> 24) & 0b00000001;
        int b6 = (colors[0] >> 16) & 0b00000001;
        int b5 = (colors[0] >> 8) & 0b00000001;
        int b4 = (colors[0] >> 0) & 0b00000001;
        int b3 = (colors[1] >> 24) & 0b00000001;
        int b2 = (colors[1] >> 16) & 0b00000001;
        int b1 = (colors[1] >> 8) & 0b00000001;
        int b0 = (colors[1] >> 0) & 0b00000001;

        out = out | (b7 << 7);
        out = out | (b6 << 6);
        out = out | (b5 << 5);
        out = out | (b4 << 4);
        out = out | (b3 << 3);
        out = out | (b2 << 2);
        out = out | (b1 << 1);
        out = out | (b0 << 0);

        return (byte)out;
    }

    @Override
    public void decode(String path) {
        try {
            File file = new File(path);

            BufferedImage lsbImage = ImageIO.read(file);
            
            int buffer[] = new int[8];
            for (int i = 0; i < 8; i++) {
                int imageX = i % lsbImage.getWidth();
                int imageY = Math.floorDiv(i, lsbImage.getHeight());

                int color = lsbImage.getRGB(imageX, imageY);
                buffer[i] = color;
            }

            int hideFileSize = extractIntFromColors(buffer);
            System.out.println(hideFileSize);

            // int buffer2[] = new int[2];
            // byte lsbImageBytes[] = new byte[hideFileSize];
            // for (int i = 8; i < 8 + (hideFileSize * 2); i++) {
            //     int imageX = i % lsbImage.getWidth();
            //     int imageY = Math.floorDiv(i, lsbImage.getHeight());

            //     int color = lsbImage.getRGB(imageX, imageY);

            //     buffer2[i % 2] = color;

            //     if(i % 2 == 1) {
            //         lsbImageBytes[(i >> 1) - 4] = extractByteFromColors(buffer2);
            //     }
            // }

            // File hiddenFile = new File("secret.png");

            // OutputStream stream = new FileOutputStream(hiddenFile);
            // stream.write(lsbImageBytes);
            // stream.close();

            
            // System.out.println();
           

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        
    }
    
}

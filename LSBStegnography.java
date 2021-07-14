import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;

public class LSBStegnography extends ImageStegnography {

    public LSBStegnography(String path) throws Exception {
        super(path);
    }

    public byte[] concatWithArrayCopy(byte[] array1, byte[] array2) {
        byte[] bytes = new byte[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            bytes[i] = array1[i];
        }

        for (int i = array1.length; i < array1.length + array2.length; i++) {
            bytes[i] = array2[i - array1.length];
        }

        return bytes;
    }

    @Override
    public void encode() {
        try {
            byte[] bytes = this.concatWithArrayCopy(new byte[4], getBytesToEncode());

            int bytesToEncodeSize = bytes.length - 4;
            bytes[0] = (byte)(bytesToEncodeSize & 0xFF000000);
            bytes[1] = (byte)(bytesToEncodeSize & 0x00FF0000);
            bytes[2] = (byte)(bytesToEncodeSize & 0x0000FF00);
            bytes[3] = (byte)(bytesToEncodeSize & 0x000000FF);

            for (int i = 0; i < 2 * bytes.length; i++) {
                int imageX = i % image.getWidth();
                int imageY = Math.floorDiv(i, image.getHeight());

                int color = image.getRGB(imageX, imageY);
                int byt = bytes[i >> 1];

                if(i % 2 == 0) {
                    byt = (byt & 0b11110000) >> 4;
                } else {
                    byt = byt & 0b00001111;
                }

                color = color & 0b11111110111111101111111011111110;
                int mask = 0b00000000000000000000000000000000;
                for (int j = 0; j < 4; j++) {
                    int bit = (byt >> j) & 0b00000001;
                    mask = mask | (bit << (j * 8));
                }

                color = color | mask;

                image.setRGB(imageX, imageY, color);
            }

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

            int buffer2[] = new int[2];
            byte lsbImageBytes[] = new byte[hideFileSize];
            for (int i = 8; i < 8 + (hideFileSize * 2); i++) {
                int imageX = i % lsbImage.getWidth();
                int imageY = Math.floorDiv(i, lsbImage.getHeight());

                int color = lsbImage.getRGB(imageX, imageY);

                buffer2[i % 2] = color;

                if(i % 2 == 1) {
                    lsbImageBytes[(i >> 1) - 4] = extractByteFromColors(buffer2);
                }
            }

            File hiddenFile = new File("secret.png");

            OutputStream stream = new FileOutputStream(hiddenFile);
            stream.write(lsbImageBytes);
            stream.close();

            
            System.out.println();
           

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        
    }
    
}

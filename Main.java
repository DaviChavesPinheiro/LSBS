import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Main {
    public void encode() {
        try {
 
            File file = new File("./rgba.png");
            
            BufferedImage bi = ImageIO.read(file);
            ImageIO.write(bi, "png", new File("./rgba.png"));
            Byte[] bytes = new Byte[] {0b00000000, 0b00000000, 0b00000000, 0b00000100};
            
            for (int h = 0; h < bi.getHeight(); h++) {
                for (int w = 0; w < bi.getWidth(); w++) {
                    int color = bi.getRGB(w, h);
                    int source = bytes[((h * bi.getWidth()) + w) >> 1];

                    if(((h * bi.getWidth()) + w) % 2 == 0) {
                        source = (source & 0b11110000) >> 4;
                    } else {
                        source = source & 0b00001111;
                    }

                    int mask = 0b11111110111111101111111011111110;
                    for (int i = 0; i < 4; i++) {
                        int bit = (source >> i) & 0b00000001;
                        mask = mask | (bit << (i * 8));
                    }
                    
                    System.out.print(Integer.toBinaryString(mask) + " ");
                    color = color & mask;
                    bi.setRGB(w, h, color);
                }
                System.out.println();
            }
            
            ImageIO.write(bi, "png", new File("./white-lsb.png"));
        
            System.out.println("Images were written succesfully.");
    
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
    public static void main(String[] args) {
       
    }
}
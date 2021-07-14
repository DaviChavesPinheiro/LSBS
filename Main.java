import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Main {
   
    public static void main(String[] args) {
        try {
            LSBStegnography stegnography = new LSBStegnography(args[0]);
            stegnography.addFile(args[1]);
            stegnography.encode();
            // stegnography.decode("out.png");

            // int num = 0b10000000010000000010000000010000;
            // System.out.println(Integer.toBinaryString(stegnography.setBit(num, 0b0, 4)));
            // System.out.println(Integer.toBinaryString(stegnography.setBit(num, 0b1, 5)));

        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
}
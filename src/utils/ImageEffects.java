package src.utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ImageEffects {
    public static Icon changeBrightness(Icon icon, float factor) {
        if (icon == null || !(icon instanceof ImageIcon)) {
            return icon;
        }
        ImageIcon imageIcon = (ImageIcon) icon;

        ImageIcon ret = new ImageIcon();

        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());

        RescaleOp op = new RescaleOp(factor, 0, null);
        image = op.filter(image, image);

        ret.setImage(image);
        return ret;
    }
}

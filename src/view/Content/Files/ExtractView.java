package src.view.Content.Files;

import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.*;
import javax.swing.border.*;

import src.controller.ExtractController;
import src.model.LSBStegnographyModel;
import src.utils.EventListener;
import src.utils.EventTypes;
import src.utils.ImageEffects;

import java.awt.*;

public class ExtractView extends JButton implements EventListener {
    private boolean enabled = false;
    Icon img, imgFocused;
    public ExtractView() {
        super();
        this.setPreferredSize(new Dimension(80, 80));
        this.setMinimumSize(new Dimension(80, 80));
        this.setMaximumSize(new Dimension(80, 80));

        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setForeground(new Color(76, 76, 76));

        this.setVerticalTextPosition(AbstractButton.BOTTOM);
        this.setHorizontalTextPosition(AbstractButton.CENTER);

        this.setText("Extract");
        this.setIconTextGap(6);

        Path imgAbsPath = Path.of("src/images/file-icon.png").toAbsolutePath();
        img = new ImageIcon(imgAbsPath.toString());
        imgFocused = ImageEffects.changeBrightness(img, 1.5f);
        this.setIcon(img);

        Border line = new LineBorder(new Color(56, 56, 56));
        Border margin = new EmptyBorder(15, 0, 0, 0);
        Border compound = new CompoundBorder(line, margin);
        this.setBorder(compound);

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        
        ExtractController controller = new ExtractController(LSBStegnographyModel.getInstance());
        this.addActionListener(controller);

        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_SET, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_ENCODED_REMOVED, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!enabled) {
            g.setColor(new Color(12, 12, 12));
        } else {
            if (getModel().isPressed()) {
                g.setColor((new Color(41, 41, 41)));
                this.setForeground(new Color(125, 125, 125));
                this.setIcon(imgFocused);
            } else if (getModel().isRollover()) {
                g.setColor((new Color(41, 41, 41)));
                this.setForeground(new Color(125, 125, 125));
                this.setIcon(imgFocused);
            } else {
                g.setColor(getBackground());
                this.setForeground(new Color(76, 76, 76));
                this.setIcon(img);
            }
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case LSB_ENCODED_SET:
                this.enabled = true;
                this.repaint();
                break;
            case LSB_ENCODED_REMOVED:
                this.enabled = false;
                this.repaint();
                break;
            default:
                break;
        }
    }
}

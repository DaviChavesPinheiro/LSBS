package src.view.Content.Files;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.nio.file.Path;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class FileView extends JButton {
    File file;
    public FileView(File file) {
        super();
        this.setPreferredSize(new Dimension(80, 80));
        this.setMinimumSize(new Dimension(80, 80));
        this.setMaximumSize(new Dimension(80, 80));

        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.setForeground(new Color(76, 76, 76));

        this.setVerticalTextPosition(AbstractButton.BOTTOM);
        this.setHorizontalTextPosition(AbstractButton.CENTER);

        this.setText(file.getName());
        this.setIconTextGap(6);

        Path imgAbsPath = Path.of("src/images/binary-file-icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.setIcon(img);

        Border line = new LineBorder(new Color(56, 56, 56));
        Border margin = new EmptyBorder(15, 3, 0, 3);
        Border compound = new CompoundBorder(line, margin);
        this.setBorder(compound);

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor((new Color(41, 41, 41)));
        } else if (getModel().isRollover()) {
            g.setColor((new Color(41, 41, 41)));
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}

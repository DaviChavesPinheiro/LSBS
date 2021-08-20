package src.view.Content.Source;

import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.*;

import src.controller.DeleteController;
import src.model.LSBStegnographyModel;

public class DeleteButtonView extends JButton {
    public DeleteButtonView() {
        super();
        this.setPreferredSize(new Dimension(15, 15));
        this.setMinimumSize(new Dimension(15, 15));
        this.setMaximumSize(new Dimension(15, 15));

        this.setOpaque(false);

        Path imgAbsPath = Path.of("src/images/delete-icon.png").toAbsolutePath();
        ImageIcon img = new ImageIcon(imgAbsPath.toString());
        this.setIcon(img);

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        
        DeleteController controller = new DeleteController(LSBStegnographyModel.getInstance());
        this.addActionListener(controller);
    }

    // @Override
    // protected void paintComponent(Graphics g) {
    //     if (getModel().isPressed()) {
    //         g.setColor((new Color(41, 41, 41)));
    //     } else if (getModel().isRollover()) {
    //         g.setColor((new Color(41, 41, 41)));
    //     } else {
    //         g.setColor(getBackground());
    //     }
    //     g.fillRect(0, 0, getWidth(), getHeight());
    //     super.paintComponent(g);
    // }
}

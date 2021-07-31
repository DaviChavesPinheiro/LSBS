package src.frontend.components;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.*;
import java.io.File;
import java.awt.*;

public class SourceContent extends JPanel implements MouseListener  {
    public SourceContent() {
        this.setOpaque(true);
        this.setBackground(new Color(29, 29, 29));
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(new Color(41, 41, 41));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.setBackground(new Color(29, 29, 29));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}

package src.controller;

import javax.swing.filechooser.FileSystemView;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
public class SourceContent_C implements MouseListener {
    private JPanel view;

    public SourceContent_C(JPanel view) {
        super();
        this.view = view;
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
        this.view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.view.setBackground(new Color(41, 41, 41));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.view.setBackground(new Color(29, 29, 29));
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

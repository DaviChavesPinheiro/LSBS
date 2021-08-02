package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnography_M;
import src.model.TargetFile_M;
import src.view.Donut_V;
import src.view.SourceContent_V;

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
    public void mouseClicked(MouseEvent event) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                LSBStegnography_M lsbStegnography = LSBStegnography_M.getInstance();
                lsbStegnography.setSource(selectedFile);
                if(!TargetFile_M.getInstance().isEmpty()) lsbStegnography.encode(TargetFile_M.getInstance());
                Donut_V.getInstance().setMaxSpace(lsbStegnography.getMaxSpaceAvailable());
                SourceContent_V.getInstance().refresh();
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
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

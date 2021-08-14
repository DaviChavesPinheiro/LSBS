package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;
import src.view.DonutView;
import src.view.SourceContentView;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
public class SourceContentController implements MouseListener {
    private JPanel view;

    public SourceContentController(JPanel view) {
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
                LSBStegnographyModel lsbStegnography = LSBStegnographyModel.getInstance();
                lsbStegnography.setSource(selectedFile);
                if(!TargetFileModel.getInstance().isEmpty()) lsbStegnography.encode(TargetFileModel.getInstance());
                DonutView.getInstance().setMaxSpace(lsbStegnography.getMaxSpaceAvailable());
                SourceContentView.getInstance().refresh();
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
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
}

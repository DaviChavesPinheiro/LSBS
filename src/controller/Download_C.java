package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnography_M;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;

public class Download_C implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Files.copy(LSBStegnography_M.getInstance().getOut().toPath(), selectedFile.toPath());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } 
        }
    }

    
    
}

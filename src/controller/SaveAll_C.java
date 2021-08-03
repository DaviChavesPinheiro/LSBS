package src.controller;

import src.model.LSBStegnography_M;

import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class SaveAll_C implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            try {
                Files.copy(LSBStegnography_M.getInstance().getDecoded().toPath(), selectedFile.toPath());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } 
        }
    }

    
    
}

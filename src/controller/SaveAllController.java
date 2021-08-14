package src.controller;

import src.model.LSBStegnographyModel;

import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class SaveAllController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            try {
                Files.copy(LSBStegnographyModel.getInstance().getDecoded().toPath(), selectedFile.toPath());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } 
        }
    }

    
    
}

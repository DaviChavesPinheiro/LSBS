package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnographyModel;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;

public class DownloadController implements ActionListener {
    private LSBStegnographyModel lsbStegnographyModel;
    public DownloadController(LSBStegnographyModel lsbStegnographyModel) {
        this.lsbStegnographyModel = lsbStegnographyModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Files.copy(lsbStegnographyModel.getEncoded().toPath(), selectedFile.toPath());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } 
        }
    }

    
    
}

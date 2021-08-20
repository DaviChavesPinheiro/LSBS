package src.controller;

import src.model.LSBStegnographyModel;

import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class SaveAllController implements ActionListener {
    private LSBStegnographyModel lsbStegnographyModel;

    public SaveAllController(LSBStegnographyModel lsbStegnographyModel) {
        this.lsbStegnographyModel = lsbStegnographyModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Seleciona onde o arquivo deve ser salvo
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Salva o arquivo
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Files.copy(lsbStegnographyModel.getDecoded().toPath(), selectedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } 
        }
    }

    
    
}

package src.controller;

import src.model.LSBStegnographyModel;
import src.view.Content.Files.FilesAreaView;

import java.awt.event.*;
import java.io.File;

public class ExtractController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // int returnValue = fileChooser.showSaveDialog(null);

        // if (returnValue == JFileChooser.APPROVE_OPTION) {
        //     File selectedFile = fileChooser.getSelectedFile();
        //     System.out.println(selectedFile.getAbsolutePath());
        //     try {
        //         LSBStegnography_M.getInstance().decode();
        //     } catch (Exception err) {
        //         System.out.println(err.getMessage());
        //     } 
        // }
        try {
            LSBStegnographyModel.getInstance().decode();
            File[] decodedFiles = LSBStegnographyModel.getInstance().getDecodedFiles();
            for(File file: decodedFiles) {
                // TODO: 
                // FilesAreaView.getInstance().addFile(file);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        } 
    }

    
    
}

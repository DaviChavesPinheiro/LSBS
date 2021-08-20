package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnographyModel;
import src.view.Content.Files.FilesAreaView;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class AddFileController implements ActionListener {
    private LSBStegnographyModel lsbStegnographyModel;

    public AddFileController(LSBStegnographyModel lsbStegnographyModel) {
        this.lsbStegnographyModel = lsbStegnographyModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            try {
                for(File file: selectedFiles) {
                    lsbStegnographyModel.targetFile.addSubFile(file);
                    FilesAreaView.getInstance().addFile(file);
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                lsbStegnographyModel.encode();
            }
        }
    }

    
    
}

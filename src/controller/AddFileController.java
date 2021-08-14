package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;
import src.view.Content.Files.FilesAreaView;
import src.view.Content.Source.SourceContentView;
import src.view.SideBar.DonutView;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class AddFileController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            TargetFileModel targetFile = TargetFileModel.getInstance();
            try {
                for(File file: selectedFiles) {
                    targetFile.addSubFile(file);
                    FilesAreaView.getInstance().addFile(file);
                }
                
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                DonutView.getInstance().setSpaceUsed(targetFile.getTargetFileSize());
                LSBStegnographyModel lsbStegnography = LSBStegnographyModel.getInstance();
                lsbStegnography.encode(targetFile);
            }
        }
    }

    
    
}

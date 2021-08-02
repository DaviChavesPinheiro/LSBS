package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnography_M;
import src.model.TargetFile_M;
import src.view.Donut_V;
import src.view.FilesArea_V;
import src.view.SourceContent_V;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class AddFile_C implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            TargetFile_M targetFile = TargetFile_M.getInstance();
            try {
                for(File file: selectedFiles) {
                    targetFile.addSubFile(file);
                    FilesArea_V.getInstance().addFile(file);
                }
                
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                Donut_V.getInstance().setSpaceUsed(targetFile.getTargetFileSize());
                LSBStegnography_M lsbStegnography = LSBStegnography_M.getInstance();
                lsbStegnography.encode(targetFile);
                SourceContent_V.getInstance().refresh();
            }
        }
    }

    
    
}

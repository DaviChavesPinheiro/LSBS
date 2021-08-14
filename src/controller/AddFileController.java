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
    private TargetFileModel targetFileModel;
    private LSBStegnographyModel lsbStegnographyModel;

    public AddFileController(TargetFileModel targetFileModel, LSBStegnographyModel lsbStegnographyModel) {
        this.targetFileModel = targetFileModel;
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
                    targetFileModel.addSubFile(file);
                    FilesAreaView.getInstance().addFile(file);
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                lsbStegnographyModel.encode(targetFileModel);
            }
        }
    }

    
    
}

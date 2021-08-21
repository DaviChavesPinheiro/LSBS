package src.controller;

import javax.swing.filechooser.FileSystemView;

import src.model.LSBStegnographyModel;

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
        // Abre o file chooser
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Adiciona os arquivos selecionados
            File[] selectedFiles = fileChooser.getSelectedFiles();

            for(File file: selectedFiles) {
                try {
                    lsbStegnographyModel.targetFile.addSubFile(file);
                } catch (Exception err) {
                    System.out.println(1);
                    System.out.println(err);
                }
            }
            
            // Faz o encode de todos os arquivos
            lsbStegnographyModel.encode();
        }
    }

    
    
}

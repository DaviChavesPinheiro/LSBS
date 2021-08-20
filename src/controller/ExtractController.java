package src.controller;

import src.model.LSBStegnographyModel;
import java.awt.event.*;

public class ExtractController implements ActionListener {
    private LSBStegnographyModel lsbStegnographyModel;

    public ExtractController(LSBStegnographyModel lsbStegnographyModel) {
        this.lsbStegnographyModel = lsbStegnographyModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            lsbStegnographyModel.decode();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        } 
    }

    
    
}

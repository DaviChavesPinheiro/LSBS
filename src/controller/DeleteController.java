package src.controller;

import src.model.LSBStegnographyModel;
import java.awt.event.*;

public class DeleteController implements ActionListener {
    private LSBStegnographyModel lsbStegnographyModel;
    public DeleteController(LSBStegnographyModel lsbStegnographyModel) {
        this.lsbStegnographyModel = lsbStegnographyModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            lsbStegnographyModel.removeSource();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        } 
    }

    
    
}

package src.controller;

import src.model.LSBStegnographyModel;
import java.awt.event.*;

public class ExtractController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            LSBStegnographyModel.getInstance().decode();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        } 
    }

    
    
}

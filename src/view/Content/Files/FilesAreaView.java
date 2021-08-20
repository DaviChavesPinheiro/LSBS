package src.view.Content.Files;
import javax.swing.*;

import src.model.EventListener;
import src.model.EventTypes;
import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class FilesAreaView extends JPanel implements EventListener {
    public FilesAreaView() {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(1080 / 4 * 3 - 50, 720 - (360 + 35 + 35 + 20)));

        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        // Inicializa os arquivos vazios
        setFiles(Collections.emptyList(), false);

        // Subscribe to model
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.TF_ADD_FILE, this);
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.LSB_DECODE, this);
    }

    private void setFiles(Collection<File> files, boolean saveAll) {
        // Limpa os arquivos existentes
        this.removeAll();

        // Adiciona os botões
        this.add(new ExtractView());
        this.add(new AddFileView());
        if(saveAll) this.add(new SaveAllView());

        // Adiciona os arquivos
        for (File file : files) {
            this.add(new FileView(file));
        }

        // ReDraw
        this.revalidate();
        this.repaint();
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case TF_ADD_FILE:
                TargetFileModel targetFile = (TargetFileModel)model;
                setFiles(targetFile.getSubFiles(), false);
                break;
            case LSB_DECODE:
                LSBStegnographyModel lsbStegnographyModel = (LSBStegnographyModel)model;
                setFiles(Arrays.asList(lsbStegnographyModel.getDecodedFiles()), true);
                break;
            default:
                break;
        }
    }
}

package src.view.Content.Files;
import javax.swing.*;

import src.model.EventListener;
import src.model.EventTypes;
import src.model.LSBStegnographyModel;
import src.model.TargetFileModel;

import java.awt.*;
import java.io.File;
import java.util.List;

public class FilesAreaView extends JPanel implements EventListener {
    public FilesAreaView() {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(1080 / 4 * 3 - 50, 720 - (360 + 35 + 35 + 20)));

        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(new ExtractView());
        this.add(new AddFileView());
        this.add(new SaveAllView());

        // Subscribe to model
        LSBStegnographyModel.getInstance().events.subscribe(EventTypes.TF_ADD_FILE, this);
    }

    private void addFiles(List<File> files) {
        for (File file : files) {
            this.add(new FileView(file));
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void onEvent(EventTypes eventType, Object model) {
        switch (eventType) {
            case TF_ADD_FILE:
                // Limpa os arquivos existentes
                this.removeAll();

                // Adiciona os botões
                this.add(new ExtractView());
                this.add(new AddFileView());
                this.add(new SaveAllView());

                // Adiciona os novos arquivos
                TargetFileModel targetFile = (TargetFileModel)model;
                List<File> files = targetFile.getSubFiles();
                addFiles(files);
                break;
            default:
                break;
        }
    }
}

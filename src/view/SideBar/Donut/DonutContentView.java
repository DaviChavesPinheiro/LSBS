package src.view.SideBar.Donut;

import java.awt.Color;

import javax.swing.JLabel;

import src.utils.ByteConverter;

public class DonutContentView extends JLabel{
    public DonutContentView() {
        this.setForeground(new Color(135, 135, 135));
    }

    public void setValue(long bytes) {
        String formmatedText = ByteConverter.humanReadableByteCountSI(bytes);
        String styledText = String.format("<html><center><font style='font-size:28px' color=#878787>%s</font><font style='font-size:15px' color=#878787> %s<br></font><font style='font-size:12px' color=#004B63>available</font></html>", formmatedText.split("\\s+")[0], formmatedText.split("\\s+")[1]);
        this.setText(styledText);
    }
}

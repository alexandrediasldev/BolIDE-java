import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;

public class Ui {
    private static class exit_listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Title");
        FlowLayout flowLayout = new FlowLayout();

        JPanel pane = new JPanel();
        JTextField field = new JTextField(); //texte

        JPanel textpanel = new JPanel(new GridLayout(13,13));
        textpanel.add(field);
        JButton button = new JButton("Exit");

        button.addActionListener(new exit_listener());

        frame.add(textpanel);
        frame.add(pane);
        frame.pack(); //auto size
        frame.setVisible(true);
    }
}

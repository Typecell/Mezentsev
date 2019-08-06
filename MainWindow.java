import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainWindow extends JFrame {

    public MainWindow(String title, int width, int height) {
        super(title);
        createGUI(title, width, height);
    }

    private void createGUI(String title, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        LinkedList<JButton> buttons = getButtons();
        for (JButton button : buttons) {
            panel.add(button);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    private LinkedList<JButton> getButtons() {
        LinkedList<JButton> buttons = new LinkedList<>();
        JButton pTag = new JButton("<P>");
        JButton liTag = new JButton("<Li>");
        JButton ulList = new JButton("<Ul>");
        JButton bTag = new JButton("<B>");

        pTag.setBounds(10, 420, 60, 30);
        ulList.setBounds(80, 420, 60, 30);
        liTag.setBounds(150, 420, 60, 30);
        bTag.setBounds(220, 420, 60, 30);

        buttons.add(pTag);
        buttons.add(ulList);
        buttons.add(liTag);
        buttons.add(bTag);

        return buttons;
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow("Mezentsev Project", 700, 500);

//        JTextField field = new JTextField();
//        field.setColumns(23);
//        contents.add(field);
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements Runnable {

    JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("FlashCard Application");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1440, 800);
        frame.setLayout(null);
        JButton addCardButton = new JButton("Add Card");
        addCardButton.setBounds(360, 200, 200, 200);
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCardButton();
            }
        });
        JButton practiceButton = new JButton("Practice");
        practiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                practiceButton();
            }
        });
        practiceButton.setBounds(920, 200, 200, 200);
        frame.add(addCardButton);
        frame.add(practiceButton);
        frame.setVisible(true);
    }

    public void addCardButton() {
        JTextField front = new JTextField(40);
        JTextField back = new JTextField(40);
        Object[] message = {
                "Field 1:", front,
                "Field 2:", back
        };

        int option = JOptionPane.showOptionDialog(
                null,
                message,
                "Custom Popup",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                null);

        if (option == JOptionPane.OK_OPTION) {
            String frontText = front.getText();
            String backText = back.getText();
            Card card = new Card(frontText, backText);
            Main.addCard(card);
        }
    }

    public void practiceButton() {
        SwingUtilities.invokeLater(new PracticeGUI());
        frame.dispose();
    }

}

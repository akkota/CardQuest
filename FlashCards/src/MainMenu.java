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
        addCardButton.setBounds(300, 200, 200, 200);
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
        practiceButton.setBounds(600, 200, 200, 200);
        JButton deleteCardButton = new JButton("Delete Card");
        deleteCardButton.setBounds(900, 200, 200,200);
        deleteCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCardButton();
            }
        });
        frame.add(addCardButton);
        frame.add(practiceButton);
        frame.add(deleteCardButton);
        frame.setVisible(true);
    }

    public void addCardButton() {
        JTextField front = new JTextField(40);
        JTextField back = new JTextField(40);
        Object[] message = {
                "Front of the card", front,
                "Back of the card", back
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
        if (Main.cards.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You don't have any cards made yet!");
            return;
        }
        SwingUtilities.invokeLater(new PracticeGUI());
        frame.dispose();
    }

    public void deleteCardButton() {
        JTextField cardName = new JTextField(40);
        Object[] message = {
                "Front of the card", cardName
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
            String cardNameString = cardName.getText();
            Main.deleteCard(cardNameString);
        }
    }
}

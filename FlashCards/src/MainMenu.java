import javax.print.attribute.standard.JobKOctets;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static javax.swing.JOptionPane.showInputDialog;

public class MainMenu implements Runnable {

    JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("FlashCard Application");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(1440, 800);
        frame.setLayout(null);
        JButton addCardButton = new JButton("Add Card");
        addCardButton.setBounds(150, 200, 200, 200);
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
        practiceButton.setBounds(375, 200, 200, 200);
        JButton deleteCardButton = new JButton("Delete Card");
        deleteCardButton.setBounds(600, 200, 200, 200);
        deleteCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCardButton();
            }
        });
        JButton testButton = new JButton("Test!");
        testButton.setBounds(825, 200, 200, 200);
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.cards.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You don't have any cards made yet!");
                    return;
                }
                int numTest = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of cards you would like to be tested for"));
                if (numTest > Main.cards.size()) {
                    JOptionPane.showMessageDialog(null, "You do not have enough cards!");
                } else {
                    ArrayList<Card> shuffledDeck = Main.cards;
                    Collections.shuffle(shuffledDeck);
                    ArrayList<Card> testCards = new ArrayList<>();
                    for (int i = 0; i < numTest; i++) {
                        testCards.add(shuffledDeck.get(i));
                    }
                    SwingUtilities.invokeLater(new TestGUI(testCards));
                    frame.dispose();
                }
            }
        });
        JButton importCards = new JButton("Import Cards!");
        importCards.setBounds(1050, 200, 200, 200);
        importCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "To continue with this action, ensure your file" +
                        " has the front and back of the card seperated by a \"-\" character and ensure each card is seperated by" +
                        " a new line");
                if (option == JOptionPane.OK_OPTION) {
                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File userFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        File cardsFile = new File("Cards.txt");
                        try {
                            PrintWriter pw = new PrintWriter(new FileOutputStream(cardsFile, true));
                            BufferedReader bfr = new BufferedReader(new FileReader(userFile));
                            while (true) {
                                String line = bfr.readLine();
                                if (line == null || line.isEmpty()) {
                                    break;
                                }
                                pw.println(line);
                                pw.flush();
                                String[] cardData = line.split("-");
                                Main.cards.add(new Card(cardData[0], cardData[1]));
                            }
                            pw.close();
                            bfr.close();
                            JOptionPane.showMessageDialog(null, "Cards imported successfully!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }


            }
        });
        frame.add(importCards);
        frame.add(testButton);
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

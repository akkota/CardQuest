import javax.swing.*;

import java.awt.HeadlessException;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Card> cards = new ArrayList<>(); //Holds all the users cards in one static arraylist.

    public static void readCards() throws IOException {
        File f = new File("Cards.txt");
        if (!f.isDirectory()) {
            f.createNewFile();
        }
        try {
            BufferedReader cardsReader = new BufferedReader(new FileReader(f));
            while (true) {
                String line = cardsReader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] cardData = line.split("-");
                Card card = new Card(cardData[0], cardData[1]); //cardData[0] -> front, cardData[1] -> Back
                Main.cards.add(card);
            }
            cardsReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCard(Card card) {
        try {
            Main.cards.add(card);
            File f = new File("Cards.txt"); //Cards.txt contains data on the cards
            if (!f.isDirectory()) {
                f.createNewFile();
            }
            PrintWriter cardWriter = new PrintWriter(new FileOutputStream(f, true));
            cardWriter.println(card.getFront() + "-" + card.getBack());
            cardWriter.flush();
            JOptionPane.showMessageDialog(null, "Card Successfully added!");
            cardWriter.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void deleteCard(String front) {
        boolean found = false;
        Card x = null;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getFront().equals(front)) {
                found = true;
                x = new Card(cards.get(i).getFront(), cards.get(i).getBack());
                break;
            }
        }
        if (found) {
            try {
                for (int i = 0; i < cards.size(); i++) {
                    if (cards.get(i).getFront().equals(x.getFront()) && cards.get(i).getBack().equals(x.getBack())) {
                        cards.remove(cards.get(i));
                    }
                }
                File cardsFile = new File("Cards.txt");
                File tempFile = new File("temp1.txt");
                PrintWriter tempWriter = new PrintWriter(new FileOutputStream(tempFile, false));
                try (BufferedReader cardReader = new BufferedReader(new FileReader(cardsFile))) {
					while (true) {
					    if (cards.isEmpty()) { //edge case, if the cards arraylist is empty
					        PrintWriter cardWriter = new PrintWriter(new FileOutputStream(cardsFile, false));
					        cardWriter.print("");
					        tempFile.delete();
					        cardWriter.flush();
					        cardWriter.close();
					        JOptionPane.showMessageDialog(null, "Card successfully deleted!");
					        break;
					    }
					    String line = cardReader.readLine();
					    if (line == null || line.isEmpty()) {
					        break;
					    }
					    String[] cardData = line.split("-");
					    if (cardData[0].equals(x.getFront()) && cardData[1].equals(x.getBack())) {
					        continue;
					    } else {
					        tempWriter.println(cardData[0] + "-" + cardData[1]);
					        tempWriter.flush();
					    }
					    JOptionPane.showMessageDialog(null, "Card successfully deleted!");
					    tempFile.renameTo(cardsFile);
					    tempWriter.close();
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no such card!");
        }
    }

    public static void main(String[] args) throws IOException {
        readCards();
        SwingUtilities.invokeLater(new IntroGUI());
    }
}
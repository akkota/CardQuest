import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Card> cards = new ArrayList<>(); //Holds all the users cards in one static arraylist.

    public static void readCards() throws FileNotFoundException {
        File f = new File("Cards.txt");
        try {
            BufferedReader cardsReader = new BufferedReader(new FileReader(f));
            while (true) {
                String line = cardsReader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] cardData = line.split("=");
                Card card = new Card(cardData[0], cardData[1]); //cardData[0] -> front, cardData[1] -> Back
                Main.cards.add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCard(Card card) {
        try {
            Main.cards.add(card);
            File f = new File("Cards.txt"); //Cards.txt contains data on the cards
            PrintWriter cardWriter = new PrintWriter(new FileOutputStream(f, true));
            cardWriter.println(card.getFront() + "=" + card.getBack());
            cardWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readCards();
        SwingUtilities.invokeLater(new MainMenu());
    }
}
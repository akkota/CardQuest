import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticeGUI implements Runnable {

    JFrame frame;

    int current = 0;

    @Override
    public void run() {
        frame = new JFrame("Practice");
        frame.setSize(400, 400);
        frame.setLayout(null);
        JPanel startPanel = new JPanel();
        startPanel.setBounds(0, 0, 400, 400);
        JButton startButton = new JButton("Start");
        startPanel.add(startButton);
        JPanel practicePanel1 = new JPanel(new GridLayout(5,1));
        practicePanel1.setBounds(0, 0, 400, 400);
        JLabel frontPracticePanel1 = new JLabel();
        practicePanel1.add(frontPracticePanel1);
        JButton showButton = new JButton("Show");
        practicePanel1.add(showButton);
        JPanel practicePanel2 = new JPanel(new GridLayout(5, 1));
        practicePanel2.setBounds(0, 0, 400, 400);
        JLabel frontPracticePanel2 = new JLabel();
        practicePanel2.add(frontPracticePanel2);
        JLabel back = new JLabel();
        practicePanel2.add(back);
        JButton nextButton = new JButton("Next");
        practicePanel2.add(nextButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton(startPanel, practicePanel1, frontPracticePanel1);
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showButton(practicePanel1, practicePanel2, frontPracticePanel2, back);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButton(practicePanel1, practicePanel2, frontPracticePanel1);
            }
        });
        frame.add(startPanel);
        frame.add(practicePanel1);
        frame.add(practicePanel2);
        startPanel.setVisible(true);
        startButton.setVisible(true);
        practicePanel1.setVisible(false);
        practicePanel2.setVisible(false);
        frontPracticePanel1.setVisible(true);
        showButton.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void startButton(JPanel startPanel, JPanel practicePanel1, JLabel front) {
        startPanel.setVisible(false);
        practicePanel1.setVisible(true);
        front.setText(Main.cards.get(current).getFront());
    }

    private void showButton(JPanel practicePanel1, JPanel practicePanel2, JLabel front, JLabel back) {
        practicePanel1.setVisible(false);
        practicePanel2.setVisible(true);
        front.setText(Main.cards.get(current).getFront());
        back.setText(Main.cards.get(current).getBack());
        current++;
    }

    private void nextButton(JPanel practicePanel1, JPanel practicePanel2, JLabel front) {
        practicePanel1.setVisible(true);
        if (current == Main.cards.size()) {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "You've finished all cards!");
            SwingUtilities.invokeLater(new MainMenu());
        } else {
            front.setText(Main.cards.get(current).getFront());
            practicePanel2.setVisible(false);
        }
    }

}

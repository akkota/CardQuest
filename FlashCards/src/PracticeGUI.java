import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
        JPanel practicePanel = new JPanel(new GridLayout(5,1));
        practicePanel.setBounds(0, 0, 400, 400);
        JLabel front = new JLabel();
        practicePanel.add(front);
        JButton showButton = new JButton("Show");
        practicePanel.add(showButton);
        JPanel practicePanel2 = new JPanel(new GridLayout(5, 1));
        practicePanel2.setBounds(0, 0, 400, 400);
        JLabel front2 = new JLabel();
        practicePanel2.add(front2);
        JLabel back = new JLabel();
        practicePanel2.add(back);
        JButton nextButton = new JButton("Next");
        practicePanel2.add(nextButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton(startPanel, practicePanel, front);
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showButton(practicePanel, practicePanel2, front2, back);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButton(practicePanel, practicePanel2, front);
            }
        });
        frame.add(startPanel);
        frame.add(practicePanel);
        frame.add(practicePanel2);
        startPanel.setVisible(true);
        startButton.setVisible(true);
        practicePanel.setVisible(false);
        practicePanel2.setVisible(false);
        front.setVisible(true);
        showButton.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void startButton(JPanel startPanel, JPanel practicePanel, JLabel front) {
        startPanel.setVisible(false);
        practicePanel.setVisible(true);
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

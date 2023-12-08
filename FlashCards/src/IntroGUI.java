import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class IntroGUI implements Runnable {

    public static JFrame frame;
    @Override
    public void run() {
        frame = new JFrame("Welcome to CardQuest!");

        frame.setSize(960, 780);
        frame.setLayout(new FlowLayout());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel();
        label.setText("CardQuest!");
        label.setFont(new Font("Comic Sans", Font.PLAIN, 108));
        label.setHorizontalTextPosition(JLabel.CENTER);
        JButton startApplication = new JButton("Start Learning!"); 
        startApplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startApplication();
            }
        });
        frame.add(startApplication, BorderLayout.SOUTH);
        startApplication.setVisible(true);
        label.setIcon(new ImageIcon(getClass().getResource("/resources/logo.png")));
        frame.add(label);

        frame.setVisible(true);
    }

    public static void startApplication() {
        SwingUtilities.invokeLater(new MainMenu());
        frame.dispose();
    }

}

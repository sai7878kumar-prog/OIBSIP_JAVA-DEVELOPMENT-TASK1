import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GuessNumberGUI extends JFrame implements ActionListener {

    private int randomNumber, attemptsLeft = 7, score = 0;

    private JTextField inputField;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private JButton guessButton, resetButton;

    public GuessNumberGUI() {
        setTitle("🎯 Guess The Number");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(102, 126, 234));

        JLabel title = new JLabel("Guess a number (1-100)");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        inputField = new JTextField(10);

        guessButton = new JButton("Submit");
        resetButton = new JButton("Restart");

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.WHITE);

        attemptsLabel = new JLabel("Attempts Left: " + attemptsLeft);
        attemptsLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);

        add(title);
        add(inputField);
        add(guessButton);
        add(messageLabel);
        add(attemptsLabel);
        add(scoreLabel);
        add(resetButton);

        guessButton.addActionListener(this);
        resetButton.addActionListener(e -> resetGame());

        generateNumber();

        setVisible(true);
    }

    private void generateNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(inputField.getText());

            if (guess == randomNumber) {
                messageLabel.setText("🎉 Correct!");
                score += attemptsLeft * 10;
                scoreLabel.setText("Score: " + score);
                inputField.setEnabled(false);
            } else if (guess < randomNumber) {
                messageLabel.setText("📉 Too Low!");
            } else {
                messageLabel.setText("📈 Too High!");
            }

            attemptsLeft--;
            attemptsLabel.setText("Attempts Left: " + attemptsLeft);

            if (attemptsLeft == 0 && guess != randomNumber) {
                messageLabel.setText("❌ Game Over! Number: " + randomNumber);
                inputField.setEnabled(false);
            }

        } catch (Exception ex) {
            messageLabel.setText("⚠️ Enter valid number!");
        }
    }

    private void resetGame() {
        generateNumber();
        attemptsLeft = 7;
        messageLabel.setText("");
        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        inputField.setText("");
        inputField.setEnabled(true);
    }

    public static void main(String[] args) {
        new GuessNumberGUI();
    }
}
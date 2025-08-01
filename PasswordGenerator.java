import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PasswordGenerator {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PASSWORD GENERATOR");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Color neonGreen = new Color(57, 255, 20);
        JLabel title = new JLabel("PASSWORD GENERATOR", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(neonGreen);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // password lenght input
        JLabel lengthLabel = new JLabel("Password Length");
        lengthLabel.setForeground(neonGreen);
        lengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 32, 1));
        lengthSpinner.setMaximumSize(new Dimension(100, 30));
        lengthSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        // requirment checks
        JCheckBox lowercase = new JCheckBox("Include Lowercase (a-z)");
        JCheckBox uppercase = new JCheckBox("Include Uppercase (A-Z)");
        JCheckBox numbers = new JCheckBox("Include Numbers (0-9)");
        JCheckBox special = new JCheckBox("Include Special Characters (!@#...)");

        for (JCheckBox cb : new JCheckBox[] { lowercase, uppercase, numbers, special }) {
            cb.setForeground(neonGreen);
            ;
            cb.setBackground(Color.BLACK);
        }

        JButton generateBtn = new JButton("Generate Password");
        generateBtn.setBackground(neonGreen);
        ;
        generateBtn.setForeground(Color.BLACK);
        generateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //result box
        JTextField resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setMaximumSize(new Dimension(300, 30));
        resultField.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(lengthLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lengthSpinner);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lowercase);
        panel.add(uppercase);
        panel.add(numbers);
        panel.add(special);
        panel.add(Box.createVerticalStrut(20));
        panel.add(generateBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(resultField);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // generate button logic
        generateBtn.addActionListener(e -> {
            int length = (int) lengthSpinner.getValue();
            String chars = "";

            if (lowercase.isSelected())
                chars += "abcdefghijklmnopqrstuvwxyz";
            if (uppercase.isSelected())
                chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (numbers.isSelected())
                chars += "0123456789";
            if (special.isSelected())
                chars += "!@#$%&*()-_=+<>?/";

            if (chars.isEmpty()) {
                resultField.setText("Please select at least one option.");
                return;
            }

            StringBuilder password = new StringBuilder();
            Random rnd = new Random();
            for (int i = 0; i < length; i++) {
                password.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            resultField.setText(password.toString());
        });
    }
}


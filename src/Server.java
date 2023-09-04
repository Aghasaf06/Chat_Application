import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Server extends JFrame implements ActionListener {

    Server() {
        setLayout(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.CYAN);
        jPanel.setBounds(0, 0, 450, 70);
        jPanel.setLayout(null);
        add(jPanel);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image image1 = icon1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image1);
        JLabel back = new JLabel(imageIcon1);
        back.setBounds(5, 20, 25, 25);
        jPanel.add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/myProfile.jpg"));
        Image image2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image2);
        JLabel profile = new JLabel(imageIcon2);
        profile.setBounds(40, 10, 50, 50);
        jPanel.add(profile);
//28
        JLabel name = new JLabel("Aghasaf");
        name.setBounds(110, 15, 100, 25);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        jPanel.add(name);

        JLabel status = new JLabel("Online");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        jPanel.add(status);

        JPanel a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        add(a1);

        JTextField textField = new JTextField();
        textField.setBounds(5, 655, 310, 40);
        textField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(textField);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(Color.CYAN);
        send.setForeground(Color.WHITE);
        textField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(send);

        setSize(450, 700);
        setLocation(200, 50);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Server();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

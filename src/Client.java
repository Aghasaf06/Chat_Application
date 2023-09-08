import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client implements ActionListener {

    JTextField textField;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame frame = new JFrame();
    static DataOutputStream dataOutputStream;

    Client() {
        frame.setLayout(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.CYAN);
        jPanel.setBounds(0, 0, 600, 70);
        jPanel.setLayout(null);
        frame.add(jPanel);

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

        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("images/4x3.jpg"));
        Image image2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image2);
        JLabel profile = new JLabel(imageIcon2);
        profile.setBounds(40, 10, 50, 50);
        jPanel.add(profile);

        JLabel name = new JLabel("Ramal Maharramov");
        name.setBounds(110, 15, 200, 25);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        jPanel.add(name);

        JLabel status = new JLabel("Online");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        jPanel.add(status);

        a1 = new JPanel();
        a1.setBounds(10, 75, 580, 570);
        frame.add(a1);

        textField = new JTextField();
        textField.setBounds(10, 655, 460, 40);
        textField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frame.add(textField);

        JButton send = new JButton("Send");
        send.setBounds(470, 655, 120, 40);
        send.setBackground(Color.CYAN);
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 24));
        textField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frame.add(send);

        frame.setSize(600, 700);
        frame.setLocation(800, 50);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(97, 93, 93));

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String out = textField.getText();


        JPanel p2 = formatLabel(out);

        a1.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        a1.add(vertical, BorderLayout.PAGE_START);

        try {
            dataOutputStream.writeUTF(out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        textField.setText("");

        frame.repaint();
        frame.invalidate();
        frame.validate();
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(Color.CYAN);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(simpleDateFormat.format(calendar.getTime()));

        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Client();

        try {
            Socket socket = new Socket("127.0.0.1", 6001);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                a1.setLayout(new BorderLayout());
                String message = dataInputStream.readUTF();
                JPanel panel = formatLabel(message);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                frame.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
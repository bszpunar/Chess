package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ViewWHITE extends JFrame{

    public int portView;
    public String address;
    public String ip;
    InetAddress inetAddress;
    BoardWHITE szachownica;



    public ViewWHITE(){
        super("Chess WHITE");

        setResizable(false);
        setBounds(200,50,420,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        Color bezowy = new Color(245,222,179);
        Color brazowy = new Color(139,69,19);
        Color czarny = new Color(0,0,0);
        Color bialy = new Color(255,255,255);
        Color limona = new Color(0,255,0);
        Color blue = new Color(0,0,255);

        JLabel jLabel1 = new JLabel("Address:");
        jLabel1.setBounds(50,100,100,30);
        jLabel1.setForeground(bezowy);
        JLabel jLabel2 = new JLabel("Port:");
        jLabel2.setBounds(50,130,100,30);
        jLabel2.setForeground(bezowy);
        JLabel jLabel3 = new JLabel("Let's Play CHESS");
        jLabel3.setBounds(150,10,100,40);
        jLabel3.setForeground(bezowy);

        try {
            inetAddress = InetAddress.getLocalHost();
            ip = "Local IP: "+inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        JLabel jLabel4 = new JLabel(ip);
        jLabel4.setBounds(200,350,150,30);
        jLabel4.setForeground(bezowy);

        JButton jButton1 = new JButton("Create GAME");
        jButton1.setBackground(bezowy);
        jButton1.setBounds(50,50,150,30);

        JButton jButton4 = new JButton("Back");
        jButton4.setBackground(bezowy);
        jButton4.setBounds(50,350,100,30);


        JButton jButton2 = new JButton("Join GAME");
        jButton2.setBackground(bezowy);
        jButton2.setBounds(200,50,150,30);


        JTextArea jTextArea1 = new JTextArea();
        jTextArea1.setBounds(120,105,150,18);
        jTextArea1.setBackground(bezowy);

        JTextArea jTextArea2 = new JTextArea();
        jTextArea2.setBounds(120,135,150,18);
        jTextArea2.setBackground(bezowy);

        JPanel jPanel = new JPanel(null);


        jPanel.setBackground(czarny);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jTextArea1);
        jPanel.add(jTextArea2);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jButton4);
        jPanel.add(jLabel4);





        jTextArea2.setText("866");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portView = Integer.parseInt(jTextArea2.getText());
                if(portView > 0){
                    szachownica = new BoardWHITE(portView);
                    setContentPane(szachownica);
                    revalidate();
                }

            }
        });


        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                address = jTextArea1.getText();
                portView = Integer.parseInt(jTextArea2.getText());

                if(portView > 0){
                    
                    if(address.isEmpty()){
                        address = "localhost";
                    }
                    szachownica = new BoardWHITE(address, portView);
                    setContentPane(szachownica);
                    revalidate();

                }

            }
        });

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameStart();
                setVisible(false);
            }
        });


        setContentPane(jPanel);


        //szachownica.pokazDane();


        Image logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png")).getImage();
        setIconImage(logo);


        setVisible(true);
    }


    public static void main(String[] args) {
        new ViewWHITE();
    }

}

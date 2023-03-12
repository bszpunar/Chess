package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class GameStart extends JFrame {

    public GameStart(){
        super("Chess");

        setResizable(false);
        setBounds(600,50,420,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        Color bezowy = new Color(245,222,179);
        Color brazowy = new Color(139,69,19);
        Color czarny = new Color(0,0,0);
        Color bialy = new Color(255,255,255);
        Color limona = new Color(0,255,0);
        Color blue = new Color(0,0,255);

        JLabel jLabel3 = new JLabel("Let's Play CHESS");
        jLabel3.setBounds(150,10,100,40);
        jLabel3.setForeground(bezowy);

        JButton jButton1 = new JButton("Play");
        jButton1.setBackground(bezowy);
        jButton1.setBounds(120,80,150,30);


        JButton jButton2 = new JButton("Exit");
        jButton2.setBackground(bezowy);
        jButton2.setBounds(120,180,150,30);

        JButton jButton3 = new JButton("Options");
        jButton3.setBackground(bezowy);
        jButton3.setBounds(120,130,150,30);

        Checkbox checkbox1 = new Checkbox("White");
        checkbox1.setBounds(320,20,60,20);
        checkbox1.setForeground(bezowy);
        Checkbox checkbox2 = new Checkbox("Black");
        checkbox2.setBounds(320,40,60,20);
        checkbox2.setForeground(bezowy);



        JPanel jPanel = new JPanel(null);


        jPanel.setBackground(czarny);
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(checkbox1);
        jPanel.add(checkbox2);


        jPanel.add(jLabel3);



        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //play
                if(checkbox1.getState() && !checkbox2.getState()){
                    new ViewWHITE();
                    setVisible(false);
                }else if(checkbox2.getState() && !checkbox1.getState()){
                    new ViewBLACK();
                    setVisible(false);
                }




            }
        });


        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //exit
                System.exit(0);
                setVisible(false);


            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //options
                //setContentPane();
                revalidate();

            }
        });

        checkbox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(checkbox1.getState() == true){
                    checkbox2.setState(false);
                }
            }
        });

        checkbox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(checkbox2.getState() == true){
                    checkbox1.setState(false);
                }
            }
        });




        setContentPane(jPanel);


        //szachownica.pokazDane();


        Image logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png")).getImage();
        setIconImage(logo);


        setVisible(true);




    }

    public static void main(String[] args) {

        new GameStart();

    }


}

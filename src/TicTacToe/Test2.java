package TicTacToe;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.*;

public class Test2 extends JFrame {
    
  //  JLabel marksP1 = new JLabel("Hellow########################");
    private static Support sup;

    public Test2(Support sup) {
      //  this.getContentPane().add(marksP1);
       
        setTitle("Test2");
        setSize(500,500);
        setBackground(Color.WHITE);
        add(new Single(sup));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                Test2 test2 = new Test2(sup);
                test2.setExtendedState(Frame.MAXIMIZED_BOTH);
                test2.setUndecorated(true);
                test2.setVisible(true);
            }
        });
    }

}

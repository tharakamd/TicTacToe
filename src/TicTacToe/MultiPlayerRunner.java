/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TicTacToe;

/**
 *
 * @author tharaka
 */
import java.awt.Color;
import java.awt.Frame;
import javax.swing.*;

public class MultiPlayerRunner extends JFrame {
     private static Support sup;
     public MultiPlayerRunner(Support sup) {
      //  this.getContentPane().add(marksP1);
        setTitle("Test2");
        setSize(500,500);
        setBackground(Color.WHITE);
        add(new Draw(sup));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                MultiPlayerRunner test2 = new MultiPlayerRunner(sup);
                test2.setExtendedState(Frame.MAXIMIZED_BOTH);
                test2.setUndecorated(true);
                test2.setVisible(true);
            }
        });
    } 
}

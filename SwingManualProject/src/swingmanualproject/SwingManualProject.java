/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swingmanualproject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author less
 */
public class SwingManualProject extends JFrame {
    
    SwingManualProject(String name){
        super(name);
        
        setPreferredSize(new Dimension(400, 300));
        
        JPanel panel = new JPanel();
        add(panel);
        
        JButton button1 = new JButton("Click me");
        button1.setBackground(Color.GREEN);
        button1.setForeground(Color.YELLOW);
        panel.add(button1);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingManualProject frame = new SwingManualProject("My first Swing window!");
    }
    
}

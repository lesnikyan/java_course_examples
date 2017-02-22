/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swinglayouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.*;

/**
 *
 * @author Less
 */
public class SwingLayouts extends JFrame {
	
	SwingLayouts(String name){
		super(name);
		
	//	setSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(400, 300));
		setBounds(300, 200, 400, 300);
		
		boolean isGrid = true;
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		if(isGrid)	
			panel.setLayout(new GridLayout(3,4));
	
		add(panel);
		
		if(! isGrid){
			JButton butt1 = new JButton("Button 1");
			butt1.setPreferredSize(new Dimension(100, 30));
			panel.add(butt1);

			JToggleButton butt2 = new JToggleButton("Button 2");
			butt2.setPreferredSize(new Dimension(250, 30));
			panel.add(butt2);

			JLabel label1 = new JLabel();
			label1.setText("Label with wide text");
			label1.setPreferredSize(new Dimension(400, 50));
		//	label1.setMinimumSize(new Dimension(200, 20));
			panel.add(label1);
		}
		
		for (int i = 9; i >= 0; i--) {
			JButton butt = new JButton(new Integer(9-i).toString());
			panel.add(butt);
		}
		JButton buttAdd = new JButton("+");
		buttAdd.setForeground(Color.red);
		panel.add(buttAdd);
		JButton buttMinus = new JButton("-");
		buttMinus.setForeground(Color.blue);
		Font minusFont = new Font(Font.SERIF, Font.BOLD, 20);
		
		buttMinus.setFont(minusFont);
		panel.add(buttMinus);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SwingLayouts frame = new SwingLayouts("Layouts");
	}
	
}

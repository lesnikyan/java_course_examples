/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tswing;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Less
 */
public class TSwing extends JFrame {
	
	private final ArrayList<Point<Integer>> points;
	private int pointCounter = 2000;
	{
		points = new ArrayList<>(pointCounter);
	}
	
	TSwing(String title){
		super(title);
		setBackground(Color.RED);
		int w = 400;
		int h = 300;
		//setSize(w, h);
		setPreferredSize(new Dimension(w, h));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Random r = new Random();
		int min = 20;
		int xMax = w - min * 2;
		int yMax = h - min * 3;
		
		for(int i=0; i< pointCounter; ++i){
			points.add(new Point<Integer>(
				min + r.nextInt(xMax),
				min*2 + r.nextInt(yMax)
			));
		}
		pack();
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
		//setBackground(Color.yellow);
		final ArrayList<Point<Integer>> points = this.points;
		g.setColor(new Color(0xFF8844));
		g.fillRect(40, 60, 320, 200);
		int pointSize = 5;
		double colStep = 255.0 / pointCounter;
		double colVal = 0;
		int otherColors = 0;
		
		for(Point<Integer> point : points){
			int colorNum = (int) Math.min(Math.floor(colVal), 255);
			g.setColor(new Color( otherColors, colorNum, otherColors));
			g.fillRect(point.x, point.y, pointSize, pointSize);
			colVal += colStep;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// make new window
		TSwing app = new TSwing("test swing app");
		System.out.printf ("%d ", app.pointCounter);
	}
	
	class Point<T extends Number>{
		T x;
		T y;
		Point(T a, T b){
			x=a;
			y=b;
		}
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadsimple;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Less
 */
public class ProcessIndicator implements Runnable {
	
	JProgressBar bar; 
	JButton button;
	JLabel label;
	SourceThread source;

	ProcessIndicator(SourceThread source){
		this.source = source;
	}
	
	public void setInterface(JProgressBar bar, JButton button, JLabel label){
		this.bar = bar;
		this.button = button;
		this.label = label;
	}
	
	@Override
	public void run() {
		double status = 0;
		button.setEnabled(false);
		while(status < 1){
			status = source.getStatus();
			//System.out.print("; " + status + " ");
			int statValue = (int)(status * 100);
			bar.setValue(statValue);
			label.setText("Done: " + statValue + " %");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				//Logger.getLogger(ProcessIndicator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		button.setEnabled(true);
	}
	
}

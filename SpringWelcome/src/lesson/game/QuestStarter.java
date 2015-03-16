/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lesson.game;

/**
 *
 * @author Less
 */
public class QuestStarter {
	
	private QuestStarter(){
		
	}
	
	public static QuestStarter getInstance(){
		return new QuestStarter();
	}
	
	public void start(){
		System.out.println("QuestStarter: Start some quest...");
	}
	
}

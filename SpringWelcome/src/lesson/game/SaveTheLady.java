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
public class SaveTheLady implements Quest {
	
	protected String name;
	
	@Override
	public void init(){
		System.out.println("Prepare quest of Princess...");
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void execute(){
		System.out.println("Quest '" + name + "' started.");
	}
	
}

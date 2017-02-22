/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BotsGame;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Less
 */
public class BotGroup {
	
	private final List<Bot> units = new ArrayList<>();
	
	public BotGroup(){
		
	}

	/**
	 * @param Bot unit
	 */
	public void addUnit(Bot unit) {
		units.add(unit);
	}
	
	public Bot getUnit(int index) throws GameException{
		if(index > units.size() - 1){
			throw new GameException("Index more than list");
		}
		return units.get(index);
	}

	/**
	 * 
	 * @param index
	 * @param skill
	 * @param success - percents of damage success
	 */
	public void attackUnit(int index, AttackSkill skill, double success) {
		units.get(index).damage(skill, success);
	}
	
	public int activeBotsCount(){
		int num = 0;
		//num = units.stream().map((unit) -> (unit.getHealth() > 0) ? 1 : 0).reduce(num, Integer::sum);
		for(Bot unit: units){
			num += unit.getHealth() > 0 ? 1 : 0;
		}
		return num;
	}
	
	public String printStatus(){
		StringBuilder res = new StringBuilder();
		for(Bot unit: units){
			res.append(String.format("\t %1$s %2$.1f:%3$.1f %4$s", (unit.isActive() ? "(" : "["), 
					unit.getHealth(), unit.getResistPower(), (unit.isActive() ? ")" : "]")));
		}
		return res.toString();
	}
	
}

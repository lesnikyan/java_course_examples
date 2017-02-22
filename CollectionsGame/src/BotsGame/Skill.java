/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BotsGame;

/**
 *
 * @author Less
 */
public class Skill {
	
	private double power;
	private SkillType type;
	
	Skill(double p, SkillType st){
		power = p;
		type = st;
	}

	/**
	 * @return the power
	 */
	public double getPower() {
		return power;
	}

	/**
	 * @return the type
	 */
	public SkillType getType() {
		return type;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(double power) {
		this.power = power;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SkillType type) {
		this.type = type;
	}
	
}

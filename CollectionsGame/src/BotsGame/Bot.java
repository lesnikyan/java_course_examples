
package BotsGame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class Bot {
	private double health; // 0-100
	private AttackSkill attack; // 0-10
	private ResistSkill resist; // 0-10
	
	private static final Logger log = Logger.getLogger(Bot.class.getName());
	
	public Bot(double h, AttackSkill att, ResistSkill res){
		health = h;
		attack = att;
		resist = res;
	}

	/**
	 * @return the health
	 */
	public Double getHealth() {
		return health;
	}
	
	public boolean isActive(){
		return health > 0;
	}

	/**
	 * @return the attack
	 */
	public AttackSkill getAttack() {
		return attack;
	}
	
	public Double getResistPower(){
		return resist.getPower();
	}
	
	public void riseAttackSkill(double ratio){
		attack.setPower(ratio * 0.01 + attack.getPower());
		
	}
	
	public void riseResistSkill(double ratio){
		double newPower = ratio * 0.01 + resist.getPower();
		//log.log(Level.INFO, String.format("ratio = %g; newPower = %g", ratio, newPower));
		resist.setPower(newPower);
	}
	
	/**
	 * getting attack from another bot
	 * 
	 * @param skill
	 * @param success : 0-1
	 * @return - realDamage of attack
	 */
	public double damage(AttackSkill skill, double success){
		if(skill.getType().equals(resist.getType())){
			success = success - resist.getPower() / 10;
			success = success < 0 ? 0 : success;
		}
		double realDamage = skill.getPower() * success;
		health -= realDamage;
		if(health < 0){
			health = 0;
		}
	//	log.log(Level.INFO, String.format("succ = %g; dam = %g; res = %g", success, realDamage, resist.getPower()));
		return realDamage;
	}
	
}

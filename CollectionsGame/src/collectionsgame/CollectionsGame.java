
package collectionsgame;

import BotsGame.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class CollectionsGame {
	
	BotGroup groupA;
	BotGroup groupB;
	Map<String, BotGroup> groups = new HashMap<>();
	List<String> groupNames = Arrays.asList("A", "B");
	int groupSize = 10;
	SkillType[] skillSet;
	double maxDamage = 10.0;
	
	private static final Logger log = Logger.getLogger(CollectionsGame.class.getName());
	
	{
		skillSet = SkillType.values();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		CollectionsGame game = new CollectionsGame();
		game.init();
		try {
			game.run();
		} catch (GameException ex) {
			log.warning(ex.getMessage());
		}
	}
	
	public void init(){
		groupA = initGroup(groupSize);
		groupB = initGroup(groupSize);
		groups.put("A", groupA);
		groups.put("B", groupB);
	}
	
	public BotGroup initGroup(int num){
		BotGroup group = new BotGroup();
		Random r = new Random();
		for(int i = 0; i < num; ++i){
			AttackSkill attSkill = new AttackSkill(maxDamage / 2, skillSet[r.nextInt(skillSet.length - 1)]);
			ResistSkill resSkill = new ResistSkill(maxDamage / 5, skillSet[r.nextInt(skillSet.length - 1)]);
			group.addUnit( new Bot(100.0, attSkill, resSkill) );
		}
		
		return group;
	}
	
	public void run() throws GameException{
		while(groups.get("A").activeBotsCount() > 0 && groups.get("B").activeBotsCount() > 0){
			step();
			p("A: " + groups.get("A").printStatus());
			p("B: " + groups.get("B").printStatus());
			p("---");
		}
	}
	
	public void step() throws GameException{
		Random r = new Random();
		
		int firstAttakerNameIndex = r.nextInt(1);
		int secondAttakerNameIndex = Math.abs(1 - firstAttakerNameIndex);
		BotGroup firstAttaker = groups.get(groupNames.get(firstAttakerNameIndex));
		BotGroup secondAttaker = groups.get(groupNames.get(secondAttakerNameIndex));
			
		for(int i=0; i < groupSize; ++i){
			// A attacks B
			Bot attacker;
			Bot target;
		
			attacker = firstAttaker.getUnit(i); // последовательный юнит команды 1
			target = secondAttaker.getUnit(r.nextInt(groupSize)); // случайный юнит команды 2
			fight(attacker, target);
			
			// B attaks A
			attacker = secondAttaker.getUnit(i); // последовательный юнит команды 2
			target = firstAttaker.getUnit(r.nextInt(groupSize)); // случайный юнит команды 1
			fight(attacker, target);
		}
	}
	
	public void fight(Bot attacker, Bot target){
		if(!attacker.isActive() || !target.isActive()){
			return;
		}
		double attackSuccess = new Random().nextDouble() * 0.5 + 0.5;
		double attPower = attacker.getAttack().getPower();
		double resavedDamage = target.damage(attacker.getAttack(), attackSuccess);
		
		if(resavedDamage > maxDamage * 0.8){
			attacker.riseAttackSkill(resavedDamage);
		} else if (resavedDamage < maxDamage * 0.5){
			target.riseResistSkill(maxDamage - resavedDamage);
		}
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}

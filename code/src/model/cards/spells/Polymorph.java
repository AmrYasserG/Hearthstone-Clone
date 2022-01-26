package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {

	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}
	public void performAction(Minion m) throws InvalidTargetException {
		m.setAttack(1);
		m.setCurrentHP(1);
		m.setManaCost(1);
		m.setMaxHP(1);
		m.setTaunt(false);
		m.setSleeping(true);
		m.setDivine(false);
		m.setName("Sheep");
		//Minion m1 = new Minion("Sheep",1, m.getRarity(), 1,
				//1,false, false, false);
		//m=m1 ;
	}

}

package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
		

	}
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField){
		int i = oppField.size()-1;
		int j = curField.size()-1;
		while(i>0){
			 i = oppField.size()-1;
			(oppField.get(0)).setCurrentHP(0);
		}
		while(j>0){
			 j = curField.size()-1;
			(curField.get(0)).setCurrentHP(0);
		
		}
	}

}

package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
	}

	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField){
		int i =0 ;
		while( i<oppField.size() ){
			if (oppField.get(i).isDivine()==true){
			oppField.get(i).setDivine(false);
			i++ ;
			}
			else{
				int health = oppField.get(i).getCurrentHP()-4 ;
				if(health == 0 ){
					(oppField.get(i)).setCurrentHP((oppField.get(i)).getCurrentHP()-4);
				}
				else{
				(oppField.get(i)).setCurrentHP((oppField.get(i)).getCurrentHP()-4);
				i++;
				}
		}
	}
}
}
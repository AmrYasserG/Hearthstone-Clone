package model.cards.spells;

import java.util.ArrayList;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{

	public MultiShot() {
		super("Multi-Shot", 4,Rarity.BASIC);
		
	}
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField){
		if(oppField.size()==1){
			if (oppField.get(0).isDivine()==true)
				oppField.get(0).setDivine(false);
				else
			oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP()-3);
		}
		else {
			if(oppField.size()==2){
				if (oppField.get(0).isDivine()==true)
					oppField.get(0).setDivine(false);
					else
				oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP()-3);
				if (oppField.get(1).isDivine()==true)
					oppField.get(1).setDivine(false);
					else
				oppField.get(1).setCurrentHP(oppField.get(1).getCurrentHP()-3);		
			}
			else{
				if(oppField.size()>2){
					boolean f = false ;
					int i=0 ; 
					int index =0 ;
					ArrayList<Integer> accured = new ArrayList<Integer>() ;
					while (i<2){
						f=false ;
					 index = (int) (Math.random() * oppField.size());
					 if(accured.size()>0){
					for(int j = 0 ; j<accured.size() ; j++){
						if (accured.get(j)==index)
							f=true ;
					}
					 }
					if(f==false){
					if (oppField.get(index).isDivine()==true)
						oppField.get(index).setDivine(false);
						else
					 oppField.get(index).setCurrentHP(oppField.get(index).getCurrentHP()-3);
					accured.add(index);
					 i++ ;
				}
				}
				}
			}
		}
			
	}

}

package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {

	public Priest() throws IOException, CloneNotSupportedException {
		super("Anduin Wrynn");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new DivineSpirit());
			getDeck().add(new HolyNova());
			getDeck().add(new ShadowWordDeath());
		}
		Minion velen=new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
		
		getDeck().add(velen.clone());
		Collections.shuffle(getDeck());
		helperBuildDeack();

	}
	public void useHeroPower(Hero h) throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		boolean fProphet =false ;
		super.useHeroPower();
		for(int i = 0 ; i<this.getField().size();i++){
        	if(this.getField().get(i).getName().equals("Prophet Velen")){
        		fProphet = true ;
        	}
		}
		if(fProphet == true)
			h.setCurrentHP(h.getCurrentHP()+8);
		else
			h.setCurrentHP(h.getCurrentHP()+2);
			
		
		
	}
	public void useHeroPower(Minion m) throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		boolean fProphet =false ;
		super.useHeroPower();
		for(int i = 0 ; i<this.getField().size();i++){
        	if(this.getField().get(i).getName().equals("Prophet Velen")){
        		fProphet = true ;
        	}
		}
		if(fProphet == true)
			m.setCurrentHP(m.getCurrentHP()+8);
		else
			m.setCurrentHP(m.getCurrentHP()+2);
	}
	

}

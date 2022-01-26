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
import model.cards.spells.Flamestrike;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;

public class Mage extends Hero {

	public Mage() throws IOException, CloneNotSupportedException {
		super("Jaina Proudmoore");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new Polymorph());
			getDeck().add(new Flamestrike());
			getDeck().add(new Pyroblast());
		}
		Minion kalycgos = (new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false));
		;
		getDeck().add(kalycgos.clone());
		Collections.shuffle(getDeck());
		helperBuildDeack();

	}
	public void useHeroPower(Minion minion) throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		if(minion.isDivine()==true)
			minion.setDivine(false);
		else
		minion.setCurrentHP(minion.getCurrentHP()-1);
	}
	
	public void useHeroPower(Hero hero) throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.getListener().damageOpponent(1);
		
	}

	

}

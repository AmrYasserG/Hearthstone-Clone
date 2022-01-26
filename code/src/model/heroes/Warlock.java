package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(
				getAllNeutralMinions("neutral_minions.csv"), 13);
		getDeck().addAll(neutrals);
		for (int i = 0; i < 2; i++) {
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY,
				4, 4, false, false, false);
		getDeck().add(wilfred.clone());
		Collections.shuffle(getDeck());
		helperBuildDeack();

	}

	public void useHeroPower() throws NotEnoughManaException,
			HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Card c = null;
		c = this.drawCard();
		boolean fWilfred = false;
		boolean fChromaggus = false;
		for (int i = 0; i < this.getField().size(); i++) {
			if (this.getField().get(i).getName().equals("Wilfred Fizzlebang"))
				fWilfred = true;
			if (this.getField().get(i).getName().equals("Chromaggus")) 
				fChromaggus = true;
			}
			if (fWilfred == true && c instanceof Minion)
				c.setManaCost(0);
			if (fChromaggus == true && this.getHand().size() < 10) { // &&!this.getName().equals("Gul'dan")){
				Card c1 = c.clone();
				this.getHand().add(c1);
			}
				// this.getListener().damageOpponent(2);
				this.setCurrentHP(this.getCurrentHP() - 2);
			

		}
	}


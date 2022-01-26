package engine;
import java.util.ArrayList;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;




import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator ,HeroListener{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException
	{
		this.setListener(listener);
		firstHero=p1;
		secondHero=p2;		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		currentHero.setListener(this);
		currentHero.setValidator(this);
		opponent.setListener(this);
		opponent.setValidator(this);
		for (int i = 0; i < 3; i++) {
			currentHero.drawCard();
		}
		for (int i = 0; i < 4; i++) {
			opponent.drawCard();
		}

		
	}
	public void validateTurn(Hero user) throws NotYourTurnException {

		if (!(currentHero.equals(user))) {

			throw new NotYourTurnException("not Your turn ");
		}
	}

	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException,
			TauntBypassException, InvalidTargetException {
		if (attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack()==0) {
			throw new CannotAttackException("can NOT attack");
		}
		boolean check2 = false;
		for (int i = 0; i < currentHero.getField().size(); i++) {

			if (currentHero.getField().get(i).equals(attacker)) {
				check2 = true;
			}
		}
		if (check2 == false) {

			throw new NotSummonedException("not summoned ");
		}


		boolean check3 = false;
		for (int i = 0; i < currentHero.getField().size(); i++) {

			if (currentHero.getField().get(i).equals(target)) {
				check3 = true;
			}
		}
		if (check3 == true) {

			throw new InvalidTargetException("Wrong target");
		}
		boolean check = false;
		for (int i = 0; i < opponent.getField().size(); i++) {

			if (opponent.getField().get(i).equals(target)) {
				check = true;
			}
		}
		if (check == false) {

			throw new NotSummonedException("target not on the field");
		}

		if(!(target.isTaunt())){
		boolean check4 = false;
		for (int i = 0; i < opponent.getField().size(); i++) {

			if (opponent.getField().get(i).isTaunt() == true) {
				check4 = true;
			}
		}
		if (check4 == true) {
			throw new TauntBypassException("taunt target");
		}
	}
	}

	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException,
			TauntBypassException, InvalidTargetException {
		if (attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack()==0) {
			throw new CannotAttackException("can NOT attack");
		}
			boolean check2 = false;
			for (int i = 0; i < currentHero.getField().size(); i++) {

				if (currentHero.getField().get(i).equals(attacker)) {
					check2 = true;
				}
			}
			if (check2 == false) {

				throw new NotSummonedException("not summoned ");
			}
			
			boolean check4 = false;
			for (int i = 0; i < opponent.getField().size(); i++) {

				if (target.getField().get(i).isTaunt() == true) {
					check4 = true;
				}
			}
			if (check4 == true) {
				throw new TauntBypassException("taunt target");
			}
			if (attacker instanceof Icehowl) {
				throw new InvalidTargetException("Icehowl cannot attack a hero");
			}
		}

	

	public void validateManaCost(Card card) throws NotEnoughManaException {
		if(currentHero.getCurrentManaCrystals()<card.getManaCost()){
			throw new NotEnoughManaException("No Enough Mana");
		}
	}
	public void validatePlayingMinion(Minion minion) throws FullFieldException{
		if(currentHero.getField().size()>6){
			throw new FullFieldException("full field");
		}
		
	}

	public void validateUsingHeroPower(Hero hero)
			throws NotEnoughManaException, HeroPowerAlreadyUsedException{
		if(currentHero.getCurrentManaCrystals()<2){
			throw new NotEnoughManaException("No Enough Mana");
		}
		if(currentHero.isHeroPowerUsed()){
			throw new HeroPowerAlreadyUsedException("power used");
		}
		
	}

	
	
	public void onHeroDeath(){
		listener.onGameOver() ;
		
	}
	
	public void damageOpponent(int amount){
		opponent.setCurrentHP(opponent.getCurrentHP() - amount);
	
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		Hero temp = null;
		temp  = opponent;
		opponent = currentHero ;
		currentHero = temp ;
		currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		currentHero.setHeroPowerUsed(false);
		//ArrayList<Minion> temp1 = currentHero.getField();
		for(int i = 0 ; i<currentHero.getField().size() ; i++){
					if(currentHero.getField().get(i) instanceof Minion){
						(currentHero.getField().get(i)).setSleeping(false);
						(currentHero.getField().get(i)).setAttacked(false);
					}
				}
		//for(int i = 0 ; i<(temp1).size() ; i++){
		//	if(temp1.get(i) instanceof Minion){
		//		(temp1.get(i)).setSleeping(false);
		//	}
		//}
		
		currentHero.drawCard() ;
		
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}


	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}

	
	
	

}

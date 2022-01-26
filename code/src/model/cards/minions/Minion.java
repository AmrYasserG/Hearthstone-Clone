 package model.cards.minions;

import exceptions.InvalidTargetException;
import model.heroes.*;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion(String name, int manaCost, Rarity rarity, int attack,
			int maxHP, boolean taunt, boolean divine, boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}

	public void attack(Hero target) throws InvalidTargetException {
		if (!this.getName().equals("Icehowl")) {

			if (sleeping == false && attacked == false) {
				target.setCurrentHP(target.getCurrentHP() - this.getAttack());
				this.setAttacked(true);
				this.setSleeping(true);
			}
		}
		else{
			throw new InvalidTargetException("Icehowl cannot attack a hero");
		}
	}

	public Minion clone() throws CloneNotSupportedException {
		Minion ml = (Minion) super.clone();
		return ml;
	}

	public void attack(Minion target) { 
		if (this.isSleeping() == false && this.isAttacked() == false) {
			if (target.isDivine() == true && this.isDivine() == true) {
				if(this.getAttack()>0)
				target.setDivine(false);
				if(target.getAttack()>0)
				this.setDivine(false);
				this.setAttacked(true);
				this.setSleeping(true);
			} else {
				if (target.isDivine() == true && this.isDivine() == false) {
					if(this.getAttack()>0)
					target.setDivine(false);
					this.setCurrentHP(this.getCurrentHP() - target.getAttack());
					this.setAttacked(true);
					this.setSleeping(true);
				} else {
					if (target.isDivine() == false && this.isDivine() == true) {
						if(target.getAttack()>0)
						this.setDivine(false);
						
						this.setCurrentHP(this.getCurrentHP()
								- target.getAttack());
						this.setAttacked(true);
						this.setSleeping(true);
					} else {
						target.setCurrentHP(target.getCurrentHP()
								- this.getAttack());
						this.setCurrentHP(this.getCurrentHP()
								- target.getAttack());
						this.setAttacked(true);
						this.setSleeping(true);
					}
				}
			}
		}
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onMinionDeath(this);

		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}

}

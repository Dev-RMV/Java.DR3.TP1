package br.com.devrmv.model;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import lombok.Getter;

@Getter
public class Orc implements ICharacter {
	int hitPoints = 20;
	int str = 6;
	int agi = 2;
	int def = 2;

	@Override
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}


	@Override
	public int damage() {
        return CombatCalculations.d8() + str;
	}

}

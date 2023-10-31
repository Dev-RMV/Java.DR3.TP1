package br.com.devrmv.model;

import br.com.devrmv.combatCalculations.CombatCalculations;
import br.com.devrmv.interfaces.ICharacter;
import lombok.Getter;

@Getter
public class Fighter implements ICharacter {
	int hitPoints = 12;
	int str = 4;
	int agi = 3;
	int def = 3;

	@Override
	public int damage() {
        return CombatCalculations.d4() + CombatCalculations.d4() + str;
	}

	@Override
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
}

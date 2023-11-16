package br.com.devrmv;

import br.com.devrmv.combatCalculations.CombatCalculations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CombatCalculationsTests {
    @Test
    @DisplayName("d10Test Valor deve estar entre 1 e 10 - inclusive")
    public void d10Test(){
        int result= CombatCalculations.d10();
        boolean testOk= result >= 1 && result <= 10;
        assertTrue(testOk);
    }
    @Test
    @DisplayName("d8Test Valor deve estar entre 1 e 8 - inclusive")
    public void d8Test(){
        int result= CombatCalculations.d8();
        boolean testOk= result >= 1 && result <= 8;
        assertTrue(testOk);
    }
    @Test
    @DisplayName("d6Test Valor deve estar entre 1 e 6 - inclusive")
    public void d6Test(){
        int result= CombatCalculations.d6();
        boolean testOk= result >= 1 && result <= 6;
        assertTrue(testOk);
    }
    @Test
    @DisplayName("d4Test Valor deve estar entre 1 e 4 - inclusive")
    public void d4Test(){
        int result= CombatCalculations.d4();
        boolean testOk= result >= 1 && result <= 4;
        assertTrue(testOk);
    }
    @Test
    @DisplayName("d2Test Valor deve estar entre 1 e 2 - inclusive")
    public void d2Test(){
        int result= CombatCalculations.d2();
        boolean testOk= result >= 1 && result <= 2;
        assertTrue(testOk);
    }
    @Test
    @DisplayName("attackFactorTest Valor deve estar entre 7 e 16 - inclusive")
    public void attackFactorTest(){
        int result= CombatCalculations.attackFactor(3,3);
        boolean testOk= result >= 7 && result <= 16;
        assertTrue(testOk);
    }
}

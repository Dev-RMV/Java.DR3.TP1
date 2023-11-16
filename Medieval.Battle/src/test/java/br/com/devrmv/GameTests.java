package br.com.devrmv;

import br.com.devrmv.interfaces.ICharacter;
import br.com.devrmv.programs.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameTests {
    Logger LOGGER=LoggerFactory.getLogger(GameTests.class);
    @Test
    @DisplayName("characterSelectTest Deve lançar uma exception para classes que nao existem")
    public void characterSelectTest(){
        Game game=new Game();
        assertThrows(RuntimeException.class,()->{game.characterSelect(4);});
    }
    @Test
    @DisplayName("characterSelectTestFighter")
    public void characterSelectTestFighter(){
        Game game=new Game();
        ICharacter result=game.characterSelect(1);
        String name=result.getClass().getSimpleName();
        LOGGER.debug(name);
        boolean testOK=(name.equals("Fighter"));
        assertTrue(testOK);
    }
    @Test
    @DisplayName("characterSelectTestPaladin")
    public void characterSelectTestPaladin(){
        Game game=new Game();
        ICharacter result=game.characterSelect(2);
        String name=result.getClass().getSimpleName();
        LOGGER.debug(name);
        boolean testOK=(name.equals("Paladin"));
        assertTrue(testOK);
    }
    @Test
    @DisplayName("characterSelectTestBarbarian")
    public void characterSelectTestBarbarian(){
        Game game=new Game();
        ICharacter result=game.characterSelect(3);
        String name=result.getClass().getSimpleName();
        LOGGER.debug(name);
        boolean testOK=(name.equals("Barbarian"));
        assertTrue(testOK);
    }
}

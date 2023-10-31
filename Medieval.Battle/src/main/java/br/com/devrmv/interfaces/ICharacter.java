package br.com.devrmv.interfaces;

public interface ICharacter {
	int hitPoints = 0;
	int str = 0;
	int agi = 0;
	int def = 0;

	public int damage();

	public int getHitPoints();

	public void setHitPoints(int hitPoints);

	public int getStr();

	public int getAgi();

	public int getDef();



}

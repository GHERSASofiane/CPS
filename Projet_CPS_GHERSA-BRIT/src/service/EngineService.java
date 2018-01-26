package service;

import impl.Commande;
import impl.FightChar;

public interface EngineService {
	
					/*************************
					 *   ->  Observators <-  *
					 *************************/
	public int getHeight();
	public int getWidth();
	public int getSpace();
	/** \pre: i \in {1, 2} **/
	public CharacterService getChar(int i);
	public boolean gameOver();
	
	/** 
	 * \pre: (h > 0) && (s > 0) && (w > s) && (p1 != p2) 
	 **/
	
	public void init(int X, int Y, int h, int w, int s, FightChar p1, FightChar p2);
	
	public void step(Commande c1, Commande c2);	
	
	
}



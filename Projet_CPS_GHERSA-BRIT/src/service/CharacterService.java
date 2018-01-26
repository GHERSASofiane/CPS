package service;

import impl.Commande;

public interface CharacterService  {
	
	/*************************
	 *   ->  Observators <-  *
	 *************************/
	
public int getPositionX();
public int getPositionY();
public HitboxService getCharBox(); 
public int getLife(); 				/** Permet de connaitre le nombre de points de vie du personnage **/
public int getSpeed(); 	 		/** Permet de savoir quel face du personnage est en place. **/
public boolean isDead(); 			/** Permet de savoir si le personnage est vivant ou mort **/



	/*************************
	 *  ->  Constructors <-  *
	 *************************/

public void init(int l, int s ,boolean f, HitboxService hb);

public void moveUp();

public void moveDown();

public void moveLeft(); /** bouge le personnage a gauche **/

public void moveUpLeft();

public void moveDownLeft();

public void moveRight(); /** bouge le personnage a droite **/

public void moveUpRight();

public void moveDownRight();

public void switchSide();

public void step(Commande com);

    public boolean faceRight();

    public EngineService getEngine();
    

}

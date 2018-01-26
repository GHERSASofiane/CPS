package contract;

import decorator.EngineDecorator;
import error.InvariantError;
import error.PostconditionError;
import error.PreconditionError;
import impl.Commande;
import impl.FightChar;
import service.CharacterService;
import service.EngineService;
import service.FightCharService;
import service.CharacterService;
import service.HitboxService;

public class EngineContract extends EngineDecorator {

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	/**
	 * \inv: gameOver() == ( \exists i : {1, 2} \in {  getChar(i).isDead() } )
	 */
	
	private void checkInvariant(){

		for(int i = 1; i<3; i++ ){
			if(!(gameOver() == getChar(i).isDead()))
					throw new InvariantError("...");
		}
		
	}
	
	/** \pre: i \in {1, 2} **/
	public CharacterService getChar(int i){
		if(!(i == 1 || i == 2)) throw new PreconditionError("il n'existe pas de character avec ce nom");
		return super.getChar(i);
	}
	

	
	/** 
	 * \pre: (h > 0) && (s > 0) && (w > s) && (p1 != p2) 
	 **/
	
	/**
	 * \post: getHeigth() == h
	 * \post: getWidth() == w
	 * \post: getPlayer(1) == p1
	 * \post: getPlayer(2) == p2
	 * 
	 * \post: getChar(1).getPositionX() == ((w/2) − ((s/2)-getWidth())) 
	 * \post: getChar(2).getPositionX() == ((w/2) + (s/2))
	 * 
	 * \post: getChar(1).getPositionY() == 0 
	 * \post: getChar(2).getPositionY() == 0
	 * \post: getChar(1).faceRight()
	 * \post: not(getChar(2).faceRight())
	 * 
	 **/
	
	public void init(int h, int w, int s, FightCharService p1, FightCharService p2){
		//Precondition
		if(!((h > 0) && (s > 0) && (w > s) && p1.equals(p2) )) 
			throw new PreconditionError("Precondition de init [engine] ne sont pas remplies");
		checkInvariant();
		super.init(h, w, s, p1, p2);
		checkInvariant();
		
		//PostCondition
		/** \post: getHeigth() == h */
		if(!(getHeight() == h) ) 
			throw new PostconditionError("pb : getHeight doit valoir la valeur passe en parametre");
		/** \post: getWidth() == w */
		if(!(getWidth() == w) ) 
			throw new PostconditionError("pb : getWidth doit valoir la valeur passe en parametre");
		/** \post: getPlayer(1) == p1 **/
		if(!( getChar(1).equals(p1) ) )
			throw new PostconditionError("pb : getChar(1) doit correspondre a l'objet passe en parametre");
		/** \post: getPlayer(2) == p2 **/
		if(!(getChar(2).equals(p2)) ) 
			throw new PostconditionError("pb : getChar(2) doit correspondre a l'objet passe en parametre");
		/**	 \post: getChar(1).getPositionX() == ((w/2) − (s/2)) **/
		if(!(getChar(1).getPositionX() == ( (int)(w/2) - ((int)(s/2))-getWidth()))) 
			throw new PostconditionError(" la position de x au depart doit valoir (int)(w/2) - (int)(s/2) pour le personnage 1");
		 /** \post: getChar(2).getPositionX() == ((w/2) + (s/2)) **/
		if(!(getChar(2).getPositionX() == ( (int)(w/2) +  (int)(s/2)) )) 
			throw new PostconditionError("la position de x au depart doit valoir (int)(w/2) + (int)(s/2) pour le personnage 2");
		/**\post: getChar(1).getPositionY() == 0 **/
		if(!(getChar(1).getPositionY() == 0 )) 
				throw new PostconditionError("la position de y du personnage 1 doit etre a 0 car notre personnage est sur le sol"); 
		/**\post: getChar(2).getPositionY() == 0**/
		if(!(getChar(2).getPositionY() == 0 )) 
			throw new PostconditionError("la position de y du personnage 2 doit etre a 0 car notre personnage est sur le sol");
		/** \post: getChar(1).faceRight() */
		if(!(getChar(1).faceRight() )) 
			throw new PostconditionError("le  joueur 1 doit avoir sa face en direction droit (face a l'autre joueur)");
		/**  \post: getChar(2).faceRight()**/
		if(!(!(getChar(2).faceRight()))) 
			throw new PostconditionError("le  joueur 2 doit avoir sa face en direction gauche (face a l'autre joueur)");
	}
	
	
	 
	public void step(Commande c1, Commande c2){
            

		/** Preconditions **/
		/** \pre: not(gameOver()) **/
 /*    
		if(gameOver()) throw new PreconditionError("Probleme le jeu est fini car le personnage n'a plus de vie");
		checkInvariant();
*/
		//** recupere getchar(1) et getchar(2) 
 /*
		 CharacterService char1 = (CharacterService) getChar(1).clone();
		
		CharacterService char2 = (CharacterService) getChar(2).clone();
		super.step(c1, c2);
		checkInvariant();
*/
		/** PostConditions  **/
		/** \post: getChar(1) == getChar(1)@pre.step(C1)   **/
/*
		char1.step(c1);
		if(!(getChar(1).equals(char1) )) 
			throw new PostconditionError("test directement des objets -> test automatiquement s'ils sont a la meme position");
*/
                /** * \post: getChar(2) == getChar(2)@pre.step(C2) **/
/*
		char2.step(c2);
		if(!(getChar(2).equals(char2) )) 
			throw new PostconditionError("test directement des objets -> test automatiquement s'ils sont a la meme position");
*/	
	}

    @Override
    public void init(int X, int Y, int h, int w, int s, FightChar p1, FightChar p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

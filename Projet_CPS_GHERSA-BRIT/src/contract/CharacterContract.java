package contract;


import decorator.CharacterDecorator;
import decorator.EngineDecorator;
import error.InvariantError;
import error.PostconditionError;
import error.PreconditionError;
import impl.Commande;
import service.CharacterService;
import service.EngineService;
import service.HitboxService;

public class CharacterContract extends CharacterDecorator implements Cloneable{

	public CharacterContract(CharacterService delegate) {
		super(delegate);
	}

	private void checkInvariant(){


		/** \inv: getPositionX() > 0 && ( getPositionX() < getEngine().getWidth() )  **/
		if(!(this.getPositionX() > 0 && ( this.getPositionX() < getEngine().getWidth()))){ 
			throw new InvariantError("Mauvaise coordonnée au niveau des abscisses ");
		}
		/** \inv: getPositionY() > 0 && ( getPositionY() < getEngine().getHeight() ) **/
		if(!(this.getPositionY() > 0 && ( this.getPositionY() < getEngine().getHeight()))){ 
			throw new InvariantError("Mauvaise coordonnée au niveau des ordonnées");
		}
		/**  \inv: isDead() ==  !(isInlife() > 0) **/
		if(!(this.isDead() == !(this.getLife() > 0))){
			throw new InvariantError("Le personnage n'a plus de vie.");
		}
	}

	/** \pre: (l > 0) && (s > 0) 
	 * Legende :
	 * s -> speed
	 * f -> false au debut car le perso n'est pas mort
	 * l -> la vie restant au personnage (au debut elle est maximal)
	 * e ->engine
	 **/
	/** 
	 * 
	 * \post: isInLife() == l 
	 * \post: getSpeed() == s 
	 * \post: faceRight() == f 
	 * \post: getEngine() == e
	 * \post: \exists h:Hitbox { getCharBox() == h }
	 * 
	 */
	public void init(int l, int s, boolean f, EngineService e){
		// Précondition
		if(!(l>0 && s >0)) 
			throw new PreconditionError("[ERREUR -> init] le personnage doit etre en vie et sa vitesse doit etre superieur a 0");
		checkInvariant();
		super.init(l, s, f, e);
		checkInvariant();
		//Postcondition
		if(!(getLife() == l)) throw new PostconditionError("pb : getLife doit valoir la valeur passe en parametre");
		if(!(getSpeed() == s)) throw new PostconditionError("pb : getSpeed doit valoir la valeur passe en parametre");
		if(!(faceRight() == f)) throw new PostconditionError("pb : faceRight doit valoir la valeur passe en parametre");
		if(!(getEngine().equals(e))) throw new PostconditionError("pb : getEngine doit valoir la valeur passe en parametre");
		if(!(getCharBox() != null)) throw new PostconditionError("pb : getCharBox ne doit pas etre null au depart");
	}

	/**
	 * 
	 *\post: not( \exists i \in {( getEngine().getChar(i) != this ) && getCharBox().collidesWith(getEngine().getChar(i).getCharBox()) }) 
	 *                                                              \ union ( getPositionY() == getPositionY()@pre )
	 * 
	 * \post: \ not ( (( ( getEngine().getHeight - ( getPositionY()@pre + getCharBox().getHeight() ) ) =< getSpeed()
	 * 									&& (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union      ( getPositionY() == getPositionY()@pre ) 
	 * 
	 * 
	 * 
	 * \post: \ not ( ( ( getEngine().getHeight - ( getPositionY()@pre + getCharBox().getHeight() ) > getSpeed() ) 
	 * 												&& (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union ((getPositionY() == (getPositionY()@pre) + getSpeed()) )
	 * 
	 * \post: (faceRight() ==  faceRight()@pre ) && ( getLife() == getLife()@pre )
	 * \post: getPositionX() == getPositionX()@pre
	 * 
	 **/
	public void moveUp(){
		//** \pre : vide
		checkInvariant();
		
		int positionX_at_pre =  getPositionX();
		int positionY_at_pre =  getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = 0;
		//** \run
		super.moveUp();

		checkInvariant();

		/** PostConditions **/

		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );

		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionY()==positionY_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}
		
		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;
		
		
		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) ) <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == positionY_at_pre )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) )> getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre ) && (getLife() == life_at_pre)  )) 
			throw new PostconditionError("la face du personnage ne doit pas changer de profil ni sa vie");
		
		if(!(getPositionX() == positionX_at_pre)) 
			throw new PostconditionError("La position du y ne doit pas changer !(getPositionY() == positionY_at_pre)");

	}
	
	
	/**
	 * 
	 *\post: not( \exists i \in {( getEngine().getChar(i) != this ) && getCharBox().collidesWith(getEngine().getChar(i).getCharBox()) }) 
	 *                                                              \ union ( getPositionY() == getPositionY()@pre )
	 * 
	 * \post: \ not ( ( getPositionY()@pre  <= getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union      ( getPositionY() == 0 ) 
	 * 
	 * 
	 * \post: \ not ( ( getPositionY()@pre  > getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union ((getPositionY() == (getPositionY()@pre) − getSpeed()) )
	 * 
	 * \post: (faceRight() == faceRight()@pre ) && ( getLife() == getLife()@pre )
	 * \post: getPositionX() == getPositionX()@pre
	 * 
	 */
	public void moveDown(){
		//** \pre : vide
		checkInvariant();
		
		int positionX_at_pre =  getPositionX();
		int positionY_at_pre =  getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = 0;
		//** \run
		super.moveDown();

		checkInvariant();

		/** PostConditions **/

		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );

		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionY()==positionY_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}
		
		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;
		
		
		if(!( ! ( ( positionY_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionY_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed() ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre ) && (getLife() == life_at_pre)  )) 
			throw new PostconditionError("la face du personnage ne doit pas changer de profil ni sa vie");
		
		if(!(getPositionX() == positionX_at_pre)) 
			throw new PostconditionError("La position du y ne doit pas changer !(getPositionY() == positionY_at_pre)");

	}
	
	
	/**
	 * 
	 *\post: not( \exists i \in {( getEngine().getChar(i) != this ) && getCharBox().collidesWith(getEngine().getChar(i).getCharBox()) }) 
	 *                                                              \ union ( getPositionX() == getPositionX()@pre )
	 * 
	 * \post: \ not ( ( getPositionX()@pre <= getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union      ( getPositionX() == 0 ) 
	 * 
	 * 
	 * \post: \ not ( ( getPositionX()@pre > getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union ((getPositionX() == (getPositionX()@pre) − getSpeed()) )
	 * 
	 * \post: (faceRight() ==  faceRight()@pre ) && ( getLife() == getLife()@pre )
	 * \post: getPositionY() == getPositionY()@pre
	 * 
	 */
	
	public void moveLeft(){
		//** \pre : vide
		checkInvariant();
		
		int positionX_at_pre =  getPositionX();
		int positionY_at_pre =  getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = 0;
		//** \run
		super.moveLeft();

		checkInvariant();

		/** PostConditions **/

		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );

		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (positionX_at_pre - getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre ) && (getLife() == life_at_pre)  )) 
			throw new PostconditionError("la face du personnage ne doit pas changer de profil ni sa vie");
		
		if(!(getPositionY() == positionY_at_pre)) 
			throw new PostconditionError("La position du y ne doit pas changer !(getPositionY() == positionY_at_pre)");
	}
	
	/**
	 *
	 *\post: not( \exists i \in {( getEngine().getChar(i) != this ) && getCharBox().collidesWith(getEngine().getChar(i).getCharBox()) }) 
	 *                                                              \ union ( getPositionX() == getPositionX()@pre )
	 * 
	 * \post: \ not ( ( ( getEngine().getWidth() - getPositionX()@pre + getCharBox().getWidth()  )<= getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union      ( getPositionX() == (getEngine().getWidth() - getCharBox().getWidth() ) ) 
	 * 
	 * 
	 * \post: \ not ( ((getEngine().getWidth() - getPositionX()@pre + getCharBox().getWidth() ) > getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union (getPositionX() == (getPositionX()@pre + getSpeed()) ) 
	 * 
	 * \post: (faceRight() ==  faceRight()@pre ) && ( getLife() == getLife()@pre )
	 * \post: getPositionY() == getPositionY()@pre
	 * 
	 */

	public void moveRight(){

		checkInvariant();

		int positionX_at_pre = getPositionX();
		int positionY_at_pre = getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = getLife();

		super.moveRight();

		checkInvariant();

		/** PostConditions **/
		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );
		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == ( (getEngine().getWidth() - getCharBox().getWidth() ) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (  (positionX_at_pre + getSpeed()) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre ) && (getLife() == life_at_pre)  )) 
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		if(!(getPositionY() == positionY_at_pre)) 
			throw new PostconditionError("La position du y ne doit pas changer !(getPositionY() == positionY_at_pre)");
	}

	public int indiceOther(){
		
		return (this != getEngine().getChar(1)) ? 1 : 2;
		
	}

	public void moveUpRight(){

		checkInvariant();

		int positionX_at_pre = getPositionX();
		int positionY_at_pre = getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = getLife();

		super.moveUpRight();

		checkInvariant();

		/** PostConditions **/
		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );
		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) ) <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == positionY_at_pre )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) )> getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");

		
		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == ( (getEngine().getWidth() - getCharBox().getWidth() ) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (  (positionX_at_pre + getSpeed()) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre) && ( getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() > getEngine().getChar(indiceOther()).getPositionX())))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		if(!( (faceRight() ==  !(faceRight_at_pre)) && (getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() < getEngine().getChar(indiceOther()).getPositionX())  ))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		
		if(!(getPositionX() != positionX_at_pre && getPositionY() != positionY_at_pre)) 
			throw new PostconditionError("La position x et y doivent changer ");
	}
	
	
	public void moveUpLeft(){

		checkInvariant();

		int positionX_at_pre = getPositionX();
		int positionY_at_pre = getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = getLife();

		super.moveUpLeft();

		checkInvariant();

		/** PostConditions **/
		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );
		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) ) <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == positionY_at_pre )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( (  ( getEngine().getHeight() - ( positionY_at_pre + getCharBox().getHeight() ) )> getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");

		
		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (positionX_at_pre - getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");
		if(!( (faceRight() ==  faceRight_at_pre) && ( getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() > getEngine().getChar(indiceOther()).getPositionX())))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		if(!( (faceRight() ==  !(faceRight_at_pre)) && (getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() < getEngine().getChar(indiceOther()).getPositionX())  ))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		
		if(!(getPositionX() != positionX_at_pre && getPositionY() != positionY_at_pre)) 
			throw new PostconditionError("La position x et y doivent changer ");
	}
	
	
	
	
	
	public void moveDownRight(){

		checkInvariant();

		int positionX_at_pre = getPositionX();
		int positionY_at_pre = getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = getLife();

		super.moveDownRight();

		checkInvariant();

		/** PostConditions **/
		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );
		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( ( positionY_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionY_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed() ) )  ))
			throw new PostconditionError("probleme de deplacement");

		
		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == ( (getEngine().getWidth() - getCharBox().getWidth() ) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (  (positionX_at_pre + getSpeed()) ) )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( (faceRight() ==  faceRight_at_pre) && ( getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() > getEngine().getChar(indiceOther()).getPositionX())))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		if(!( (faceRight() ==  !(faceRight_at_pre)) && (getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() < getEngine().getChar(indiceOther()).getPositionX())  ))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		
		if(!(getPositionX() != positionX_at_pre && getPositionY() != positionY_at_pre)) 
			throw new PostconditionError("La position x et y doivent changer ");
	}
	
	
	public void moveDownLeft(){

		checkInvariant();

		int positionX_at_pre = getPositionX();
		int positionY_at_pre = getPositionY();
		boolean faceRight_at_pre = false;
		int life_at_pre = getLife();

		super.moveDownLeft();

		checkInvariant();

		/** PostConditions **/
		boolean cote_gauche_1_c1 = (getEngine().getChar(1)  != this );
		boolean cote_gauche_1_c2 = (getEngine().getChar(2)  != this );
		boolean cote_gauche_2_c1 = getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		boolean cote_gauche_2_c2 = getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		boolean cote_gauche = !( (cote_gauche_1_c1 && cote_gauche_2_c1) || (cote_gauche_1_c2 && cote_gauche_2_c2)) ;
		boolean cote_droit = (getPositionX()==positionX_at_pre) ;
		if(!( cote_gauche || cote_droit )){
			throw new PreconditionError("collision !!! probleme de deplacement");
		}

		cote_gauche_1_c1 = !(getEngine().getChar(1)  != this );
		cote_gauche_1_c2 = !(getEngine().getChar(2)  != this );

		cote_gauche_2_c1 = !getCharBox().collidesWith(getEngine().getChar(1).getCharBox()) ;
		cote_gauche_2_c2 = !getCharBox().collidesWith(getEngine().getChar(2).getCharBox()) ;

		if(!( ! ( ( positionY_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionY_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionY() == (positionY_at_pre + getSpeed() ) )  ))
			throw new PostconditionError("probleme de deplacement");
		
		if(!( ! ( ( positionX_at_pre <= getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == 0 )  ))
			throw new PostconditionError("probleme de deplacement");

		if(!( ! ( ( positionX_at_pre > getSpeed() ) && ( (cote_gauche_1_c1 || cote_gauche_2_c1 ) && (cote_gauche_1_c2 || cote_gauche_2_c2) )  )
				||   (getPositionX() == (positionX_at_pre - getSpeed()) )  ))
			throw new PostconditionError("probleme de deplacement");
		if(!( (faceRight() ==  faceRight_at_pre) && ( getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() > getEngine().getChar(indiceOther()).getPositionX())))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		if(!( (faceRight() ==  !(faceRight_at_pre)) && (getEngine().getChar(indiceOther()) != this ) && (this.getPositionX() < getEngine().getChar(indiceOther()).getPositionX())  ))
			throw new PostconditionError("la face du personnage ne doit pas changer de profil");

		
		if(!(getPositionX() != positionX_at_pre && getPositionY() != positionY_at_pre)) 
			throw new PostconditionError("La position x et y doivent changer ");
	}
	
	
	
	
	
	
	
	
	/**
	 * \post: faceRight() != faceRight()@pre
	 * \post: positionX() == positionX()@pre 
	 */
	public void switchSide(){

		boolean faceRight_at_pre;
		int getPostionX_at_pre;
		/** PAS DE PRECONDITION **/
		checkInvariant();
		faceRight_at_pre = faceRight();
		getPostionX_at_pre = getPositionX();
		super.switchSide();
		checkInvariant();

		if(!(faceRight() != faceRight_at_pre)) throw new PostconditionError("Pb : le changement de face n'a pas eu lieu");
		if(!(getPositionX() == getPostionX_at_pre)) throw new PostconditionError("Pb:  le changement de face ne doit pas impacter la position");

	}
	/** \pre: isDead() == false TODO a verifier **/
	/**
	 *step(LEFT) == moveLeft() 
	 *step(RIGHT) == moveRight() 
	 *step(NEUTRAL) == this  // TODO : C ou this ?
	 */
	public void step(Commande com){
		/** PRECONDITIONS **/
		if(!(isDead() == false)) throw new PreconditionError("Sorry le personnage est deja dead il ne peut plus bouger");
		
		checkInvariant();

		int getPositionX_at_pre = this.getPositionX();
		int	getPositionY_at_pre = this.getPositionY();
	
		super.step(com);
		checkInvariant();


		/** POSTCONDITIONS **/
		int indiceOtherPlayer = 0;
		if(getEngine().getChar(1).equals(this)) indiceOtherPlayer = 2;
		else if(getEngine().getChar(2).equals(this)) indiceOtherPlayer = 1;
		if( indiceOtherPlayer != 0 ){

			if(com == Commande.LEFT){
				if((getEngine().getChar(indiceOtherPlayer).getPositionX() + getEngine().getChar(indiceOtherPlayer).getCharBox().getWidth() ) != this.getPositionX()  )
					if(!( (getPositionX() != getPositionX_at_pre) && (getPositionY() == getPositionY_at_pre))) throw new PostconditionError("Le personnage n'est pas a la bonne place positionX_at_pre - speed ( commande LEFT)");
			}

			if(com == Commande.RIGHT){
				if((this.getPositionX() + this.getCharBox().getWidth() ) != getEngine().getChar(indiceOtherPlayer).getPositionX()  )
					if(!(  (getPositionX() != getPositionX_at_pre) && (getPositionY() == getPositionY_at_pre)    )) throw new PostconditionError("Le personnage n'est pas a la bonne place positionX_at_pre + speed ( commande Right)");
			}

			if(com == Commande.NEUTRAL){
				if(!(   (getPositionX() == getPositionX_at_pre) && (getPositionY() == getPositionY_at_pre)   )) throw new PostconditionError("Le personnage ne doit pas bouger car le player a jouer la commande NEUTRAL");
			}
		}throw new PostconditionError("pb pas d'autre player (on ne doit jamais arriver dans ce cas la .");

	}

    @Override
    public void init(int l, int s, boolean f, HitboxService hb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	


}

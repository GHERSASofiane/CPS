package impl;

import error.PreconditionError;
import service.CharacterService;
import service.EngineService;
import service.HitboxService;

public class Character implements CharacterService{

        public int PositionX;
        public int PositionY;
	protected HitboxService hitbox;
	public int life = 0; 
	protected int speed = 0;
        public boolean isfaceRight ;
        public boolean isdead = false;


	/** Constructors **/
	  @Override
        public void init(int l, int s, boolean f, HitboxService hb) {
          /**** Precondition */
            /* l > 0 ∧ s > 0 */
            if(l > 0 && s > 0){
                isdead = false;
                PositionX = hb.getPositionX();
                PositionY = hb.getPositionY();
                hitbox = hb;
		life = l;
		speed = s;
                isfaceRight = f;
            }else{
             throw new PreconditionError("erreur des variable qui ne respect pas les precondition l > 0 ∧ s > 0");
            }
               
        }


	/** Operators **/
	@Override
	public void moveUp() { }
	@Override
	public void moveDown() { }

	@Override
	public void moveLeft() { 
            /** se déplacer à gauche */
            PositionX = PositionX - speed ;
	}
        
	
	@Override
	public void moveUpLeft() {  }
	@Override
	public void moveDownLeft() { }

	@Override
	public void moveRight() {
            /** déplacer vers la droite */
            PositionX = PositionX + speed;
        }
        
	@Override
	public void moveUpRight() { }
	@Override
	public void moveDownRight() { }


	/** on change de face **/
	@Override
	public void switchSide() { }

	@Override
	public void step(Commande com) {
           /*** Precondition */
            /* ¬dead */ 
            if(!isDead()){
                switch(com){ 

		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		
		case NEUTRAL:
			break;

		default:
			System.out.println("Mauvaise commande"); 
			break;
		}
            }else{
                throw new PreconditionError("erreur des variable qui ne respect pas les precondition ¬dead ");
            }
		

	}

	/** Observateurs **/
	@Override
	public int getPositionX() { 
		return PositionX;
	}

	@Override
	public int getPositionY() { 
		return PositionY;
	}


	@Override
	public HitboxService getCharBox() {
		return this.hitbox;
	}

	@Override
	public int getLife() {
		return this.life;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}


	@Override
	public boolean isDead() {
		return ( life <= 0 );
	}
	@Override
     public EngineService getEngine(){
         return null;
     }

    @Override
    public boolean faceRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

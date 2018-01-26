package impl;

import error.*;
import service.CharacterService;
import service.EngineService;

public class Engine implements EngineService{

	private int height;
	private int width;
        public int PositionX;
        public int PositionY;
	private FightChar characters[] = { null , null , null}; 
        private boolean gameOver;
        
	private int space;
	private final int NBCHARS = 2;
	/** Observateurs **/
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public int getSpace() {
		return this.space;
	}
	
	@Override
	public FightChar getChar(int i) {
            if ( (i == 1) || (i == 2)) {
               
		return characters[i]; 
                 
            }else{
                throw new PreconditionError("erreur des variable qui ne respect pas les precondition i ∈ {1, 2}");
            }
	}

	

	@Override
	public boolean gameOver() {
		boolean is_g_ov = false ; 
                
			if(getChar(1).isDead()){ is_g_ov = true;
			}else if(getChar(2).isDead()){ is_g_ov = true;}
		
		return is_g_ov;	
	}
	/** Constructors **/
	@Override
	public void init(int X, int Y, int height, int width, int space, FightChar p1, FightChar p2) {
        /*** PreCondition */
            /* h > 0 ∧ s > 0 ∧ w > s ∧ p1 != p2 */ 
            if ( (height > 0) && (width > 0)  && (width > space)  && (p1 != p2) ) {
             
		this.height = height;
		this.width = width;
		this.space = space;
                
                this.PositionX = X;
                this.PositionY = Y;
                
		characters[1] = p1 ;
		characters[2] = p2 ;
	   
            }else{
                throw new PreconditionError("erreur des variable qui ne respect pas les precondition h > 0 ∧ s > 0 ∧ w > s ∧ p1 != p2");
            }
	}

	/** Operators **/
	@Override
	public void step(Commande c1, Commande c2) {
          /*** PreCondition */
            /* ¬gameOver(E) */
            if (!gameOver()) {
                this.getChar(1).step(c1);
		this.getChar(2).step(c2);
            }else{
                throw new PreconditionError("erreur des variable qui ne respect pas les precondition  ¬gameOver(E)");
            }
				
	}



}

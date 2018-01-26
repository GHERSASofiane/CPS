package contract;

import decorator.HitboxDecorator;
import error.InvariantError;
import error.PostconditionError;
import error.PreconditionError;
import service.HitboxService;

public class HitboxContract extends HitboxDecorator{

	public HitboxContract(HitboxService delegate) {
		super(delegate);
	}

	private void checkInvariant(){

		/** \inv: collidesWith(H1) == ( \exists x:int && y:int \in { belongsTo(x,y) && H1.belongsTo(x,y) }) **/
		/* Les point a teste sont les extrimite  */ 
		int x_0 = this.getPositionX() ;
		int x_1 = this.getPositionX() + this.getWidth();

		int y_0 = this.getPositionY(); 
		int y_1 = this.getPositionX() + this.getHeight();
		/* les point de l'autre Hitbox */

		int x_0_hb = H1.getPositionX() ;
		int x_1_hb = H1.getPositionX() + H1.getWidth();

		int y_0_hb = H1.getPositionX() ;
		int y_1_hb = H1.getPositionX() + H1.getWidth();

		boolean Is_colis = false ;

		if ( ( (x_0 >= x_0_hb) && (x_0 <= x_1_hb) ) 
				|| ( (x_1 >= x_0_hb) && (x_1 <= x_1_hb) ) 
				||  ( (x_0_hb >= x_0) && (x_0_hb <= x_1) ) 
				|| ( (x_1_hb >= x_0) && (x_1_hb <= x_1) ) 

				|| ( (y_0 >= y_0_hb) && (y_0 <= y_1_hb) ) 
				|| ( (y_1 >= y_0_hb) && (y_1 <= y_1_hb) ) 
				||  ( (y_0_hb >= x_0) && (y_0_hb <= y_1) ) 
				|| ( (y_1_hb >= x_0) && (y_1_hb <= y_1) ) ) {
			Is_colis = true; 
		}

		if( !(collidesWith(H1) == Is_colis )){
			throw new InvariantError("Probleme de collision !(collidesWith(H1) == Is_colis ) ");
		}

		/**  \inv: equalsTo(H1) == ( \forall x:int && y:int \in { belongsTo(x,y) == H1.belongsTo(x,y) })  **/


		boolean is_equ = false;

		if ( ( x_0 == x_0_hb ) && ( x_1 == x_1_hb ) && ( y_0 == y_0_hb ) && ( y_1 == y_1_hb ) ) {
			is_equ = true;
		}

		if( !(equalsTo(H1) == is_equ )){
			throw new InvariantError("Les 2 hitbox ne sont pas egales .s");
		}


		/** \inv : \forall x:int && y:int \in {  
		 * 			belongsTo(x,y) == ( ( ( x >= getPositionX() ) && ( x < (getPositionX()+getWidth()) ) )  
		 * 		     && ( ( y >= getPositionY() ) && ( y < ( getPositionY()+getHeight()) ) ) ) 
		 * 			}
		 **/

		boolean isBelongsTo = true;

		if ( ( (x_0 >= x_0_hb) && (x_0 <= x_1_hb) ) 
				|| ( (x_1 >= x_0_hb) && (x_1 <= x_1_hb) ) 
				||  ( (x_0_hb >= x_0) && (x_0_hb <= x_1) ) 
				|| ( (x_1_hb >= x_0) && (x_1_hb <= x_1) ) 

				|| ( (y_0 >= y_0_hb) && (y_0 <= y_1_hb) ) 
				|| ( (y_1 >= y_0_hb) && (y_1 <= y_1_hb) ) 
				||  ( (y_0_hb >= x_0) && (y_0_hb <= y_1) ) 
				|| ( (y_1_hb >= x_0) && (y_1_hb <= y_1) ) ) {
					isBelongsTo = false ;
		}
		if( !( isBelongsTo )){
			throw new InvariantError("les 2 hitbox ne sont pas identique ");
		}		


	}

	/** pour l'utilise dans les autres tests **/
	public void testPosition(int x, int y){
		/** \post: getPositionX() == x **/
		if(!(getPositionX() == x))
			throw new PostconditionError("la position de x doit etre celle passer en parametre du init");
		/** \post: getPositionY() == y **/
		if(!(getPositionY() == y))
			throw new PostconditionError("la position de y doit etre celle passer en parametre du init");

	}

	public void init(int x, int y, int width, int height) {
		/** PreConditions. **/
		/** \pre: width > 0 && height > 0 **/
		if(!(width>0 && height>0))
			throw new PreconditionError("la taille doit etre superieur a 0 ( heigth > 0 && width> 0)");
		// pre-invariant
		checkInvariant();
		// Traitement
		super.init(x,y, width, height);
		// post-invariant
		checkInvariant();

		/** PostConditions. **/
		testPosition(x, y);
		/** \post: getWidth() == width  **/
		if(!(getWidth() == width))
			throw new PostconditionError("la largueur doit etre celle passer en parametre du init (width)");
		/** \post: getHeight() == height  **/
		if(!(getHeight() == height))
			throw new PostconditionError("la longueur doit etre celle passer en parametre du init (width)");

	}

	
	public void moveTo(int x, int y){
		/** \pre: Pas de PreConditions. **/

		boolean belongsTo_centre_at_pre;
		boolean belongsTo_centre_100_at_pre;
		int getPositionX_at_pre;
		int getPositionY_at_pre;
		boolean belongsTo_abs_at_pre;

		// pre-invariant
		checkInvariant();

		/** Capture du point de reference **/
		belongsTo_centre_at_pre = belongsTo(getPositionX(), getPositionY());
		/** Capture du point de reference  + 100 **/
		belongsTo_centre_100_at_pre = belongsTo(getPositionX() + 100, getPositionY() + 100);
		/**	Capture d’un point absolu **/
		getPositionX_at_pre = getPositionX();
		getPositionY_at_pre = getPositionY();
		belongsTo_abs_at_pre = belongsTo(300, 0);

		super.moveTo(x,y);

		// post-invariant
		checkInvariant();

		/** PostConditions. **/
		testPosition(x, y);
		/** \post: \forall u:int && v:int \in { belongsTo(u,v) == belongsTo(u-(x-getPositionX()@pre),v-(y-getPositionY()@pre)) }  **/ 
		/** Test du point de reference **/
		if(!( belongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre))
			throw new PostconditionError("Le point de reference n'est pas au bon endroit");
		/** Test du point de reference + 100 **/
		if(!( belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsTo_centre_100_at_pre))
			throw new PostconditionError(" Le point de reference + 100 n'est pas au bon endroit "); 
		/** Test d’un point absolu **/
		if(! (belongsTo(300 + (x - getPositionX_at_pre), 0 + (y - getPositionY_at_pre)) == belongsTo_abs_at_pre))
			throw new PostconditionError(" Le point absolu n'est pas au bon endroit ");	
	
	}

    @Override
    public boolean Is_beside(HitboxService hb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

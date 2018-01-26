package impl;

import service.HitboxService;

public class Hitbox implements HitboxService{

	private int x;
	private int y;
	private int width;
	private int height;



	/** Constructors **/
	@Override
	public void init(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	/** Operators **/
	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Observateurs **/

	@Override
	public boolean belongsTo(int x, int y) {
		return (( ( x >= getPositionX() ) && ( x < (  getPositionX() + getWidth()) ) )  
				&& (  y >= getPositionY()   ) && ( y < ( getPositionX() + getWidth() ) )) ;

	}

	@Override
	public boolean collidesWith(HitboxService hb) {
		/** On prends pour reference la taille de la hitbox this 
		 * et on regarde si un pixel de this est contenue aussi dans l'autre hitbox passe en parametre **/
	/* Les point a teste sont les extrimite  */ 
                int x_0 = this.x ;
                int x_1 = this.x + this.width;
        /* les point de l'autre Hitbox */
         
                int x_0_hb = hb.getPositionX() ;
                int x_1_hb = hb.getPositionX() + hb.getWidth();
                
            boolean Is_colis = false ;
             
            if ( ( (x_0 >= x_0_hb) && (x_0 <= x_1_hb) ) || ( (x_1 >= x_0_hb) && (x_1 <= x_1_hb) ) ||  ( (x_0_hb >= x_0) && (x_0_hb <= x_1) ) || ( (x_1_hb >= x_0) && (x_1_hb <= x_1) ) ) {
               Is_colis = true; 
            }
	 return Is_colis;
	}


	@Override
	public boolean equalsTo(HitboxService hb) { 
            boolean is_equ = false;
            if (hb == null){
                    return false;
                }else{
                
        /* Les point a teste sont les extrimite  */ 
                int x_0 = this.x ;
                int x_1 = this.x + this.width;
                int y_0 = this.y ;
                int y_1 = this.y + this.height;
        /* les point de l'autre Hitbox */
         
                int x_0_hb = hb.getPositionX() ;
                int x_1_hb = hb.getPositionX() + hb.getWidth();
                int y_0_hb = hb.getPositionY() ;
                int y_1_hb = hb.getPositionY() + hb.getHeight();
               
                if ( ( x_0 == x_0_hb ) && ( x_1 == x_1_hb ) && ( y_0 == y_0_hb ) && ( y_1 == y_1_hb ) ) {
                    is_equ = true;
                }
		
                    
                }
			
                
		return is_equ;
	}

	@Override
	public int getPositionX() {
		return this.x;
	}

	@Override
	public int getPositionY() {
		return this.y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public void setPositionX(int posX){
		this.x = posX;
	}
	@Override
	public void setPositionY(int posY) {
		this.y = posY ;
		
	}

    @Override
    public boolean Is_beside(HitboxService hb) {
        boolean Is_b = false;
        /* Les point a teste sont les extrimite  */ 
                int x_0 = this.x ;
                int x_1 = this.x + this.width;
        /* les point de l'autre Hitbox */
         
                int x_0_hb = hb.getPositionX() ;
                int x_1_hb = hb.getPositionX() + hb.getWidth();
                
          if (x_0_hb >= x_0) {
              if (  (x_1+5) >= x_0_hb  ) {
               Is_b = true; 
              }
          }else{
              if (  (x_1_hb+5) >= x_0  ) {
               Is_b = true; 
              }
          }
             
           
        return Is_b;
    }
}

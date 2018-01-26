package service;

public interface HitboxService {
	
	
									/*************************
									 *   ->  Observators <-  *
									 *************************/
									
	public int getPositionX(); 
	public int getPositionY();
	
	//   
	public int getWidth();
	public int getHeight();
	public void setWidth(int width);
	public void setHeight(int height);
	
	
	
	public boolean belongsTo(int x, int y);        /** test si un pixel appartient a une hitbox **/
	public boolean collidesWith(HitboxService hb); /** teste si 2 hitbox sont en collision **/ 
	public boolean equalsTo(HitboxService hb);     /** teste l'egalite avec une autre hitbox **/  
	public boolean Is_beside(HitboxService hb);
	// 
	
	public void setPositionX(int posX);
	public void setPositionY(int posY);
	
	
	public void init(int x, int y, int width, int height);
	public void moveTo(int x, int y);
}

package decorator;

import service.HitboxService;

public abstract class HitboxDecorator implements HitboxService {
	
	protected HitboxService delegate;
	protected HitboxService H1; // utile pour les invariants .

	public HitboxDecorator(HitboxService delegate) {
		super();
		this.delegate = delegate;
	}
	@Override
	public int getPositionX(){
		return delegate.getPositionX();
	}
	@Override
	public int getPositionY(){
		return delegate.getPositionY();
	}
	
	// TODO : rajout pour RectangleHitBox. 
	@Override
	public int getWidth(){
		return delegate.getWidth();
	}
	@Override
	public int getHeight(){
		return delegate.getHeight();
	}
	@Override
	public void setWidth(int width){
		delegate.setWidth(width);
	}
	@Override
	public void setHeight(int height){
		delegate.setHeight(height);
	}
	
	// FIN RECTANGLE HITBOX
	// pour les invariants
	public HitboxService getH1() {
		return H1;
	}
	public void setH1(HitboxService h1) {
		this.H1 = h1;
	}
	// Fin 
	
	
	@Override
	public boolean belongsTo(int x, int y){
		return delegate.belongsTo(x, y);
	}
	@Override
	public boolean collidesWith(HitboxService hb){
		return collidesWith(hb);
	}
	@Override
	public boolean equalsTo(HitboxService hb){
		return delegate.equalsTo(hb);
	}
	
	@Override
	public void setPositionX(int posX){
		delegate.setPositionX(posX);
	}
	@Override
	public void setPositionY(int posY){
		delegate.setPositionY(posY);
	}
	
	@Override
	public void init(int x, int y , int width, int height){
		delegate.init(x, y, width, height);
	}
	@Override
	public void moveTo(int x, int y){
		delegate.moveTo(x, y);
	}

}

package decorator;

import contract.CharacterContract;
import impl.Commande;
import service.EngineService;
import service.HitboxService;
import service.CharacterService;

public abstract class CharacterDecorator implements CharacterService {

	protected CharacterService delegate;
	public CharacterDecorator(CharacterService delegate) {
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
	@Override
	public EngineService getEngine(){
		return delegate.getEngine();
	}
	@Override
	public HitboxService getCharBox(){
		return delegate.getCharBox();
	}
	@Override
	public int getLife(){
		return delegate.getLife();
	}
	@Override
	public int getSpeed(){
		return delegate.getSpeed();
	}
	@Override
	public boolean faceRight(){
		return delegate.faceRight();
	}
	@Override
	public boolean isDead(){
		return delegate.isDead();
	}

	public void init(int l, int s, boolean f, EngineService e){
		delegate.init(l, s, f, (HitboxService) e);
	}
	@Override
	public void moveUp(){
		delegate.moveUp();
	}
	@Override
	public void moveDown(){
		delegate.moveDown();
	}
	@Override
	public void moveLeft(){
		delegate.moveLeft();
	}
	@Override
	public void moveUpLeft(){
		delegate.moveUpLeft();
	}
	@Override
	public void moveDownLeft(){
		delegate.moveDownLeft();
	}
	@Override
	public void moveRight(){
		delegate.moveRight();
	}
	@Override
	public void moveUpRight(){
		delegate.moveUpRight();
	}
	@Override
	public void moveDownRight(){
		delegate.moveDownRight();
	}
	@Override
	public void switchSide(){
		delegate.switchSide();
	}
	@Override
	public void step(Commande com){
		delegate.step(com);
	}
	@Override
	public CharacterService clone() {
		CharacterContract data = null;
		try {
			data = (CharacterContract) super.clone();
		} catch(CloneNotSupportedException cnse) {

			cnse.printStackTrace(System.err);
		}
		return data;
	}
}

package decorator;

import contract.CharacterContract;
import impl.Commande;
import impl.Technique;
import service.CharacterService;
import service.EngineService;
import service.FightCharService;
import service.HitboxService;

public class FightCharDecorator implements FightCharService {

	protected FightCharService delegate;

	public FightCharDecorator(FightCharService delegate) {
		super();
		this.delegate = delegate;
	}
	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public HitboxService getCharBox() {
		return delegate.getCharBox();
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		return delegate.getSpeed();
	}

	@Override
	public boolean faceRight() {
		return delegate.faceRight();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}


	public void init(int l, int s, boolean f, EngineService e) {
	//	delegate.init(l, s, f, e);
	}

	@Override
	public void moveUp() {
		delegate.moveUp();
	}

	@Override
	public void moveDown() {
		delegate.moveDown();
	}

	@Override
	public void moveLeft() {
		delegate.moveLeft();
	}

	@Override
	public void moveUpLeft() {
		delegate.moveUpLeft();
	}

	@Override
	public void moveDownLeft() {
		delegate.moveDownLeft();		
	}

	@Override
	public void moveRight() {
		delegate.moveRight();
	}

	@Override
	public void moveUpRight() {
		delegate.moveUpRight();
	}

	@Override
	public void moveDownRight() {
		delegate.moveDownRight();
	}

	@Override
	public void switchSide() {
		delegate.switchSide();
	}

	@Override
	public void step(Commande com) {
		delegate.step(com);
	}

	@Override
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	@Override
	public boolean isBlockStunned() {
		return delegate.isBlockStunned();
	}

	@Override
	public boolean isHitStunned() {
		return delegate.isHitStunned();
	}

	@Override
	public boolean isTeching() {
		return delegate.isTeching();
	}

	@Override
	public Technique getTech() {
		return delegate.getTech();
	}

	@Override
	public boolean techFrame() {
		return delegate.techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() {
		return delegate.techHasAlreadyHit();
	}

	@Override
	public void startTech(Technique tech) {
		delegate.startTech(tech);
	}

	@Override
	public FightCharService clone() {
		FightCharService data = null;
		try {
			data = (FightCharService) super.clone();
		} catch(CloneNotSupportedException cnse) {

			cnse.printStackTrace(System.err);
		}
		return data;
	}

    @Override
    public void init(int l, int s, boolean f, HitboxService hb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

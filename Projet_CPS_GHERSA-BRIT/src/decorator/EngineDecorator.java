package decorator;

import impl.Commande;
import service.EngineService;
import service.FightCharService;
import service.CharacterService;

public abstract class EngineDecorator implements EngineService{
	
	protected EngineService delegate;

	public EngineDecorator(EngineService delegate) {
		super();
		this.delegate = delegate;
	}
	@Override
	public int getHeight(){
		return delegate.getHeight();
	}
	@Override
	public int getWidth(){
		return delegate.getWidth();
	}
	@Override
	public int getSpace(){
		return delegate.getSpace();
	}
	@Override
	public CharacterService getChar(int i){
		return delegate.getChar(i);
	}
	@Override
	public boolean gameOver(){
		return delegate.gameOver();
	}
	
	public void init(int h, int w, int s, FightCharService p1, FightCharService p2){
//               delegate.init(h, w, s, p1, p2);
	}
	@Override
	public void step(Commande c1, Commande c2){
		delegate.step(c1, c2);
	}
}

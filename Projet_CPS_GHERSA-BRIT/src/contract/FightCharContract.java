package contract;

import decorator.FightCharDecorator;
import error.InvariantError;
import error.PostconditionError;
import error.PreconditionError;
import impl.Technique;
import service.FightCharService;

public class FightCharContract extends FightCharDecorator{

	public FightCharContract(FightCharService delegate) {
		super(delegate);
	}
	


	/** \pre: isTeching() **/
	@Override
	public Technique getTech(){
		if(isTeching())
			throw new PreconditionError("Le personnage n'est pas en train d'effectuer une technique");
		return super.getTech();
		
	}
	/** \pre: isTeching() **/
	@Override
	public boolean techFrame(){
		if(isTeching())
			throw new PreconditionError("Le personnage n'est pas en train d'effectuer une technique");
		return techFrame();
	}
	/** \pre: isTeching() **/
	@Override
	public boolean techHasAlreadyHit(){
		if(isTeching())
			throw new PreconditionError("Le personnage n'est pas en train d'effectuer une technique");
		return super.techHasAlreadyHit();
	}
	public int indiceOther(){
		return (this != getEngine().getChar(1)) ? 1 : 2;
	}

	/*************************
	 *    ->  Operators <-   *
	 *************************/

	/** \pre: not(isTeching()) **/
	@Override
	public void startTech(Technique tech){
		if(isTeching())
			throw new PreconditionError("Le personnage ne peut pas effectuer cette technique car il est effectue deja une technique");
		
		int getLife_atpre = getEngine().getChar(indiceOther()).getLife();
		
		
		super.startTech(tech);
		
	
	}
	
}

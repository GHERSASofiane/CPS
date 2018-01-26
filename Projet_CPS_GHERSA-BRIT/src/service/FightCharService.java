package service;

import impl.Technique;

public interface FightCharService extends CharacterService {
	/*************************
	 *   ->  Observators <-  *
	 *************************/

	public boolean isBlocking();
	public boolean isBlockStunned();
	public boolean isHitStunned();
	public boolean isTeching();
	/** \pre: isTeching() **/
	public Technique getTech();
	/** \pre: isTeching() **/
	public boolean techFrame();
	/** \pre: isTeching() **/
	public boolean techHasAlreadyHit();


	/*************************
	 *    ->  Operators <-   *
	 *************************/

	/** \pre: not(isTeching()) **/
	/** 
	 * \post: ( \exist i \in {1,2} && ( (this != getEngine().getChar(i), getEngine().getChar(i).getLife() == getEngine().getChar(i).getLife()@pre - Technique.damage())  && !getEngine().getChar(i).isBlocking() ) )
	 * \post: ( \exist i \in {1,2} && ( (this != getEngine().getChar(i), getEngine().getChar(i).getLife() == getEngine().getChar(i).getLife()@pre)  && getEngine().getChar(i).isBlocking() ))
	 */
	public void startTech(Technique tech);
}

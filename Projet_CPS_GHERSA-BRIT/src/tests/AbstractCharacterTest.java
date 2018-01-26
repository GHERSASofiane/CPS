package tests;

import org.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import contract.CharacterContract;
import error.PreconditionError;
import impl.Commande;
import service.CharacterService;
import service.EngineService;
import service.HitboxService;

public abstract class AbstractCharacterTest {

	private CharacterService character;
	private int l =50 ;
	private int s  = 5;
	private boolean f = true;
	private EngineService e ;
	private HitboxService h ;

	@Rule
	public ExpectedException thrown = ExpectedException.none();


	@Test
	public void testInitPre1() {
		//cas test 1.1 : cas positif Character :: testInitPre1
		assertTrue(l > 0  && s > 0 );
		character.init(l, s, f, e);
		assertTrue(character.getLife() == l  && character.getSpeed() == s );
		assertTrue(character.faceRight() == f  && character.getEngine().equals(e) );
		assertTrue(character.getCharBox().equals(h));

	}
	@Test
	public void testInitPre2() {
		//Cas test 1.2 : Cas negatif Character :: testInitPre2
		thrown.expect(PreconditionError.class);
		character.init(-1, s, f, e );

	}





	/** \pre: isDead() == false **/
	/**
	 *	step(LEFT) == moveLeft() 
	 *	step(RIGHT) == moveRight() 
	 *	step(NEUTRAL) == this  
	 */
	//public void step(Commande com); /** TODO verifier si c'est bien commandes **/


	
	public Character clone(CharacterService c) {
		CharacterService data = null;
		try {
			data = c.clone();
		} catch(CloneNotSupportedException cnse) {

			cnse.printStackTrace(System.err);
		}
		return data;
	}
	/** \pre: isDead() == false **/
	/**
	 *	step(LEFT) == moveLeft() 
	 *	step(RIGHT) == moveRight() 
	 *	step(NEUTRAL) == this  
	 **/
	@Test
	public void testStepPre1() {
		//cas test 1.1 : cas positif Character :: testStepPre1
		assertTrue(character.isDead()==false);
		CharacterService this_at_pre =  character.clone(); // TODO a verifiers
		character.step(Commande.LEFT);
		this_at_pre.step(Commande.LEFT);
		this_at_pre.moveLeft();
		assertEquals(character.getPositionX(), this_at_pre.getPositionX() );

	}
	@Test
	public void testStepPre2() { //TODO
		//Cas test 1.2 : Cas negatif Character :: testStepPre2
		thrown.expect(PreconditionError.class);
		character.step(Commande.LEFT);
	}
	
	
	
	
	
	
	/**
	 * 
	 *\post: not( \exists i \in {( getEngine().getChar(i) != this ) && getCharBox().collidesWith(getEngine().getChar(i).getCharBox()) }) 
	 *                                                              \ union ( getPositionX() == getPositionX()@pre )
	 * 
	 * \post: \ not ( ( getPositionX()@pre <= getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union      ( getPositionX() == 0 ) 
	 * 
	 * 
	* \post: \ not ( ( getPositionX()@pre > getSpeed() ) && (\forall i \in { \not ( getEngine().getchar(i) != this ) 
	 * \ union \not( getCharBox().collisionWith(getEngine().getChar(i).getCharBox()) ) }) )
	 * \ union ((getPositionX() == (getPositionX()@pre) − getSpeed()) )
	 * 
	 * \post: (faceRight() ==  faceRight()@pre ) && ( getLife() == getLife()@pre )
	 * \post: getPositionY() == getPositionY()@pre
	 * 
	 */
	
	
	@Test
	public void testMoveLeftPre1() { 
		//cas test 1.1 : cas positif Character :: testMoveLeftPre1
	

	}
	@Test
	public void testMoveLeftPre2() { //TODO
		//Cas test 1.2 : Cas negatif Character :: testMoveLeftPre2
		thrown.expect(PreconditionError.class);

	}
	
	
	
	
	
	@Before
	public abstract void beforeTests(); 

	
	@After
	public final void afterTests() {
		this.character = null;
	}
	
	
	
	public CharacterService getCharacter() {
		return character;
	}

	public void setHitBox(CharacterService character) {
		this.character = character;
	}

}

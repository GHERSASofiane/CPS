package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import error.PreconditionError;
import service.HitboxService;




public abstract class AbstractHitboxTest {

	private HitboxService hitBox;
	private int x = 10;
	private int y = 10;
	private int width = 3;
	private int height = 3;

	 // TODO pour moveTo faut il le faire car ya pas de preconditions
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	
	/** \pre: width > 0 && height > 0 TODO : j'ai rajouté **/ 
	/**
	 * \post: getPositionX() == x 
	 * \post: getPositionY() == y 
	 * \post: getWidth() == width 
	 * \post: getHeight() == height 
	 **/
	
	@Test
	public void testInitPre1() {
		//cas test 1.1 : cas positif Hitbox :: testInitPre1
		hitBox.init(x, y, width, height);
		assertTrue(hitBox.getPositionX() == x  && hitBox.getPositionY() == y );
		assertTrue(hitBox.getWidth() == width  && hitBox.getWidth() == height );
	}
	@Test
	public void testInitPre2() {
		//Cas test 1.2 : Cas negatif Hitbox :: testInitPre2
		thrown.expect(PreconditionError.class);
		hitBox.init(x, y, -1, width );
	}
	

	/**
	 * \post: getPositionX() == getPositionX()@pre + x 
	 * \post: getPositionY() == getPositionY()@pre + y 
	 * \post: \forall u:int && v:int \in { belongsTo(u,v) == belongsTo(u-(x-getPositionX()@pre),v-(y-getPositionY()@pre)) }   
	 **/

	@Test
	public void testMovePre1() {
		//cas test 1.1 : cas positif Hitbox :: testInitPre1
		hitBox.init(x, y, width, height);
		assertTrue(hitBox.getPositionX() == x  && hitBox.getPositionY() == y );
		assertTrue(hitBox.getWidth() == width  && hitBox.getWidth() == height );
	}
	@Test
	public void testMovePre2() {
		//Cas test 1.2 : Cas negatif Hitbox :: testInitPre2
		thrown.expect(PreconditionError.class);
		hitBox.init(x, y, -1, width );
	}
	
	
	
	@Before
	public abstract void beforeTests(); 

	
	@After
	public final void afterTests() {
		this.hitBox = null;
	}
	
	
	
	public HitboxService getHitBox() {
		return hitBox;
	}

	public void setHitBox(HitboxService hitBox) {
		this.hitBox = hitBox;
	}

}

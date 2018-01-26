package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import error.PreconditionError;
import impl.Commande;
import service.EngineService;
import service.CharacterService;

public abstract class AbstractEngineTest {

	private EngineService engine;
	private int height = 10;
	private int width = 10;
	private int space = 3;
	private CharacterService c1;
	private CharacterService c2;
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	

	@Test
	public void testInitPre1() {
		// TODO : (p1 != p2) 
		//cas test 1.1 : cas positif Engine :: testInitPre1
		engine.init(height, width, space, c1, c2);
		assertEquals(engine.getHeight(), height);
		assertEquals(engine.getWidth(), width);
		assertEquals(engine.getPlayer(1), c1);http://www.fightersgeneration.com/np7/sprite/pf/chunpf-sp4.gif
		assertEquals(engine.getPlayer(2), c2);
		assertEquals(engine.getChar(1).getPositionX(), (width/2) - (space/2));
		assertEquals(engine.getChar(2).getPositionX(), (width/2) - (space/2));
		assertEquals(engine.getChar(1).getPositionX(), 0);
		assertEquals(engine.getChar(2).getPositionX(), 0);
		assertTrue(engine.getChar(1).faceRight());
		assertTrue(!(engine.getChar(2).faceRight()));
		
	}
	@Test
	public void testInitPre2() {
		//Cas test 1.2 : Cas negatif Engine :: testInitPre2
		thrown.expect(PreconditionError.class);
		engine.init(height, width, -2, c1, c2);
	}
  
	
	/** \pre: not(gameOver()) **/
	/**
	 * \post: getChar(1) == getChar(1)@pre.step(C1)  
	 * \post: getChar(2) == getChar(2)@pre.step(C2)
	 **/
	@Test
	public void testStepPre1() {
		//cas test 1.1 : cas positif Engine :: testStepPre1
		
		engine.init(height, width, space, c1, c2);
		CharacterService c1_at_pre = c1.clone();
		CharacterService c2_at_pre = c2.clone();
		engine.step(Commande.LEFT, Commande.RIGHT);
		c1_at_pre.step(Commande.LEFT);
		c2_at_pre.step(Commande.LEFT);
		assertEquals(c1.getPositionX(), c1_at_pre.getPositionX() );
		assertEquals(c2.getPositionX(), c2_at_pre.getPositionX() );
	}
	@Test
	public void testStepPre2() { 
		//Cas test 1.2 : Cas negatif Engine :: testStepPre2
		thrown.expect(PreconditionError.class);
		assertEquals(engine.gameOver(),true);
	}
	

	@Before
	public abstract void beforeTests(); 

	@After
	public final void afterTests() {
		this.engine = null;
	}

	public EngineService getEngine() {
		return engine;
	}

	public void setEngine(EngineService engine) {
		this.engine = engine;
	}
}

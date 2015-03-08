package trunk.verkefni3;

import trunk.Java.FootballPlayer;

import static org.junit.Assert.*;

import org.junit.Test;


import trunk.Java.Position;

//TEST CASES:
//	

public class FootballPlayerTest {

	private FootballPlayer A, B;
	
	private final String nameA = "Gerrard";
	private final String teamNameA = "Liverpool";
	private final String positionA = "Midfielder";

	private final String nameB = "Rooney";
	private final String teamNameB = "Manchester United";
	private final String positionB = "Forward";
	
	
	public void setUp(){
		this.A = new FootballPlayer(this.nameA, this.teamNameA, this.positionA);
		this.B = new FootballPlayer(this.nameB, this.teamNameB, this.positionB);
	}
	
	
	public void tearUp(){
		this.A = null;
		this.B = null;
	}
	
	// check if FootballPlayer A have object
	// position equal to MF.
	//
	@Test
	public void testPosition(){
		assertEquals(Position.MF, A.getPosition());
	}
	

	// check if FootballPlayer B have object
	// position equal to FW.
	//
	@Test
	public void testPosition(){
		assertEquals(Position.FW, B.getPosition());
	}
	
	  
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

package trunk.verkefni3;
import trunk.Java.Position;
import trunk.Java.MockFootballPlayer;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TEST CASES:
// 	- Test position on two football players.
//		+ Position enum is tested throw those players
//	- Test Updates method on football player A.
//  - get- and set methods in FootballPlayer is not tested, they are to simple.

public class FootballPlayerTest {

	private MockFootballPlayer A, B;
	
	private final String nameA = "Gerrard";
	private final String teamNameA = "Liverpool";
	private final String positionA = "Midfielder";

	private final String nameB = "Rooney";
	private final String teamNameB = "Manchester United";
	private final String positionB = "Forward";
	
	@Before
	public void setUp(){
		this.A = new MockFootballPlayer(this.nameA, this.teamNameA, this.positionA);
		this.B = new MockFootballPlayer(this.nameB, this.teamNameB, this.positionB);
		
		this.A.setScore(40);
		this.A.setMarketValue(10);
		this.A.setPickProbability(0.7);		
	}
		
	//Testing updates method in football player A
	@Test
	public void testAUpdates(){
		this.A.updateFootballPlayer( );
		
		assertEquals(   43,  this.A.getScore()                );
		assertEquals(   14,  this.A.getMarketValue()          );
		assertEquals( 0.71, this.A.getPickProbability(), 0.0);
	}
	
	@Test
	public void testConstructorWorkA(){
		assertEquals(  "Gerrard", this.A.getName()    );
		assertEquals("Liverpool", this.A.getTeamName());
		// check if FootballPlayer A have object
		// position equal to MF ("Midfielder").
		assertEquals(Position.MF, A.getPosition()     );		
	}	
	
	@Test
	public void testConstructorWorkB(){
		assertEquals("Rooney", 		      this.B.getName()    );
		assertEquals("Manchester United", this.B.getTeamName());
		// check if FootballPlayer B have object
		// position equal to FW ("Forward").
		assertEquals(Position.FW, B.getPosition());
	}
	
	@After
	public void tearUp(){
		this.A = null;
		this.B = null;
	}
}

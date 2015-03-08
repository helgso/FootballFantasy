package trunk.verkefni3;

import trunk.Java.Position;

public class testPosition {

	private Position posGK;
	private Position posDF;
	private Position posMF;
	private Position posFW;
	
	private Position testPos;
	
	public void setUp( ){
		this.testPos = new Position( "" );
		
		this.posGK = new Position( "Goalkeeper" );
		this.posDF = new Position( "Defender"   );
		this.posMF = new Position( "Midfielder" );
		this.posFW = new Position( "Forward"    );
	}
	
	
	public void tearDown(){
		this.testPos = null;
		
		/*this.posGK = null;
		this.posDF = null;
		this.posMF = null;
		this.posFW = null;*/
	}
	@
	public void testConvertStringToEnum(){
		assertEquals(DF, this.testPos.convertStringToEnum("Defender")  );
	}	
}


/*		this.posGK = new Position( "Goalkeeper" );
this.posDF = new Position( "Defender"   );
this.posMF = new Position( "Midfielder" );
this.posFW = new Position( "Forward"    );*/

/*	private Position posGK;
private Position posDF;
private Position posMF;
private Position posFW;*/
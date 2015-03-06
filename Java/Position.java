package trunk.Java;


//HOW TO USE:

//1:	Position pos = new Position("Defender");
//2:	Position pos = new Position("DF");
//
// case 1 and 2 have same effect. pos value is enum variable DF.

public class Position {
	
	PlayerPosition position;
	
	public Position( String position ){
		this.position = this.convertStringToEnum( position );
	}

	
	//GK: goalkeeper
	//DF: defender
	//MF: midfielder
	//FW: forward
	public enum PlayerPosition {
		GK, DF, MF, FW
	}
	
	//Convert String to enum if position String compares to PlayerPosition
	//fieldposition
	public PlayerPosition convertStringToEnum( String position ){
		
		if( this.checkPositionEqualTo(position, "Goalkeeper", "GK"))
		{
			PlayerPosition x = PlayerPosition.valueOf("GK");			
			return x;
		}
		else if( this.checkPositionEqualTo(position, "Defender", "DF"))
		{
			PlayerPosition x = PlayerPosition.valueOf("DF");			
			return x;
		}
		else if( this.checkPositionEqualTo(position, "Midfielder", "MF"))
		{
			PlayerPosition x = PlayerPosition.valueOf("MF");			
			return x;
		}
		else if( this.checkPositionEqualTo(position, "Forward", "FW"))
		{
			PlayerPosition x = PlayerPosition.valueOf("FW");			
			return x;
		} 
		else return null;
	}
	
	//return true if position is equal to pos1 og pos2, otherwise false
	private Boolean checkPositionEqualTo( String position, String pos1, String pos2 ){
		if( position.equals(pos1) || position.equals(pos2))
		{
			return true;
		} else {
			return false;
		}
	}
	
}

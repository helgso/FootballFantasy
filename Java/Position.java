package trunk.Java;


public enum Position {
	
	GK("Goalkeeper"), DF("Defender"), MF("Midfielder"), FW("Forward");
	
	//pos is one of the object's String
	private final String pos;
	
	Position( String position ){
		pos = position;
	}
	
	public String getPos(){
		return pos;
	}
}
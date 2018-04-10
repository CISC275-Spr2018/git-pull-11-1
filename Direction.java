

public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest"),
	JUMPNORTH("jump north"),
	JUMPSOUTH("jump south"),
	JUMPWEST("jump west"),
	JUMPEAST("jump east"),
	FIRENORTH("fire north"),
	FIRESOUTH("fire south"),
	FIREWEST("fire west"),
	FIREEAST("fire east");
		
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}

}

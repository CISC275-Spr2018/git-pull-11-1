

public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest"),
//<<<<<<< ManuelCuesta
	JUMPNORTH("jump north"),
	JUMPSOUTH("jump south"),
	JUMPWEST("jump west"),
	JUMPEAST("jump east"),
	FIRENORTH("fire north"),
	FIRESOUTH("fire south"),
	FIREWEST("fire west"),
	FIREEAST("fire east"),
	IDLEEWNS("idel ewns");

//=======
	
	
//	JUMP_NORTH("jump_north"),
//	JUMP_SOUTH("jump_south"),
//	JUMP_WEST("jump_west"),
//	JUMP_EAST("jump_east"),
//	JUMP_NORTHWEST("jump_northwest"),
//	JUMP_SOUTHEAST("jump_southeast"),
//	JUMP_NORTHEAST("jump_northeast"),
//	JUMP_SOUTHWEST("jump_southwest");
	
	
//>>>>>>> master
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}

}

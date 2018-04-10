/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {

	final static int Incr = 10;
	final static int noIncr = 0;
	final static int xIncr = 8;
	final static int yIncr = 2;
	int picNum;
	int picDir;
	static int right;
	static int down;
	int xloc;
	int yloc;
	int frameWidth;
	int frameHeight;
	int imgWidth;
	int imgHeight;
	int buffer = -50;
	static Direction dir = Direction.SOUTHEAST;
	
	Model(int frameWidth,int frameHeight,int imgWidth,int imgHeight) {
		right = xIncr;
		down = yIncr;
		picNum = 0;
		picDir = 0;
		xloc = 0;
		yloc = 0;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}

	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}

	public static Direction getDirect() {
		return dir;
	}
	
	public static void setDirect(Direction d) {
		dir = d;
		if (dir == Direction.SOUTHWEST) {
			right = -xIncr;
			down = yIncr;
		}
		else if (dir == Direction.SOUTHEAST) {
			right = xIncr;
			down = yIncr;
		}
		else if (dir == Direction.NORTHEAST) {
			right = xIncr;
			down = -yIncr;
		}
		else if (dir == Direction.NORTHWEST) {
			right = -xIncr;
			down = -yIncr;
		}
		else if (dir == Direction.SOUTH) {
			right = noIncr;
			down = Incr;
		}
		else if (dir == Direction.NORTH) {
			right = noIncr;
			down = -Incr;
		}
		else if (dir == Direction.EAST) {
			right = Incr;
			down = noIncr;
		}
		else if (dir == Direction.WEST) {
			right = -Incr;
			down = noIncr;
		}
	}

	public int detectWall() {
		// 1-right side, 2-top side, 3-left side, 4-bottom side, 0-not on a side
		if (xloc >= frameWidth-imgWidth) {
			if (down > 0) {
				dir = Direction.SOUTHWEST;
			} else if (down == noIncr) {
				dir = Direction.WEST;
			} else {
				dir = Direction.NORTHWEST;
			}
			return 1;
		}
		else if (xloc <= buffer) {
			if (down > 0) {
				dir = Direction.SOUTHEAST;
			} else if (down == noIncr) {
				dir = Direction.EAST;
			} else {
				dir = Direction.NORTHEAST;
			}
			return 3;
		}
		else if (yloc >= frameHeight-imgHeight) {
			if (right > 0) {
				dir = Direction.NORTHEAST;
			} else if (right == noIncr) {
				dir = Direction.NORTH;
			} else {
				dir = Direction.NORTHWEST;
			}
			return 4;
		}
		else if (yloc <= buffer) {
			if (right > 0) {
				dir = Direction.SOUTHEAST;
			} else if (right == noIncr) {
				dir = Direction.SOUTH;
			} else {
				dir = Direction.SOUTHWEST;
			}
			return 2;
		}
		else { return 0;} // No wall detected 
	}

	public static void ModelReDir(int wall) {
		switch (wall) {
			case 0: break;

			case 1: if (down == noIncr) {
					right = -Incr;
				} else {
					right = -xIncr;
				}
				break;
			case 2: if (right == noIncr) {
					down = Incr;
				} else {
					down = yIncr;
				}
				break;
			case 3: if (down == noIncr) {
					right = Incr;
				} else {
					right = xIncr;
				}
				break;
			case 4: if (right == noIncr) {
					down = -Incr;
				} else {
					down = -yIncr;
				}
				break;
		}
	}

	public void UpdateLocation() {
		xloc+=right;
		yloc+=down;
	}

	public void updateLocationAndDirection() {
		ModelReDir(detectWall());
		UpdateLocation();
	}
}
	


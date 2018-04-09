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
	}

	public int detectWall() {
		// 1-right side, 2-top side, 3-left side, 4-bottom side, 0-not on a side
		if (xloc >= frameWidth-imgWidth) {
			if (down > 0) {
				dir = Direction.SOUTHWEST;
			} else {
				dir = Direction.NORTHWEST;
			}
			return 1;
		}
		else if (xloc <= buffer) {
			if (down > 0) {
				dir = Direction.SOUTHEAST;
			} else {
				dir = Direction.NORTHEAST;
			}
			return 3;
		}
		else if (yloc >= frameHeight-imgHeight) {
			if (right > 0) {
				dir = Direction.NORTHEAST;
			} else {
				dir = Direction.NORTHWEST;
			}
			return 4;
		}
		else if (yloc <= buffer) {
			if (right > 0) {
				dir = Direction.SOUTHEAST;
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

			case 1: right = -xIncr;
				break;
			case 2: down = yIncr;
				break;
			case 3: right = xIncr;
				break;
			case 4: down = -yIncr;
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
	


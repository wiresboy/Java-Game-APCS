package util;

public class Direction {
	public static final Direction UP = new Direction(), DOWN = new Direction()
			, LEFT = new Direction(), RIGHT = new Direction();
	public Direction(){}
	public static int eval(Direction d){
		if(d == UP)
			return 2;
		if(d == RIGHT)
			return 3;
		if(d == DOWN)
			return 0;
		if(d == LEFT)
			return 1;
		return -1;
	}
}

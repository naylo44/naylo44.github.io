
public class Projectile {
	static int x = 0;
	static int y = 0;
	static int angle = 0;
	static int speed = 15;
	
	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Projectile.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Projectile.y = y;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Projectile.speed = speed;
	}
	public static void setAngle(int angle) {
		Projectile.angle = angle;
	}

	Projectile() {
	}
	
	Projectile(int rotation, int x, int y) {
		setAngle(rotation);
		setX(x);
		setY(y);
	}
	
	static void MoveProjectile(Projectile e) {
		e.x-=Math.cos(Math.toRadians(angle+90))*speed;
		e.y-=Math.sin(Math.toRadians(angle+90))*speed;
	}
	
}

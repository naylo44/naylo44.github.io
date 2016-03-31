import java.util.Random;


public class Target {
	static int x = 50;
	static int y = 50;
	static int width = 30;
	static int height = 30;
	
	Target() {
		Target.x =  (int) ((int) 100+(Math.random()*400));
		Target.y = (int) ((int) 100+(Math.random()*250));
	}
	
	
	static void MoveTarget(Target e) {
		if (MainClass.direction == 1) Target.x= Target.x - 1;
		else if (MainClass.direction==0) Target.x= Target.x + 1;
		else;
	}
}
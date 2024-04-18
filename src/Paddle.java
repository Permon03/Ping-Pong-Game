import java.awt.*;

public class Paddle extends Rectangle {

    protected int velocityY;

    public Paddle(int x, int y, int width, int height, int velocityY){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocityY = velocityY;
    }
}

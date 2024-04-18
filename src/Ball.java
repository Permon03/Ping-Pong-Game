import java.awt.*;

public class Ball extends Rectangle {
    protected int radius;
    protected int velocityX;
    protected int velocityY;

    public Ball (int x, int y, int radius, int velocityX, int velocityY){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }


    public boolean intersects1(Rectangle other){
        if (this.x > other.x  && this.x < other.x + other.width) {
            return this.y > other.y && this.y < other.y + other.height;
        }
        return false;
    }
    public boolean intersects2(Rectangle other){
        if (this.x + this.radius > other.x && this.x < other.x + other.width){
            return this.y > other.y && this.y < other.y + other.height;
        }
        return false;
    }
}

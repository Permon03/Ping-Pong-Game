import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PongGame extends JPanel implements ActionListener, KeyListener {

    private final int windowWidth;
    private final int windowHeight;
    private final int radius = 20;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;

    private Random random;
    private Timer gameLoop;
    private boolean gameOver = false;
    private int score1 = 0;
    private int score2 = 0;




    public PongGame (int windowWidth, int windowHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        random = new Random();

        // Create two Paddles and Ball
        paddle1 = new Paddle (25, 250, 25, 100, 0);
        paddle2 = new Paddle(550, 250, 25, 100, 0);
        ball = new Ball(windowWidth/2 - radius/2, windowHeight/2 - radius/2, radius, random.nextInt(2) == 1? 1 : -1, random.nextInt(2) == 1? 1 : -1);

        // GameLoop
        gameLoop = new Timer(10, this);
        gameLoop.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(paddle1.x, paddle1.y, paddle1.width, paddle1.height);
        g.fillRect(paddle2.x, paddle2.y, paddle2.width, paddle2.height);
        g.fillOval(ball.x, ball.y, ball.radius, ball.radius);

        //EndGame
        if(gameOver){
            String winner;
            g.setFont(new Font("Arial", Font.BOLD, 20));
            if (ball.x < 0){
                g.setColor(Color.RED);
                winner = "Player 2 wins!";
                score2++;
            } else{
                g.setColor(Color.BLUE);
                winner = "Player 1 wins!";
                score1++;
            }
            g.drawString(winner, windowWidth/2 - 70, windowHeight/2);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString((score1/2) + " : " + (score2/2), windowWidth/2 - 10, 20);

    }

    public void move() {
        paddle1.y += paddle1.velocityY * 3;
        paddle2.y += paddle2.velocityY * 3;
        ball.x += ball.velocityX;
        ball.y += ball.velocityY;

        if(ball.x < 0 || ball.x+ball.radius > windowWidth){
            gameOver = true;
        }
    }

    public void collision (){
        if (ball.intersects1(paddle1)){
            System.out.println("Paddle 1");
            ball.velocityX *= -1;
        }
        if (ball.intersects2(paddle2)) {
            System.out.println("Paddle 2");
            ball.velocityX *= -1;
        }
        if (ball.y <= 0 || ((ball.y + ball.radius) >= windowHeight)){
            System.out.println("Wand");
            ball.velocityY *= -1;
        }

    }

    public void reset(){
        gameOver = false;
        ball = new Ball(windowWidth/2 - radius/2, windowHeight/2 - radius/2, radius, random.nextInt(2) == 1? 1 : -1, random.nextInt(2) == 1? 1 : -1);
        gameLoop.restart();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver){
            gameLoop.stop();
        }
        collision();
        move();
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> paddle1.velocityY = -1;
            case KeyEvent.VK_S -> paddle1.velocityY = 1;
            case KeyEvent.VK_UP -> paddle2.velocityY = -1;
            case KeyEvent.VK_DOWN -> paddle2.velocityY = 1;
        }
        if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE){
            reset();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_S -> paddle1.velocityY = 0;
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> paddle2.velocityY = 0;

        }
    }
}

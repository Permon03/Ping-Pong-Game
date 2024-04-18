import javax.swing.*;

public class Main {

    private static final int windowWidth = 600;
    private static final int windowHeight = 600;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PongGame pongGame = new PongGame(windowWidth, windowHeight);

        frame.add(pongGame);
        frame.pack();
        pongGame.requestFocus();
    }
}
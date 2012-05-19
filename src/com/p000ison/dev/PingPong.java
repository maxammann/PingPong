package com.p000ison.dev;

import com.p000ison.dev.entities.Ball;
import com.p000ison.dev.entities.Entity;
import com.p000ison.dev.entities.Player;
import java.awt.Font;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

/**
 *
 * @author Max
 */
public class PingPong extends BasicGame
{

    public static int WIDTH = 1280;
    public static int HEIGHT = 1024;
    public static int MAX_SCORE = 1;
    public static Font FONT;
    private Player player1;
    private Player player2;
    private Ball ball;
    private State state;
    private InputListener inputListener;
    private Line MIDDLE_LINE = new Line(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    private LinkedList<Entity> entityList = new LinkedList<Entity>();
    private Input input;
    private static AppGameContainer pong;
    private ParticleSystem left, right;
    private float lastX;
    private float lastY;

    public static void main(String[] args) throws SlickException
    {
        pong = new AppGameContainer(new PingPong());
        pong.setDisplayMode(WIDTH, HEIGHT, false);
        pong.setShowFPS(false);
        pong.setVSync(true);
        pong.setMultiSample(16);
        pong.start();
    }

    public PingPong()
    {
        super("PingPong");
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        try {
//            left = ParticleIO.loadConfiguredSystem("testdata/test.xml");
//            right = ParticleIO.loadConfiguredSystem("testdata/test.xml");
            left = ParticleIO.loadConfiguredSystem("left.xml");
            right = ParticleIO.loadConfiguredSystem("right.xml");

            right.setVisible(false);
            left.setVisible(false);

        } catch (IOException e) {
            throw new SlickException("Failed to load particle systems", e);
        }


        inputListener = new InputListener(pong);
        input = gc.getInput();

        input.addKeyListener(inputListener);

        state = State.START;

        player1 = new Player(5, HEIGHT / 2);
        player2 = new Player(WIDTH - 28, HEIGHT / 2);

        player1.setVelocityY(0f);
        player2.setVelocityY(0f);
        player1.setWidth(20);
        player2.setWidth(20);
        player1.setHeight(100);
        player2.setHeight(100);

        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        ball.setBallRadius(25);
        Random r = new Random();
        ball.setVelocityX(0.5f);
        ball.setVelocityY(((float) r.nextInt(6)) / 100.0f);

        entityList.add(ball);
        entityList.add(player1);
        entityList.add(player2);

        FONT = new Font("Verdana", Font.PLAIN, 18);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        left.update(delta);
        right.update(delta);

        if (state == State.START) {
            if (input.isKeyDown(Input.KEY_ENTER)) {
                state = State.PLAY;
            }
        }

        if (state == State.PLAYER1_WINS || state == State.PLAYER2_WINS || state != State.PLAY && state != State.BALL_IS_OUT_OF_SCREEN) {
            return;
        }

        if (player1.getShape().intersects(ball.getShape())) {
            ball.setVelocityX(Math.abs(ball.getVelocityX()));
            left.setVisible(true);
            left.reset();
            lastX = ball.getShape().getMinX();
            lastY = ball.getShape().getCenterY();
            if (new Random().nextBoolean()) {
                ball.setVelocityY(0.4f);
            } else {
                ball.setVelocityY(-0.4f);
            }

        } else if (player2.getShape().intersects(ball.getShape())) {
            ball.setVelocityX(-Math.abs(ball.getVelocityX()));
            right.setVisible(true);
            right.reset();
            lastX = ball.getShape().getMaxX();
            lastY = ball.getShape().getCenterY();
            if (new Random().nextBoolean()) {
                ball.setVelocityY(0.4f);
            } else {
                ball.setVelocityY(-0.4f);
            }
        }

        if (inputListener.getMappings().get(Input.KEY_W)) {
            player1.setVelocityY(-Player.DEFAULT_VELOCITY);
        }

        if (!inputListener.getMappings().get(Input.KEY_W) && !inputListener.getMappings().get(Input.KEY_S)) {
            player1.setVelocityY(0.0f);
        }

        if (inputListener.getMappings().get(Input.KEY_S)) {
            player1.setVelocityY(+Player.DEFAULT_VELOCITY);
        }

        if (!inputListener.getMappings().get(Input.KEY_S) && !inputListener.getMappings().get(Input.KEY_W)) {
            player1.setVelocityY(0.0f);
        }

        if (inputListener.getMappings().get(Input.KEY_UP)) {
            player2.setVelocityY(-Player.DEFAULT_VELOCITY);
        }
        if (!inputListener.getMappings().get(Input.KEY_UP) && !inputListener.getMappings().get(Input.KEY_DOWN)) {
            player2.setVelocityY(0.0f);
        }
        if (inputListener.getMappings().get(Input.KEY_DOWN)) {
            player2.setVelocityY(+Player.DEFAULT_VELOCITY);
        }
        if (!inputListener.getMappings().get(Input.KEY_DOWN) && !inputListener.getMappings().get(Input.KEY_UP)) {
            player2.setVelocityY(0.0f);
        }




        //links
        if (ball.getX() < 0) {
            ball.setVelocityX(Math.abs(ball.getVelocityX()));
            player2.addPoint();
        }
        //oben
        if (ball.getY() < 0) {
            ball.setVelocityY(-ball.getVelocityY());
        }
        //rechts
        if (ball.getX() > WIDTH) {
            ball.setVelocityX(-Math.abs(ball.getVelocityX()));
            player1.addPoint();
        }
        //unten
        if (ball.getY() > HEIGHT) {
            ball.setVelocityY(-ball.getVelocityY());
        }

        if (player1.getPoints() >= MAX_SCORE) {
            state = State.PLAYER1_WINS;
        }

        if (player2.getPoints() >= MAX_SCORE) {
            state = State.PLAYER2_WINS;
        }


        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                if (entity.getY() + entity.getVelocityY() * delta > 0 && entity.getY() + entity.getVelocityY() * delta + entity.getHeight() < HEIGHT) {
                    entity.setY(entity.getY() + entity.getVelocityY() * delta);
                }
            } else {
                entity.setX(entity.getX() + entity.getVelocityX() * delta);
                entity.setY(entity.getY() + entity.getVelocityY() * delta);
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.white);
        if (state == State.START) {
            g.drawString("[Press Enter to Start]", WIDTH / 2, HEIGHT / 2);
        }

        if (state == State.PLAYER1_WINS) {
            g.drawString("Player 1 has won!", WIDTH / 2, HEIGHT / 2);
            if (input.isKeyDown(Input.KEY_R)) {
                resetGame();
                state = State.START;
            }
            return;
        } else if (state == State.PLAYER2_WINS) {
            g.drawString("Player 2 has won!", WIDTH / 2, HEIGHT / 2);
            if (input.isKeyDown(Input.KEY_R)) {
                resetGame();
                state = State.START;
            }
            return;
        }

        left.setPosition(lastX, lastY);
        right.setPosition(lastX, lastY);

        left.render();
        right.render();

        g.drawString("Points: " + player1.getPoints(), 20, 20);
        g.drawString("Points: " + player2.getPoints(), WIDTH - 100, 20);

        g.setColor(Color.white);
        g.fillRect(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
        g.fillRect(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
        g.fillOval(ball.getShape().getX(), ball.getShape().getY(), ball.getShape().getWidth(), ball.getShape().getHeight());
        g.setColor(Color.yellow);
        g.drawRect(player1.getShape().getX(), player1.getShape().getY(), player1.getShape().getWidth(), player1.getShape().getHeight());
        g.drawRect(player2.getShape().getX(), player2.getShape().getY(), player2.getShape().getWidth(), player2.getShape().getHeight());
        g.setColor(Color.orange);
        g.draw(MIDDLE_LINE);
        left.render();


        //g.drawRect(ball.getShape().getX(), ball.getShape().getY(), ball.getShape().getWidth(), ball.getShape().getHeight());
    }

    public void resetGame()
    {
        for (Entity entity : entityList) {
            entity.reset();
        }
    }
}

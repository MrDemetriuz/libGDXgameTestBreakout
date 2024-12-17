package io.github.gameTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    Ball ball;
    Paddle paddle;
    ShapeRenderer shape;
    ArrayList <Brick> bricks = new ArrayList<>();

    @Override
    public void create() {
        shape = new ShapeRenderer();

        ball = new Ball(50,Gdx.graphics.getHeight()/2,3,3,10);
        paddle = new Paddle(100,10,100,15);

        int brickWidth = 63;
        int brickHeight = 20;

        for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += brickHeight + 10){
            for (int x = 0; x < Gdx.graphics.getWidth();x += brickWidth + 10){
                bricks.add(new Brick(x,y,brickWidth,brickHeight));
            }

        }
    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shape.begin(ShapeRenderer.ShapeType.Filled);

        ball.draw(shape);
        ball.update();
        ball.checkCollision(paddle);

        paddle.draw(shape);
        paddle.update();

        for (Brick brick : bricks){
            brick.draw(shape);
            ball.checkCollisionBrick(brick);
        }

        for (int i = 0; i < bricks.size(); i++){
            Brick brick = bricks.get(i);
            if (brick.destroyed){
                bricks.remove(brick);
                i--;
            }

        }

        shape.end();
    }


    @Override
    public void dispose() {
        shape.dispose();
    }
}

package io.github.gameTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int xSpeed;
    int ySpeed;
    int size;
    Color color;


    public Ball(int x, int y, int xSpeed, int ySpeed, int size) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.size = size;
        this.color = Color.BLUE;
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;

        if (x - size <= 0 || x + size >= Gdx.graphics.getWidth()){
            xSpeed = -xSpeed;
        }

        if ( y + size >= Gdx.graphics.getHeight()){
            ySpeed = -ySpeed;
        }
        if (y - size <= 0 ){
            Gdx.app.exit();
            //ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x, y, size);
        shape.setColor(color);
    }

    public boolean isMovingRight(int speed){
        boolean positive = false;
        return (positive = Math.signum(speed) == 1.0);
    }

    public boolean rightOf(Paddle paddle){
        return (x > (paddle.x + paddle.width/2));
    }

    public boolean rightOf(Brick brick){
        return (x > (brick.x + brick.width/2));
    }


    public boolean collidesWith(Paddle paddle){
        return (x + size >= paddle.x  &&
                x - size <= paddle.x+ paddle.width &&
                y + size >= paddle.y &&
                y - size <= paddle.y + paddle.height );
    }

    public boolean collidesWith(Brick brick){
        return (x + size >= brick.x  &&
            x - size <= brick.x + brick.width &&
            y + size >= brick.y &&
            y - size <= brick.y + brick.height );
    }


    public void checkCollision(Paddle paddle){
        if (collidesWith(paddle)){
            color = Color.BLUE;
            if (rightOf(paddle)){
                if (!isMovingRight(xSpeed)){
                    xSpeed = - xSpeed;
                }
                ySpeed = -ySpeed;
            }
            if (!rightOf(paddle)){
                color = Color.RED;
                if (isMovingRight(xSpeed)){
                    xSpeed = - xSpeed;
                }
                ySpeed = -ySpeed;
            }
        }

    }

    public void checkCollisionBrick(Brick brick){
        if (collidesWith(brick)){
            color = Color.BLUE;
            if (rightOf(brick)){
                if (!isMovingRight(xSpeed)){
                    xSpeed = - xSpeed;
                }
                ySpeed = -ySpeed;
                brick.destroyed = true;

            }
            if (!rightOf(brick)){
                color = Color.RED;
                if (isMovingRight(xSpeed)){
                    xSpeed = - xSpeed;
                }
                ySpeed = -ySpeed;
                brick.destroyed = true;
            }
        }

    }

}

package com.p000ison.dev.entities;

import com.p000ison.dev.PingPong;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Max
 */
public class Ball extends Entity
{

    private float ballRadius = 50;

    public Ball(int x, int y)
    {
        super(x, y);
        
    }

    /**
     * @return the shape
     */
    public Shape getShape()
    {
        return new Circle(getX(), getY(), getBallRadius());
    }

    /**
     * @return the ballRadius
     */
    public float getBallRadius()
    {
        return ballRadius;
    }

    /**
     * @param ballRadius the ballRadius to set
     */
    public void setBallRadius(float ballRadius)
    {
        this.ballRadius = ballRadius;
    }
    
    @Override
    public void reset() {
        setX(PingPong.WIDTH/2);
        setY(PingPong.HEIGHT/2);
    }
}

package com.p000ison.dev.entities;

import com.p000ison.dev.PingPong;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Max
 */
public class Player extends Entity
{

    private int points;
    public static float DEFAULT_VELOCITY = 0.5f;

    public Player(int x, int y)
    {
        super( x, y);
        points = 0;
    }

    /**
     * @return the points
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points)
    {
        this.points = points;
    }

    public void addPoint()
    {
        setPoints(getPoints() + 1);
    }

    /**
     * @return the shape
     */
    public Rectangle getShape()
    {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
    
    @Override
    public void reset() {
        setPoints(0);
        setY(PingPong.WIDTH/2);
    }
}

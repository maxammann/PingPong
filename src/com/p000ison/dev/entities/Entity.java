package com.p000ison.dev.entities;

/**
 *
 * @author Max
 */
public class Entity
{

    protected float x;
    protected float y;
    protected float velocityY;
    protected float velocityX;
    protected float width;
    protected float height;

    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the velocityX
     */
    public float getVelocityX()
    {
        return velocityX;
    }

    /**
     * @param velocityX the velocityX to set
     */
    public void setVelocityX(float velocityX)
    {
        this.velocityX = velocityX;
    }

    /**
     * @return the x
     */
    public float getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x)
    {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY()
    {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y)
    {
        this.y = y;
    }

    /**
     * @return the velocityY
     */
    public float getVelocityY()
    {
        return velocityY;
    }

    /**
     * @param velocityY the velocityY to set
     */
    public void setVelocityY(float velocityY)
    {
        this.velocityY = velocityY;
    }

    /**
     * @return the width
     */
    public float getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public float getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height)
    {
        this.height = height;
    }

    public void reset() {
        
    }
}

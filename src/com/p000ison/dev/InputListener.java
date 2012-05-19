package com.p000ison.dev;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 *
 * @author Max
 */
public class InputListener implements KeyListener
{

    private Map<Integer, Boolean> mappings = new HashMap<Integer, Boolean>();
    private AppGameContainer pingpong;

    public InputListener(AppGameContainer pingpong)
    {
        this.pingpong = pingpong;
        mappings.put(Input.KEY_W, Boolean.FALSE);
        mappings.put(Input.KEY_S, Boolean.FALSE);
        mappings.put(Input.KEY_UP, Boolean.FALSE);
        mappings.put(Input.KEY_DOWN, Boolean.FALSE);
    }

    @Override
    public void keyPressed(int key, char c)
    {
        if (getMappings().containsKey(key)) {
            getMappings().put(key, Boolean.TRUE);
        } else if (key == Input.KEY_ESCAPE) {
            pingpong.setPaused(!pingpong.isPaused());
        }
        
    }

    @Override
    public void keyReleased(int key, char c)
    {
        if (getMappings().containsKey(key)) {
            getMappings().put(key, Boolean.FALSE);
        }
    }

    @Override
    public void setInput(Input input)
    {
    }

    @Override
    public boolean isAcceptingInput()
    {
        return true;
    }

    @Override
    public void inputEnded()
    {
    }

    @Override
    public void inputStarted()
    {
    }

    /**
     * @return the mappings
     */
    public Map<Integer, Boolean> getMappings()
    {
        return mappings;
    }
}

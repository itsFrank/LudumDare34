package core.rendering;

import data.Renders;
import org.newdawn.slick.Color;

import java.util.HashMap;

/**
 * Created by Francis O'Brien - 12/12/2015 - 6:16 AM
 */

public class StateRender implements Render{

    private int currentState = 0;
    private int width, height = 0;
    private boolean animation = false;
    private HashMap<Integer, Render> renders;


    public StateRender(){
        renders = new HashMap<>();
    }

    public void addState(int stateID, Render render){
        if (renders.get(stateID) != null) throw new RuntimeException("StateID: " + stateID + " already assigned");
        renders.put(stateID, render);
        if (render.isAnimation()) animation = true;
        width = render.getWidth();
        height = render.getHeight();
    }

    public void enterState(int id){
        if (currentState == id) return;
        currentState = id;
        if (renders.get(id).isAnimation()) ((Animation) renders.get(id)).reset();
    }


    @Override
    public void draw(float x, float y) {
        renders.get(currentState).draw(x, y);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isAnimation() {
        return animation;
    }

    @Override
    public void draw(float x, float y, Color color) {
        renders.get(currentState).draw(x, y, color);
    }
}

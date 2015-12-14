package core.rendering;

import org.newdawn.slick.Color;

/**
 * Created by Francis O'Brien - 12/13/2015 - 10:27 AM
 */

public class SwapRender implements Render {

    private Texture start, swap;
    boolean swapped = false;

    public SwapRender(Texture start, Texture swap) {
        this.start = start;
        this.swap = swap;
    }

    @Override
    public void draw(float x, float y) {
        if (swapped) swap.draw(x, y);
        else start.draw(x, y);
    }

    public void swap(){
        swapped = !swapped;
    }

    @Override
    public int getWidth() {
        return start.getWidth();
    }

    @Override
    public int getHeight() {
        return start.getHeight();
    }

    @Override
    public boolean isAnimation() {
        return false;
    }

    @Override
    public void draw(float x, float y, Color color) {
        if (swapped) swap.draw(x, y, color);
        else start.draw(x, y, color);
    }
}

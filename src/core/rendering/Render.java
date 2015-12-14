package core.rendering;

import org.newdawn.slick.Color;

/**
 * Created by Francis O'Brien - 12/12/2015 - 2:41 AM
 */

public interface Render {

    void draw(float x, float y);
    int getWidth();
    int getHeight();
    boolean isAnimation();

    void draw(float x, float y, Color color);
}

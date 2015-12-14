package entities;

import core.gamestates.Play;
import core.rendering.Render;
import util.Box;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 2/12/2015 - 9:40 PM
 */

public abstract class Entity {

    protected vec2 pos;
    protected Box box;
    protected Render render;
    private boolean drawon = true;
    private Play playState;


    public Entity(Render render){
        this.render = render;

        pos = new vec2();
        box = new Box(pos, render);
    }

    public abstract void update(int delta);
    public abstract void preDraw();

    public void draw(){
        if (drawon) {
            preDraw();
            render.draw(pos.x, pos.y);
        }
    }

    public vec2 getPos(){
        return pos;
    }

    public Box getBox(){
        return box;
    }

    protected void setRender(Render render){
        this.render = render;
    }

    public boolean isWithin(vec2 position) {
        return box.isWithin(position);
    }

    public void setPlayState(Play playState) {
        this.playState = playState;
    }

    public void drawoff(){
        drawon = false;
    }

    public void drawon(){
        drawon = true;
    }
}

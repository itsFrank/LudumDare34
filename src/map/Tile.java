package map;

import core.rendering.Texture;
import org.newdawn.slick.Color;
import util.Box;
import util.vectors.ivec2;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 3:13 PM
 */

public class Tile {

    private boolean buildeable = true;
    private boolean walkable = true;
    private ivec2 ij;
    private vec2 pos;

    private Texture texture;
    private Box box;

    public Tile(ivec2 ij, Texture texture) {
        this.ij = ij;
        this.pos = new vec2(ij.x * 64, ij.y * 64);
        this.texture = texture;
        box = new Box(pos, texture);
    }

    public void draw(){
        texture.draw(pos.x, pos.y);
    }

    public void draw(Color filter){
        texture.draw(pos.x, pos.y, filter);
    }



    public ivec2 getIJ(){
        return ij;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public Box box() {
        return box;
    }

    public boolean isBuildeable() {
        return buildeable;
    }

    public void setBuildeable(boolean buildeable) {
        this.buildeable = buildeable;
    }

    public vec2 getPos() {
        return pos;
    }
}

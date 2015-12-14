package core.rendering;

import org.newdawn.slick.Color;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 12/12/2015 - 2:30 AM
 */

public class Animation implements Render{

    protected int currentFrame = 0;
    protected int numFrames = 0;
    protected long frameStart;
    protected ArrayList<Frame> frames;

    public Animation(){
        this.frames = new ArrayList<>();
        frameStart = System.currentTimeMillis();
    }

    public void addFrame(Frame frame){
        this.frames.add(frame);
        frameStart = System.currentTimeMillis();
        numFrames++;
    }

    public void addFrame(Texture texture, int duration){
        this.frames.add(new Frame(texture, duration));
        frameStart = System.currentTimeMillis();
        numFrames++;
    }

    @Override
    public int getWidth(){ return frames.get(0).getTexture().getWidth(); }
    @Override
    public int getHeight(){ return frames.get(0).getTexture().getHeight(); }

    @Override
    public boolean isAnimation() {
        return true;
    }

    @Override
    public void draw(float x, float y, Color color) {
        long difference = System.currentTimeMillis() - frameStart;
        if (difference > frames.get(currentFrame).duration) {
            currentFrame = (currentFrame + 1) % numFrames;
            frameStart = System.currentTimeMillis();
        }
        frames.get(currentFrame).getTexture().draw(x, y, color);
    }

    public Texture getTexture(int i){ return frames.get(i).getTexture(); }

    @Override
    public void draw(float x, float y) {
        long difference = System.currentTimeMillis() - frameStart;
        if (difference > frames.get(currentFrame).duration) {
            currentFrame = (currentFrame + 1) % numFrames;
            frameStart = System.currentTimeMillis();
        }
        frames.get(currentFrame).getTexture().draw(x, y);
    }

    public void reset() {
        currentFrame = 0;
        frameStart = System.currentTimeMillis();
    }


    //FRAME SUBCLASS
    public class Frame {
        private int duration;
        private Texture texture;

        public Frame(Texture texture, int duration) {
            this.duration = duration;
            this.texture = texture;
        }

        public int getDuration() {
            return duration;
        }

        public Texture getTexture() {
            return texture;
        }
    }

}

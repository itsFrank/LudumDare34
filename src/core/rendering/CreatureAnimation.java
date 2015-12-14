package core.rendering;

import util.vectors.ivec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 12/12/2015 - 2:30 AM
 */

public class CreatureAnimation extends Animation {

    private ArrayList<Item> items;

    public CreatureAnimation(){
        super();
        items = new ArrayList<>();
    }

    public void addItem(Texture texture, int offx, int offy){
        items.add(new Item(texture, offx, offy));
    }

    @Override
    public void draw(float x, float y) {
        long difference = System.currentTimeMillis() - frameStart;
        if (difference > frames.get(currentFrame).getDuration()) {
            currentFrame = (currentFrame + 1) % numFrames;
            frameStart = System.currentTimeMillis();
        }
        frames.get(currentFrame).getTexture().draw(x, y);

        for (Item i : items){
            i.draw(x, y, currentFrame);
        }
    }


    private class Item {
        private Texture texture;
        private ivec2 baseOffset;
        private int dropFrame = 3;

        public Item(Texture texture, int off_x, int off_y){
            this.texture = texture;
            baseOffset = new ivec2(off_x, off_y);
        }

        public void draw(float x, float y, int frame){
            int dropoffset = frame == dropFrame ? -4 : 0;
            texture.draw(x + baseOffset.x, y + baseOffset.y - dropoffset);
        }
    }

}

package core.rendering;

import util.vectors.ivec2;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 12/12/2015 - 6:48 AM
 */

public class ItemTexture extends Texture {

    private ArrayList<Item> items;

    public ItemTexture(Texture texture) {
        super(texture.getImage());
        items = new ArrayList<>();
    }

    public void addItem(Texture texture, int offx, int offy){
        items.add(new Item(texture, offx, offy));
    }

    @Override
    public void draw(float x, float y){
        image.draw(x, y);

        for (Item i : items){
            i.draw(x, y);
        }
    }

    private class Item {
        private Texture texture;
        private ivec2 baseOffset;

        public Item(Texture texture, int off_x, int off_y){
            this.texture = texture;
            baseOffset = new ivec2(off_x, off_y);
        }

        public void draw(float x, float y){
            texture.draw(x + baseOffset.x, y + baseOffset.y);
        }
    }
}

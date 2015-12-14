package gui.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 4/7/2015 - 12:13 AM
 */

public class TextBox {

    private String value = "";
    private vec2 pos;

    private int width, height;
    private boolean selected = false;

    private Graphics graphics;

    private static Color UNSELECTED = new Color(86, 179, 255);
    private static Color SELECTED = new Color(255, 161, 85);
    private static Color BACKGROUND = new Color(128, 128, 128);
    private static Color TEXT = new Color(255, 141, 65);

    public TextBox(Graphics g, float x, float y, int width, int height){
        graphics = g;
        pos = new vec2(x, y);
        this.width = width;
        this.height = height;
    }

    public void draw() {
        if (selected){
            graphics.setColor(SELECTED);
            graphics.fillRect(pos.x - 2, pos.y - 2 , width + 4, height + 4);
        }else{
            graphics.setColor(UNSELECTED);
            graphics.fillRect(pos.x - 2, pos.y - 2 , width + 4, height + 4);
        }

        graphics.setColor(BACKGROUND);
        graphics.fillRect(pos.x, pos.y, width, height);

        graphics.setColor(TEXT);
        graphics.drawString(value, pos.x + 2, pos.y - 1);
    }

    public void select(){
        selected = true;
    }

    public void unSelect(){
        selected = false;
    }

    public void setText(String string){
        value = string;
    }

    public void enterValue(char c){
        value += c;
    }

    public void delete(){
        if (value.length() > 0) {
            value = value.substring(0, value.length() - 1);
        }
    }

    public String getText(){
        return value;
    }

    public float getX(){
        return pos.x;
    }
    public float getY(){
        return pos.y;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}

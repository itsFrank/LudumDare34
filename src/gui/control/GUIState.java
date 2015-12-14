package gui.control;

import gui.elements.GUIElement;
import org.newdawn.slick.Graphics;
import util.MouseWrapper;

/**
 * Created by Francis O'Brien - 12/12/2015 - 8:48 PM
 */

public abstract class GUIState {

    protected final int ID;

    protected GUIController controller;
    protected MouseWrapper mouse;
    protected GUIOverlay currentOverlay;

    public GUIState(int id){
        ID = id;
    }

    public abstract void init();
    public abstract void enter();
    public abstract void exit();
    protected abstract void buttonClicked(int buttonID, int mouseButton, int clickCount);
    public abstract void addElement(GUIElement e);
    public abstract void update();
    public abstract void mouseClicked(int mouseButton, int clickCount, boolean fromOverlay);
    public abstract void draw(Graphics graphics);
    public abstract void textRender(Graphics graphics);
    public abstract void keyPressed(int key, char c);

    public int getID(){ return ID; }

    public void setController(GUIController controller){
        this.controller = controller;
    }
    public void setMouse(MouseWrapper mouse){
        this.mouse = mouse;
    }

    public void closeOverlay(){
        currentOverlay = null;
    }



}

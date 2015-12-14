package gui.control;

import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import gui.elements.buttons.DoublePNGButton;
import org.newdawn.slick.Graphics;
import util.MouseWrapper;

import java.util.ArrayList;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:11 AM
 */

public abstract class GUISingleState extends GUIState {

    protected GUIMultiState parent;
    protected ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
    protected ArrayList<Button> buttons = new ArrayList<Button>();

    public GUISingleState(int id){
        super(id);
    }

    public int getID(){ return ID; }

    public void setController(GUIController controller){
        this.controller = controller;
    }

    public void setMouse(MouseWrapper mouse){
        this.mouse = mouse;
    }

    public void addElement(GUIElement e){
        if (e instanceof Button) buttons.add(((Button) e).getID(), (Button) e);
        else elements.add(e);
    }

    public void setParent(GUIMultiState parent) {
        this.parent = parent;
    }

    public void update(){
        for (Button b : buttons){
            b.isMouseWithin(mouse);
        }
        if (currentOverlay != null){
            currentOverlay.update();
        }
    }

    public void allUp(){
        for (Button b : buttons){
            if (b instanceof  DoublePNGButton){
                ((DoublePNGButton) b).up();
            }
        }
    }

    public DoublePNGButton getDPB(int id){
        for (Button b : buttons){
            if (b instanceof  DoublePNGButton){
                if (b.getID() == id) return (DoublePNGButton) b;
            }
        }
        return null;
    }

    protected void customMouseClicked(int mouseButton, int clickCount, boolean fromOverlay){}

    public void mouseClicked(int mouseButton, int clickCount, boolean fromOverlay){
        customMouseClicked(mouseButton, clickCount, fromOverlay);
        for (Button b : buttons){
            if (b.mouseClicked(mouseButton, clickCount)) buttonClicked(b.getID(), mouseButton, clickCount);
        }
    }

    public void draw(Graphics graphics){
        for (GUIElement e : elements){
            e.draw();
        }

        for (Button b : buttons) {
            b.draw();
        }

        textRender(graphics);
    }

    public void closeOverlay(){
        currentOverlay = null;
    }

    public void keyPressed(int key, char c) {

    }
}

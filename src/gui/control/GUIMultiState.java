package gui.control;

import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import gui.elements.buttons.DoublePNGButton;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:11 AM
 */

public abstract class GUIMultiState extends GUIState {

    protected ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
    protected ArrayList<Button> buttons = new ArrayList<Button>();

    protected GUISingleState current_state;
    protected HashMap<Integer, GUISingleState> subStates;

    public GUIMultiState(int id){
        super(id);
        subStates = new HashMap<>();
    }

    protected abstract void customUpdate();
    protected abstract void customDraw(Graphics graphics);
    protected abstract void customMouseClicked(int mouseButton, int clickCount, boolean fromOverlay);

    public void enterState(int id){
        if (current_state != null) current_state.exit();
        current_state = subStates.get(id);
        current_state.enter();
    }

    public void addSubState(int id, GUISingleState state){
        subStates.put(id, state);
        state.setParent(this);
        state.setController(controller);
        state.setMouse(mouse);
        state.init();
    }

    public void addElement(GUIElement e, int subStateID){
        subStates.get(subStateID).addElement(e);
    }

    public DoublePNGButton getDPB(int id){
        for (Button b : buttons){
            if (b instanceof  DoublePNGButton){
                if (b.getID() == id) return (DoublePNGButton) b;
            }
        }
        return null;
    }

    @Override
    public void update(){
        customUpdate();

        for (Button b : buttons){
            b.isMouseWithin(mouse);
        }

        current_state.update();
    }

    public void mouseClicked(int mouseButton, int clickCount, boolean fromOverlay){
        customMouseClicked(mouseButton, clickCount, fromOverlay);

        for (Button b : buttons){
            if (b.mouseClicked(mouseButton, clickCount)) buttonClicked(b.getID(), mouseButton, clickCount);
        }

        current_state.mouseClicked(mouseButton, clickCount, fromOverlay);
    }

    public void draw(Graphics graphics){
        customDraw(graphics);

        for (GUIElement e : elements){
            e.draw();
        }
        for (Button b : buttons) {
            b.draw();
        }

        textRender(graphics);

        current_state.draw(graphics);
    }

    public void addElement(GUIElement e){
        if (e instanceof Button) buttons.add(((Button) e).getID(), (Button) e);
        else elements.add(e);
    }
}

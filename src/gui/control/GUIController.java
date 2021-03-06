package gui.control;

import entities.creatures.Attributes;
import gui.control.states.Play_gui;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import util.MouseWrapper;

import java.util.HashMap;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:06 AM
 */

public class GUIController {

    private MouseWrapper mouse;
    private GameContainer gameContainer;
    private StateBasedGame stateBasedGame;

    private GUIState currentstate;
    private HashMap<Integer, GUIState> states = new HashMap<>();


    public GUIController(GameContainer gameContainer, StateBasedGame stateBasedGame, MouseWrapper mouse){
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;
        this.mouse = mouse;
    }

    public void update(){
        currentstate.update();
    }

    public void render(Graphics graphics){
        currentstate.draw(graphics);
    }

    public void mouseClicked(int mouseButton, int clickCount){
        currentstate.mouseClicked(mouseButton, clickCount, false);
    }

    public void addState(GUIState state){
        state.setController(this);
        state.setMouse(mouse);
        state.init();
        states.put(state.getID(), state);
    }

    public void enterState(int stateID){
        if (currentstate != null) {currentstate.exit();}
        currentstate = states.get(stateID);
        currentstate.enter();
    }

    public GameContainer getGameContainer(){ return gameContainer; }
    public StateBasedGame getStateBasedGame(){ return stateBasedGame; }

    public void keyPressed(int key, char c) {
        currentstate.keyPressed(key, c);
    }

    public void childrenpopup(Attributes[] attr, int[] colors, boolean[] isMale) {
        ((Play_gui) currentstate).childrenpopup(attr, colors, isMale);
    }
}

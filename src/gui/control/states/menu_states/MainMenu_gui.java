package gui.control.states.menu_states;

import core.Core;
import data.Renders;
import gui.control.GUISingleState;
import gui.control.states.GUIStateIDs;
import gui.elements.buttons.MenuButton;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/7/2015 - 8:51 AM
 */

public class MainMenu_gui extends GUISingleState {

    private final int PLAY_ID = 0;
    private final int SETTINGS_ID = 1;
    private final int CONTROLS_ID = 2;
    private final int EXIT_ID = 3;
    private final int EDITOR_ID = 4;



    public MainMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        addElement(new MenuButton(PLAY_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.PLAY_BUTTON_TEXTURE.getWidth() / 2), 50), Renders.PLAY_BUTTON_TEXTURE));
        addElement(new MenuButton(SETTINGS_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.SETTINGS_BUTTON_TEXTURE.getWidth() / 2), 175), Renders.SETTINGS_BUTTON_TEXTURE));
        addElement(new MenuButton(CONTROLS_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.CONTROLS_BUTTON_TEXTURE.getWidth() / 2), 300), Renders.CONTROLS_BUTTON_TEXTURE));
        addElement(new MenuButton(EXIT_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.EXIT_BUTTON_TEXTURE.getWidth() / 2), 575), Renders.EXIT_BUTTON_TEXTURE));
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        switch (buttonID){
            case PLAY_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.getStateBasedGame().enterState(Core.PLAY);
                }
                break;
            case SETTINGS_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.SETTINGS.ID);
                }
                break;
            case CONTROLS_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.CONTROLS.ID);
                }
                break;
            case EXIT_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.getGameContainer().exit();
                }
                break;
        }
    }

    @Override
    public void textRender(Graphics graphics) {

    }
}

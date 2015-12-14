package gui.control.states.menu_states;

import data.Renders;
import gui.control.GUISingleState;
import gui.control.states.GUIStateIDs;
import gui.elements.non_interfaceable.Tag;
import gui.elements.buttons.MenuButton;
import gui.elements.buttons.Toggle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import util.vectors.vec2;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class SettingsMenu_gui extends GUISingleState {
    private final int BACK_ID = 0;
    private final int TOGGLE_1_ID = 1;
    private final int TOGGLE_2_ID = 2;

    boolean vsync;
    boolean fullscreen;


    public SettingsMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(BACK_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.BACK_BUTTON_TEXTURE.getWidth() / 2), 500), Renders.BACK_BUTTON_TEXTURE));
        addElement(new Toggle(TOGGLE_1_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.TOGGLE_OFF_TEXTURE.getWidth() / 2) + 200, 100 - (Renders.TOGGLE_OFF_TEXTURE.getHeight() / 2)), Renders.TOGGLE_OFF_TEXTURE, Renders.TOGGLE_ON_TEXTURE, vsync));
        addElement(new Toggle(TOGGLE_2_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.TOGGLE_OFF_TEXTURE.getWidth() / 2) + 200, 300 - (Renders.TOGGLE_OFF_TEXTURE.getHeight() / 2)), Renders.TOGGLE_OFF_TEXTURE, Renders.TOGGLE_ON_TEXTURE, fullscreen));

        /// Add tags
        addElement(new Tag(new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.VSYNC_TAG_TEXTURE.getWidth() / 2), 100 - (Renders.VSYNC_TAG_TEXTURE.getHeight() / 2)), Renders.VSYNC_TAG_TEXTURE, new Color(160, 160, 160)));
        addElement(new Tag(new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.FULLSCREEN_TAG_TEXTURE.getWidth() / 2), 300 - (Renders.FULLSCREEN_TAG_TEXTURE.getHeight() / 2)), Renders.FULLSCREEN_TAG_TEXTURE, new Color(160, 160, 160)));

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
            case BACK_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON){
                    controller.enterState(GUIStateIDs.MAIN_MENU.ID);
                }
                break;
            case TOGGLE_1_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON && clickCount == 1){
                    vsync = !vsync;
                    controller.getGameContainer().setVSync(vsync);
                    System.out.println(vsync);
                }
                break;
            case TOGGLE_2_ID:
                if (mouseButton == Input.MOUSE_LEFT_BUTTON && clickCount == 1){
                    fullscreen = !fullscreen;
                    try {
                        controller.getGameContainer().setFullscreen(fullscreen);
                    }catch (SlickException e){
                        e.printStackTrace();
                        System.out.println("Attempted to toggle fullscreen but failed");
                        System.exit(1);
                    }
                    System.out.println(vsync);
                }
                break;

        }
    }

    @Override
    public void textRender(Graphics graphics) {

    }
}

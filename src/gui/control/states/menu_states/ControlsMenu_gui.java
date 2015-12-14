package gui.control.states.menu_states;

import data.Renders;
import gui.control.GUISingleState;
import gui.control.states.GUIStateIDs;
import gui.elements.GUIElement;
import gui.elements.buttons.Button;
import gui.elements.buttons.MenuButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import util.vectors.vec2;


/**
 * Created by Francis O'Brien - 1/7/2015 - 1:35 PM
 */

public class ControlsMenu_gui extends GUISingleState {
    private final int BACK_ID = 0;

    boolean vsync;
    boolean fullscreen;


    public ControlsMenu_gui(int id){
        super(id);
    }

    @Override
    public void init() {
        vsync = controller.getGameContainer().isVSyncRequested();
        fullscreen = controller.getGameContainer().isFullscreen();

        /// Add buttons
        addElement(new MenuButton(BACK_ID, new vec2((controller.getGameContainer().getWidth() / 2) - (Renders.BACK_BUTTON_TEXTURE.getWidth() / 2), 500), Renders.BACK_BUTTON_TEXTURE));

        /// Add tags

    }

    @Override
    public void draw(Graphics graphics){

        graphics.setColor(Color.white);
        graphics.drawString("Welcome to Alien Breeder, a game of genetics and management (limited management)", 100, 50);
        graphics.drawString("To view an alien's traits, simply click on him/her (girls have bows on their heads)", 100, 75);

        graphics.drawString("You only start with aliens, to breed them send two to the mating chamber", 100, 125);
        graphics.drawString("To do so, click on an alien and the click on the heart button that appears", 100, 150);
        graphics.drawString("Look at their traits to try and select good ones", 100, 175);

        graphics.drawString("For your aliens to be useful, they must work. To do so, click on one and the the luggage button", 100, 225);
        graphics.drawString("There are two professions:", 100, 250);
        graphics.drawString("\tScientist: will man the tech lab and research you selected tech", 150, 275);
        graphics.drawString("\tBuilder / Miner: will build buildings and man the drill to generate credits", 150, 300);
        graphics.drawString("\t*Aliens can not breed once they have been given a profession", 100, 325);

        graphics.drawString("Space pauses, ESC returns to the menu to restart the game", 100, 375);
        graphics.drawString("To build or research, click the BUILD / TECH buttons on the right", 100, 400);
        graphics.drawString("The only available tech at the moment allows you to determine with more precision the traits of your aliens", 100, 425);


        for (GUIElement e : elements){
            e.draw();
        }
        for (Button b : buttons) {
            b.draw();
        }

        if (currentOverlay != null){
            currentOverlay.draw(graphics);
        }
    }

    @Override
    public void textRender(Graphics graphics) {

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

        }
    }
}

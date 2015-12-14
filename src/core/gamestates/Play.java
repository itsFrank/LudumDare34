/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import core.Core;
import core.rendering.SwapRender;
import data.GameVars;
import data.Messages;
import data.Renders;
import entities.Player;
import entities.buildings.Drill;
import entities.buildings.MatingBldg;
import entities.buildings.TechLab;
import entities.creatures.Attributes;
import entities.creatures.Creature;
import entities.creatures.CreatureValues;
import entities.managers.BuildingManager;
import entities.managers.CreatureManager;
import gui.control.GUIController;
import gui.control.states.GUIStateIDs;
import gui.control.states.Play_gui;
import map.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import util.MouseWrapper;
import util.vectors.vec2;

public class Play extends BasicGameState {
    private int stateID;

    public static Player player;
    public Map map;
    private MouseWrapper mouse;

    private GameContainer gameContainer;
    private StateBasedGame stateBasedGame;
    public CreatureManager creatureManager;
    public BuildingManager buildingManager;
    private GUIController guiController;

    public MatingBldg mating_building;

    private boolean menu = false;
    private boolean paused = false;

    private SwapRender play_pause;

    /// Testing ///



    /// End Testing ///

    public Play(int ID){
        stateID = ID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        mouse = new MouseWrapper(gameContainer.getHeight());
        guiController = new GUIController(gameContainer, stateBasedGame, mouse);
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;
        play_pause = new SwapRender(Renders.PLAY, Renders.PAUSED);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.clear();
        map.draw();
        buildingManager.render(graphics);
        creatureManager.render();

        guiController.render(graphics);

        if (paused){
            //Pause screen
        }

        /// Testing ///

        /// End Testing ///

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        mouse.update();
        guiController.update();
        if (!paused) {
            creatureManager.update(delta);
            buildingManager.update(delta);
            GameVars.update(delta);
        }
        /// Testing ///

        /// End Testing ///
    }

    @Override
    public void keyPressed(int key, char c) {
            super.keyPressed(key, c);

        switch (key) {
            case Input.KEY_ESCAPE:
                stateBasedGame.enterState(Core.MENU);
                break;
            case Input.KEY_SPACE:
                paused = !paused;
                play_pause.swap();
                break;
        }

        if (paused) {
            switch (key) {

            }
        }


    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        Messages.messages.clear();
        Messages.newMessage("Welcome to [INSERT NAME HERE]");
        Messages.newMessage("Press SPACE to un-pause, ESC to quit");
        Messages.newMessage("Make sure to get your creatures to mate (Heart Button)");
        Messages.newMessage("Before you assign them a job (Briefcase Button)");

        player = new Player();
        map = new Map(mouse, player);
        creatureManager = new CreatureManager(this);
        buildingManager = new BuildingManager(this);
        guiController.addState(new Play_gui(GUIStateIDs.PLAY_MAIN.ID, player, play_pause, this));
        guiController.enterState(GUIStateIDs.PLAY_MAIN.ID);

        paused = false;

        buildMating(new vec2(18*64, 8 *64));

        /// Testing ///
        creatureManager.createCreature("Joe", null, CreatureValues.Color.GREEN, CreatureValues.Job.NONE, true, new vec2(500, 300));
        creatureManager.createCreature("Sarah", null, CreatureValues.Color.CYAN, CreatureValues.Job.NONE, false, new vec2(720, 300));
        creatureManager.createCreature("Patrick", null, CreatureValues.Color.ORANGE, CreatureValues.Job.NONE, true, new vec2(650, 500));
        creatureManager.createCreature("Katarina", null, CreatureValues.Color.PURPLE, CreatureValues.Job.NONE, false, new vec2(450, 420));
        /// End Testing ///

    }

    @Override
    public void mouseClicked(int mouseButton, int x, int y, int clickCount) {
        super.mouseClicked(mouseButton, x, y, clickCount);
        guiController.mouseClicked(mouseButton, clickCount);
    }

    public GameContainer getGameContainer(){
        return gameContainer;
    }

    public Creature checkCreatureClick(vec2 position) {
        return creatureManager.creatureAt(position);
    }

    public void buildTech(vec2 pos) {
        buildingManager.addBuilding(new TechLab(pos));
        map.setUnbuildeable(pos);
    }

    public void buildDrill(vec2 pos) {
        buildingManager.addBuilding(new Drill(pos));
        map.setUnbuildeable(pos);
    }

    public void buildMating(vec2 pos){
        MatingBldg b = new MatingBldg(pos);
        mating_building = b;
        buildingManager.addBuilding(b);
        b.builtRender();
        map.setUnbuildeable(pos);
    }

    public void childrenpopup(Attributes[] attr, int[] colors, boolean[] isMale) {
        guiController.childrenpopup(attr, colors, isMale);
        paused = !paused;
        play_pause.swap();
    }
}

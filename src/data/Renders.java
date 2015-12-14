package data;

import core.rendering.CreatureAnimation;
import core.rendering.ItemTexture;
import core.rendering.StateRender;
import core.rendering.Texture;
import entities.creatures.Creature;
import entities.creatures.CreatureValues;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Francis O'Brien - 1/7/2015 - 2:02 AM
 *
 * This is a static class that will hold the single instance of the textures used throughout the application
 */

public final class Renders {

    private static StateRender creatureRender;

    private Renders() {} ///< This is a static class, a private constructor prevents any instance being created

    private static SpriteSheet cyan_walk = loadSpriteSheet("/res/creatures/sprite_sheets/cyan_walk_ss.png", 32, 52);
    private static SpriteSheet green_walk = loadSpriteSheet("/res/creatures/sprite_sheets/green_walk_ss.png", 32, 52);
    private static SpriteSheet orange_walk = loadSpriteSheet("/res/creatures/sprite_sheets/orange_walk_ss.png", 32, 52);
    private static SpriteSheet purple_walk = loadSpriteSheet("/res/creatures/sprite_sheets/purple_walk_ss.png", 32, 52);

    //Creature animations
    public static final CreatureAnimation CYAN_RIGHT_WALK = creatureWalkAnimation(cyan_walk, true);
    public static final CreatureAnimation CYAN_LEFT_WALK = creatureWalkAnimation(cyan_walk, false);

    public static final CreatureAnimation GREEN_RIGHT_WALK = creatureWalkAnimation(green_walk, true);
    public static final CreatureAnimation GREEN_LEFT_WALK = creatureWalkAnimation(green_walk, false);

    public static final CreatureAnimation ORANGE_RIGHT_WALK = creatureWalkAnimation(orange_walk, true);

    public static final CreatureAnimation ORANGE_LEFT_WALK = creatureWalkAnimation(orange_walk, false);

    public static final CreatureAnimation PURPLE_RIGHT_WALK = creatureWalkAnimation(purple_walk, true);
    public static final CreatureAnimation PURPLE_LEFT_WALK = creatureWalkAnimation(purple_walk, false);

    /// Creature item textures
    public static final Texture LAB_COAT_RIGHT = new Texture("/res/creatures/items/lab_coat_right.png");
    public static final Texture LAB_COAT_LEFT = new Texture("/res/creatures/items/lab_coat_left.png");

    public static final Texture HAT_RIGHT = new Texture("/res/creatures/items/hat_right.png");
    public static final Texture HAT_LEFT = new Texture("/res/creatures/items/hat_left.png");

    public static final Texture BOW_RIGHT = new Texture("/res/creatures/items/bow_right.png");
    public static final Texture BOW_LEFT = new Texture("/res/creatures/items/bow_left.png");

    /// Buildings
    public static final Texture BOXES = new Texture("/res/buildings/boxes.png");
    public static final Texture TECH_1 = new Texture("/res/buildings/lab_1.png");
    public static final Texture DRILL_1 = new Texture("/res/buildings/drill_1.png");
    public static final Texture MATING_BLDG = new Texture("/res/buildings/mating_bldg.png");


    /// MAP
    public static final Texture GREY_OUTLINE = new Texture("/res/ground/grey_outlined.png");

    public static Texture[] DARK_GREY_GROUND = new Texture[16];
    static {
        for (int i = 0; i < 16; i++){
            DARK_GREY_GROUND[i] = new Texture("res/ground/green_gen_" + i + ".png");
        }
    }

    public static Texture[] LIGHT_GREY_GROUND = new Texture[16];
    static {
        for (int i = 0; i < 16; i++){
            LIGHT_GREY_GROUND[i] = new Texture("res/ground/grey_l_gen_" + i + ".png");
        }
    }

    /// Main menu
    public static final Texture PLAY_BUTTON_TEXTURE = new Texture("/res/textures/gui/playbutton.png");

    public static final Texture SETTINGS_BUTTON_TEXTURE = new Texture("/res/textures/gui/settingsbutton.png");
    public static final Texture CONTROLS_BUTTON_TEXTURE = new Texture("/res/textures/gui/controlsbutton.png");
    public static final Texture EXIT_BUTTON_TEXTURE = new Texture("/res/textures/gui/exitbutton.png");
    public static final Texture BACK_BUTTON_TEXTURE = new Texture("/res/textures/gui/backbutton.png");

    /// Play GUI
    public static final Texture BUILD_IDLE = new Texture("/res/gui/play/main/build_idle.png");
    public static final Texture BUILD_MO = new Texture("/res/gui/play/main/build_mo.png");

    public static final Texture TECH_IDLE = new Texture("/res/gui/play/main/tech_idle.png");
    public static final Texture TECH_MO = new Texture("/res/gui/play/main/tech_mo.png");

    public static final Texture CREATURE_ICON = new Texture("/res/gui/play/main/creature_icon.png");
    public static final Texture CREDIT_ICON = new Texture("/res/gui/play/main/credit_icon.png");

    public static final Texture CREATURE_JOB_IDLE = new Texture("/res/gui/creature_info/job_idle.png");
    public static final Texture CREATURE_JOB_MO = new Texture("/res/gui/creature_info/job_mo.png");
    public static final Texture CREATURE_JOB_GREY = new Texture("/res/gui/creature_info/job_grey.png");

    public static final Texture CREATURE_MATE_IDLE = new Texture("/res/gui/creature_info/mate_idle.png");
    public static final Texture CREATURE_MATE_MO = new Texture("/res/gui/creature_info/mate_mo.png");
    public static final Texture CREATURE_MATE_GREY = new Texture("/res/gui/creature_info/mate_grey.png");

    public static final Texture TECH_BUY_IDLE = new Texture("/res/gui/build/tech_idle.png");
    public static final Texture TECH_BUY_MO = new Texture("/res/gui/build/tech_mo.png");

    public static final Texture DRILL_BUY_IDLE = new Texture("/res/gui/build/drill_idle.png");
    public static final Texture DRILL_BUY_MO = new Texture("/res/gui/build/drill_mo.png");

    public static final Texture PLAY = new Texture("/res/gui/play/main/play.png");
    public static final Texture PAUSED = new Texture("/res/gui/play/main/pause.png");

    public static final Texture KILL_IDLE = new Texture("/res/gui/children/skull_idle.png");
    public static final Texture KILL_MO = new Texture("/res/gui/children/skull_mo.png");
    public static final Texture KILL_DWN = new Texture("/res/gui/children/skull_dwn.png");

    public static final Texture GLASS_IDLE = new Texture("/res/gui/tech/glass_idle.png");
    public static final Texture GLASS_MO = new Texture("/res/gui/tech/glass_mo.png");
    public static final Texture GLASS_DWN = new Texture("/res/gui/tech/glass_dwn.png");

    public static final Texture CONFIRM_IDLE = new Texture("/res/gui/children/confirm_idle.png");
    public static final Texture CONFIRM_MO = new Texture("/res/gui/children/confirm_mo.png");

    public static final Texture PICK_IDLE = new Texture("/res/gui/job/miner_idle.png");
    public static final Texture PICK_MO = new Texture("/res/gui/job/miner_mo.png");

    /// Toggle textures
    public static final Texture TOGGLE_ON_TEXTURE = new Texture("/res/textures/gui/toggle_on.png");


    public static final Texture TOGGLE_OFF_TEXTURE = new Texture("/res/textures/gui/toggle_off.png");
    /// Tag textures
    public static final Texture VSYNC_TAG_TEXTURE = new Texture("/res/textures/gui/vsync_tag.png");

    public static final Texture FULLSCREEN_TAG_TEXTURE = new Texture("/res/textures/gui/fullscreen_tag.png");

    /// Background textures
    public static final Texture PLAY_MENU_BACKGROUND = new Texture("res/textures/gui/play_menu_back.png");

    private static SpriteSheet loadSpriteSheet(String filepath, int tw, int th) {
        try {
            return new SpriteSheet(filepath, tw, th);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CreatureAnimation creatureWalkAnimation(SpriteSheet sprite_sheet, boolean right) {
        CreatureAnimation a = new CreatureAnimation();

        if (right) {
            for (int i = 0; i < 5; i++) {
                a.addFrame(new Texture(sprite_sheet, i, 0), 100);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                a.addFrame(new Texture(sprite_sheet, i, 1), 100);
            }
        }
        return a;
    }

    static {
        ORANGE_RIGHT_WALK.addItem(LAB_COAT_RIGHT, 0, 22);
        ORANGE_RIGHT_WALK.addItem(BOW_RIGHT, 0, 0);
    }

    public static StateRender makeCreatureRender(int color, int job, boolean isMale) {
        StateRender render = new StateRender();

        ItemTexture stand_right = null;
        ItemTexture stand_left = null;
        CreatureAnimation walk_right = null;
        CreatureAnimation walk_left = null;


        if (color == CreatureValues.Color.CYAN){
            stand_right = new ItemTexture(CYAN_RIGHT_WALK.getTexture(0));
            stand_left = new ItemTexture(CYAN_LEFT_WALK.getTexture(0));
            walk_right = creatureWalkAnimation(cyan_walk, true);
            walk_left = creatureWalkAnimation(cyan_walk, false);

            render.addState(Creature.animation_state.STAND_RIGHT.getValue(), stand_right);
            render.addState(Creature.animation_state.STAND_LEFT.getValue(), stand_left);
            render.addState(Creature.animation_state.WALK_RIGHT.getValue(), walk_right);
            render.addState(Creature.animation_state.WALK_LEFT.getValue(), walk_left);

        } else if (color == CreatureValues.Color.GREEN){
            stand_right = new ItemTexture(GREEN_RIGHT_WALK.getTexture(0));
            stand_left = new ItemTexture(GREEN_LEFT_WALK.getTexture(0));
            walk_right = creatureWalkAnimation(green_walk, true);
            walk_left = creatureWalkAnimation(green_walk, false);

            render.addState(Creature.animation_state.STAND_RIGHT.getValue(), stand_right);
            render.addState(Creature.animation_state.STAND_LEFT.getValue(), stand_left);
            render.addState(Creature.animation_state.WALK_RIGHT.getValue(), walk_right);
            render.addState(Creature.animation_state.WALK_LEFT.getValue(), walk_left);

        } else if (color == CreatureValues.Color.ORANGE){
            stand_right = new ItemTexture(ORANGE_RIGHT_WALK.getTexture(0));
            stand_left = new ItemTexture(ORANGE_LEFT_WALK.getTexture(0));
            walk_right = creatureWalkAnimation(orange_walk, true);
            walk_left = creatureWalkAnimation(orange_walk, false);

            render.addState(Creature.animation_state.STAND_RIGHT.getValue(), stand_right);
            render.addState(Creature.animation_state.STAND_LEFT.getValue(), stand_left);
            render.addState(Creature.animation_state.WALK_RIGHT.getValue(), walk_right);
            render.addState(Creature.animation_state.WALK_LEFT.getValue(), walk_left);

        } else if (color == CreatureValues.Color.PURPLE){
            stand_right = new ItemTexture(PURPLE_RIGHT_WALK.getTexture(0));
            stand_left = new ItemTexture(PURPLE_LEFT_WALK.getTexture(0));
            walk_right = creatureWalkAnimation(purple_walk, true);
            walk_left = creatureWalkAnimation(purple_walk, false);

            render.addState(Creature.animation_state.STAND_RIGHT.getValue(), stand_right);
            render.addState(Creature.animation_state.STAND_LEFT.getValue(), stand_left);
            render.addState(Creature.animation_state.WALK_RIGHT.getValue(), walk_right);
            render.addState(Creature.animation_state.WALK_LEFT.getValue(), walk_left);

        }

        if (job == CreatureValues.Job.SCIENTIST){
            stand_right.addItem(LAB_COAT_RIGHT, 0, 22);
            stand_left.addItem(LAB_COAT_LEFT, 0, 22);
            walk_right.addItem(LAB_COAT_RIGHT, 0, 22);
            walk_left.addItem(LAB_COAT_LEFT, 0, 22);
        } else if (job == CreatureValues.Job.BUILDER){
            stand_right.addItem(HAT_RIGHT, 0, -2);
            stand_left.addItem(HAT_LEFT, -8, -2);
            walk_right.addItem(HAT_RIGHT, 0, -2);
            walk_left.addItem(HAT_LEFT, -8, -2);
        }

        if (!isMale){
            stand_right.addItem(BOW_RIGHT, 0, 0);
            stand_left.addItem(BOW_LEFT, 22, 0);
            walk_right.addItem(BOW_RIGHT, 0, 0);
            walk_left.addItem(BOW_LEFT, 22, 0);
        }

        return render;
    }
}
